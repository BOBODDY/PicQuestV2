package dev.mathewsmobile.picquestv2.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.model.Location

object NewLocationScreen {
    const val route = "NewLocationScreen"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLocationScreen(
    navController: NavController,
) {
    val locationRepository = LocationRepository() // TODO Inject
    NewLocationComponent {
        locationRepository.addLocation(it)
        navController.popBackStack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLocationComponent(onSaveClick: (Location) -> Unit) {
    var newName by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp)) {
        Text("Add a New Location", fontSize = 32.sp, fontWeight = Bold)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = newName,
            onValueChange = { newName = it },
            label = { Text("Location Name") },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            trailingIcon = { Icon(Icons.Default.Info, modifier = Modifier.clickable { /* TODO Show a popup */ }, contentDescription = null) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { onSaveClick(Location(name = newName, notes = notes)) }) {
            Text("Save")
        }
    }

}

@Preview
@Composable
fun Preview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NewLocationScreen(rememberNavController())
    }
}