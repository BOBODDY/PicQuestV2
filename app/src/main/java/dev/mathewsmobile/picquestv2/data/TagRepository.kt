package dev.mathewsmobile.picquestv2.data

import dev.mathewsmobile.picquestv2.model.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TagRepository @Inject constructor() {

    val allTags: Flow<List<Tag>> = flow {
        emit(testTags)
    }

    companion object {
        val testTags = listOf(
            Tag(name = "Sunny"),
            Tag(name = "Rainy"),
            Tag(name = "Cloudy"),
            Tag(name = "Foggy"),
            Tag(name = "Morning"),
            Tag(name = "Evening"),
            Tag(name = "Midday"),
            Tag(name = "Film"),
            Tag(name = "Digital"),
            Tag(name = "Close-up"),
            Tag(name = "Telephoto"),
        )
    }
}