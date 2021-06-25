package us.spaceclouds42.greek.myths.entity.golem.client

import net.minecraft.util.Identifier
import software.bernie.geckolib3.model.AnimatedGeoModel
import us.spaceclouds42.greek.myths.Common
import us.spaceclouds42.greek.myths.entity.golem.StoneGolem

class StoneGolemModel : AnimatedGeoModel<StoneGolem>() {
    override fun getModelLocation(entity: StoneGolem?): Identifier = Identifier("${Common.MOD_ID}:geo/stone_golem.geo.json")

    override fun getTextureLocation(entity: StoneGolem?): Identifier = Identifier("${Common.MOD_ID}:textures/entity/stone_golem.png")

    override fun getAnimationFileLocation(entity: StoneGolem?): Identifier = Identifier("${Common.MOD_ID}:animations/stone_golem.animation.json")
}