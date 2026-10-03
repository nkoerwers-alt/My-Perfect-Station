package com.myperfectstation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StationGame()
        }
    }
}

@Composable
fun StationGame() {
    val context = androidx.compose.ui.platform.LocalContext.current

    val saveSystem = remember {
        SaveSystem(context.applicationContext)
    }

    var gameState by remember {
        mutableStateOf(saveSystem.load())
    }

    LaunchedEffect(gameState) {
        saveSystem.save(gameState)
    }

    LaunchedEffect(gameState.stationLevel) {
        while (true) {
            delay(3000)

            if (gameState.passengers < gameState.passengerCapacity) {
                gameState = gameState.copy(
                    passengers = gameState.passengers + 1
                )
            }
        }
    }

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
                    text = "💰 ${gameState.money}",
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
                value = "⭐ ${gameState.rating}"
            )

            StatCard(
                modifier = Modifier.weight(1f),
                title = "Waiting",
                value = "${gameState.passengers}/${gameState.passengerCapacity}"
            )

            StatCard(
                modifier = Modifier.weight(1f),
                title = "Trains",
                value = "${gameState.trainsServed}"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            StationMap(
                passengers = gameState.passengers
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (gameState.passengers > 0) {
                    val earned =
                        gameState.passengers *
                            gameState.ticketIncomePerPassenger

                    gameState = gameState.copy(
                        money = gameState.money + earned,
                        passengers = 0,
                        trainsServed = gameState.trainsServed + 1
                    )
                }
            },
            enabled = gameState.passengers > 0,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2D6A73)
            )
        ) {
            Text(
                text = if (gameState.passengers > 0) {
                    "🚆 Depart Train  •  +${
                        gameState.passengers *
                            gameState.ticketIncomePerPassenger
                    }"
                } else {
                    "No passengers waiting"
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (gameState.money >= gameState.upgradeCost) {
                    gameState = gameState.copy(
                        money = gameState.money - gameState.upgradeCost,
                        stationLevel = gameState.stationLevel + 1,
                        passengers = (
                            gameState.passengers + 2
                        ).coerceAtMost(
                            (gameState.stationLevel + 1) * 5
                        )
                    )
                }
            },
            enabled = gameState.money >= gameState.upgradeCost,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8A6D3B)
            )
        ) {
            Text(
                text = "Upgrade Station  •  💰 ${gameState.upgradeCost}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
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