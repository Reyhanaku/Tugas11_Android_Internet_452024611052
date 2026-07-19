package com.example.tugas11

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Custom Binding Adapter untuk memuat gambar dari URL internet menggunakan Glide.
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        // Mengonversi string URL menjadi URI dengan skema HTTPS
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(
                RequestOptions()
                    // Menampilkan indikator loading sederhana bawaan sistem jika gambar belum siap
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    // Menampilkan ikon error jika gambar gagal diunduh atau internet terputus
                    .error(android.R.drawable.ic_dialog_alert)
            )
            .into(imgView)
    }
}

/**
 * Binding Adapter untuk mengatur visibilitas ProgressBar berdasarkan status jaringan.
 */
@BindingAdapter("apiStatus")
fun bindStatus(progressBar: ProgressBar, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> progressBar.visibility = View.VISIBLE
        MarsApiStatus.DONE -> progressBar.visibility = View.GONE
        MarsApiStatus.ERROR -> progressBar.visibility = View.GONE
        else -> progressBar.visibility = View.GONE
    }
}
