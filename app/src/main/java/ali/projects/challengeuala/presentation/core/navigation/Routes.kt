package ali.projects.challengeuala.presentation.core.navigation

import ali.projects.challengeuala.presentation.utils.Constants.Routes.CITY_LIST_SCREEN
import ali.projects.challengeuala.presentation.utils.Constants.Routes.LOADING_SCREEN
import ali.projects.challengeuala.presentation.utils.Constants.Routes.CITY_MAP_SCREEN as CITY_MAP_SCREEN1

sealed class Routes(val route: String) {
    data object CityListScreen : Routes(CITY_LIST_SCREEN)
    data object CityMapScreen : Routes(CITY_MAP_SCREEN1)
    data object LoadingScreen: Routes(LOADING_SCREEN)
}