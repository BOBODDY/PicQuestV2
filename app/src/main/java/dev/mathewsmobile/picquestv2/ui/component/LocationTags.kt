package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mathewsmobile.picquestv2.data.TagRepository
import dev.mathewsmobile.picquestv2.model.Tag

@Composable
fun LocationTags(
    modifier: Modifier = Modifier,
    availableTags: List<Tag>,
    selectedTags: List<Tag>,
    toggleTag: (Tag) -> Unit
) {
    Box(modifier.fillMaxSize()) {
        Column(modifier.padding(16.dp)) {
            Text("Add some tags", fontSize = 32.sp, fontWeight = Bold)
            Spacer(modifier.height(16.dp))
            Text("These can be anything from conditions you want to come back to this location with to what equipment you want.")
            Spacer(modifier.height(8.dp))
            TagGroup(availableTags = availableTags, selectedTags = selectedTags) {
                toggleTag(it)
            }
        }
    }
}

@Preview
@Composable
private fun LocationTagsPreview() {
    Box(Modifier.background(Color.White)) {
        LocationTags(
            availableTags = TagRepository.testTags,
            selectedTags = emptyList(),
            toggleTag = {}
        )
    }
}