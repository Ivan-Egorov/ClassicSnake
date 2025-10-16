package ru.mygames.classicsnake.domain.models

import ru.mygames.classicsnake.data.local.datastore.UserGameResultDto

data class UserGameResult(
    val score: Int,
    val time: String
)

fun UserGameResultDto.toDomain(): UserGameResult {
    return UserGameResult(score = score, time = time)
}

fun UserGameResult.toDto(): UserGameResultDto {
    return UserGameResultDto(score = score, time = time)
}