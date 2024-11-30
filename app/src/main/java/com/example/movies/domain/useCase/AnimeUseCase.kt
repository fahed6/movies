package com.example.movies.domain.usecase

import com.example.movies.data.repository.KitsuRepository
import com.example.movies.domain.model.AnimeData
import javax.inject.Inject

class AnimeUseCase @Inject constructor(
    private val repository: KitsuRepository
) {

    suspend fun getAnimeDetails(id: Int): AnimeData? {
        return repository.getAnime(id)
    }

    suspend fun getTrendingAnime(): List<AnimeData> {
        return repository.getTrendingAnime()
    }
}
