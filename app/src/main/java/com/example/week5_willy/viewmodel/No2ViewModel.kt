package com.example.week5_willy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.week5_willy.model.No2Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class No2ViewModel : ViewModel() {


    private val _uiState = MutableStateFlow<List<No2Model>>(emptyList())
    val uiState: StateFlow<List<No2Model>> = _uiState.asStateFlow()

    fun addNo2Model(sks: String, score: String, name : String) {
        val sksValue = sks.toInt()
        val scoreValue = score.toDouble()

        val no2Model = No2Model(
            SKS = sksValue,
            nilai = scoreValue,
            name = name
        )
        _uiState.value = _uiState.value + no2Model
    }

    fun removeNo2Model(no2Model: No2Model) {
        _uiState.value = _uiState.value - no2Model
    }

    fun totalSKS(uiState: List<No2Model>): Int {
        return uiState.sumOf { it.SKS }
    }

    fun totalIPK(uiState: List<No2Model>): String {
        val totalSKS = uiState.sumOf { it.SKS }
        val totalNilai = uiState.sumOf { it.SKS * it.nilai }

        val ipk = if (totalSKS != 0) {
            String.format("%.2f", totalNilai / totalSKS)
        } else {
            "0.00"
        }

        return ipk
    }

    fun isValidScore(score: String): Boolean {
        return try {
            val scoreValue = score.toDouble()
            scoreValue > 0.0 && scoreValue <= 4.0
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun isValidSKS(sks: String): Boolean {
        val sksPattern = Regex("^[1-9]\\d*$")
        return sksPattern.matches(sks)
    }
}


