package us.spaceclouds42.greek.myths

import net.fabricmc.api.ModInitializer

object Common : ModInitializer {
    val CONFIG = TablistCloakConfig()

    override fun onInitialize() {
        println("[SpaceServe] Cloaking tablists")
        CONFIG.load()
    }
}
