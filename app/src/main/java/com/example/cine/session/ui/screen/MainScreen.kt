package com.example.cine.session.ui.screen

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cine.session.R
import com.example.cine.session.data.remote.response.movie.ListMoviesResponse
import com.example.cine.session.data.remote.response.series.ListSeriesResponse
import com.example.cine.session.ui.component.NavItem
import com.example.cine.session.ui.screen.home.HomeScreen
import com.example.cine.session.ui.screen.home.HomeViewModel
import com.example.cine.session.ui.screen.initial.InitialScreen
import com.example.cine.session.ui.screen.initial.InitialViewModel
import com.example.cine.session.ui.screen.login.LoginScreen
import com.example.cine.session.ui.screen.login.LoginViewModel
import com.example.cine.session.ui.screen.movie.AllMoviesScreen
import com.example.cine.session.ui.screen.movie.MovieScreen
import com.example.cine.session.ui.screen.movie.MovieViewModel
import com.example.cine.session.ui.screen.search.SearchScreen
import com.example.cine.session.ui.screen.search.SearchViewModel
import com.example.cine.session.ui.screen.serie.SerieScreen
import com.example.cine.session.ui.screen.serie.SerieViewModel
import com.example.cine.session.ui.screen.serie.season.SeasonViewModel
import com.example.cine.session.ui.screen.signup.SignUpScreen
import com.example.cine.session.ui.screen.signup.SignUpViewModel
import com.example.cine.session.ui.screen.splash.SplashScreen
import com.example.cine.session.ui.screen.tv_show.AllTVShowsScreen
import com.example.cine.session.ui.screen.tv_show.TVShowScreen
import com.example.cine.session.ui.screen.tv_show.TVShowViewModel
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Quaternary
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    initialScreenViewModel: InitialViewModel,
    loginScreenViewModel: LoginViewModel,
    signUpScreenViewModel: SignUpViewModel,
    homeScreenViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
    movieViewModel: MovieViewModel,
    serieViewModel: SerieViewModel,
    seasonViewModel: SeasonViewModel,
    tvShowViewModel: TVShowViewModel

) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val navItems = listOf(
        NavItem(
            label = "Home",
            image = R.drawable.ic_home,
            imageSelected = R.drawable.ic_home_active,
            route = "Home"
        ),
        NavItem(
            label = "Search",
            image = R.drawable.ic_search,
            imageSelected = R.drawable.ic_search_active,
            route = "Search"
        ),
        NavItem(
            label = "TV Shows",
            image = R.drawable.ic_tv,
            imageSelected = R.drawable.ic_tv_active,
            route = "TV"
        ),
        NavItem(
            label = "Profile",
            image = R.drawable.ic_account_circle,
            imageSelected = R.drawable.ic_account_circle_active,
            route = "Login"
        )
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Primary,
        bottomBar = {
            val noBottomBarRoutes =
                listOf(
                    "Splash",
                    "Initial",
                    "Login",
                    "SignUp",
                    "Movie/{movieId}",
                    "Serie/{serieId}",
                    "AllMovies/{listMoviesResponse}",
                    "AllSeries/{listSeriesResponse}",
                )

            if (currentRoute !in noBottomBarRoutes) {
                NavigationBar(
                    modifier = Modifier.height(65.dp),
                ) {
                    navItems.forEachIndexed { _, item ->
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                selectedIconColor = Color.Transparent,
                            ),
                            selected = currentRoute == item.route,
                            onClick = {
                                val route = item.route
                                if (currentRoute != route) {
                                    navController.navigate(route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Image(
                                    modifier = Modifier.size(18.dp),
                                    painter = painterResource(
                                        id = if (currentRoute == item.route) item.imageSelected else item.image
                                    ),
                                    contentDescription = "Icon"
                                )
                            },
                            label = {
                                if (currentRoute == item.route) {
                                    Text(text = item.label, color = Quaternary, fontSize = 12.sp)
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "Splash"
        ) {
            composable("Splash") {
                SplashScreen(
                    onNavigateToScreen = {
                        navController.popBackStack()
                        navController.navigate("Initial")
                    }
                )
            }

            composable("Initial") {
                val initialUiState by initialScreenViewModel.uiState.collectAsStateWithLifecycle()
                InitialScreen(
                    modifier = Modifier,
                    uiState = initialUiState,
                    onEvent = initialScreenViewModel::onEvent,
                    onNavigateToLogin = { navController.navigate("Login") },
                    onLoginGoogle = {
                    },
                    onNavigateToSignUp = { navController.navigate("SignUp") }
                )
            }

            composable("Login") {
                val loginUiState by loginScreenViewModel.uiState.collectAsStateWithLifecycle()
                LoginScreen(
                    modifier = Modifier,
                    uiState = loginUiState,
                    onEvent = loginScreenViewModel::onEvent,
                    onNavigateToHome = { navController.navigate("Home") },
                    onNavigateToSignUp = { navController.navigate("SignUp") },
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable("SignUp") {
                val signUpUiState by signUpScreenViewModel.uiState.collectAsStateWithLifecycle()
                SignUpScreen(
                    modifier = Modifier,
                    uiState = signUpUiState,
                    onEvent = signUpScreenViewModel::onEvent,
                    onNavigateToHome = { navController.navigate("Home") },
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable("Home") {
                val homeUiState by homeScreenViewModel.uiState.collectAsStateWithLifecycle()
                HomeScreen(
                    modifier = Modifier,
                    onNavigateToMovie = {
                        val movieId = it.id
                        navController.navigate("Movie/$movieId")
                    },
                    uiState = homeUiState,
                    onEvent = homeScreenViewModel::onEvent,
                    onViewAllMovies = {
                        val json = Json.encodeToString(it)
                        val encodedJson = Uri.encode(json)
                        navController.navigate("AllMovies/$encodedJson")
                    }
                )
            }

            composable("Movie/{movieId}") { it ->
                val movieUiState by movieViewModel.uiState.collectAsStateWithLifecycle()
                val movieId = it.arguments?.getString("movieId")?.toIntOrNull()
                movieId?.let { id ->
                    MovieScreen(
                        modifier = Modifier,
                        movieId = id,
                        uiState = movieUiState,
                        onEvent = movieViewModel::onEvent,
                        onNavigateToMovie = { movieInfo ->
                            val newMovieId = movieInfo.id
                            navController.popBackStack()
                            navController.navigate("Movie/$newMovieId")
                        },
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }

            composable("Serie/{serieId}") { it ->
                val seriesUiState by serieViewModel.uiState.collectAsStateWithLifecycle()
                val serieId = it.arguments?.getString("serieId")?.toIntOrNull()
                serieId?.let { id ->
                    SerieScreen(
                        modifier = Modifier,
                        serieId = id,
                        uiState = seriesUiState,
                        seasonViewModel = seasonViewModel,
                        onEvent = serieViewModel::onEvent,
                        seasonUiEvent = seasonViewModel::onEvent,
                        onNavigateToSerie = { serieInfo ->
                            val newSerieId = serieInfo.id
                            navController.popBackStack()
                            navController.navigate("Serie/$newSerieId")
                        },
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }

            composable("Search") {
                val searchUiState by searchViewModel.uiState.collectAsStateWithLifecycle()
                SearchScreen(
                    modifier = Modifier,
                    uiState = searchUiState,
                    onEvent = searchViewModel::onEvent,
                    onNavigateToMovie = {
                        val movieId = it.id
                        navController.navigate("Movie/$movieId")
                    }
                )
            }

            composable("TV") {
                val tvShowUiState by tvShowViewModel.uiState.collectAsStateWithLifecycle()
                TVShowScreen(
                    modifier = Modifier,
                    uiState = tvShowUiState,
                    onEvent = tvShowViewModel::onEvent,
                    onNavigateToSerie = {
                        val serieId = it.id
                        navController.navigate("Serie/$serieId")
                    },
                    onViewAllSeries = {
                        val json = Json.encodeToString(it)
                        val encodedJson = Uri.encode(json)
                        navController.navigate("AllSeries/$encodedJson")
                    }
                )
            }

            composable(
                "AllMovies/{listMoviesResponse}",
                arguments = listOf(navArgument("listMoviesResponse") { type = NavType.StringType })
            ) { navBackStackEntry ->
                val json = navBackStackEntry.arguments?.getString("listMoviesResponse")
                val decodedJson = json?.let { Uri.decode(it) }
                val listMoviesResponse =
                    decodedJson?.let { Json.decodeFromString<ListMoviesResponse>(it) }

                listMoviesResponse?.let { response ->
                    AllMoviesScreen(
                        modifier = Modifier,
                        onNavigateToMovie = { movie ->
                            val movieId = movie.id
                            navController.navigate("Movie/$movieId")
                        },
                        listMoviesResponse = response,
                        onBackClick = navController::popBackStack,
                    )
                }
            }

            composable(
                "AllSeries/{listSeriesResponse}",
                arguments = listOf(navArgument("listSeriesResponse") { type = NavType.StringType })
            ) { navBackStackEntry ->
                val json = navBackStackEntry.arguments?.getString("listSeriesResponse")
                val decodedJson = json?.let { Uri.decode(it) }
                val listSeriesResponse =
                    decodedJson?.let { Json.decodeFromString<ListSeriesResponse>(it) }

                listSeriesResponse?.let { response ->
                    AllTVShowsScreen(
                        modifier = Modifier,
                        onNavigateToSerie = { serie ->
                            val serieId = serie.id
                            navController.navigate("Serie/$serieId")
                        },
                        listSeriesResponse = response,
                        onBackClick = navController::popBackStack
                    )
                }
            }
        }
    }
}