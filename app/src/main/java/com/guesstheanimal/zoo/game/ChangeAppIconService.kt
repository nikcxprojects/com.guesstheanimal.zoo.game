package com.guesstheanimal.zoo.game

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.telephony.TelephonyManager
import android.util.Log
import java.util.*

class ChangeAppIconService: Service() {
    private val aliases = arrayOf(".one", ".two")

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onTaskRemoved(rootIntent: Intent?) {
        Log.d("sakdnasd","rootIntent" + rootIntent)
        changeAppIcon()
        stopSelf()
    }

    fun changeAppIcon() {
        val sp = getSharedPreferences("appSettings", Context.MODE_PRIVATE)
        if (Country(this)!!.contains("az") || Country(this)!!.contains("am") || Country(this)!!.contains("by")
            || Country(this)!!.contains("kz") || Country(this)!!.contains("kg") || Country(this)!!.contains("md")
            || Country(this)!!.contains("ru") || Country(this)!!.contains("tj") || Country(this)!!.contains("uz")
            || Country(this)!!.contains("ua")){
            sp.getString("activeActivityAlias", ".one").let { aliasName ->
                Log.d("sakdnasd",".two" + "ialiasName" + aliasName)
                Log.d("sakdnasd",".two" + "isAliasEnabled" + isAliasEnabled(aliasName!!))
                Log.d("sakdnasd",".two" + "setAliasEnabled" + setAliasEnabled(aliasName))
                if (!isAliasEnabled(aliasName!!)) {
                    setAliasEnabled(aliasName)
                }
            }
        }else{
            sp.getString("activeActivityAlias", ".two").let { aliasName ->
                Log.d("sakdnasd",".two" + "ialiasName" + aliasName)
                Log.d("sakdnasd",".two" + "isAliasEnabled" + isAliasEnabled(aliasName!!))
                Log.d("sakdnasd",".two" + "setAliasEnabled" + setAliasEnabled(aliasName))
                if (!isAliasEnabled(aliasName!!)) {
                    setAliasEnabled(aliasName)
                }
            }
        }

    }

    private fun isAliasEnabled(aliasName: String): Boolean {
        return packageManager.getComponentEnabledSetting(
            ComponentName(
                this,
                "${BuildConfig.APPLICATION_ID}$aliasName"
            )
        ) == PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    }

    private fun setAliasEnabled(aliasName: String) {
        aliases.forEach {
            val action = if (it == aliasName)
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            else
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED

            packageManager.setComponentEnabledSetting(
                ComponentName(
                    this,
                    "${BuildConfig.APPLICATION_ID}$aliasName"
                ),
                action,
                PackageManager.DONT_KILL_APP
            )
        }
    }


    fun Country(context: Context): String? {
        try {
            val tm: TelephonyManager = context.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            val simCountry = tm.getSimCountryIso()
            if (simCountry != null && simCountry.length == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            }
            else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                val networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        }
        catch (e:Exception) {

        }
        return null;
    }
}