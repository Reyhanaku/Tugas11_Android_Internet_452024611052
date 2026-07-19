package com.example.tugas11

import retrofit2.http.GET

/**
 * Interface untuk mendefinisikan endpoint RESTful Web Service menggunakan Retrofit.
 */
interface ApiService {
    /**
     * Mengambil daftar foto dari server.
     * Fungsi bersifat 'suspend' agar dapat dipanggil di dalam coroutine tanpa memblokir thread UI.
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}
