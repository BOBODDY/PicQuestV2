package dev.mathewsmobile.picquestv2.data.usecase

import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.model.Location
import javax.inject.Inject

class AddLocationUseCase @Inject constructor(private val repository: LocationRepository) {

    suspend operator fun invoke(location: Location) {
        repository.addLocation(location)
    }

}