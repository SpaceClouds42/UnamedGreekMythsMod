package us.spaceclouds42.greek.myths.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.Vec3d
import us.spaceclouds42.greek.myths.util.combined
import kotlin.math.round
import kotlin.random.Random

class PetrificationEffect : StatusEffect(StatusEffectType.HARMFUL, 0x4f3d25) {
    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        if (entity is PlayerEntity && !entity.world.isClient && !entity.isCreative && !entity.isSpectator && !entity.isInvulnerable) {
            val combined = entity.inventory.combined
            repeat(4) {
                entity.dropItem(entity.inventory.removeStack(Random.nextInt(0, combined.size)), true, false)
            }

            if (amplifier >= 12) {
                val closestMultiple = round(entity.yaw / 90) * 90

                val center = Vec3d.ofCenter(entity.blockPos)

                entity.prevBodyYaw = closestMultiple
                entity.bodyYaw = closestMultiple
                entity.updatePositionAndAngles(center.x, entity.y, center.z, closestMultiple, 0f)
                if (entity is ServerPlayerEntity) {
                    entity.networkHandler.sendPacket(
                        PlayerPositionLookS2CPacket(
                            center.x,
                            entity.y,
                            center.z,
                            closestMultiple,
                            0f,
                            setOf(),
                            0,
                            true
                        )
                    )
                }
            }

            if (amplifier >= 13) {
                //TODO("Make statue")
            }
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }
}
