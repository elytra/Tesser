package net.tinzin.tesser.entity

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.init.SoundEvents
import net.minecraft.inventory.InventoryHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityBeacon
import net.minecraft.util.DamageSource
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.tinzin.tesser.Tesser
import kotlin.math.pow

class EntityTesserCrystal(world: World) : Entity(Tesser.TESSER_CRYSTAL, world) {

    var timeLeft : Int = 100
    @JvmField
    var innerRotation: Float = this.rand.nextInt(100000).toFloat()
    var bounceHeight: Int = this.rand.nextInt(100000)

    constructor(world: World, x : Double, y : Double, z : Double) : this(world) {
        this.setPosition(x, y, z)
        this.preventEntitySpawning = true
        this.setSize(2.0f, 2.0f)
        this.timeLeft = 100
    }

    override fun onUpdate() {
        if (timeLeft == 75) {
            world.playSound(posX, posY, posZ, SoundEvents.BLOCK_PORTAL_TRIGGER, SoundCategory.AMBIENT, 1f, 1f, true)
        }
        if (timeLeft <= 0 && !world.isRemote) {
            this.setDead()
            this.world.createExplosion(null as Entity?, this.posX, this.posY, this.posZ, 4.0f, false)
            InventoryHelper.spawnItemStack(world, posX, posY, posZ, ItemStack(Tesser.TESSERACT))
        }
        else {
            val te: TileEntity? =  world.getTileEntity(BlockPos(posX, posY-1, posZ))
            if (te != null && te is TileEntityBeacon ) {
                val beacon: TileEntityBeacon = te
                if (beacon.levels > 0) {
                    --timeLeft
                } else timeLeft = 100
            }
        }
        this.prevPosX = this.posX
        this.prevPosY = this.posY
        this.prevPosZ = this.posZ
        ++bounceHeight
        this.innerRotation += 2.toFloat().pow((100-timeLeft)/20.toFloat())
        super.onUpdate()
    }

//    override fun attackEntityFrom(source: DamageSource, damage: Float): Boolean {
//        if (this.isEntityInvulnerable(source)) return false
//        if (!this.isDead && !this.world.isRemote())
//            this.setDead()
//        if (!this.world.isRemote()) {
//            if (!source.isExplosion) {
//
//            }
//        }
//        return super.attackEntityFrom(source, damage)
//    }

    override fun writeEntityToNBT(compound: NBTTagCompound) {}

    override fun readEntityFromNBT(compound: NBTTagCompound) {}

    override fun entityInit() {}

    override fun canTriggerWalking(): Boolean {
        return false
    }

    override fun canBeCollidedWith(): Boolean {
        return true
    }

}