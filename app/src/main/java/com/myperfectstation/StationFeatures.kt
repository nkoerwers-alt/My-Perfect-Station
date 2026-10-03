package com.myperfectstation

object StationFeatures {

    fun passengerCapacity(
        stationLevel: Int,
        upgradeState: UpgradeState
    ): Int {
        var capacity = stationLevel * 5

        if (upgradeState.hasUpgrade(UpgradeType.PLATFORM)) {
            capacity += 5
        }

        if (upgradeState.hasUpgrade(UpgradeType.WAITING_AREA)) {
            capacity += 5
        }

        return capacity
    }

    fun ticketIncomePerPassenger(
        stationLevel: Int,
        upgradeState: UpgradeState
    ): Int {
        var income = 25 + stationLevel * 5

        if (upgradeState.hasUpgrade(UpgradeType.TICKET_COUNTER)) {
            income += 10
        }

        if (upgradeState.hasUpgrade(UpgradeType.CAFE)) {
            income += 15
        }

        return income
    }

    fun rating(
        baseRating: Double,
        upgradeState: UpgradeState
    ): Double {
        var rating = baseRating

        if (upgradeState.hasUpgrade(UpgradeType.WAITING_AREA)) {
            rating += 0.2
        }

        if (upgradeState.hasUpgrade(UpgradeType.CLEANING)) {
            rating += 0.4
        }

        return rating.coerceAtMost(5.0)
    }
}