package dev.mathewsmobile.picquestv2.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.navigation.NavController
import com.mapbox.maps.MapboxExperimental
import dev.mathewsmobile.picquestv2.data.TagRepository
import dev.mathewsmobile.picquestv2.model.Tag
import dev.mathewsmobile.picquestv2.ui.component.LocationNotesExplanation
import dev.mathewsmobile.picquestv2.ui.component.MapComponent
import dev.mathewsmobile.picquestv2.ui.component.PhotoPicker
import dev.mathewsmobile.picquestv2.ui.component.TagGroup
import dev.mathewsmobile.picquestv2.viewmodel.MapViewModel
import dev.mathewsmobile.picquestv2.viewmodel.NewLocationViewModel
import kotlinx.coroutines.launch

object NewLocationScreen {
    const val route = "NewLocationScreen"
}

@OptIn(MapboxExperimental::class)
@Composable
fun NewLocationScreen(
    navController: NavController,
    viewModel: NewLocationViewModel,
    mapViewModel: MapViewModel
) {
    val name by viewModel.name.collectAsState()
    val notes by viewModel.notes.collectAsState()
    val tags by viewModel.tags.collectAsState(initial = emptyList())
    val selectedTags by viewModel.selectedTags.collectAsState()
    val photos by viewModel.photos.collectAsState()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
        uris.forEach { viewModel.addPhoto(it) }
    }

    NewLocationComponent(
        mapViewModel = mapViewModel,
        name = name,
        notes = notes,
        allTags = tags,
        selectedTags = selectedTags,
        photos = photos,
        onNameChange = { viewModel.setName(it) },
        onNoteChange = { viewModel.setNotes(it) },
        onTagChanged = { viewModel.toggleTag(it) },
        onSaveClick = {
            viewModel.setLocation(mapViewModel.mapViewportState.value.cameraState.center)
            viewModel.addNewLocation(name, notes, selectedTags)
            navController.popBackStack()
        },
        onCloseClick = { navController.popBackStack() },
        onAddImage = {
            launcher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun NewLocationComponent(
    mapViewModel: MapViewModel,
    name: String,
    notes: String,
    allTags: List<Tag>,
    selectedTags: List<Tag>,
    photos: List<Uri>,
    onNameChange: (String) -> Unit,
    onNoteChange: (String) -> Unit,
    onTagChanged: (Tag) -> Unit,
    onAddImage: () -> Unit,
    onSaveClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var showBottomSheet by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    var mapExpanded by remember {
        mutableStateOf(false)
    }
    val mapHeight = if (mapExpanded) {
        512.dp
    } else {
        256.dp
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            LocationNotesExplanation()
        }
    ) {
        Column(modifier = Modifier
            .padding(8.dp)) {
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(mapHeight)
                    .clip(RoundedCornerShape(4.dp))
                    .animateContentSize()
            ) {
                MapComponent(mapViewModel) { mapExpanded = !mapExpanded }
                Image(
                    Icons.Default.LocationOn,
                    modifier = Modifier.align(Alignment.Center),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            TagGroup(availableTags = allTags, selectedTags = selectedTags) {
                onTagChanged(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            PhotoPicker(photos = photos) {
                onAddImage()
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
    val mapViewModel = MapViewModel()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NewLocationComponent(
            mapViewModel,
            "Big Bend National Park",
            "Perfect for astrophotography",
            TagRepository.testTags,
            emptyList(),
            emptyList(),
            {},
            {},
            {},
            {},
            {},
            {})
    }
}