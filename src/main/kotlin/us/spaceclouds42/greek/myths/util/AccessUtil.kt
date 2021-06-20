package us.spaceclouds42.greek.myths.util

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import us.spaceclouds42.greek.myths.mixin.CombinedInventoryAccessor

@Suppress("CAST_NEVER_SUCCEEDS")
val PlayerInventory.combined: List<ItemStack>
    get() = (this as CombinedInventoryAccessor).getCombinedInventory().flatten()
