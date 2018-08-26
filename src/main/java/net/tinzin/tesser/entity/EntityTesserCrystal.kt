package net.tinzin.tesser.entity

import net.minecraft.entity.Entity
import net.minecraft.inventory.InventoryHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.DamageSource
import net.minecraft.world.World
import net.tinzin.tesser.Tesser

class EntityTesserCrystal(world: World) : Entity(Tesser.TESSER_CRYSTAL, world) {

    constructor(world: World, x : Double, y : Double, z : Double) : this(world) {
        this.setPosition(x, y, z)
    }

    @JvmField
    var innerRotation: Int = 0

    override fun onUpdate() {
        this.prevPosX = this.posX
        this.prevPosY = this.posY
        this.prevPosZ = this.posZ
        ++this.innerRotation

    }

    override fun attackEntityFrom(source: DamageSource, damage: Float): Boolean {
        if (this.isEntityInvulnerable(source)) return false
        if (!this.isDead && !this.world.isRemote())
            this.setDead()
        if (!this.world.isRemote()) {
            if (!source.isExplosion) {
                this.world.createExplosion(null as Entity?, this.posX, this.posY, this.posZ, 6.0f, false)
                InventoryHelper.spawnItemStack(world, posX, posY, posZ,  ItemStack(Tesser.TESSERACT))
            }
        }
        return super.attackEntityFrom(source, damage)
    }

    override fun writeEntityToNBT(compound: NBTTagCompound) {}

    override fun readEntityFromNBT(compound: NBTTagCompound) {}

    override fun entityInit() {}
}