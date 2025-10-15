package ru.mygames.classicsnake.ui.screens.rating.models

import ru.mygames.classicsnake.data.local.datastore.GameLevel
import ru.mygames.classicsnake.data.local.datastore.UserGameResult
import ru.mygames.classicsnake.ui.base.architecture.UiState

sealed interface RatingTableViewState: UiState {

    data object Loading: RatingTableViewState

    data class Display(
        val gameLevel: GameLevel,
        val gameResults: List<UserGameResult>
    ): RatingTableViewState
}