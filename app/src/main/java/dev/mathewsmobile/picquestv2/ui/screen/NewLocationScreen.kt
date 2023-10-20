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
import androidx.compose.runtime.collectAsState
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
import dev.mathewsmobile.picquestv2.viewmodel.NewLocationViewModel

object NewLocationScreen {
    const val route = "NewLocationScreen"
}

@Composable
fun NewLocationScreen(
    navController: NavController,
    viewModel: NewLocationViewModel,
) {
    val name by viewModel.name.collectAsState()
    val notes by viewModel.notes.collectAsState()

    NewLocationComponent(
        name = name,
        notes = notes,
        onNameChange = { viewModel.setName(it) },
        onNoteChange = { viewModel.setNotes(it) },
        onNoteTooltipClicked = { /* TODO Show a popup */ },
        onSaveClick = {
            viewModel.addNewLocation(name, notes)
            navController.popBackStack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLocationComponent(
    name: String,
    notes: String,
    onNameChange: (String) -> Unit,
    onNoteChange: (String) -> Unit,
    onNoteTooltipClicked: () -> Unit,
    onSaveClick: () -> Unit
) {

    Column(modifier = Modifier.padding(8.dp)) {
        Text("Add a New Location", fontSize = 32.sp, fontWeight = Bold)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = onNameChange,
            label = { Text("Location Name") },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = notes,
            onValueChange = onNoteChange,
            label = { Text("Notes") },
            trailingIcon = { Icon(Icons.Default.Info, modifier = Modifier.clickable { onNoteTooltipClicked() }, contentDescription = null) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onSaveClick) {
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
        NewLocationComponent("Big Bend National Park", "Perfect for astrophotography", {}, {}, {}, {})
    }
}