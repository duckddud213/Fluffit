package com.kiwa.domain.usecase

import com.kiwa.domain.repository.MainRepository
import javax.inject.Inject

class UpdateHealthUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    operator fun invoke() = mainRepository.updateHealth()
}