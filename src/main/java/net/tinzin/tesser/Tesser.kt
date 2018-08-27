package net.tinzin.tesser

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.entity.EntityType
import net.minecraft.entity.item.EntityEnderCrystal
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.ResourceLocation
import net.tinzin.tesser.blocks.BlockTransparent
import net.tinzin.tesser.entity.EntityTesserCrystal
import org.dimdev.rift.listener.BlockAdder
import org.dimdev.rift.listener.EntityTypeAdder
import org.dimdev.rift.listener.ItemAdder

class Tesser : BlockAdder, ItemAdder, EntityTypeAdder {

    override fun registerBlocks() {
        Block.register(ResourceLocation("tesser:tesseract"), TESSERACT)
        //Block.register(ResourceLocation("examplemod:example_block"), EXAMPLE_BLOCK)
    }

    override fun registerItems() {
        //Item.registerItem(ResourceLocation("relocate:springboard"), SPRINGBOARD)
        //Item.registerItemBlock(ItemBlock(SPEEDDUST,Item.Builder().group(ItemGroup.TRANSPORTATION)))
        Item.registerItemBlock(TESSERACT, ItemGroup.DECORATIONS)
        //Item.registerItem(ResourceLocation("examplemod:example_item"), EXAMPLE_ITEM)
    }

    override fun registerEntityTypes() {
//        val TESSER_CRYSTAL = EntityType.register<EntityTesserCrystal>("tesser_crystal", EntityType.Builder.create(EntityTesserCrystal::class.java, EntityTesserCrystal::new))
        TESSER_CRYSTAL = EntityType.register("tesser:tesser_crystal", EntityType.Builder.create(EntityTesserCrystal::class.java, ::EntityTesserCrystal))
    }

    companion object {

        val TESSERACT = BlockTransparent()

        lateinit var TESSER_CRYSTAL: EntityType<EntityTesserCrystal>
            private set

    }
}
