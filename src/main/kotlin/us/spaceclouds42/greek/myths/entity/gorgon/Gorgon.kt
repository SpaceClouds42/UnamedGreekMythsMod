package us.spaceclouds42.greek.myths.entity.gorgon

import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.world.World
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

abstract class Gorgon(entityType: EntityType<out HostileEntity>?, world: World?) : HostileEntity(entityType, world), IAnimatable {
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
        event.controller.setAnimation(AnimationBuilder().addAnimation("animation.gorgon.walk", true))
        return PlayState.CONTINUE
    }
}
