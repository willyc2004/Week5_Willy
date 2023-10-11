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
    fun calculateTotalSKSAndIPK(): Pair<Int, String> {
        val models = _uiState.value

        val totalSKS = models.sumOf { it.SKS }
        val totalNilai = models.sumOf { it.SKS * it.nilai }

        val ipk = if (totalSKS != 0) {
            String.format("%.2f", totalNilai / totalSKS)
        } else {
            "0.00"
        }

        return totalSKS to ipk
    }




}


