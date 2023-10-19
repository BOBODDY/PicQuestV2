package dev.mathewsmobile.picquestv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mapbox.maps.MapboxExperimental
import dev.mathewsmobile.picquestv2.ui.screen.LocationListScreen
import dev.mathewsmobile.picquestv2.ui.theme.PicQuestV2Theme

class MainActivity : ComponentActivity() {
    @OptIn(MapboxExperimental::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicQuestV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LocationListScreen()
                }
            }
        }
    }
}
