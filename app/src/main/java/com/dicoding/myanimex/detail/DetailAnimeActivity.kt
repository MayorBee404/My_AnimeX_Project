package com.dicoding.myanimex.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.dicoding.core.domain.model.Anime
import com.dicoding.myanimex.R
import com.dicoding.myanimex.databinding.ActivityDetailAnimeBinding


import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailAnimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAnimeBinding
    private lateinit var mAnime: Anime
    private var statusFavorite: Boolean = false

    private val detailViewModel: DetailAnimeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_anime)

        mAnime = DetailAnimeActivityArgs.fromBundle(intent.extras!!).anime
        statusFavorite = mAnime.isFavorite
        setStatusFavorite(statusFavorite)

        with(binding) {
            activity = this@DetailAnimeActivity
            lifecycleOwner = this@DetailAnimeActivity
            anime = mAnime
            fab.setOnClickListener { fabListener() }
        }
    }

    fun playTrailer(id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$id"))

        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }
    }

    private fun fabListener() {
        statusFavorite = !statusFavorite
        detailViewModel.setFavoriteAnime(mAnime, statusFavorite)
        setStatusFavorite(statusFavorite)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

}