package com.example.week5_willy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5_willy.model.No1Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class No1ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(No1Model(number = generateRandomNumber(), chances = 0, score = 0, endGame = false))
    val uiState: StateFlow<No1Model> = _uiState.asStateFlow()
    fun onGuess(guess: String) {
        viewModelScope.launch {
            val curr = _uiState

            if (guess.toInt() == curr.value.number) {
                //bener
                curr.update { uiState->
                    uiState.copy(
                        score = curr.value.score + 1,
                        number = generateRandomNumber()
                    )
                }


                if (curr.value.score == 3) {
                    //win
                    curr.update { uiState->
                        uiState.copy(
                            endGame = true
                        )
                    }
                }

            } else {
                //salah
                curr.update { uiState->
                    uiState.copy(
                        chances = curr.value.chances + 1
                    )
                }

                if (curr.value.chances == 3) {
                    //lose
                    curr.update { uiState->
                        uiState.copy(
                            endGame = true
                        )
                    }
                }
            }
        }
    }

    fun restartGame() {
        _uiState.update { uiState->
            uiState.copy(
                number = generateRandomNumber(),
                chances = 0,
                score = 0,
                endGame = false
            )
        }
    }
}
private fun generateRandomNumber(): Int {
    return (1..10).random()
}