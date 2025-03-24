package ali.projects.challengeuala.presentation.screens.components

import ali.projects.challengeuala.R
import ali.projects.challengeuala.domain.model.CityModel
import ali.projects.challengeuala.ui.theme.accentColor
import ali.projects.challengeuala.ui.theme.primaryColor
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CityItem(
    city: CityModel,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                TextView(
                    text = "${city.name}, ${city.country}",
                    modifier = Modifier.padding(bottom = 8.dp),
                    textColor = primaryColor
                )
                Row {
                    TextView(
                        text = "${stringResource(id = R.string.latitude)} ${city.latitude}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TextView(
                        text = "${stringResource(id = R.string.longitude)} ${city.longitude}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = stringResource(id= R.string.lbl_show_detail),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color.Blue,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 5.dp).align(Alignment.CenterHorizontally).clickable {
                        showDialog = true
                    }
                )
            }
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (city.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.icon_description),
                    tint = if (city.isFavorite) accentColor else Color.Gray
                )
            }
        }
    }
    if (showDialog) {
        CityInfoDialog(city = city, onDismiss = { showDialog = false })
    }
}