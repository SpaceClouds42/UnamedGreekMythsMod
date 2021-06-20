package us.spaceclouds42.greek.myths

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Common : ModInitializer {
    val LOGGER: Logger = LogManager.getLogger("UnamedGreekMythsMod")

    override fun onInitialize() {
        LOGGER.info("Initializing!")
    }
}
