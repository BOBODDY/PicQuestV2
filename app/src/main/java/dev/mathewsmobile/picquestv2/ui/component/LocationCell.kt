package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.ui.theme.PicQuestV2Theme

@Composable
fun LocationCell(location: Location) {
    Card(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(location.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(location.notes, maxLines = 3, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview
@Composable
private fun LocationCellPreview() {
    PicQuestV2Theme {
        LocationCell(
            LocationRepository.testLocations.first()
        )
    }
}