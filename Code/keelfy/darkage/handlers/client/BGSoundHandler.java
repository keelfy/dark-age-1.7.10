/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.util.ResourceLocation;

/**
 * @author keelfy
 */
@SideOnly(Side.CLIENT)
// TODO: Добавить свою фоновую музыку
public class BGSoundHandler extends PositionedSound {
	
	public static BGSoundHandler create(ResourceLocation p_147674_0_, float v) {
		return new BGSoundHandler(p_147674_0_, v, 1f, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F);
	}

	public static BGSoundHandler create(ResourceLocation p_147673_0_) {
		return new BGSoundHandler(p_147673_0_, 1.0F, 1.0F, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F);
	}

	public static BGSoundHandler create(ResourceLocation p_147675_0_, float p_147675_1_, float p_147675_2_, float p_147675_3_) {
		return new BGSoundHandler(p_147675_0_, 4.0F, 1.0F, false, 0, ISound.AttenuationType.LINEAR, p_147675_1_, p_147675_2_, p_147675_3_);
	}
	
    public BGSoundHandler(ResourceLocation p_i45107_1_, float p_i45107_2_, float p_i45107_3_, float p_i45107_4_, float p_i45107_5_, float p_i45107_6_)
    {
        this(p_i45107_1_, p_i45107_2_, p_i45107_3_, false, 0, ISound.AttenuationType.LINEAR, p_i45107_4_, p_i45107_5_, p_i45107_6_);
    }
	
    private BGSoundHandler(ResourceLocation p_i45108_1_, float p_i45108_2_, float p_i45108_3_, boolean p_i45108_4_, int p_i45108_5_, ISound.AttenuationType p_i45108_6_, float p_i45108_7_, float p_i45108_8_, float p_i45108_9_) {
        super(p_i45108_1_);
        this.volume = p_i45108_2_;
        this.field_147663_c = p_i45108_3_;
        this.xPosF = p_i45108_7_;
        this.yPosF = p_i45108_8_;
        this.zPosF = p_i45108_9_;
        this.repeat = p_i45108_4_;
        this.field_147665_h = p_i45108_5_;
        this.field_147666_i = p_i45108_6_;
    }
}
