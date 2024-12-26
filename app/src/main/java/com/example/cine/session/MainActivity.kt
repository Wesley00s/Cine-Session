package com.example.cine.session

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.ui.route.Home
import com.example.cine.session.ui.route.Movie
import com.example.cine.session.ui.screen.home.HomeScreen
import com.example.cine.session.ui.screen.home.HomeViewModel
import com.example.cine.session.ui.screen.movie.MovieScreen
import com.example.cine.session.ui.screen.movie.MovieViewModel
import com.example.cine.session.ui.theme.CineSessionTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineSessionTheme {
                val navController = rememberNavController()
                val homeScreenViewModel by viewModels<HomeViewModel>()
                val homeUiState by homeScreenViewModel.uiState.collectAsStateWithLifecycle()
                val movieViewModel by viewModels<MovieViewModel>()
                val movieUiState by movieViewModel.uiState.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = Home) {
                        composable<Home> {
                            HomeScreen(
                                modifier = Modifier
                                    .padding(innerPadding),
                                onNavigateToMovie = {
                                    val movieId = it.id
                                    navController.navigate("$Movie/$movieId")

                                },
                                uiState = homeUiState,
                                onEvent = homeScreenViewModel::onEvent
                            )
                        }
                        composable("$Movie/{movieId}") {
                            val movieId = it.arguments?.getString("movieId")?.toIntOrNull()
                            movieId?.let { id ->
                                MovieScreen(
                                    modifier = Modifier,
                                    movieId = id,
                                    uiState = movieUiState,
                                    onEvent = movieViewModel::onEvent,
                                    onBackClick = { navController.popBackStack() }

                                )
                            }
                        }


                    }
                }
            }
        }
    }
}


//@Composable
//fun TestMovieScreen(
//    modifier: Modifier = Modifier,
//) {
//    var movie by remember { mutableStateOf(MovieInfo()) }
//    var similarMovies by remember { mutableStateOf<List<InfoCard>?>(null) }
//
//    LaunchedEffect(Unit) {
//        val ktorMovieRemoteDatasource = KtorMovieRemoteDatasource
//        try {
//            val result  = ktorMovieRemoteDatasource.getDetailsMoviesDataResponse(1000075).getOrNull()
//            val similarResult = ktorMovieRemoteDatasource.getSimilarMovies(1000075, 1).getOrNull()?.results
//
//            movie = result ?: MovieInfo()
//            similarMovies = similarResult.orEmpty().map {
//                InfoCard(
//                    id = it.id,
//                    path = it.posterPath.toString()
//                )
//            }
//
//            Log.d("SimilarMovies", "Response:" + similarMovies.toString())
//        } catch (e: Exception) {
//            Log.e("Error", "Failed to fetch movies: ${e.message}")
//        }
//    }
//
//    MovieScreen(uiState = MovieUiState(movie = movie, similarMovies = similarMovies), onEvent = {})
//}


