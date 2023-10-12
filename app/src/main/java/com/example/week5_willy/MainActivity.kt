package com.example.week5_willy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.week5_willy.ui.theme.Week5_WillyTheme
import com.example.week5_willy.ui.view.No2View
import com.example.week5_willy.viewmodel.No2ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week5_WillyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //No1View(viewModel = No1ViewModel())
                    No2View(viewModel = No2ViewModel())
                }
            }
        }
    }
}