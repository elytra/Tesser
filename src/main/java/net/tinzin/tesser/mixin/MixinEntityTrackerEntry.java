package net.tinzin.tesser.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketSpawnObject;

import net.tinzin.tesser.entity.EntityTesserCrystal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(EntityTrackerEntry.class)
public class MixinEntityTrackerEntry
{

    @Shadow @Final private Entity trackedEntity;

    @Inject(method = "createSpawnPacket", at = @At("INVOKE"), cancellable = true)
    private void createTesserSpawnPacket(CallbackInfoReturnable<Packet<?>> info)
    {
        if (this.trackedEntity instanceof EntityTesserCrystal)
        {
            EntityTesserCrystal crystal = (EntityTesserCrystal) this.trackedEntity;
            info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 735));
        }
    }

}