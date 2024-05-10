package com.kiwa.domain.usecase

import com.kiwa.domain.repository.UserRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke() = userRepository.logout()
}
