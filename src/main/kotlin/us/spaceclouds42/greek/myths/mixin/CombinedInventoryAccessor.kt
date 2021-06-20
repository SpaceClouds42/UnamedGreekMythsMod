package us.spaceclouds42.greek.myths.mixin

import org.spongepowered.asm.mixin.Mixin
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.util.collection.DefaultedList
import net.minecraft.item.ItemStack
import org.spongepowered.asm.mixin.gen.Accessor
import java.lang.AbstractMethodError

@Mixin(PlayerInventory::class)
interface CombinedInventoryAccessor {
    @Accessor
    fun getCombinedInventory(): List<DefaultedList<ItemStack>>
}
