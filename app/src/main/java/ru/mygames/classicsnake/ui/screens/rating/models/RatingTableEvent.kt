package ru.mygames.classicsnake.ui.screens.rating.models

import ru.mygames.classicsnake.ui.base.architecture.UiEvent

sealed interface RatingTableEvent: UiEvent {

    data object EnterScreen: RatingTableEvent

    data object CloseScreen: RatingTableEvent

    data class ChangeDifficultyLevel(val number: Int): RatingTableEvent

}