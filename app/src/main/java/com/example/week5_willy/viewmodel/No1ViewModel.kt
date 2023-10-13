package com.example.week5_willy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5_willy.model.No1Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class No1ViewModel : ViewModel() {
    private val newNumber = generateRandomNumber()

    private val _uiState = MutableStateFlow(No1Model(number = newNumber, chances = 0, score = 0, endGame = false))
    val uiState: StateFlow<No1Model> = _uiState.asStateFlow()
    fun onGuess(guess: String) {
        viewModelScope.launch {
            val curr = _uiState.value

            if (guess.toInt() == curr.number) {
                //bener
                val update = curr.copy(
                    score = curr.score + 1,
                    number = generateRandomNumber()
                )
                _uiState.value = update

                if (update.score == 3) {
                    //win
                    _uiState.value = update.copy(endGame = true)
                }

            } else {
                // Salah
                val update = curr.copy(
                    chances = curr.chances + 1
                )
                _uiState.value = update

                if (update.chances == 3) {
                    //lose
                    _uiState.value = update.copy(endGame = true)
                }
            }
        }
    }

    fun restartGame() {
        _uiState.value = No1Model(
            number = newNumber,
            chances = 0,
            score = 0,
            endGame = false
        )
    }
}
private fun generateRandomNumber(): Int {
    return (1..10).random()
}