package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LocationDetails(
    modifier: Modifier = Modifier,
    name: String,
    notes: String,
    setName: (String) -> Unit,
    setNotes: (String) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier.padding(16.dp)
        ) {
            item { Text("Add a New Location", fontSize = 32.sp, fontWeight = Bold) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = { setName(it) },
                    label = { Text("Location Name") },
                    singleLine = true,
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                TextField(modifier = Modifier.fillMaxWidth(),
                    value = notes,
                    onValueChange = { setNotes(it) },
                    label = { Text("Notes") },
                    trailingIcon = {
                        Icon(
                            Icons.Default.Info, modifier = Modifier.clickable {
//                                coroutineScope.launch { sheetState.show() }; showBottomSheet = true
                            }, contentDescription = null
                        )
                    })
            }
        }
    }
}

@Preview
@Composable
private fun DetailPreview() {
    Box(Modifier.background(Color.White)) {
        LocationDetails(modifier = Modifier,
            name = "Location name",
            notes = "Location notes",
            setName = {},
            setNotes = {})
    }
}