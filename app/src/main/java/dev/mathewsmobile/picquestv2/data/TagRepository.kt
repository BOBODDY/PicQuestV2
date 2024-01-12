package dev.mathewsmobile.picquestv2.data

import dev.mathewsmobile.picquestv2.data.dao.TagDao
import dev.mathewsmobile.picquestv2.model.Tag
import dev.mathewsmobile.picquestv2.model.db.LocationTagPair
import dev.mathewsmobile.picquestv2.model.db.TagDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TagRepository @Inject constructor(
    private val tagDao: TagDao
) {

    fun getAllTags(): Flow<List<Tag>> {
        val tags = tagDao.getAll().map {
            if (it.isEmpty()) {
                testTags.forEach { addCustomTag(it) }
            }
            it
        }
        return tags.map {
            it.map { mapDbToTag(it) }
        }
    }

    suspend fun addCustomTag(tag: Tag) {
        val dbTag = TagDb(
            id = 0,
            name = tag.name
        )
        tagDao.addTag(dbTag)
    }

    suspend fun getTagsForLocation(locationId: Int): List<Tag> {
        return tagDao.getTagsForLocation(locationId)
            .map { tagDao.getTagById(it.tagId) }
            .map { mapDbToTag(it) }
    }

    suspend fun addTagForLocation(locationId: Int, tag: Tag) {
        // TODO When custom tags are supported, need to check if a tag already exists and add it if not
        val locationTagPair = LocationTagPair(
            locationId = locationId,
            tagId = tag.id
        )
        tagDao.addLocationTagPair(locationTagPair)
    }

    suspend fun getTagById(tagId: Int): Tag {
        val dbTag = tagDao.getTagById(tagId)
        return mapDbToTag(dbTag)
    }

    private fun mapDbToTag(tagDb: TagDb): Tag {
        return Tag(
            id = tagDb.id,
            name = tagDb.name
        )
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