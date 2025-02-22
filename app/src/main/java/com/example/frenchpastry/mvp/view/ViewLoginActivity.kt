package com.example.frenchpastry.mvp.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.frenchpastry.AndroidWrapper.ActivityUtils
import com.example.frenchpastry.AndroidWrapper.DeviceInfo
import com.example.frenchpastry.AndroidWrapper.NetworkAdapter
import com.example.frenchpastry.data.local.pref.SecureSharedPref
import com.example.frenchpastry.data.local.pref.SharedPrefKeys
import com.example.frenchpastry.data.remote.ApiRepository.LoginApiRepository
import com.example.frenchpastry.data.remote.DataModel.DefaultModel
import com.example.frenchpastry.data.remote.DataModel.RequestSendPhone
import com.example.frenchpastry.data.remote.DataModel.RequestVerifyCode
import com.example.frenchpastry.data.remote.ext.CallBackRequest
import com.example.frenchpastry.databinding.ActivityLoginBinding
import com.example.frenchpastry.databinding.CustomDialogLoginBinding
import com.example.frenchpastry.mvp.ext.ToastUtils
import com.example.frenchpastry.ui.activity.MainActivity

class ViewLoginActivity(context: Context) : FrameLayout(context), ActivityUtils {

    private val inflate = LayoutInflater.from(context)
    val binding = ActivityLoginBinding.inflate(inflate)

    private lateinit var number: String
    private lateinit var deviceInfo: DeviceInfo
    private var resendState = false

    fun setDeviceInfo(info: DeviceInfo) {
        deviceInfo = info
    }

    fun pressedSendCode(id: String, key: String) {

        binding.btnSendCode.getBtn().setOnClickListener {

            number = binding.phoneEditText.getText()

            if (numberValid(number)) {
                if (checkNetwork()) {
                    binding.btnSendCode.enableProgress()
                    sendCode(true, id, key)
                }
            }

        }

    }

    private fun numberValid(number: String): Boolean {

        if (number.isEmpty()) {
            binding.phoneEditText.setError("شماره تلفن خود را وارد کنید.")
            return false
        }
        if (number.length < 11) {
            binding.phoneEditText.setError("شماره را صحیح وارد نمایید.")
            return false
        }
        if (!number.matches(Regex("(\\+98|0)?9\\d{9}"))) {
            binding.phoneEditText.setError("شماره را صحیح وارد نمایید.")
            return false
        }

        binding.phoneEditText.nullError()

        return true


    }


