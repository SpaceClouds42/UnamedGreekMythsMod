package us.spaceclouds42.greek.myths.entity.golem

import net.minecraft.entity.EntityType
import net.minecraft.entity.passive.GolemEntity
import net.minecraft.world.World
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class StoneGolem(entityType: EntityType<out GolemEntity>?, world: World?) : GolemEntity(entityType, world), IAnimatable {
    private lateinit var animationFactory: AnimationFactory

    init {
        ignoreCameraFrustum = true
    }

    override fun getFactory(): AnimationFactory {
        return if (this::animationFactory.isInitialized) {
            animationFactory
        } else {
            animationFactory = AnimationFactory(this)
            animationFactory
        }
    }

    override fun registerControllers(data: AnimationData?) {
        data?.addAnimationController(AnimationController(this, "controller", 0F, this::predicate))
    }

    private fun <E: IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
        event.controller.setAnimation(AnimationBuilder().addAnimation("animation.golem.walk", true))
        return if (movementSpeed != 0F) { PlayState.CONTINUE } else { PlayState.STOP }
    }
}