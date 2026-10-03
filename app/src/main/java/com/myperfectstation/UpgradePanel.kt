package com.myperfectstation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UpgradePanel(
    stationLevel: Int,
    money: Int,
    upgradeState: UpgradeState,
    onPurchase: (StationUpgrade) -> Unit
) {
    val upgrades = UpgradeSystem.availableUpgrades(stationLevel)

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(
                text = "Station Upgrades",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF252525)
            )

            Text(
                text = "Improve your station and unlock new features.",
                fontSize = 13.sp,
                color = Color(0xFF777777),
                modifier = Modifier.padding(top = 3.dp, bottom = 10.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(upgrades) { upgrade ->

                    val purchased =
                        upgradeState.hasUpgrade(upgrade.type)

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF5F3EE)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = upgrade.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )

                                Text(
                                    text = upgrade.description,
                                    fontSize = 12.sp,
                                    color = Color(0xFF666666)
                                )
                            }

                            Button(
                                onClick = {
                                    onPurchase(upgrade)
                                },
                                enabled = !purchased &&
                                    money >= upgrade.cost
                            ) {
                                Text(
                                    text = when {
                                        purchased -> "Owned"
                                        else -> "💰 ${upgrade.cost}"
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}