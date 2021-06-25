package us.spaceclouds42.greek.myths.entity.gorgon.client

import net.minecraft.util.Identifier
import software.bernie.geckolib3.model.AnimatedGeoModel
import us.spaceclouds42.greek.myths.Common.MOD_ID
import us.spaceclouds42.greek.myths.entity.gorgon.Gorgon

open class GorgonModel : AnimatedGeoModel<Gorgon>() {
    override fun getModelLocation(entity: Gorgon?): Identifier = Identifier("$MOD_ID:geo/gorgon.geo.json")

    override fun getTextureLocation(entity: Gorgon?): Identifier = Identifier("$MOD_ID:textures/entity/gorgon/default.png")

    override fun getAnimationFileLocation(entity: Gorgon?): Identifier = Identifier("$MOD_ID:animations/gorgon.animation.json")
}