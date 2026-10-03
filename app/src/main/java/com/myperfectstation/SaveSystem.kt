package com.myperfectstation

import android.content.Context
import org.json.JSONObject

class SaveSystem(context: Context) {

    private val preferences =
        context.getSharedPreferences(
            "my_perfect_station_save",
            Context.MODE_PRIVATE
        )

    fun save(gameState: GameState) {
        val json = JSONObject().apply {
            put("money", gameState.money)
            put("passengers", gameState.passengers)
            put("stationLevel", gameState.stationLevel)
            put("trainsServed", gameState.trainsServed)
            put("rating", gameState.rating)
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

    fun reset() {
        preferences.edit()
            .remove("game_state")
            .apply()
    }
}