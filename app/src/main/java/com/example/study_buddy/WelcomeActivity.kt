package com.example.study_buddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Inicijalizirajte TextView i postavite klik slušatelja
        val startStudyBuddyTextView = findViewById<TextView>(R.id.startStudyBuddyTextView)
        val powerButton = findViewById<ImageView>(R.id.powerButton)

        powerButton.setOnClickListener {
            finishAffinity()
        }

        // Startiranje pulsirajuće animacije iz XML-a
        startStudyBuddyTextView.startAnimation(
            AnimationUtils.loadAnimation(this, R.anim.pulse_animation)
        )

        // Postavljanje klika na TextView
        startStudyBuddyTextView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Metoda koja se poziva pritiskom na TextView
    fun startMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}