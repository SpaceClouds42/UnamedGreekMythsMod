package us.spaceclouds42.greek.myths.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import us.spaceclouds42.greek.myths.mixin.CombinedInventoryAccessor
import us.spaceclouds42.greek.myths.util.combined
import kotlin.random.Random

class PetrificationEffect : StatusEffect(StatusEffectType.HARMFUL, 0x4f3d25) {
    val petrificationDamage = PetrificationDamage()

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        if (entity.hurtTime <= 0 && !entity.isInvulnerable) {
            entity.damage(petrificationDamage, 1.0f)

            repeat(4) {
                if (entity is PlayerEntity && !entity.world.isClient) {
                    val combined = entity.inventory.combined
                    entity.dropItem(entity.inventory.removeStack(Random.nextInt(0, combined.size)), true, false)
                }
            }
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }
}
