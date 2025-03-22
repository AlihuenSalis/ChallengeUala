package ali.projects.challengeuala.presentation.screens.components

import ali.projects.challengeuala.domain.model.CityModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun CityInfoDialog(city: CityModel, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Id: ${city.id}") },
        text = {
            Column {
                Text("City: ${city.name}")
                Text("Country: ${city.country}")
                Text("Coordinates: Lat ${city.latitude}, Lon ${city.longitude}")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}