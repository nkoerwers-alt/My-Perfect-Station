package com.myperfectstation

enum class UpgradeType {
    PLATFORM,
    TICKET_COUNTER,
    WAITING_AREA,
    CAFE,
    CLEANING,
    SECOND_TRACK
}

data class StationUpgrade(
    val type: UpgradeType,
    val name: String,
    val description: String,
    val cost: Int,
    val requiredLevel: Int
)

object UpgradeSystem {

    val upgrades = listOf(
        StationUpgrade(
            type = UpgradeType.PLATFORM,
            name = "Platform Extension",
            description = "Increase passenger capacity.",
            cost = 300,
            requiredLevel = 1
        ),
        StationUpgrade(
            type = UpgradeType.TICKET_COUNTER,
            name = "Ticket Counter",
            description = "Passengers can buy tickets faster.",
            cost = 500,
            requiredLevel = 2
        ),
        StationUpgrade(
            type = UpgradeType.WAITING_AREA,
            name = "Waiting Area",
            description = "Give passengers a comfortable place to wait.",
            cost = 750,
            requiredLevel = 3
        ),
        StationUpgrade(
            type = UpgradeType.CAFE,
            name = "Station Café",
            description = "Earn additional income from passengers.",
            cost = 1200,
            requiredLevel = 4
        ),
        StationUpgrade(
            type = UpgradeType.CLEANING,
            name = "Cleaning Service",
            description = "Keep the station clean and improve its rating.",
            cost = 1600,
            requiredLevel = 5
        ),
        StationUpgrade(
            type = UpgradeType.SECOND_TRACK,
            name = "Second Track",
            description = "Handle more trains at the same time.",
            cost = 2500,
            requiredLevel = 6
        )
    )

    fun availableUpgrades(stationLevel: Int): List<StationUpgrade> {
        return upgrades.filter {
            it.requiredLevel <= stationLevel
        }
    }
}