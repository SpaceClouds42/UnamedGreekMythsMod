package us.spaceclouds42.greek.myths.mixin

import net.minecraft.client.MinecraftClient
import net.minecraft.client.Mouse
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.ModifyVariable
import us.spaceclouds42.greek.myths.Common

@Mixin(Mouse::class)
class PetrifyMouseModifier {
    @ModifyVariable(
        method = ["updateMouse"],
        at = At(value = "CONSTANT", args = ["doubleValue=8.0"], shift = At.Shift.BY, by = -9),
        ordinal = 1
    )
    fun modifyMouseSensitivityFromPetrification(f: Double): Double {
        val level = MinecraftClient.getInstance().player?.getStatusEffect(Common.PETRIFICATION_EFFECT)?.amplifier
        return if (level != null) {
            f * ((-level / 10) + 1)
        } else {
            f
        }
    }
}
