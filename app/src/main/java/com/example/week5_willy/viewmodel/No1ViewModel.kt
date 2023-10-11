package com.example.week5_willy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5_willy.model.No1Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class No1ViewModel : ViewModel() {
    val newNumber = generateRandomNumber()

    private val _uiState = MutableStateFlow(No1Model(number = newNumber, chances = 0, score = 0, gameOver = false, gameWin = false))
    val uiState: StateFlow<No1Model> = _uiState.asStateFlow()
    fun onGuess(guess: Int) {
        viewModelScope.launch {
            val currentState = uiState.value

            if (guess == currentState.number) {
                val updatedState = currentState.copy(
                    score = currentState.score + 1,
                    number = generateRandomNumber()
                )
                _uiState.value = updatedState

                if (updatedState.score == 3) {
                    //win
                    _uiState.value = updatedState.copy(gameWin = true)
                }

            } else {
                // Incorrect guess
                val updatedChances = currentState.chances + 1
                val updatedState = currentState.copy(
                    chances = updatedChances
                )
                _uiState.value = updatedState

                if (updatedChances == 3) {
                    _uiState.value = updatedState.copy(gameOver = true)
                }
            }
        }
    }

    fun restartGame() {
        _uiState.value = No1Model(
            number = newNumber,
            chances = 0,
            score = 0,
            gameOver = false,
            gameWin = false
        )
    }
}
private fun generateRandomNumber(): Int {
    return (1..10).random()
}