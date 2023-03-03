package com.guesstheanimal.zoo.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import com.guesstheanimal.zoo.game.Music.mediaplayer_music
import kotlinx.android.synthetic.main.activity_how.*

class HowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how)

        button7.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("onStops", 1).apply()
            onBackPressed()
        }

    }

    override fun onResume() {
        super.onResume()
        val music = PreferenceManager.getDefaultSharedPreferences(this).getInt("music", 1)
        if(music == 1){
            mediaplayer_music!!.start()
            mediaplayer_music!!.isLooping = true
        }else{

        }

        Handler(Looper.myLooper()!!).postDelayed(
            {
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                    .putInt("onStops", 0).apply()
            }, 1000)
    }

    override fun onStop() {
        super.onStop()
        val onStops = PreferenceManager.getDefaultSharedPreferences(this).getInt("onStops", 0)
        if(onStops == 0){
            mediaplayer_music!!.pause()
        }
    }
}