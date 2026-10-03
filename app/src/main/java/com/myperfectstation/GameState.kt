package com.myperfectstation

data class GameState(
    val money: Int = 250,
    val passengers: Int = 3,
    val stationLevel: Int = 1,
    val trainsServed: Int = 0,
    val rating: Double = 4.2
) {
    val passengerCapacity: Int
        get() = stationLevel * 5

    val ticketIncomePerPassenger: Int
        get() = 25 + stationLevel * 5

    val upgradeCost: Int
        get() = stationLevel * 150
}