package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mathewsmobile.picquestv2.viewmodel.MapViewModel

@Composable
fun LocationMap(modifier: Modifier = Modifier, mapViewModel: MapViewModel) {
    Box(modifier.fillMaxSize()) {
        Column(modifier.padding(16.dp)) {
            Text("Location", fontSize = 32.sp, fontWeight = Bold)
            Spacer(modifier.height(16.dp))
            Text("Choose where you want to visit")
            Spacer(modifier.height(16.dp))
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(512.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .animateContentSize()
            ) {
                MapComponent(modifier, mapViewModel, false) { }
                Image(
                    Icons.Default.LocationOn,
                    modifier = modifier.align(Alignment.Center),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun LocationMapPreview() {
    Box(Modifier.background(Color.White)) {
        LocationMap(Modifier, MapViewModel())
    }
}