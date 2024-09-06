package dev.mathewsmobile.picquestv2.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.mathewsmobile.picquestv2.ui.NewLocationFlowScreens.*
import dev.mathewsmobile.picquestv2.ui.component.LocationDetails
import dev.mathewsmobile.picquestv2.ui.component.LocationMap
import dev.mathewsmobile.picquestv2.ui.component.LocationPhotos
import dev.mathewsmobile.picquestv2.ui.component.LocationTags
import dev.mathewsmobile.picquestv2.ui.component.MapComponent
import dev.mathewsmobile.picquestv2.ui.component.PhotoPicker
import dev.mathewsmobile.picquestv2.ui.component.TagGroup
import dev.mathewsmobile.picquestv2.viewmodel.MapViewModel
import dev.mathewsmobile.picquestv2.viewmodel.NewLocationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class NewLocationFlowScreens {
    Details, Tags, Location, Photos
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewLocationFlow(
    modifier: Modifier = Modifier,
    viewModel: NewLocationViewModel,
    mapViewModel: MapViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    val screens = listOf(
        Details, Tags, Location, Photos
    )

    val pagerState = rememberPagerState(pageCount = { screens.size })
    val config = remember(key1 = pagerState.currentPage) {
        ScreenConfig(
            showNext = pagerState.currentPage < (screens.size - 1),
            showPrev = pagerState.currentPage > 0,
            onNext = {
                if (pagerState.currentPage == (screens.size - 1)) {
                    // TODO Implement the map
//                    viewModel.setLocation(mapViewModel.mapViewportState.value.cameraState.center)
                    // TODO Make sure everything is filled in correctly
                    viewModel.addNewLocation()
                    navController.popBackStack()
                } else {
                    coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            },
            onBack = {
                if (pagerState.currentPage == 0) {
                    navController.popBackStack()
                } else {
                    coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                }
            },
            progress = ((pagerState.currentPage.toFloat() + 1) / screens.size.toFloat())
        )
    }
    Box(modifier.fillMaxSize()) {

        HorizontalPager(modifier = modifier.fillMaxSize(), state = pagerState) { page ->
            val screen = screens[page]

            when (screen) {
                Details -> DetailsScreen(
                    modifier = modifier,
                    config = config,
                    viewModel = viewModel,
                    coroutineScope = coroutineScope
                )

                Tags -> TagsScreen(
                    modifier = modifier,
                    config = config,
                    viewModel = viewModel,
                )

                Photos -> PhotosScreen(
                    modifier = modifier,
                    config = config,
                    viewModel = viewModel,
                )

                Location -> MapScreen(
                    modifier = modifier,
                    config = config,
                    viewModel = viewModel,
                    mapViewModel = mapViewModel,
                )
            }

        }
        PagerNavigator(modifier = modifier.align(Alignment.BottomCenter), config = config)
    }

}

data class ScreenConfig(
    val showNext: Boolean,
    val onNext: () -> Unit,
    val showPrev: Boolean,
    val onBack: () -> Unit,
    val progress: Float,
)

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    config: ScreenConfig,
    viewModel: NewLocationViewModel,
    coroutineScope: CoroutineScope
) {
    val name by viewModel.name.collectAsState()
    val notes by viewModel.notes.collectAsState()

    LocationDetails(
        modifier,
        name,
        notes,
        setName = { viewModel.setName(it) },
        setNotes = { viewModel.setNotes(it) }
    )
}

@Composable
fun TagsScreen(
    modifier: Modifier = Modifier,
    config: ScreenConfig,
    viewModel: NewLocationViewModel
) {
    val tags by viewModel.tags.collectAsState(initial = emptyList())
    val selectedTags by viewModel.selectedTags.collectAsState()

    LocationTags(modifier, tags, selectedTags) { viewModel.toggleTag(it) }
}

@Composable
fun PhotosScreen(
    modifier: Modifier = Modifier,
    config: ScreenConfig,
    viewModel: NewLocationViewModel
) {
    val photos by viewModel.photos.collectAsState()
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
            uris.forEach { viewModel.addPhoto(it) }
        }
    LocationPhotos(modifier, photos) {
        launcher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    config: ScreenConfig,
    viewModel: NewLocationViewModel,
    mapViewModel: MapViewModel,
) {
    LocationMap(modifier, mapViewModel)
}

@Composable
fun PagerNavigator(modifier: Modifier = Modifier, config: ScreenConfig) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = modifier.padding(8.dp),
            onClick = { config.onBack() }
        ) {
            val icon = if (config.showPrev) {
                Icons.Default.ArrowBack
            } else {
                Icons.Default.Close
            }
            Icon(icon, contentDescription = "Back button")
        }

        LinearProgressIndicator(
            modifier = modifier
                .padding(8.dp)
                .weight(1f), progress = config.progress
        )

        IconButton(
            modifier = modifier.padding(8.dp),
            onClick = { config.onNext() }
        ) {
            val icon = if (config.showNext) {
                Icons.Default.ArrowForward
            } else {
                Icons.Default.Check
            }
            Icon(icon, contentDescription = "Next button")
        }
    }
}