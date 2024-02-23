package dev.mathewsmobile.picquestv2.ui.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PhotoPicker(
    enabled: Boolean = true,
    photos: List<Uri>,
    maxPerLine: Int = 5,
    onAddPhoto: () -> Unit,
) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        // TODO Only center the icon, not the title
        if (enabled) {
            Text(
                text = "Add Photos",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (photos.isNotEmpty()) {
            photos.chunked(maxPerLine).map { photoRow ->
                Row(
                    modifier = Modifier
                        .height(64.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
                ) {

                    repeat(maxPerLine) {
                        val photo = photoRow.getOrNull(it)
                        PhotoIcon(photoUri = photo)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (enabled) {
            PhotoButton {
                onAddPhoto()
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun PhotoIcon(photoUri: Uri?) {
    Card(
        modifier = Modifier
            .size(64.dp)
            .background(Color.Gray)
    ) {
        photoUri?.let {
            AsyncImage(model = it, contentScale = ContentScale.FillBounds, contentDescription = "Your example image")
        }
    }
}

@Composable
fun PhotoButton(onClick: () -> Unit) {
    IconButton(modifier = Modifier
        .size(24.dp)
        .clip(
            RoundedCornerShape(4.dp)
        )
        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
        , onClick = { onClick() }) {
        Icon(Icons.Default.Add, contentDescription = "Add new photo")
    }
}

@Preview
@Composable
fun PhotoPickerPreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        PhotoPicker(enabled = true, listOf(), 5, {})
    }
}