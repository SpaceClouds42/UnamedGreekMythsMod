package us.spaceclouds42.greek.myths

import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import us.spaceclouds42.greek.myths.effect.PetrifactionEffect

object Common : ModInitializer {
    val LOGGER: Logger = LogManager.getLogger("UnamedGreekMythsMod")
    val MOD_ID = "greekmyths"

    override fun onInitialize() {
        LOGGER.info("Initializing!")
        registerEffects()
    }

    fun registerEffects() {
        Registry.register(
            Registry.STATUS_EFFECT,
            Identifier(MOD_ID, "petrifaction"),
            PetrifactionEffect()
        )
    }
}
