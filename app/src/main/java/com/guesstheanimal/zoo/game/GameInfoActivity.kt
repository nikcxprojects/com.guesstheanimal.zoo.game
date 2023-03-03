package com.guesstheanimal.zoo.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import com.guesstheanimal.zoo.game.Music.mediaplayer_music
import kotlinx.android.synthetic.main.activity_game_info.*

class GameInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)

        val pts = PreferenceManager.getDefaultSharedPreferences(this).getInt("pts", 0)
        textView11.text = "YOUR SCORE: \n$pts pts"

        button12.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("pts", 0).apply()
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("onStops", 1).apply()
            startActivity(Intent(this,GamesActivity::class.java))
            finish()
        }

        button13.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("pts", 0).apply()
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("onStops", 1).apply()
            startActivity(Intent(this,BestActivity::class.java))
            finish()
        }

        button14.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("pts", 0).apply()
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