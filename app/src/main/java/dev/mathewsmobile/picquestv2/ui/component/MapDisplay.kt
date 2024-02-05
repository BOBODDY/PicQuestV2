package dev.mathewsmobile.picquestv2.ui.component

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.mapbox.geojson.Point
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.plugin.gestures.generated.GesturesSettings
import dev.mathewsmobile.picquestv2.R
import dev.mathewsmobile.picquestv2.model.LatLng

@OptIn(MapboxExperimental::class)
@Composable
fun MapDisplay(
    interactionEnabled: Boolean = true,
    mapViewportState: MapViewportState,
    pinnedLocation: LatLng?,
) {
    /*
    val annotationApi = mapView?.annotations
val pointAnnotationManager = annotationApi?.createPointAnnotationManager(mapView!!)
// Set options for the resulting symbol layer.
val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
// Define a geographic coordinate.
.withPoint(Point.fromLngLat(18.06, 59.31))
// Specify the bitmap you assigned to the point annotation
// The bitmap will be added to map style automatically.
.withIconImage(it)
// Add the resulting pointAnnotation to the map.
pointAnnotationManager?.create(pointAnnotationOptions)
     */
    Box(modifier = Modifier.pointerInput(Unit) {}) {
        MapboxMap(
            modifier = Modifier.fillMaxSize(),
            mapViewportState = mapViewportState,
            gesturesSettings = GesturesSettings {
                GesturesSettings.Builder()
                    .setPitchEnabled(false)
                    .setPinchToZoomEnabled(interactionEnabled)
                    .setRotateEnabled(interactionEnabled)
                    .setDoubleTapToZoomInEnabled(interactionEnabled)
                    .setDoubleTouchToZoomOutEnabled(interactionEnabled)
                    .setScrollEnabled(interactionEnabled)
                    .build()
            },
            mapInitOptionsFactory = { context ->
                MapInitOptions(
                    context = context,
                    styleUri = Style.STANDARD,
                )
            },
        ) {
            pinnedLocation?.let { LocationPointer(pinnedLocation = it) }
        }
    }
}

@OptIn(MapboxExperimental::class)
@Composable
fun LocationPointer(pinnedLocation: LatLng) {
    val annotationPoint = Point.fromLngLat(pinnedLocation.longitude?.toDouble()!!, pinnedLocation.latitude?.toDouble()!!)

    val context = LocalContext.current
    val drawable = ResourcesCompat.getDrawable(
        context.resources,
        R.drawable.red_marker,
        null
    )
    val bitmap = drawable!!.toBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )


    PointAnnotation(
        iconImageBitmap = bitmap,
        iconSize = 0.5,
        point = annotationPoint,
    )
}