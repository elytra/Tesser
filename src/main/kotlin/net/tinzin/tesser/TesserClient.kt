package net.tinzin.tesser

import net.minecraft.client.renderer.entity.Render
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.entity.Entity
import net.tinzin.tesser.entity.EntityTesserCrystal
import net.tinzin.tesser.entity.RenderTesserCrystal
import org.dimdev.rift.listener.client.EntityRendererAdder

class TesserClient : EntityRendererAdder {
    override fun addEntityRenderers(entityRenderMap: MutableMap<Class<out Entity>, Render<out Entity>>?, renderManager: RenderManager?) {
        entityRenderMap!![EntityTesserCrystal::class.java] = RenderTesserCrystal(renderManager)
    }
}