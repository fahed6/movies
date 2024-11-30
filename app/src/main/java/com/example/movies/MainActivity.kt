@file:OptIn(ExperimentalSharedTransitionApi::class)


package com.example.movies
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movies.ui.screen.movie.AnimeScreen
import com.example.movies.ui.screen.moviesList.TrendingAnimeScreen
import com.example.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTheme {
                val navController = rememberNavController()

                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        android.graphics.Color.TRANSPARENT
                    )
                )

                SharedTransitionLayout {
                    NavHost(navController = navController, startDestination = TrendingAnimeRoute) {
                        composable<TrendingAnimeRoute> {
                            TrendingAnimeScreen(
                                animatedVisibilityScope = this,
                                onAnimeClick = { coverUrl, id ->
                                    navController.navigate(
                                        AnimeRoute(coverUrl = coverUrl, id = id)
                                    )
                                }
                            )
                        }

                        composable<AnimeRoute> {
                            val args = it.toRoute<AnimeRoute>()

                            AnimeScreen(
                                animatedVisibilityScope = this,
                                id = args.id.toInt(),
                                coverImage = args.coverUrl
                            )
                        }
                    }
                }

            }
        }
    }
}

@Serializable
data object TrendingAnimeRoute

@Serializable
data class AnimeRoute(val coverUrl: String, val id: String)