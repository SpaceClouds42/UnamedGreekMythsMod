package us.spaceclouds42.greek.myths.entity.gorgon.client

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier
import software.bernie.geckolib3.model.AnimatedGeoModel
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer
import us.spaceclouds42.greek.myths.Common.MOD_ID
import us.spaceclouds42.greek.myths.entity.gorgon.Gorgon

open class GorgonRenderer(
    context: EntityRendererFactory.Context?,
    modelProvider: AnimatedGeoModel<Gorgon>? = GorgonModel(),
) : GeoEntityRenderer<Gorgon>(context, modelProvider) {
    init {
        shadowRadius = 0.5F
    }
}