package com.elvitalya.weatherapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.elvitalya.weatherapp.databinding.ActivityMainBinding
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.apply {
            checkWeather.setOnClickListener {
                    Log.d("responce", "${editText.text}")
                if (editText.text.isNullOrEmpty()) {
                    result.text = "Wrong enter city"
                } else {
                    val city = editText.text.toString()
                    val key = "95b07f01d986c7644f2812866d87885c"
                    val URL =
                        "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key&units=metric&lang=en"
                    doAsync {
                        val apiResponce = URL(URL).readText()
                        Log.d("responce", apiResponce)
                        val weather = JSONObject(apiResponce).getJSONArray("weather")
                        val desc = weather.getJSONObject(0).getString("description")
                        val main = JSONObject(apiResponce).getJSONObject("main")
                        val temp = main.getString("temp")
                        result.text = "Temperature is $temp\n$desc"
                    }
                }
            }
        }


    }
}