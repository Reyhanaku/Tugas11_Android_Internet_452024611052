package com.example.tugas11

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data class yang mewakili respons JSON dari web service.
 * Anotasi @JsonClass menginstruksikan Moshi untuk membuat adapter otomatis saat kompilasi.
 */
@JsonClass(generateAdapter = true)
data class MarsPhoto(
    val id: String,
    
    // Menggunakan @Json untuk memetakan nama key di JSON ("img_src") ke variabel Kotlin ("imgSrcUrl")
    @Json(name = "img_src") val imgSrcUrl: String
)
