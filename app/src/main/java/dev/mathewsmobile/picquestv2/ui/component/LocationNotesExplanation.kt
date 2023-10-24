package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LocationNotesExplanation() {
    Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
        Text("Notes?", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            """
            Your notes can be anything you want to remember about a certain location and why you want to save it.
            
            It can be a reminder of why you thought this was a good location to save, or what you want to do when
            you come back to this location.
            """.trimIndent()
        )
    }
}

@Preview
@Composable
private fun NotesPreview() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
    ) {
        LocationNotesExplanation()
    }
}