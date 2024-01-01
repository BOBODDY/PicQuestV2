package dev.mathewsmobile.picquestv2.model

import java.util.UUID

data class Tag(
    val name: String,
    val id: UUID = UUID.randomUUID(),
)