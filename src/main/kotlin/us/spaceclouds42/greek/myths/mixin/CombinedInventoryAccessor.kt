package us.spaceclouds42.greek.myths.mixin

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.util.collection.DefaultedList
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.gen.Accessor

@Mixin(PlayerInventory::class)
interface CombinedInventoryAccessor {
    @Accessor
    fun getCombinedInventory(): List<DefaultedList<ItemStack>>
}
