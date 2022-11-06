package com.example.inapplocalechangesample

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_en).setOnClickListener {
            setLocale(this, "en")
            finish()
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_ja).setOnClickListener {
            setLocale(this, "ja")
            finish()
            startActivity(intent)
        }
    }

}

private fun setLocale(activity: Activity, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val resources: Resources = activity.resources
    val config: Configuration = resources.configuration
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
}
