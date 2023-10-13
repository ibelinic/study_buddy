package com.example.myapp

data class Osoba(
    val photo: Int,
    val akademskatitula : String,
    val ime: String,
    val prezime: String,
    val odgovornost: String,
    val email: String,
    val radnoVrijeme: Map<String, String>) {
    // Dodajte ovu funkciju za pretvorbu radnog vremena u String
    fun getRadnoVrijemeAsString(): String {
        val sb = StringBuilder()
        for ((dan, vrijeme) in radnoVrijeme) {
            sb.append("$dan: $vrijeme\n")
        }
        return sb.toString()
    }
}
