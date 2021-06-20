package us.spaceclouds42.greek.myths.effect

import net.minecraft.entity.damage.DamageSource

class PetrificationDamage : DamageSource("petrification") {
    override fun bypassesArmor() = true
    override fun isUnblockable() = true
    override fun isMagic() = true
}
