package ru.mygames.classicsnake.domain.models

data class BonusItem(
    val position: Point,
    val status: BonusType
)

sealed interface BonusType {
    data object ExtraLife: BonusType
    data object IncreaseScore: BonusType
}