package com.guesstheanimal.zoo.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guesstheanimal.zoo.game.Music.mediaplayer_music
import kotlinx.android.synthetic.main.activity_best.*
import java.lang.NullPointerException
import java.util.ArrayList

class BestActivity : AppCompatActivity() {

    val adapter = BestRcAcapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best)

        try {
            fetchArrayList()
        }catch (e: NullPointerException){

        }

        button7.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("onStops", 1).apply()
            onBackPressed()
        }

        for (it in 0 until DataArraylist.DataAray.size) {
            val plant = BestData(DataArraylist.DataAray[it],(it + 1).toString())
            adapter.addPlant(plant)
            rc_best_list.layoutManager = LinearLayoutManager(this)
            rc_best_list.adapter = adapter
        }

    }

    private val gson = Gson()

    fun fetchArrayList(): ArrayList<String> {
        val json = applicationContext?.getSharedPreferences("sharedPrefs", MODE_PRIVATE)?.getString("data", "")
        DataArraylist.DataAray = when {
            json.isNullOrEmpty() -> ArrayList()
            else -> gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
        }
        Log.d("sadasd", DataArraylist.DataAray.toString())
        return DataArraylist.DataAray
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