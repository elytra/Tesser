package net.tinzin.tesser.mixin;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.tinzin.tesser.entity.EntityTesserCrystal;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient
{

    @Shadow private WorldClient world;

    @Inject(method = "handleSpawnObject", at = @At("RETURN"))
    public void handleAetherSpawnObject(SPacketSpawnObject packetIn, CallbackInfo ci)
    {
        double d0 = packetIn.getX();
        double d1 = packetIn.getY();
        double d2 = packetIn.getZ();
        Entity crystalEntity = null;

        if (packetIn.getType() == 735)
        {
            crystalEntity = new EntityTesserCrystal(this.world, d0, d1, d2);
            packetIn.setData(0);
        }

        if (crystalEntity != null)
        {
            EntityTracker.updateServerPosition(crystalEntity, d0, d1, d2);
            crystalEntity.rotationPitch = (float)(packetIn.getPitch() * 360) / 256.0F;
            crystalEntity.rotationYaw = (float)(packetIn.getYaw() * 360) / 256.0F;
            Entity[] centityy = crystalEntity.getParts();

            if (centityy != null)
            {
                int i = packetIn.getEntityID() - crystalEntity.getEntityId();

                for (Entity entity2 : centityy)
                {
                    entity2.setEntityId(entity2.getEntityId() + i);
                }
            }

            crystalEntity.setEntityId(packetIn.getEntityID());
            crystalEntity.setUniqueId(packetIn.getUniqueId());
            this.world.addEntityToWorld(packetIn.getEntityID(), crystalEntity);
        }
    }

}