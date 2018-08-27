package net.tinzin.tesser.blocks

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.util.BlockRenderLayer

class BlockTransparent : Block(Block.Builder.create(Material.GLASS, MapColor.PURPLE).hardnessAndResistance(6f, 6f).sound(SoundType.GLASS)) {
    override fun getRenderLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT;
    }
}