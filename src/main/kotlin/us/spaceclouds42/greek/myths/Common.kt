package us.spaceclouds42.greek.myths

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Material
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.passive.IronGolemEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponents.*
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import software.bernie.geckolib3.GeckoLib
import us.spaceclouds42.greek.myths.block.MedusaStatue
import us.spaceclouds42.greek.myths.block.StatueLowerHalf
import us.spaceclouds42.greek.myths.block.StatueTable
import us.spaceclouds42.greek.myths.block.StatueUpperHalf
import us.spaceclouds42.greek.myths.effect.PetrificationEffect
import us.spaceclouds42.greek.myths.entity.golem.StoneGolem
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

    private fun registerEntities() {
        val gorgonAttributes = HostileEntity
            .createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0)

        FabricDefaultAttributeRegistry.register(EntityType.MEDUSA, gorgonAttributes)
        FabricDefaultAttributeRegistry.register(EntityType.STHENO, gorgonAttributes)
        FabricDefaultAttributeRegistry.register(EntityType.EURYALE, gorgonAttributes)

        FabricDefaultAttributeRegistry.register(
            EntityType.STONE_GOLEM,
            IronGolemEntity
                .createIronGolemAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 9.0)
        )
    }

    private fun registerBlocks() {
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "statue_upper"), Blocks.STATUE_UPPER_HALF)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "statue_lower"), Blocks.STATUE_LOWER_HALF)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "medusa_statue"), Blocks.STATUE_MEDUSA)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "statue_table"), Blocks.STATUE_TABLE)
    }

    private fun registerItems() {
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "medusa_statue"), Items.STATUE_MEDUSA)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "statue_head"), Items.STATUE_HEAD)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "statue_body"), Items.STATUE_BODY)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "statue_arm"), Items.STATUE_ARM)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "statue_leg"), Items.STATUE_LEG)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "medusa_head"), Items.MEDUSA_HEAD)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "gorgon_flesh"), Items.GORGON_FLESH)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "player_flesh"), Items.PLAYER_FLESH)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "gorgon_blood"), Items.GORGON_BLOOD)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "player_blood"), Items.PLAYER_BLOOD)

        FabricItemGroupBuilder
            .create(Identifier(MOD_ID, "main"))
            .icon { ItemStack(Items.MEDUSA_HEAD) }
            .appendItems {
                it.addAll(
                    listOf(
                        ItemStack(Items.MEDUSA_HEAD),
                        ItemStack(Items.STATUE_MEDUSA),
                        ItemStack(Items.STATUE_HEAD),
                        ItemStack(Items.STATUE_ARM),
                        ItemStack(Items.STATUE_BODY),
                        ItemStack(Items.STATUE_LEG),
                        ItemStack(Items.GORGON_FLESH),
                        ItemStack(Items.GORGON_BLOOD),
                        ItemStack(Items.PLAYER_FLESH),
                        ItemStack(Items.PLAYER_BLOOD),
                    )
                )
            }
            .build()
    }

    object EntityType {
        private val gorgonDimensions = EntityDimensions.fixed(0.65F, 2.0625F)

        val MEDUSA = Registry.register(
            Registry.ENTITY_TYPE,
            Identifier(MOD_ID, "medusa"),
            FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, ::Medusa)
                .dimensions(gorgonDimensions)
                .build(),
        )

        val STHENO = Registry.register(
            Registry.ENTITY_TYPE,
            Identifier(MOD_ID, "stheno"),
            FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, ::Stheno)
                .dimensions(gorgonDimensions)
                .build(),
        )

        val EURYALE = Registry.register(
            Registry.ENTITY_TYPE,
            Identifier(MOD_ID, "euryale"),
            FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, ::Euryale)
                .dimensions(gorgonDimensions)
                .build(),
        )

        val STONE_GOLEM = Registry.register(
            Registry.ENTITY_TYPE,
            Identifier(MOD_ID, "stone_golem"),
            FabricEntityTypeBuilder
                .create(SpawnGroup.MISC, ::StoneGolem)
                .dimensions(EntityDimensions.fixed(0.6F, 1.8F))
                .build(),
        )
    }

    object Blocks {
        val STATUE_UPPER_HALF = StatueUpperHalf(
            FabricBlockSettings
                .of(Material.STONE)
                .strength(1.5F, 6.0F)
        )

        val STATUE_LOWER_HALF = StatueLowerHalf(FabricBlockSettings.copyOf(STATUE_UPPER_HALF))

        val STATUE_MEDUSA = MedusaStatue(FabricBlockSettings.copyOf(STATUE_UPPER_HALF))

        val STATUE_TABLE = StatueTable(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.SMITHING_TABLE))
    }

    object Items {
        private val statueSettings = FabricItemSettings().group(ItemGroup.DECORATIONS).maxCount(1)

        val STATUE_MEDUSA = BlockItem(Blocks.STATUE_MEDUSA, statueSettings)

        val STATUE_HEAD = Item(statueSettings)

        val STATUE_BODY = Item(statueSettings)

        val STATUE_ARM = Item(statueSettings)

        val STATUE_LEG = Item(statueSettings)

        val MEDUSA_HEAD = Item(
            FabricItemSettings()
                .group(ItemGroup.COMBAT)
                .maxCount(1)
                .equipmentSlot { EquipmentSlot.HEAD }
        )

        val GORGON_FLESH = Item(FabricItemSettings().group(ItemGroup.FOOD).food(ROTTEN_FLESH))

        val PLAYER_FLESH = Item(FabricItemSettings().group(ItemGroup.FOOD).food(ROTTEN_FLESH))

        val GORGON_BLOOD = Item(FabricItemSettings().group(ItemGroup.BREWING).food(PUFFERFISH))

        val PLAYER_BLOOD = Item(FabricItemSettings().group(ItemGroup.BREWING).food(SPIDER_EYE))

        // TODO: Not sure about how to do the blades, should that just be an nbt tag on any sword item?
    }
}
