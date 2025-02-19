package com.example.frenchpastry.AndroidWrapper

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.example.frenchpastry.data.local.pref.SecureSharedPref
import com.example.frenchpastry.data.local.pref.SharedPrefKeys
import java.security.MessageDigest

class DeviceInfo {

    companion object {

        private const val PRIVATE_KEY = "NKFewSfScCRrbxzULrSivWAXq2yvGd"
        private var deviceID: String? = null
        private var publicKey: String? = null
        private var publicKeyWithoutApiKey:String? = null

        @SuppressLint("HardwareIds")
        fun getDeviceID(context: Context): String {

            if (deviceID == null) {
                deviceID = Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
            }
            return deviceID ?: ""
        }

        fun getApi(context: Context): String {
            val shared = SecureSharedPref.getSharedPref(context)
            val apikey = shared.getString(SharedPrefKeys.API_KEY, "")

            return apikey ?: ""
        }


        fun publicKey(context: Context): String {

            val input = PRIVATE_KEY + getDeviceID(context) + getApi(context)

            if (publicKey == null){
                publicKey = hashSHA256(input)
            }

            return publicKey ?: ""

        }


        fun publicKeyWithoutApi(context: Context) : String {

            val input = PRIVATE_KEY + getDeviceID(context)


            if (publicKeyWithoutApiKey == null)
                publicKeyWithoutApiKey = hashSHA256(input)


            return publicKeyWithoutApiKey ?: ""
        }

        private fun hashSHA256(input: String) : String {
            val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
            return bytes.joinToString("") {"%02x".format(it)} // to hexadecimal
        }

    }




}