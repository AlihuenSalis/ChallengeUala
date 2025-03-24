package ali.projects.challengeuala.presentation.screens

import ali.projects.challengeuala.R
import ali.projects.challengeuala.presentation.core.navigation.Routes
import ali.projects.challengeuala.presentation.screens.components.CityInfoDialog
import ali.projects.challengeuala.presentation.screens.components.CityItem
import ali.projects.challengeuala.presentation.screens.components.FavouritesToggle
import ali.projects.challengeuala.presentation.screens.components.SearchTextField
import ali.projects.challengeuala.presentation.screens.components.TextView
import ali.projects.challengeuala.presentation.viewModels.CityViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CityListScreenPot(navController: NavController, viewModel: CityViewModel) {

    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredCities by viewModel.cities.collectAsState()
    val listState = rememberLazyListState()
    val isFavouritesOnly by viewModel.isFavouritesOnly.collectAsState()
    val lastVisibleIndex by remember {
        derivedStateOf { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
    }

    LaunchedEffect(lastVisibleIndex) {
        if (lastVisibleIndex == filteredCities.size - 1) {
            viewModel.loadNextPage()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(top = 16.dp, start = 5.dp, end = 5.dp)) {
        SearchTextField(
            searchQuery = searchQuery,
            onValueChange = { query -> viewModel.updateSearchQuery(query) }
        )
        LazyColumn(
            state = listState
        ) {
            item {
                FavouritesToggle(isFavouritesOnly = isFavouritesOnly, onToggleFavouritesChanged = { viewModel.showOnlyFavorites(it) })
            }
            if (filteredCities.isNotEmpty()) {

                items(filteredCities) { city ->
                    CityItem(
                        city = city,
                        onClick = {
                            navController.navigate(
                                "${Routes.CityMapScreen.route}/${city.name}/${city.latitude}/${city.longitude}"
                            )
                        },
                        onFavoriteClick = {
                            viewModel.onToggleFavorite(city)
                        }
                    )
                }
            } else {
                item {
                    TextView(
                        text = stringResource(id = R.string.no_cities_found),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                }
            }

        }
    }
}