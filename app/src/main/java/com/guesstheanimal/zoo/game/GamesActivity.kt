package com.guesstheanimal.zoo.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guesstheanimal.zoo.game.Music.mediaplayer_music
import kotlinx.android.synthetic.main.activity_games.*
import java.lang.NullPointerException
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class GamesActivity : AppCompatActivity() {

    val image_list = arrayListOf(R.drawable.lion,R.drawable.panda,R.drawable.koala,R.drawable.elephant,
        R.drawable.hen,R.drawable.turtle,R.drawable.cat,R.drawable.dog,R.drawable.cow,R.drawable.frog,
        R.drawable.rabbit,R.drawable.deer,R.drawable.horse,R.drawable.fox,R.drawable.whale,R.drawable.jellyfish,
        R.drawable.chameleon,R.drawable.squirrel,R.drawable.tiger,R.drawable.penguin)

    var name_list = arrayListOf("LION","PANDA","KOALA","ELEPHANT","HEN","TURTLE","CAT","DOG","COW","FROG",
        "RABBIT","DEER","HORSE","FOX","WHALE","JELLYFISH","CHAMELEON","SQUIRREL","TIGER","PENGUIN")//20

    var button_list = arrayListOf<Button>()

    var mian_index = 0

    var click_index = 0

    private var mCountDownTimer1: CountDownTimer? = null
    private var mTimerRunning = false
    private var mTimeLeftInMillis: Long = 0
    private var mEndTime: Long = 0

    lateinit var button1:Button
    lateinit var button2:Button
    lateinit var button3:Button
    lateinit var button4:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)
        button1 = findViewById(R.id.button8)
        button2 = findViewById(R.id.button9)
        button3 = findViewById(R.id.button10)
        button4 = findViewById(R.id.button11)

        try {
            fetchArrayList()
        }catch (e: NullPointerException){

        }

        val pts = PreferenceManager.getDefaultSharedPreferences(this).getInt("pts", 0)

        textView8.text = "$pts pts"
        button_list = arrayListOf(button1,button2,button3,button4)
        Log.d("sajdn",button_list.size.toString())


        GameStart()

        button_list[0].setOnClickListener {
            if(click_index == 0) {
                click_index = 1
                val index_text = button_list[0].text.toString()
                val index = name_list.indexOf(index_text)
                if (mian_index == index) {
                    button_list[0].setBackgroundResource(R.drawable.button_backgraund_ok)
                    val ptss = PreferenceManager.getDefaultSharedPreferences(this).getInt("pts", 0)
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putInt("pts", ptss+1).apply()
                    textView8.text = "${ptss+1} pts"
                } else {
                    button_list[0].setBackgroundResource(R.drawable.button_backgraund_no)
                }
                Handler(Looper.myLooper()!!).postDelayed(
                    {
                        GameStart()
                        button_list[0].setBackgroundResource(R.drawable.button_backgraund)
                        click_index = 0
                    }, 500
                )
            }
        }

        button_list[1].setOnClickListener {
            if(click_index == 0) {
                click_index = 1
                val index_text = button_list[1].text.toString()
                val index = name_list.indexOf(index_text)
                if (mian_index == index) {
                    button_list[1].setBackgroundResource(R.drawable.button_backgraund_ok)
                    val ptss = PreferenceManager.getDefaultSharedPreferences(this).getInt("pts", 0)
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putInt("pts", ptss+1).apply()
                    textView8.text = "${ptss+1} pts"
                } else {
                    button_list[1].setBackgroundResource(R.drawable.button_backgraund_no)
                }
                Handler(Looper.myLooper()!!).postDelayed(
                    {
                        GameStart()
                        button_list[1].setBackgroundResource(R.drawable.button_backgraund)
                        click_index = 0
                    }, 500
                )
            }
        }

        button_list[2].setOnClickListener {
            if(click_index == 0) {
                click_index = 1
                val index_text = button_list[2].text.toString()
                val index = name_list.indexOf(index_text)
                if (mian_index == index) {
                    button_list[2].setBackgroundResource(R.drawable.button_backgraund_ok)
                    val ptss = PreferenceManager.getDefaultSharedPreferences(this).getInt("pts", 0)
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putInt("pts", ptss+1).apply()
                    textView8.text = "${ptss+1} pts"
                } else {
                    button_list[2].setBackgroundResource(R.drawable.button_backgraund_no)
                }
                Handler(Looper.myLooper()!!).postDelayed(
                    {
                        GameStart()
                        button_list[2].setBackgroundResource(R.drawable.button_backgraund)
                        click_index = 0
                    }, 500
                )
            }
        }

        button_list[3].setOnClickListener {
            if(click_index == 0) {
                click_index = 1
                val index_text = button_list[3].text.toString()
                val index = name_list.indexOf(index_text)
                if (mian_index == index) {
                    button_list[3].setBackgroundResource(R.drawable.button_backgraund_ok)
                    val ptss = PreferenceManager.getDefaultSharedPreferences(this).getInt("pts", 0)
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putInt("pts", ptss+1).apply()
                    textView8.text = "${ptss+1} pts"
                } else {
                    button_list[3].setBackgroundResource(R.drawable.button_backgraund_no)
                }
                Handler(Looper.myLooper()!!).postDelayed(
                    {
                        GameStart()
                        button_list[3].setBackgroundResource(R.drawable.button_backgraund)
                        click_index = 0
                    }, 500
                )
            }
        }

        button6.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("onStops", 1).apply()
            onBackPressed()
        }

    }

    fun GameStart(){
        val random = Random.nextInt(0..19)
        mian_index = random
        val random_button1 = Random.nextInt(0..3)
        val random_button2 = Random.nextInt(0..2)
        val random_button3 = Random.nextInt(0..1)
        val random_button4 = 0
        val random_text1 = Random.nextInt(0..18)
        val random_text2 = Random.nextInt(0..17)
        val random_text3 = Random.nextInt(0..16)
        imageView2.setImageResource(image_list[random])
        button_list[random_button1].text = name_list[random]
        name_list.removeAt(random)
        button_list.removeAt(random_button1)
        button_list[random_button2].text = name_list[random_text1]
        name_list.removeAt(random_text1)
        button_list.removeAt(random_button2)
        button_list[random_button3].text = name_list[random_text2]
        name_list.removeAt(random_text2)
        button_list.removeAt(random_button3)
        button_list[random_button4].text = name_list[random_text3]
        name_list.removeAt(random_text3)
        button_list.removeAt(random_button4)

        name_list = arrayListOf("LION","PANDA","KOALA","ELEPHANT","HEN","TURTLE","CAT","DOG","COW","FROG",
            "RABBIT","DEER","HORSE","FOX","WHALE","JELLYFISH","CHAMELEON","SQUIRREL","TIGER","PENGUIN")

        button_list = arrayListOf(button1,button2,button3,button4)
        Log.d("sajdn",button_list.size.toString())
    }

    private val gson = Gson()

    fun saveObjectToArrayList(yourObject: String) {
        val bookmarks = DataArraylist.DataAray
        var idex = PreferenceManager.getDefaultSharedPreferences(this).getInt("idexs", 0)
        bookmarks?.add(idex, yourObject)
        val prefsEditor = this?.getSharedPreferences("sharedPrefs", MODE_PRIVATE)?.edit()
        val json = gson.toJson(bookmarks)
        prefsEditor?.putString("data", json)
        prefsEditor?.apply()
        idex++
        PreferenceManager.getDefaultSharedPreferences(this).edit()
            .putInt("idexs", idex).apply()
    }

    fun fetchArrayList(): ArrayList<String> {
        val json = applicationContext?.getSharedPreferences("sharedPrefs", MODE_PRIVATE)?.getString("data", "")
        DataArraylist.DataAray = when {
            json.isNullOrEmpty() -> ArrayList()
            else -> gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
        }
        Log.d("sadasd", DataArraylist.DataAray.toString())
        return DataArraylist.DataAray
    }

    private fun startTimer(): Boolean {
        mTimeLeftInMillis = START_TIME_IN_MILLIS
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis
        mCountDownTimer1 = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                mTimerRunning = false
            }

        }.start()
        mTimerRunning = true
        return true
    }

    private fun pauseTimer() {
        mCountDownTimer1!!.cancel()
        mTimerRunning = false
    }

    private fun resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS
//        updateCountDownText()
        pauseTimer()
    }

    private fun updateCountDownText() {
        val hours = ((mTimeLeftInMillis / (1000 * 60 * 60)) % 24)
        val minutes = ((mTimeLeftInMillis / (1000 * 60)) % 60)
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
        val timeLeftFormatted =
            String.format(Locale.getDefault(), "%2d:%02d", minutes, seconds)
        textView7!!.text = timeLeftFormatted

        if (seconds == 0) {
            resetTimer()
            val text = textView8!!.text.toString()
            saveObjectToArrayList(text)
            Toast.makeText(this, "TIME IS OUT", Toast.LENGTH_SHORT).show()
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("onStops", 1).apply()
            startActivity(Intent(this,GameInfoActivity::class.java))
            finish()
        }
    }

    companion object {
        private var START_TIME_IN_MILLIS: Long = 60000
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
        startTimer()
    }

    override fun onStop() {
        super.onStop()
        pauseTimer()
        val onStops = PreferenceManager.getDefaultSharedPreferences(this).getInt("onStops", 0)
        if(onStops == 0){
            mediaplayer_music!!.pause()
        }
    }
}