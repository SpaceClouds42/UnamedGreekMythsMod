package us.spaceclouds42.greek.myths.mixin

import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import us.spaceclouds42.greek.myths.Common

@Mixin(ItemEntity::class)
class PreventItemPickup {
    @Inject(method = ["onPlayerCollision"], at = [At("HEAD")], cancellable = true)
    fun preventPickupIfPetrified(player: PlayerEntity, ci: CallbackInfo) {
        val effect = player.getStatusEffect(Common.PETRIFICATION_EFFECT)
        if (effect != null) {
            ci.cancel()
        }
    }
}
