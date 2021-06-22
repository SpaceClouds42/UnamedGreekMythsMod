package us.spaceclouds42.greek.myths

import jdk.jshell.Snippet
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponents.*
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
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
        registerBlocks()
        registerItems()
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

    private fun registerBlocks() {
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "statue.upper"), Blocks.STATUE_UPPER_HALF)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "statue.lower"), Blocks.STATUE_LOWER_HALF)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "statue.medusa"), Blocks.STATUE_MEDUSA)
    }

    private fun registerItems() {
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "statue.medusa"), Items.STATUE_MEDUSA)
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

    object Blocks {
        val STATUE_UPPER_HALF = Block(
            FabricBlockSettings
                .of(Material.STONE)
                .strength(1.5F, 6.0F)
        )

        val STATUE_LOWER_HALF = Block(FabricBlockSettings.copyOf(STATUE_UPPER_HALF))

        val STATUE_MEDUSA = Block(FabricBlockSettings.copyOf(STATUE_UPPER_HALF))
    }

    object Items {
        private val statueSettings = FabricItemSettings().group(ItemGroup.DECORATIONS).maxCount(1)

        val STATUE_MEDUSA = BlockItem(Blocks.STATUE_MEDUSA, statueSettings)

        val STATUE_HEAD = Item(statueSettings)

        val STATUE_BODY = Item(statueSettings)

        val STATUE_ARM = Item(statueSettings)

        val STATUE_LEG = Item(statueSettings)

        val MEDUSA_HEAD = Item(FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1))

        val GORGON_FLESH = Item(FabricItemSettings().group(ItemGroup.FOOD).food(ROTTEN_FLESH))

        val PLAYER_FLESH = Item(FabricItemSettings().group(ItemGroup.FOOD).food(ROTTEN_FLESH))

        val GORGON_BLOOD = Item(FabricItemSettings().group(ItemGroup.BREWING).food(PUFFERFISH))

        val PLAYER_BLOOD = Item(FabricItemSettings().group(ItemGroup.BREWING).food(SPIDER_EYE))

        // TODO: Not sure about how to do the blades, should that just be an nbt tag on any sword item?
    }
}