//@Composable
//fun TestHorizontalList(modifier: Modifier = Modifier) {
//
//    var moviesInfoCard by remember { mutableStateOf<List<InfoCard?>?>(null) }
//
//    LaunchedEffect(Unit) {
//        val ktorMovieRemoteDatasource = KtorMovieRemoteDatasource
//        try {
//            val results = ktorMovieRemoteDatasource.getSimilarMovies(845781, 1).getOrNull()?.results
//            moviesInfoCard = results?.map {
//                it.posterPath?.let { it1 ->
//                    InfoCard(
//                        id = it.id,
//                        path = it1
//                    )
//                }
//            }
//            Log.d("SimilarMovies", "Response:" + moviesInfoCard.toString())
//        } catch (e: Exception) {
//            Log.e("Error", "Failed to fetch movies: ${e.message}")
//        }
//    }
//    HorizontalList(infos = moviesInfoCard.orEmpty(), text = "Similar Movies", onMovieClick = {})
//
//}
//
//
//@Composable
//fun Scrollable(modifier: Modifier = Modifier) {
//
//    val scrollState = rememberLazyListState()
//
//    var responseMovieDetails by remember { mutableStateOf("Carregando...") }
////    var responseSerieDetails by remember { mutableStateOf("Carregando...") }
////    var responseMoviePopular by remember { mutableStateOf("Carregando...") }
////    var responseSeriePopular by remember { mutableStateOf("Carregando...") }
//    var responseTopRatedMovie by remember { mutableStateOf("Carregando...") }
////    var responseTopRatedSerie by remember { mutableStateOf("Carregando...") }
////    var responseSeasonDetails by remember { mutableStateOf("Carregando...") }
////    var responseEpisodeDetails by remember { mutableStateOf("Carregando...") }
////    var responseFavoriteMovies by remember { mutableStateOf("Carregando...") }
////    var responseFavoriteSeries by remember { mutableStateOf("Carregando...") }
////    var responsePostFavoriteMovie by remember { mutableStateOf("Carregando...") }
////    var responsePostFavoriteSerie by remember { mutableStateOf("Carregando...") }
////    var responsePostWatchlistMovie by remember { mutableStateOf("Carregando...") }
////    var searchMovies by remember { mutableStateOf("Carregando...") }
//
//
//    LaunchedEffect(Unit) {
//        val ktorMovieRemoteDatasource = KtorMovieRemoteDatasource
//        val ktorSerieRemoteDatasource = KtorSerieRemoteDatasource
//        try {
//            responseMovieDetails = ktorMovieRemoteDatasource.getDetailsMoviesDataResponse(278).toString()
////            responseSerieDetails =
////                ktorRemoteDatasource.getDetailsSeriesDataResponse(1399).toString()
////            responseMoviePopular = ktorRemoteDatasource.getPopularMoviesDataResponse(1).toString()
////            responseSeriePopular = ktorRemoteDatasource.getPopularSeriesDataResponse(1).toString()
//            responseTopRatedMovie = ktorMovieRemoteDatasource.getTopRatedMovies(1).toString()
////            responseTopRatedSerie = ktorRemoteDatasource.getTopRatedSeriesDataResponse(1).toString()
////            responseSeasonDetails =
////                ktorRemoteDatasource.getDetailsSeasonDataResponse(1399, 1).toString()
////            responseEpisodeDetails =
////                ktorRemoteDatasource.getSeasonEpisodesDataResponse(1399, 1, 1).toString()
////            responseFavoriteMovies = ktorRemoteDatasource
////                .getFavoritesMoviesDataResponse(1, "7db984741d1d3e7d21ef85e902cc935cc6a71887")
////                .toString()
////            responseFavoriteSeries = ktorRemoteDatasource
////                .getFavoriteSeriesDataResponse(1, "7db984741d1d3e7d21ef85e902cc935cc6a71887")
////                .toString()
//
////            responsePostFavoriteMovie = ktorMovieRemoteDatasource
////                .postFavoriteMovie(
////                    "7db984741d1d3e7d21ef85e902cc935cc6a71887",
////                    FavoriteRequest(
////                        mediaType = "movie",
////                        mediaId = 278,
////                        favorite = false
////                    )
////                ).toString()
////
////            responsePostFavoriteSerie = ktorSerieRemoteDatasource
////                .postFavoriteMovie(
////                    "7db984741d1d3e7d21ef85e902cc935cc6a71887"
////                    , FavoriteRequest(
////                        mediaType = "tv",
////                        mediaId = 1399,
////                        favorite = true
////                    )
////                ).toString()
////
////            responsePostWatchlistMovie = ktorMovieRemoteDatasource
////                .postWatchlistMovie(
////                    "7db984741d1d3e7d21ef85e902cc935cc6a71887",
////                    FavoriteRequest(
////                        mediaType = "movie",
////                        mediaId = 278,
////                        favorite = true
////                    )
////                ).toString()
//
////            searchMovies = ktorMovieRemoteDatasource
////                .searchMovies("spiderman", 1).toString()
//
//            Log.d("MovieData", "Response: $responseMovieDetails")
////            Log.d("SerieData", "Response: $responseSerieDetails")
////            Log.d("MoviePopular", "Response: $responseMoviePopular")
////            Log.d("SeriePopular", "Response: $responseSeriePopular")
//            Log.d("TopRatedMovie", "Response: $responseTopRatedMovie")
////            Log.d("TopRatedSerie", "Response: $responseTopRatedSerie")
////            Log.d("SeasonData", "Response: $responseSeasonDetails")
////            Log.d("EpisodeData", "Response: $responseEpisodeDetails")
////            Log.d("FavoriteMovies", "Response: $responseFavoriteMovies")
////            Log.d("FavoriteSeries", "Response: $responseFavoriteSeries")
////            Log.d("PostFavoriteMovie", "Response: $responsePostFavoriteMovie")
////            Log.d("PostFavoriteSerie", "Response: $responsePostFavoriteSerie")
////            Log.d("PostWatchlistMovie", "Response: $responsePostWatchlistMovie")
////            Log.d("SearchMovies", "Response: $searchMovies")
//
//        } catch (e: Exception) {
////            responseMovieDetails = "Erro ao buscar os dados"
////            Log.e("MovieData", "Erro ao buscar os dados", e)
////            responseSerieDetails = "Erro ao buscar os dados"
////            Log.e("SerieData", "Erro ao buscar os dados", e)
////            responseMoviePopular = "Erro ao buscar os dados"
////            Log.e("MoviePopular", "Erro ao buscar os dados", e)
////            responseSeriePopular = "Erro ao buscar os dados"
////            Log.e("SeriePopular", "Erro ao buscar os dados", e)
////            responseTopRatedMovie = "Erro ao buscar os dados"
////            Log.e("TopRatedMovie", "Erro ao buscar os dados", e)
////            responseTopRatedSerie = "Erro ao buscar os dados"
////            Log.e("TopRatedSerie", "Erro ao buscar os dados", e)
////            responseSeasonDetails = "Erro ao buscar os dados"
////            Log.e("SeasonData", "Erro ao buscar os dados", e)
////            responseEpisodeDetails = "Erro ao buscar os dados"
////            Log.e("EpisodeData", "Erro ao buscar os dados", e)
////            responseFavoriteMovies = "Erro ao buscar os dados"
////            Log.e("FavoriteMovies", "Erro ao buscar os dados", e)
////            responseFavoriteSeries = "Erro ao buscar os dados"
////            Log.e("FavoriteSeries", "Erro ao buscar os dados", e)
////            responsePostFavoriteMovie = "Erro ao buscar os dados"
////            Log.e("PostFavoriteMovie", "Erro ao buscar os dados", e)
////            responsePostFavoriteSerie = "Erro ao buscar os dados"
////            Log.e("PostFavoriteSerie", "Erro ao buscar os dados", e)
////            responsePostWatchlistMovie = "Erro ao buscar os dados"
////            Log.e("PostWatchlistMovie", "Erro ao buscar os dados", e)
////            searchMovies = "Erro ao buscar os dados"
////            Log.e("SearchMovies", "Erro ao buscar os dados", e)
//        }
//    }
//
//    LazyColumn(
//        modifier = modifier
//            .fillMaxSize(),
//        state = scrollState
//    ) {
//        item {
//            Text(text = responseMovieDetails)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responseSerieDetails)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responseMoviePopular)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responseSeriePopular)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responseTopRatedMovie)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responseTopRatedSerie)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responseSeasonDetails)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responseEpisodeDetails)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responseFavoriteMovies)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responseFavoriteSeries)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responsePostFavoriteMovie)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responsePostFavoriteSerie)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = responsePostWatchlistMovie)
////            Spacer(modifier = Modifier.height(100.dp))
////            Text(text = searchMovies)
//        }
//    }
//}