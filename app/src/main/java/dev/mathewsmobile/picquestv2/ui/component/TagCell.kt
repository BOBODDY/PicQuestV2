package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.picquestv2.model.Tag

@Composable
fun TagCell(tag: Tag, isSelected: Boolean, onTap: (Tag) -> Unit) {
    val icon = if (isSelected) {
        Icons.Default.Check
    } else {
        Icons.Default.Add
    }

    Row(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { onTap(tag) }
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, modifier = Modifier.size(16.dp), contentDescription = null)

        Text(tag.name)
    }
}

@Preview
@Composable
private fun TagPreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            TagCell(Tag("Item 1"), false, {})
            TagCell(Tag("Item 2"), true, {})
            TagCell(Tag("Longer item name"), false, {})
        }
    }
}