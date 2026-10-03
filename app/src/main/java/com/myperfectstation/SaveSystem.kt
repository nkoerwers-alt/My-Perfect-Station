package com.myperfectstation

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class SaveSystem(context: Context) {

    private val preferences =
        context.getSharedPreferences(
            "my_perfect_station_save",
            Context.MODE_PRIVATE
        )

    fun save(
        gameState: GameState,
        upgradeState: UpgradeState = UpgradeState()
    ) {
        val upgrades = JSONArray()

        upgradeState.purchasedUpgrades.forEach {
            upgrades.put(it.name)
        }

        val json = JSONObject().apply {
            put("money", gameState.money)
            put("passengers", gameState.passengers)
            put("stationLevel", gameState.stationLevel)
            put("trainsServed", gameState.trainsServed)
            put("rating", gameState.rating)
            put("upgrades", upgrades)
        }

        preferences.edit()
            .putString("game_state", json.toString())
            .apply()
    }

    fun load(): GameState {
        val saved = preferences.getString("game_state", null)
            ?: return GameState()

        return try {
            val json = JSONObject(saved)

            GameState(
                money = json.optInt("money", 250),
                passengers = json.optInt("passengers", 3),
                stationLevel = json.optInt("stationLevel", 1),
                trainsServed = json.optInt("trainsServed", 0),
                rating = json.optDouble("rating", 4.2)
            )
        } catch (_: Exception) {
            GameState()
        }
    }

    fun loadUpgrades(): UpgradeState {
        val saved = preferences.getString("game_state", null)
            ?: return UpgradeState()

        return try {
            val json = JSONObject(saved)
            val upgrades = json.optJSONArray("upgrades")
                ?: return UpgradeState()

            val purchased = mutableSetOf<UpgradeType>()

            for (i in 0 until upgrades.length()) {
                val name = upgrades.optString(i)

                UpgradeSystem.upgrades
                    .firstOrNull { it.name == name }
                    ?.let {
                        purchased.add(it.type)
                    }
            }

            UpgradeState(purchased)
        } catch (_: Exception) {
            UpgradeState()
        }
    }

    fun reset() {
        preferences.edit()
            .remove("game_state")
            .apply()
    }
}