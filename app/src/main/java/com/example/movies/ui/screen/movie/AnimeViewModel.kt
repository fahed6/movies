package com.example.movies.ui.screen.movie

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
class AnimeViewModel @Inject constructor(
    private val animeUseCase: AnimeUseCase
) : ViewModel() {

    private var _anime = MutableStateFlow<AnimeData?>(null)
    val anime = _anime.asStateFlow()

    fun fetchAnime(id: Int) {
        viewModelScope.launch {
            val animeDetails = animeUseCase.getAnimeDetails(id)
            _anime.update { animeDetails }
        }
    }
}
