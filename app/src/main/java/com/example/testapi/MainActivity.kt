package com.example.testapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var url: URL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        url = URL("https://dev-api.raddy.me/api/v1/app/onboarding")
        LoadingThread().start()
    }

    private inner class LoadingThread : Thread() {

        override fun run() {
            Log.e("testLog", "LoadingThread run(), url = $url")
            try {
                val connection = url.openConnection() as HttpsURLConnection
                connection.connectTimeout = 5000
                connection.readTimeout = 10000
                Log.e("testLog", "responseCode = ${connection.responseCode}")

                val inputStream: InputStream = BufferedInputStream(connection.inputStream)
                val text = inputStream.bufferedReader().use {
                    it.readText()
                }

                Log.e("testLog", "text = $text")
                inputStream.close()
            } catch (e: Exception) {
                Log.e("testLog", "error", e)
            }
        }
    }
}
