package us.spaceclouds42.greek.myths

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import us.spaceclouds42.greek.myths.entity.gorgon.client.GorgonRenderer
import us.spaceclouds42.greek.myths.entity.gorgon.client.MedusaRenderer

object Client : ClientModInitializer {
    override fun onInitializeClient() {
        registerEntities()
    }

    private fun registerEntities() {
        EntityRendererRegistry.INSTANCE.register(Common.EntityType.MEDUSA) { MedusaRenderer(it) }
        EntityRendererRegistry.INSTANCE.register(Common.EntityType.STHENO) { GorgonRenderer(it) }
        EntityRendererRegistry.INSTANCE.register(Common.EntityType.EURYALE) { GorgonRenderer(it) }
    }
}