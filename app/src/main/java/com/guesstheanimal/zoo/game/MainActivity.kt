package com.guesstheanimal.zoo.game

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.view.View
import com.guesstheanimal.zoo.game.Music.mediaplayer_music
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaplayer_music = MediaPlayer.create(this,R.raw.game_music)
        imageView3.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("music", 0).apply()
            mediaplayer_music!!.pause()
            imageView4.visibility = View.VISIBLE
            imageView3.visibility = View.GONE
        }

        imageView4.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("music", 1).apply()
            mediaplayer_music!!.start()
            mediaplayer_music!!.isLooping = true
            imageView4.visibility = View.GONE
            imageView3.visibility = View.VISIBLE
        }

        button5.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("onStops", 1).apply()
            startActivity(Intent(this,GamesActivity::class.java))
        }

        button4.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("onStops", 1).apply()
            startActivity(Intent(this,BestActivity::class.java))
        }

        button3.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("onStops", 1).apply()
            startActivity(Intent(this,HowActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val music = PreferenceManager.getDefaultSharedPreferences(this).getInt("music", 1)
        if(music == 1){
            mediaplayer_music!!.start()
            mediaplayer_music!!.isLooping = true
            imageView4.visibility = View.GONE
            imageView3.visibility = View.VISIBLE
        }else{
            imageView4.visibility = View.VISIBLE
            imageView3.visibility = View.GONE
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