package dev.mathewsmobile.picquestv2.model

import android.net.Uri

data class Location(
    val id: Int = 0,
    val name: String,
    val notes: String,
    val latLng: LatLng? = null,
    val tags: List<Tag>,
    val photoUris: List<Uri>,
)
