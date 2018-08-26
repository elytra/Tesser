package net.tinzin.tesser.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelEnderCrystal;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderTesserCrystal extends Render<EntityTesserCrystal> {
    private static final ResourceLocation ENDER_CRYSTAL_TEXTURES = new ResourceLocation("textures/entity/end_crystal/end_crystal.png");
    private final ModelBase modelEnderCrystal = new ModelEnderCrystal(0.0F, true);
    private final ModelBase modelEnderCrystalNoBase = new ModelEnderCrystal(0.0F, false);

    public RenderTesserCrystal(RenderManager p_i46184_1_) {
        super(p_i46184_1_);
        this.shadowSize = 0.5F;
    }

    public void doRender(EntityTesserCrystal p_doRender_1_, double p_doRender_2_, double p_doRender_4_, double p_doRender_6_, float p_doRender_8_, float p_doRender_9_) {
        float lvt_10_1_ = (float)p_doRender_1_.innerRotation + p_doRender_9_;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)p_doRender_2_, (float)p_doRender_4_, (float)p_doRender_6_);
        this.bindTexture(ENDER_CRYSTAL_TEXTURES);
        float lvt_11_1_ = MathHelper.sin(lvt_10_1_ * 0.2F) / 2.0F + 0.5F;
        lvt_11_1_ += lvt_11_1_ * lvt_11_1_;
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(p_doRender_1_));
        }

            this.modelEnderCrystal.render(p_doRender_1_, 0.0F, lvt_10_1_ * 3.0F, lvt_11_1_ * 0.2F, 0.0F, 0.0F, 0.0625F);

        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();

        super.doRender(p_doRender_1_, p_doRender_2_, p_doRender_4_, p_doRender_6_, p_doRender_8_, p_doRender_9_);
    }

    protected ResourceLocation getEntityTexture(EntityTesserCrystal p_getEntityTexture_1_) {
        return ENDER_CRYSTAL_TEXTURES;
    }

    public boolean shouldRender(EntityTesserCrystal p_shouldRender_1_, ICamera p_shouldRender_2_, double p_shouldRender_3_, double p_shouldRender_5_, double p_shouldRender_7_) {
        return super.shouldRender(p_shouldRender_1_, p_shouldRender_2_, p_shouldRender_3_, p_shouldRender_5_, p_shouldRender_7_);
    }
}
