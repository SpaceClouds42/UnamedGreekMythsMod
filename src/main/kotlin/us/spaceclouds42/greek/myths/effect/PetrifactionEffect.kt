package us.spaceclouds42.greek.myths.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectType

class PetrifactionEffect : StatusEffect(StatusEffectType.HARMFUL, 0x4f3d25) {
    val petrificationDamage = PetrifactionDamage()

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        // TODO: Testing effect, remove/adapt later
        entity.damage(petrificationDamage, 1.0f)
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }
}
