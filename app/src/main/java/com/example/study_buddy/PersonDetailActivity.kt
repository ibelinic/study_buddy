package com.example.study_buddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class PersonDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)

        val backButton = findViewById<ImageView>(R.id.backtopreviousButton)

        // Dodajte ovaj kod unutar onCreate metode PersonDetailActivity
        val intent = intent
        if (intent != null) {
            val ime = intent.getStringExtra("ime")
            val prezime = intent.getStringExtra("prezime")
            val akademskatitula = intent.getStringExtra("akademskatitula")
            val radnoVrijeme = intent.getStringExtra("radnoVrijeme")
            val email = intent.getStringExtra("email")
            val photoResourceId = intent.getIntExtra("photo", R.drawable.default_photo) // Default photo resource

            // Postavite ove podatke na odgovarajuće TextView-ove u layoutu
            val imeTextView = findViewById<TextView>(R.id.imeTextView)
            val prezimeTextView = findViewById<TextView>(R.id.prezimeTextView)
            val akademskatitulaTextView = findViewById<TextView>(R.id.akademskatitulaTextView)
            val radnoVrijemeTextView = findViewById<TextView>(R.id.radnoVrijemeTextView)
            val emailTextView = findViewById<TextView>(R.id.emailTextView)
            val photoImageView = findViewById<ImageView>(R.id.photoImageView)

            imeTextView.text = ime
            prezimeTextView.text = prezime
            akademskatitulaTextView.text = akademskatitula
            radnoVrijemeTextView.text = radnoVrijeme
            emailTextView.text = email
            photoImageView.setImageResource(photoResourceId)
        }

        backButton.setOnClickListener(View.OnClickListener {
            // Handle the click event here, for example, to navigate back onBackPressed()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

    }
}