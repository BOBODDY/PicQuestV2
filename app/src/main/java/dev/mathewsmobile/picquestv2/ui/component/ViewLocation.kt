package dev.mathewsmobile.picquestv2.ui.component

import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mathewsmobile.picquestv2.model.LatLng
import dev.mathewsmobile.picquestv2.model.Tag

@Composable
fun ViewLocation(
    name: String,
    description: String,
    tags: List<Tag>,
    location: LatLng?,
    photos: List<Uri>,
    onCloseClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Icon(
            Icons.Default.Close,
            modifier = Modifier
                .padding(8.dp)
                .clickable { onCloseClick() },
            contentDescription = null
        )
        Text(name, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(description, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .animateContentSize()
            ) {
                // TODO Show Google map
            }
        Spacer(modifier = Modifier.height(8.dp))
        TagGroup(enabled = false, availableTags = tags, selectedTags = tags) {}
        Spacer(modifier = Modifier.height(8.dp))
        PhotoPicker(enabled = false, photos = photos) {}
    }
}

@Preview
@Composable
private fun ViewPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ViewLocation(
            "Sun Voyager",
            "Gleaming like a mythical dreamboat, the Sun Voyager statue in Reykjavik cuts through the air with its sleek steel form, symbolizing Iceland's enduring spirit of exploration and hope.", // Thanks, Bard
            listOf(Tag(name = "Sunny")),
            null,
            emptyList(),
            {}
        )
    }
}