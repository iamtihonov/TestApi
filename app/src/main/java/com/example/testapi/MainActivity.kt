package com.example.testapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.*
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var url: URL

    //Оба работают
    private val url1 = "https://testtykhonov.000webhostapp.com/test.json"
    private val url2 = "https://dev-api.raddy.me/api/v1/app/onboarding"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("testLog", "MainActivity onCreate()")

        test1()
        //test2()
    }

    private fun test1() {
        Log.e("testLog", "MainActivity test1()")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-api.raddy.me/api/v1/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GetAppOnboardingService::class.java)
        service.screens.enqueue(object:Callback<AppOnboardingResponse> {
            override fun onFailure(call: Call<AppOnboardingResponse>, t: Throwable) {
                Log.e("testLog", "MainActivity onFailure()", t)
            }

            override fun onResponse(call: Call<AppOnboardingResponse>,
                                    response: Response<AppOnboardingResponse>) {
                Log.e("testLog", "MainActivity onResponse(), code = ${response.code()}")
                Log.e("testLog", "MainActivity onResponse(), message = ${response.body()?.toString()}")
            }

        })
    }

    private fun test2() {
        url = URL(url2)
        LoadingThread().start()
    }

    private inner class LoadingThread : Thread() {

        override fun run() {
            Log.e("testLog", "LoadingThread run(), url = $url")
            try {
                val connection = url.openConnection() as HttpsURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 5000
                connection.readTimeout = 10000
                connection.connect()
                Log.e("testLog", "responseCode = ${connection.responseCode}")

                val inputStream: InputStream = BufferedInputStream(connection.inputStream)
                val text = inputStream.bufferedReader().use {
                    it.readText()
                }

                Log.e("testLog", "response = \n $text")
                inputStream.close()
            } catch (e: Exception) {
                Log.e("testLog", "error", e)
            }
        }
    }
}
