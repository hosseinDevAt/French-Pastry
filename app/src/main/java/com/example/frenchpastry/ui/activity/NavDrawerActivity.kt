package com.example.frenchpastry.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.frenchpastry.AndroidWrapper.DeviceInfo
import com.example.frenchpastry.AndroidWrapper.HideStatus
import com.example.frenchpastry.R
import com.example.frenchpastry.data.local.pref.SecureSharedPref
import com.example.frenchpastry.data.local.pref.SharedPrefKeys
import com.example.frenchpastry.data.remote.ApiRepository.UserInfoApiRepository
import com.example.frenchpastry.data.remote.DataModel.UserInfoData
import com.example.frenchpastry.data.remote.ext.CallBackRequest
import com.example.frenchpastry.databinding.ActivityNavDrawerBinding
import com.example.frenchpastry.mvp.ext.ToastUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NavDrawerActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityNavDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNavDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)

        binding.txtProfilePage.setOnClickListener(this)
        binding.txtMyOrders.setOnClickListener(this)
        binding.txtSupport.setOnClickListener(this)
        binding.txtAboutUs.setOnClickListener(this)
        binding.txtConnectUs.setOnClickListener(this)
        binding.txtUpdate.setOnClickListener(this)
        binding.txtLogout.setOnClickListener(this)
        binding.icCloseNav.setOnClickListener(this)

        onBack()
        HideStatus().setFullScreen(window)
        getUserInfo()


    }

    private fun getUserInfo() {

        UserInfoApiRepository.instance.getUserInfo(
            DeviceInfo.getApi(this),
            DeviceInfo.getDeviceID(this),
            DeviceInfo.publicKey(this),
            object : CallBackRequest<UserInfoData>{
                override fun onSuccess(code: Int, data: UserInfoData) {
                    binding.txtName.text = data.user.fullname
                    binding.txtPhone.text = data.user.phone
                    Log.i("NAME_PHONE", "${data.user.fullname} | ${data.user.phone}")
                }

                override fun onNotSuccess(code: Int, error: String) {
                    ToastUtils.toast(this@NavDrawerActivity, error)
                }

                override fun onError(error: String) {
                    ToastUtils.setNetToast(this@NavDrawerActivity)
                }
            }
        )

    }

    private fun onBack() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    finish()
                    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
                }
            }
        )
    }


    override fun onClick(view: View) {

        when(view.id){

            R.id.txt_profile_page -> {}

            R.id.txt_my_orders -> {}

            R.id.txt_support -> {}

            R.id.txt_about_us -> {}

            R.id.txt_connect_us -> {}

            R.id.txt_update -> {
                ToastUtils.toast(this, "شما از آخرین نسخه استفاده میکنید")
            }

            R.id.txt_logout -> {

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("خروج از حساب کاربری")
                dialog.setMessage("آیا قصد خروج از حساب کاربری خود را دارید؟")
                dialog.setPositiveButton("تایید"){_,_ ->
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO){
                            val shared = SecureSharedPref.getSharedPref(this@NavDrawerActivity)
                            val edit = shared.edit().putBoolean(SharedPrefKeys.LOGIN_STATE_KEY, false)
                            edit.apply()

                            withContext(Dispatchers.Main){
                                startActivity(Intent(this@NavDrawerActivity, LoginActivity::class.java))
                            }
                        }
                    }
                }
                dialog.setNegativeButton("خیر!"){dialog,_ ->
                    dialog.dismiss()
                }
                dialog.create().show()
            }

            R.id.ic_close_nav -> {
                finish()
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
            }

        }

    }


}