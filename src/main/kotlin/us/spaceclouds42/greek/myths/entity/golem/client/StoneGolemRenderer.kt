package us.spaceclouds42.greek.myths.entity.golem.client

import net.minecraft.client.render.entity.EntityRendererFactory
import software.bernie.geckolib3.model.AnimatedGeoModel
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer
import us.spaceclouds42.greek.myths.entity.golem.StoneGolem

open class StoneGolemRenderer(
    context: EntityRendererFactory.Context?,
    modelProvider: AnimatedGeoModel<StoneGolem>? = StoneGolemModel(),
) : GeoEntityRenderer<StoneGolem>(context, modelProvider) {
    init {
        shadowRadius = 0.5F
    }
}