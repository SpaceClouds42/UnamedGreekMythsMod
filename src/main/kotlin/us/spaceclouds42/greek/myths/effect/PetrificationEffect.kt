package us.spaceclouds42.greek.myths.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.property.DirectionProperty
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import us.spaceclouds42.greek.myths.Common
import us.spaceclouds42.greek.myths.block.StatueUpperHalf
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
                val world = entity.world as ServerWorld
                val direction = entityToFacing(entity)
                world.setBlockState(
                    entity.blockPos,
                    Common.Blocks.STATUE_LOWER_HALF.defaultState.also {
                        // TODO Set state to match player
                    }
                )
                world.setBlockState(
                    entity.blockPos.add(0, 1, 0),
                    Common.Blocks.STATUE_UPPER_HALF.defaultState.also {
                        it.with(StatueUpperHalf.FACING, direction)
                    }
                )
            }
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }

    private fun entityToFacing(entity: LivingEntity): Direction {
        return when ((round(entity.yaw / 90) * 90) % 180) {
            -90f -> Direction.EAST
            0f -> Direction.SOUTH
            90f -> Direction.WEST
            180f, -180f -> Direction.NORTH
            else -> Direction.NORTH
        }
    }
}
