package com.myperfectstation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StationMap(
    passengers: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFB9C99A))
            .border(
                width = 2.dp,
                color = Color(0xFF8F9E76),
                shape = RoundedCornerShape(20.dp)
            )
    ) {

        // Station building
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 18.dp)
                .fillMaxWidth(0.72f)
                .height(90.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFE6D8BC))
                .border(
                    width = 2.dp,
                    color = Color(0xFFB69E78),
                    shape = RoundedCornerShape(14.dp)
                )
        ) {
            Text(
                text = "CENTRAL STATION",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A3A28)
            )
        }

        // Waiting area
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 10.dp)
                .fillMaxWidth(0.55f)
                .height(55.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFEFE8D8))
        ) {
            Text(
                text = "🪑  Waiting Area",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 14.sp,
                color = Color(0xFF514A3D)
            )
        }

        // Track
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 62.dp)
                .fillMaxWidth()
                .height(74.dp)
                .background(Color(0xFF555555))
        )

        // Platform
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 136.dp)
                .fillMaxWidth()
                .height(34.dp)
                .background(Color(0xFFD5C8AE))
        ) {
            Text(
                text = "Platform 1",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 14.dp),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A4030)
            )
        }

        // Train
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 74.dp)
                .fillMaxWidth(0.55f)
                .height(52.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF2D6A73))
        ) {
            Text(
                text = "🚆",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 30.sp
            )
        }

        // Passengers
        Text(
            text = "🧍 ".repeat(passengers.coerceAtMost(8)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 148.dp),
            fontSize = 20.sp
        )
    }
}