package com.example.movies.data.repository

import com.example.movies.domain.model.AnimeData

interface KitsuRepository {

    suspend fun getTrendingAnime(): List<AnimeData>

    suspend fun getAnime(id: Int): AnimeData?
}