    @SuppressLint("SetTextI18n")
    private fun createDialog(id: String, key: String) {

        val view = CustomDialogLoginBinding.inflate(inflate)
        view.textNumber.text = "کد تایید به شماره $number ارسال شد "
        view.txtResendCode.setTextColor(Color.parseColor("#D9888383"))
        view.txtResendCode.isClickable = false
        view.edtCode.textDirection = TEXT_DIRECTION_RTL

        createTimer(view, id, key)

        val dialog = Dialog(context)
        dialog.setContentView(view.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        view.btnConfirm.getBtn().setOnClickListener {

            val code = view.edtCode.text.toString()
            if (code.length < 4) {
                view.edtCode.error = "کد 4 رقمی را وارد کنید"
                return@setOnClickListener
            } else
                view.edtCode.error = null

            if (checkNetwork()) {

                view.btnConfirm.enableProgress()

                LoginApiRepository.instance.verifyCode(
                    code,
                    number,
                    object : CallBackRequest<RequestVerifyCode> {
                        override fun onSuccess(code: Int, data: RequestVerifyCode) {
                            view.btnConfirm.disableProgress()
                            dialog.dismiss()

                            val nameView = CustomDialogLoginBinding.inflate(inflate)
                            val nameDialog = Dialog(context)
                            nameDialog.setContentView(nameView.root)
                            createDialogName(nameView)
                            nameDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            nameDialog.setCancelable(false)
                            nameDialog.show()

                            val shared = SecureSharedPref.getSharedPref(context)
                            val edit = shared.edit().putString(SharedPrefKeys.API_KEY, data.api)
                            edit.apply()

                            nameView.btnConfirm.getBtn().setOnClickListener {

                                val name = nameView.edtCode.text.toString()

                                if (name.isEmpty() || name.length < 3)
                                    nameView.edtCode.error = "لطفا نام خود را وارد کنید"
                                else
                                    nameView.edtCode.error = null

                                if (checkNetwork()) {

                                    nameView.btnConfirm.enableProgress()

                                    LoginApiRepository.instance.editUser(
                                        DeviceInfo.getApi(context),
                                        DeviceInfo.getDeviceID(context),
                                        DeviceInfo.publicKey(context),
                                        name,
                                        object : CallBackRequest<DefaultModel> {
                                            override fun onSuccess(code: Int, data: DefaultModel) {
                                                nameView.btnConfirm.disableProgress()

                                                val loginState =
                                                    SecureSharedPref.getSharedPref(context)
                                                val editLogin = loginState.edit().putBoolean(
                                                    SharedPrefKeys.LOGIN_STATE_KEY,
                                                    true
                                                )
                                                editLogin.apply()

                                                context.startActivity(
                                                    Intent(
                                                        context,
                                                        MainActivity::class.java
                                                    )
                                                )
                                            }

                                            override fun onNotSuccess(code: Int, error: String) {
                                                nameView.btnConfirm.disableProgress()
                                                ToastUtils.toast(context, error)
                                            }

                                            override fun onError(error: String) {
                                                nameView.btnConfirm.disableProgress()
                                                ToastUtils.setNetToast(context)
                                            }
                                        }
                                    )

                                }

                            }


                        }

                        override fun onNotSuccess(code: Int, error: String) {
                            view.btnConfirm.disableProgress()
                            ToastUtils.toast(context, error)
                        }

                        override fun onError(error: String) {
                            view.btnConfirm.disableProgress()
                            ToastUtils.setNetToast(context)
                        }
                    }
                )

            }

        }

        view.txtEditNumber.setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun createDialogName(nameView: CustomDialogLoginBinding){
        nameView.txtResendCode.visibility = GONE
        nameView.txtTime.visibility = GONE
        nameView.txtEditNumber.visibility = GONE
        nameView.textNumber.visibility = GONE
        nameView.edtCode.inputType = InputType.TYPE_CLASS_TEXT
        nameView.txtTitle.text = "اطلاعات کاربری"
        nameView.edtCode.hint = "نام و نام خانوادگی"
        nameView.edtCode.gravity = Gravity.START
        nameView.edtCode.textDirection = TEXT_DIRECTION_RTL
        nameView.edtCode.filters = arrayOf(InputFilter.LengthFilter(40))
        nameView.btnConfirm.getBtn().text = "ثبت اطلاعات"
    }

    @SuppressLint("SetTextI18n")
    private fun createTimer(
        view: CustomDialogLoginBinding,
        id: String,
        key: String
    ) {

        object : CountDownTimer(181000, 1000) {
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                view.txtTime.text = String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                view.txtTime.text = "00:00"
                resendState = true
                view.txtResendCode.setTextColor(Color.parseColor("#101219"))
                view.txtResendCode.isClickable = true
                view.txtResendCode.setOnClickListener {
                    if (resendState) {
                        view.txtResendCode.setTextColor(Color.parseColor("#D9888383"))
                        view.txtResendCode.isClickable = false
                        resendState = false
                        sendCode(false, id, key)
                        createTimer(view, id, key)
                    }
                }
            }
        }.start()

    }


    private fun sendCode(dialog: Boolean, id: String, key: String) {

        LoginApiRepository.instance.sendPhoneAuth(
            id,
            key,
            number,
            object : CallBackRequest<RequestSendPhone> {
                override fun onSuccess(code: Int, data: RequestSendPhone) {
                    binding.btnSendCode.disableProgress()
                    if (dialog)
                        createDialog(id, key)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    binding.btnSendCode.disableProgress()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    binding.btnSendCode.disableProgress()
                    ToastUtils.setNetToast(context)
                }
            }
        )

    }


    private fun checkNetwork() = NetworkAdapter.isNetworkState(context, this)
}