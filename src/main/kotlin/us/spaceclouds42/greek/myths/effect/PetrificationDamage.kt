package us.spaceclouds42.greek.myths.effect

import net.minecraft.entity.damage.DamageSource

class PetrificationDamage : DamageSource("petrification") {
    override fun bypassesArmor() = true
}
