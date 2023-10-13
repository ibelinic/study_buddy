package com.example.study_buddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.example.myapp.Osoba
import com.example.myapp.osobe

class MainActivity : AppCompatActivity() {

    private lateinit var zaduzeneOsobe: List<Osoba>
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val editText = findViewById<EditText>(R.id.editText)
        val submitButton = findViewById<Button>(R.id.submitButton)
        backButton = findViewById<ImageView>(R.id.backButton)

        val personSpinner = findViewById<Spinner>(R.id.personSpinner)
        personSpinner.visibility = View.GONE

        backButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        })

        submitButton.setOnClickListener {
            personSpinner.visibility = View.VISIBLE
            val userInput = editText.text.toString().toLowerCase()
            val uneseneKljucneRijeci = userInput.split(" ").map { it.trim() }

            zaduzeneOsobe = osobe.filter { osoba ->
                val kljucneRijeci = osoba.odgovornost.split(", ").map { it.trim() }
                uneseneKljucneRijeci.all { unesenaKljucnaRijec ->
                    kljucneRijeci.any { kljucnaRijec ->
                        kljucnaRijec.equals(
                            unesenaKljucnaRijec,
                            ignoreCase = true
                        )
                    }
                }
            }

            if (zaduzeneOsobe.isNotEmpty()) {
                val rezultat = buildString {
                    append("Osobe odgovorne za rješavanje navedenih problema:\n\n")
                    for ((index, zaduzenaOsoba) in zaduzeneOsobe.withIndex()) {
                        append("${zaduzenaOsoba.akademskatitula} ${zaduzenaOsoba.ime} ${zaduzenaOsoba.prezime}, radno vrijeme:\n")
                        val radnoVrijemeMap = zaduzenaOsoba.radnoVrijeme
                        for ((dan, vrijeme) in radnoVrijemeMap) {
                            append("$dan: $vrijeme\n")
                        }
                        if (index < zaduzeneOsobe.size - 1) {
                            append("\n")
                        }
                    }
                }

                resultTextView.text = rezultat

                val personNames = mutableListOf("Odaberi") // Dodajte "Odaberi" kao prvu opciju
                personNames.addAll(zaduzeneOsobe.map { "${it.ime} ${it.prezime}" })
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, personNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                personSpinner.adapter = adapter

                personSpinner.setSelection(0)

                personSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (position > 0) {
                            val selectedPerson =
                                zaduzeneOsobe[position - 1] // Oduzmite 1 zbog "Odaberi" opcije

                            val intent = Intent(this@MainActivity, PersonDetailActivity::class.java)

                            intent.putExtra("ime", selectedPerson.ime)
                            intent.putExtra("prezime", selectedPerson.prezime)
                            intent.putExtra("akademskatitula", selectedPerson.akademskatitula)
                            intent.putExtra("email", selectedPerson.email)
                            intent.putExtra(
                                "radnoVrijeme",
                                selectedPerson.getRadnoVrijemeAsString()
                            )
                            intent.putExtra("photo", selectedPerson.photo)

                            startActivity(intent)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }

            }
        }

        val clearButton = findViewById<Button>(R.id.clearButton)
        clearButton.setOnClickListener {
            editText.text.clear()
            resultTextView.text = ""
            personSpinner.adapter = null
            personSpinner.visibility = View.GONE
        }
    }
}