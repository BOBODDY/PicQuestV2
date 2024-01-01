package dev.mathewsmobile.picquestv2.model

data class Location(
    val name: String,
    val notes: String,
    val latLng: LatLng? = null,
    val tags: List<Tag>
)