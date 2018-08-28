package net.tinzin.tesser.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.tinzin.tesser.entity.EntityTesserCrystal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTracker.class)
public class MixinEntityTracker
{

    @Inject(method = "track", at = @At("RETURN"))
    public void trackTesserEntity(Entity entityIn, CallbackInfo ci)
    {
        if (entityIn instanceof EntityTesserCrystal)
        {
            ((EntityTracker) (Object) this).track(entityIn, 160, 20, true);
        }
    }

}