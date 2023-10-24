package dev.mathewsmobile.picquestv2.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.rememberModalBottomSheetState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.mathewsmobile.picquestv2.ui.component.LocationNotesExplanation
import dev.mathewsmobile.picquestv2.ui.component.MapComponent
import dev.mathewsmobile.picquestv2.viewmodel.NewLocationViewModel
import kotlinx.coroutines.launch

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
        onSaveClick = {
            viewModel.addNewLocation(name, notes)
            navController.popBackStack()
        },
        onCloseClick = { navController.popBackStack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun NewLocationComponent(
    name: String,
    notes: String,
    onNameChange: (String) -> Unit,
    onNoteChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var showBottomSheet by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            LocationNotesExplanation()
        }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Icon(
                Icons.Default.Close,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onCloseClick() },
                contentDescription = null
            )
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
                trailingIcon = {
                    Icon(
                        Icons.Default.Info,
                        modifier = Modifier.clickable {
                            coroutineScope.launch { sheetState.show() }; showBottomSheet = true
                        },
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth().height(128.dp).clip(RoundedCornerShape(4.dp))) {
                MapComponent(viewModel())
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onSaveClick
            ) {
                Text("Save")
            }
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
        NewLocationComponent(
            "Big Bend National Park",
            "Perfect for astrophotography",
            {},
            {},
            {},
            {})
    }
}