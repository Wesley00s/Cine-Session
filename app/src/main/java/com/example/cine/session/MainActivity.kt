package com.example.cine.session

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.cine.session.ui.screen.MainScreen
import com.example.cine.session.ui.screen.home.HomeViewModel
import com.example.cine.session.ui.screen.initial.InitialViewModel
import com.example.cine.session.ui.screen.login.LoginViewModel
import com.example.cine.session.ui.screen.movie.MovieViewModel
import com.example.cine.session.ui.screen.movie.all.AllMoviesViewModel
import com.example.cine.session.ui.screen.search.SearchViewModel
import com.example.cine.session.ui.screen.serie.SerieViewModel
import com.example.cine.session.ui.screen.serie.season.SeasonViewModel
import com.example.cine.session.ui.screen.signup.SignUpViewModel
import com.example.cine.session.ui.screen.tv_show.TVShowViewModel
import com.example.cine.session.ui.theme.CineSessionTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineSessionTheme {
                val initialScreenViewModel by viewModels<InitialViewModel>()
                val loginScreenViewModel by viewModels<LoginViewModel>()
                val signUpScreenViewModel by viewModels<SignUpViewModel>()
                val movieViewModel by viewModels<MovieViewModel>()
                val serieViewModel by viewModels<SerieViewModel>()
                val seasonViewModel by viewModels<SeasonViewModel>()
                val homeScreenViewModel by viewModels<HomeViewModel>()
                val searchViewModel by viewModels<SearchViewModel>()
                val tvShowViewModel by viewModels<TVShowViewModel>()
                val allMoviesViewModel by viewModels<AllMoviesViewModel>()

                MainScreen(
                    initialScreenViewModel = initialScreenViewModel,
                    loginScreenViewModel = loginScreenViewModel,
                    signUpScreenViewModel = signUpScreenViewModel,
                    homeScreenViewModel = homeScreenViewModel,
                    searchViewModel = searchViewModel,
                    movieViewModel = movieViewModel,
                    serieViewModel = serieViewModel,
                    seasonViewModel = seasonViewModel,
                    tvShowViewModel = tvShowViewModel,
                    allMoviesViewModel = allMoviesViewModel
                )
            }
        }
    }
}