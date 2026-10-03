package com.myperfectstation

data class UpgradeState(
    val purchasedUpgrades: Set<UpgradeType> = emptySet()
) {
    fun hasUpgrade(type: UpgradeType): Boolean {
        return type in purchasedUpgrades
    }

    fun purchase(type: UpgradeType): UpgradeState {
        return copy(
            purchasedUpgrades = purchasedUpgrades + type
        )
    }
}