package us.spaceclouds42.greek.myths.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.DirectionProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

class StatueUpperHalf(settings: Settings) : Block(settings) {
    init {
        defaultState = stateManager
            .defaultState
            .with(LEFT_ARM, true)
            .with(RIGHT_ARM, true)
            .with(HEAD, true)
            .with(TORSO, true)
            .with(FACING, Direction.NORTH)
    }

    override fun appendProperties(stateManagerBuilder: StateManager.Builder<Block, BlockState>) {
        stateManagerBuilder.add(LEFT_ARM)
        stateManagerBuilder.add(RIGHT_ARM)
        stateManagerBuilder.add(HEAD)
        stateManagerBuilder.add(TORSO)
        stateManagerBuilder.add(FACING)
    }

    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult {
        world?.setBlockState(pos, state?.with(HEAD, false))
        return ActionResult.SUCCESS
    }

    companion object Properties {
        val LEFT_ARM = BooleanProperty.of("left_arm")
        val RIGHT_ARM = BooleanProperty.of("right_arm")
        val HEAD = BooleanProperty.of("head")
        val TORSO = BooleanProperty.of("torso")
        val FACING = DirectionProperty.of("facing", Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
    }
}
