package com.example.movies.ui.screen.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.model.AnimeData
import com.example.movies.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingAnimeViewModel @Inject constructor(
    private val animeUseCase: AnimeUseCase
) : ViewModel() {

    private var _animeData = MutableStateFlow<List<AnimeData>>(emptyList())
    val animeData = _animeData.asStateFlow()

    init {
        fetchTrendingAnime()
    }

    private fun fetchTrendingAnime() {
        viewModelScope.launch {
            val trendingAnime = animeUseCase.getTrendingAnime()
            _animeData.update { trendingAnime }
        }
    }
}
