package us.spaceclouds42.greek.myths.entity.gorgon.client

import net.minecraft.util.Identifier
import us.spaceclouds42.greek.myths.Common
import us.spaceclouds42.greek.myths.entity.gorgon.Gorgon

class MedusaModel : GorgonModel() {
    override fun getTextureLocation(entity: Gorgon?): Identifier = Identifier("${Common.MOD_ID}:textures/entity/gorgon/medusa.png")
}