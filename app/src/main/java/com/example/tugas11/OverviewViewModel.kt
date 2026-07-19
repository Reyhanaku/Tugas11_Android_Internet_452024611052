package com.example.tugas11

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Representasi status permintaan jaringan web service.
 */
enum class MarsApiStatus { LOADING, ERROR, DONE }

/**
 * ViewModel yang bertanggung jawab mengambil data dari internet secara asinkron
 * menggunakan Kotlin Coroutines (viewModelScope) dan menyimpannya ke LiveData.
 */
class OverviewViewModel : ViewModel() {

    // Status request internal (MutableLiveData) dan eksternal (LiveData)
    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus> = _status

    // Data teks status atau info untuk ditampilkan di layar
    private val _statusText = MutableLiveData<String>()
    val statusText: LiveData<String> = _statusText

    // Data objek foto tunggal untuk dimuat gambarnya oleh Glide
    private val _photo = MutableLiveData<MarsPhoto?>()
    val photo: LiveData<MarsPhoto?> = _photo

    init {
        getMarsPhotos()
    }

    /**
     * Memanggil layanan Retrofit secara asinkron di dalam viewModelScope.
     */
    fun getMarsPhotos() {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                // Memanggil suspend function dari Retrofit di background thread
                val listResult = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
                
                if (listResult.isNotEmpty()) {
                    // Mengambil objek pertama dari list untuk ditampilkan gambarnya
                    _photo.value = listResult[0]
                    _statusText.value = "Berhasil memuat \ data foto dari server RESTful!"
                }
            } catch (e: Exception) {
                // Penanganan saat koneksi internet terputus atau gagal memuat data
                _status.value = MarsApiStatus.ERROR
                _statusText.value = "Gagal memuat data: Koneksi internet terputus atau error."
                _photo.value = null
            }
        }
    }
}
