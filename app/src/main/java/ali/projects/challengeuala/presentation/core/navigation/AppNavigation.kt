package ali.projects.challengeuala.presentation.core.navigation

import ali.projects.challengeuala.presentation.core.UIState
import ali.projects.challengeuala.presentation.screens.CityListScreenLand
import ali.projects.challengeuala.presentation.screens.CityListScreenPot
import ali.projects.challengeuala.presentation.screens.CityMapScreen
import ali.projects.challengeuala.presentation.screens.components.LoadingScreen
import ali.projects.challengeuala.presentation.screens.components.isPortrait
import ali.projects.challengeuala.presentation.utils.Constants.CITY
import ali.projects.challengeuala.presentation.utils.Constants.LATITUDE
import ali.projects.challengeuala.presentation.utils.Constants.LONGITUDE
import ali.projects.challengeuala.presentation.viewModels.CityViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(cityViewModel: CityViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val uiState by cityViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = Routes.LoadingScreen.route) {
        composable(Routes.LoadingScreen.route) {
            when (uiState) {
                is UIState.Loading -> LoadingScreen()
                is UIState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(Routes.CityListScreen.route) {
                            popUpTo(Routes.LoadingScreen.route) { inclusive = true }
                        }
                    }
                }
                is UIState.Empty -> {
                    LoadingScreen()
                }
            }
        }
        composable(Routes.CityListScreen.route) {
            if (isPortrait()) {
                CityListScreenPot(navController, cityViewModel)
            } else {
                CityListScreenLand(cityViewModel)
            }
        }
        composable(
            "${Routes.CityMapScreen.route}/{${CITY}}/{$LATITUDE}/{$LONGITUDE}",
            arguments = listOf(
                navArgument(CITY) { type = NavType.StringType },
                navArgument(LATITUDE) { type = NavType.FloatType },
                navArgument(LONGITUDE) { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val latitude = backStackEntry.arguments?.getFloat(LATITUDE) ?: 0f
            val longitude = backStackEntry.arguments?.getFloat(LONGITUDE) ?: 0f
            CityMapScreen(latitude = latitude, longitude = longitude)
        }
    }
}