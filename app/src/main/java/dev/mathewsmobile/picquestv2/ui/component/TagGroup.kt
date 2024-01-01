package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.mathewsmobile.picquestv2.model.Tag
import java.util.UUID

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagGroup(availableTags: List<Tag>, selectedTags: List<Tag>, onTagTapped: (Tag) -> Unit) {
    FlowRow() {
        availableTags.map { TagCell(it, selectedTags.contains(it)) { onTagTapped(it) } }
    }
}

@Preview
@Composable
private fun TagGroupPreview() {
    val uuid = UUID.randomUUID()
    val tags = listOf(
        Tag(name = "Item 1"),
        Tag(id = uuid, name = "Item 2"),
        Tag(name = "Longer item name"),
    )
    val selectedTags = listOf(
        Tag(id = uuid, name = "Item 2"),
    )
    Surface(color = MaterialTheme.colorScheme.background) {
        TagGroup(availableTags = tags, selectedTags = selectedTags) {}
    }
}