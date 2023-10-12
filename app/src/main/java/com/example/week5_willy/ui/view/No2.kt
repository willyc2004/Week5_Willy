package com.example.week5_willy.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week5_willy.model.No2Model
import com.example.week5_willy.viewmodel.No2ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun No2View(
    viewModel: No2ViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    var sks by rememberSaveable { mutableStateOf("") }
    var score by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Text(
            text = "Courses",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Total SKS: ${viewModel.totalSKS()}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "IPK: ${viewModel.totalIPK()}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            OutlinedTextField(
                value = sks,
                onValueChange = {
                    sks = it
                },
                label = {
                    Text("SKS", modifier = Modifier.padding(start = 5.dp))
                },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Black
                )
            )


            OutlinedTextField(
                value = score,
                onValueChange = {
                    score = it
                },
                label = {
                    Text("Score", modifier = Modifier.padding(start = 5.dp))
                },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Black
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Name", modifier = Modifier.padding(start = 5.dp))
                },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Black
                )
            )

            Button(
                onClick = {
                    val sksValue = sks.toIntOrNull()
                    val scoreValue = score.toDoubleOrNull()
                    val nameValue = name

                    if (sksValue != null && scoreValue != null && nameValue.isNotBlank()) {
                        val newNo2Model = No2Model(
                            SKS = sksValue,
                            nilai = scoreValue,
                            name = nameValue
                        )
                        viewModel.addNo2Model(newNo2Model)

                        sks = ""
                        score = ""
                        name = ""
                    }
                },
                modifier = Modifier
                    .height(65.dp)
                    .width(100.dp)
                    .padding(top = 6.dp)
                    .background(Color.Transparent),
                shape = RoundedCornerShape(20.dp),
                enabled = sks.isNotBlank() && score.isNotBlank() && name.isNotBlank()
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.background(Color.Transparent),
                )
            }
        }
        LazyColumn() {
            items(uiState) { no2Model ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {
                            Text(
                                text = "Course Name: ${no2Model.name}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "SKS: ${no2Model.SKS}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = "Score: ${no2Model.nilai}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }

                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(10.dp)
                                .clickable {
                                    viewModel.removeNo2Model(no2Model)
                                    sks = " "
                                    sks = ""
                                },
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun No2Preview() {
    No2View(viewModel = No2ViewModel())
}