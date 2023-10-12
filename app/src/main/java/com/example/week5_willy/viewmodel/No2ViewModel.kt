package com.example.week5_willy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.week5_willy.model.No2Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class No2ViewModel : ViewModel() {


    private val _uiState = MutableStateFlow<List<No2Model>>(emptyList())
    val uiState: StateFlow<List<No2Model>> = _uiState.asStateFlow()

    fun addNo2Model(no2Model: No2Model) {
        _uiState.value = _uiState.value + no2Model
    }

    fun removeNo2Model(no2Model: No2Model) {
        _uiState.value = _uiState.value - no2Model
    }

    fun totalSKS(): Int {
        val curr = _uiState.value

        val totalSKS = curr.sumOf { it.SKS }

        return totalSKS
    }

    fun totalIPK() : String {
        val curr = _uiState.value

        val totalSKS = curr.sumOf { it.SKS }
        val totalNilai = curr.sumOf { it.SKS * it.nilai }

        val ipk = if (totalSKS != 0) {
            String.format("%.2f", totalNilai / totalSKS)
        } else {
            "0.00"
        }

        return ipk
    }

    fun isValidScore(score: String): Boolean {
        try {
            val scoreValue = score.toDouble()
            return scoreValue > 0.0 && scoreValue <= 4.0
        } catch (e: NumberFormatException) {
            return false
        }
    }

    fun isValidSKS(sks: String): Boolean {
        val sksPattern = Regex("^[1-9]\\d*$")
        return sksPattern.matches(sks)
    }
}


