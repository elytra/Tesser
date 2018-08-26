package net.tinzin.tesser.mixin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemUseContext;
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
public abstract class MixinItemEndCrystal extends Item {

    public MixinItemEndCrystal(Item.Builder builder) {
        super(builder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Inject(method = "a",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false)
    public void onItemUse(ItemUseContext p_onItemUse_1_, CallbackInfoReturnable<EnumActionResult> ci, World lvt_2_1_, BlockPos lvt_3_1_, IBlockState lvt_4_1_) {
        if (lvt_4_1_.getBlock() == Blocks.BEACON) {
            if (placeTesserCrystal(lvt_2_1_, lvt_3_1_)) p_onItemUse_1_.getItem().shrink(1);
            ci.setReturnValue(EnumActionResult.SUCCESS);
        }
    }

    private boolean placeTesserCrystal(World world, BlockPos pos) {
        BlockPos placePos = pos.up();
        List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(placePos.getX(), placePos.getY(), placePos.getZ(), placePos.getX() + 1.0D, placePos.getY() + 2.0D, placePos.getZ() + 1.0D));
        if (entities.isEmpty() && !world.isRemote()) {
            EntityTesserCrystal crystal = new EntityTesserCrystal(world, placePos.getX() + 0.5D, placePos.getY(), placePos.getZ() + 0.5D);
            crystal.setShowBottom(false);
            world.spawnEntity(crystal);
            return true;
        }
        return false;
    }
}
