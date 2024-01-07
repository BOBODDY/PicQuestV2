package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.mathewsmobile.picquestv2.model.Tag

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagGroup(availableTags: List<Tag>, selectedTags: List<Tag>, onTagTapped: (Tag) -> Unit) {
    FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        availableTags.map { TagCell(it, selectedTags.contains(it)) { onTagTapped(it) } }
    }
}

@Preview
@Composable
private fun TagGroupPreview() {
    val tags: List<Tag> = listOf(
        Tag(name = "Item 1"),
        Tag(name = "Item 2"),
        Tag(name = "Longer item name"),
    )
    var selectedTags: List<Tag> by remember {
        mutableStateOf(emptyList())
    }
    Surface(color = MaterialTheme.colorScheme.background) {
        TagGroup(availableTags = tags, selectedTags = selectedTags) {
            selectedTags = if (selectedTags.contains(it)) {
                selectedTags - it
            } else {
                selectedTags + it
            }
        }
    }
}