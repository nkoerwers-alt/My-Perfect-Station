package com.myperfectstation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyPerfectStation()
        }
    }
}

@Composable
fun MyPerfectStation() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F0EA)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MY PERFECT",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF252525)
            )

            Text(
                text = "STATION",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D6A73),
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                text = "Your station. Your way.",
                fontSize = 16.sp,
                color = Color(0xFF666666),
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}