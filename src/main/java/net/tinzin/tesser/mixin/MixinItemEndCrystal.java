package net.tinzin.tesser.mixin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemUseContext;
import net.minecraft.network.play.server.SPacketSpawnGlobalEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tinzin.tesser.entity.EntityTesserCrystal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(ItemEndCrystal.class)
public abstract class MixinItemEndCrystal {

    @Inject(method = "onItemUse",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;"),
            cancellable = true)
    public void onItemUse(ItemUseContext ctx, CallbackInfoReturnable<EnumActionResult> cir) {
        if (ctx.getWorld().getBlockState(ctx.getPos()).getBlock() == Blocks.BEACON) {
            if (placeTesserCrystal(ctx.getWorld(), ctx.getPos())) {
                ctx.getItem().shrink(1);
                cir.setReturnValue(EnumActionResult.SUCCESS);
            }
        }
    }

    private static boolean placeTesserCrystal(World world, BlockPos pos) {
        BlockPos placePos = pos.up();
        List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(placePos.getX(), placePos.getY(), placePos.getZ(), placePos.getX() + 1.0D, placePos.getY() + 2.0D, placePos.getZ() + 1.0D));
        if (entities.isEmpty() && !world.isRemote()) {
            EntityTesserCrystal crystal = new EntityTesserCrystal(world, placePos.getX() + 0.5D, placePos.getY(), placePos.getZ() + 0.5D);
            world.spawnEntity(crystal);
            return true;
        }
        return false;
    }
}
