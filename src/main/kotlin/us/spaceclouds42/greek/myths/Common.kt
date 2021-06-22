package us.spaceclouds42.greek.myths

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import software.bernie.geckolib3.GeckoLib
import us.spaceclouds42.greek.myths.effect.PetrifactionEffect
import us.spaceclouds42.greek.myths.entity.gorgon.Euryale
import us.spaceclouds42.greek.myths.entity.gorgon.Medusa
import us.spaceclouds42.greek.myths.entity.gorgon.Stheno

object Common : ModInitializer {
    val LOGGER: Logger = LogManager.getLogger("UnamedGreekMythsMod")
    const val MOD_ID = "greekmyths"

    override fun onInitialize() {
        LOGGER.info("Initializing!")
        registerAll()
        GeckoLib.initialize()
    }

    /**
     * Registers everything that needs registration
     */
    private fun registerAll() {
        registerEffects()
        registerEntities()
    }

    private fun registerEffects() {
        Registry.register(
            Registry.STATUS_EFFECT,
            Identifier(MOD_ID, "petrifaction"),
            PetrifactionEffect(),
        )
    }

    private fun registerEntities() {
        val gorgonAttributes = HostileEntity
            .createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0)

        FabricDefaultAttributeRegistry.register(EntityType.MEDUSA, gorgonAttributes)
        FabricDefaultAttributeRegistry.register(EntityType.STHENO, gorgonAttributes)
        FabricDefaultAttributeRegistry.register(EntityType.EURYALE, gorgonAttributes)
    }

    object EntityType {
        private val gorgonDimensions = EntityDimensions.fixed(0.65F, 2.0625F)

        val MEDUSA = Registry.register(
            Registry.ENTITY_TYPE,
            Identifier(MOD_ID, "gorgon.medusa"),
            FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, ::Medusa)
                .dimensions(gorgonDimensions)
                .build(),
        )

        val STHENO = Registry.register(
            Registry.ENTITY_TYPE,
            Identifier(MOD_ID, "gorgon.stheno"),
            FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, ::Stheno)
                .dimensions(gorgonDimensions)
                .build(),
        )

        val EURYALE = Registry.register(
            Registry.ENTITY_TYPE,
            Identifier(MOD_ID, "gorgon.euryale"),
            FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, ::Euryale)
                .dimensions(gorgonDimensions)
                .build(),
        )
    }
}
