@file:Suppress("unused")

package com.example.testapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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


        url = URL(url2)

        Log.e("testLog", "MainActivity onCreate()")
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
