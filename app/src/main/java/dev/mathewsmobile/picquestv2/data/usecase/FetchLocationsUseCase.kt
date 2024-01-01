package dev.mathewsmobile.picquestv2.data.usecase

import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.model.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchLocationsUseCase @Inject constructor(private val repository: LocationRepository) {

    operator fun invoke(): Flow<List<Location>> {
        return repository.getLocations()
    }
}