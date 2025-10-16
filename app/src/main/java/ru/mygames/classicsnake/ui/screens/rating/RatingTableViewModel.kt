package ru.mygames.classicsnake.ui.screens.rating

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.mygames.classicsnake.data.local.datastore.DataStoreManager
import ru.mygames.classicsnake.data.local.datastore.GameLevel
import ru.mygames.classicsnake.domain.models.toDomain
import ru.mygames.classicsnake.ui.base.architecture.BaseViewModel
import ru.mygames.classicsnake.ui.screens.rating.models.RatingTableAction
import ru.mygames.classicsnake.ui.screens.rating.models.RatingTableEvent
import ru.mygames.classicsnake.ui.screens.rating.models.RatingTableViewState

class RatingTableViewModel(
    private val dataStoreManager: DataStoreManager
): BaseViewModel<RatingTableEvent, RatingTableViewState, RatingTableAction> (initialState = RatingTableViewState.Loading) {

    override fun obtainEvent(event: RatingTableEvent) {
        when(val state = viewState) {
            is RatingTableViewState.Loading -> reduce(state, event)
            is RatingTableViewState.Display -> reduce(state, event)
        }
    }

    private fun reduce(state: RatingTableViewState.Loading, event: RatingTableEvent) {
        when(event) {
            is RatingTableEvent.EnterScreen -> loadData()
            else -> {}
        }
    }
    private fun reduce(state: RatingTableViewState.Display, event: RatingTableEvent) {
        when(event) {
            is RatingTableEvent.ChangeDifficultyLevel -> changeDifficultyLevel(event.number)
            else -> {}
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val settings = dataStoreManager.appSettings.first()
            val results = settings.gameResults.getOrDefault(settings.gameLevel, emptyList())
            viewState = RatingTableViewState.Display(settings.gameLevel, results.map { it.toDomain() })
        }
    }

    private fun changeDifficultyLevel(number: Int) {
        viewModelScope.launch {
            val gameLevel = GameLevel.entries[number]

            val settings = dataStoreManager.appSettings.first()
            val results = settings.gameResults.getOrDefault(gameLevel, emptyList())
            viewState = RatingTableViewState.Display(gameLevel, results.map { it.toDomain() })
        }
    }

}