package us.spaceclouds42.greek.myths

import net.fabricmc.api.ModInitializer
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import us.spaceclouds42.greek.myths.effect.PetrificationEffect

object Common : ModInitializer {
    val LOGGER: Logger = LogManager.getLogger("UnamedGreekMythsMod")
    const val MOD_ID = "greekmyths"

    override fun onInitialize() {
        LOGGER.info("Initializing!")
        registerEffects()
    }

    fun registerEffects() {
        Registry.register(
            Registry.STATUS_EFFECT,
            Identifier(MOD_ID, "petrification"),
            PetrificationEffect()
                .addAttributeModifier(
                    EntityAttributes.GENERIC_MOVEMENT_SPEED,
                    "cf5d31aa-4c84-4f1e-b544-d99c09165d3d",
                    -0.1,
                    EntityAttributeModifier.Operation.MULTIPLY_TOTAL
                )
        )
    }
}
