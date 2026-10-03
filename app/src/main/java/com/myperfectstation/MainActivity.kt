package com.myperfectstation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StationGame()
        }
    }
}

@androidx.compose.runtime.Composable
fun StationGame() {
    var money by remember { mutableIntStateOf(250) }
    var passengers by remember { mutableIntStateOf(3) }
    var stationLevel by remember { mutableIntStateOf(1) }

    val upgradeCost = stationLevel * 150

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F0EA))
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "MY PERFECT",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF252525)
                )

                Text(
                    text = "STATION",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2D6A73)
                )
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "💰 $money",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StatCard(
                modifier = Modifier.weight(1f),
                title = "Rating",
                value = "⭐ 4.2"
            )

            StatCard(
                modifier = Modifier.weight(1f),
                title = "Passengers",
                value = "🧍 $passengers"
            )

            StatCard(
                modifier = Modifier.weight(1f),
                title = "Level",
                value = "Lv. $stationLevel"
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Central Platform",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF252525)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFD8D2C4))
                .border(
                    width = 2.dp,
                    color = Color(0xFFB5AD9C),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .align(Alignment.Center)
                    .background(Color(0xFF555555))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .align(Alignment.Center)
                    .background(Color(0xFFD8B84C))
            )

            Text(
                text = "🚉  Platform 1",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF252525)
            )

            Text(
                text = "🚆",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 48.sp
            )

            Text(
                text = "$passengers passengers waiting",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 18.dp),
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                if (money >= upgradeCost) {
                    money -= upgradeCost
                    stationLevel++
                    passengers += 2
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2D6A73)
            )
        ) {
            Text(
                text = "Upgrade Station  •  💰 $upgradeCost",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@androidx.compose.runtime.Composable
fun StatCard(
    modifier: Modifier,
    title: String,
    value: String
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 12.sp,
                color = Color(0xFF777777)
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF252525)
            )
        }
    }
}