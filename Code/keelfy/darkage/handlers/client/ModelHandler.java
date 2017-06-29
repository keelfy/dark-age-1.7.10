/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.handlers.client;

import keelfy.darkage.client.models.KeelfyModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.IModelCustomLoader;
import net.minecraftforge.client.model.ModelFormatException;

/**
 * @author keelfy
 */
public class ModelHandler implements IModelCustomLoader {
	
	public ModelHandler() {
		AdvancedModelLoader.registerModelHandler(this);
	}

	@Override
	public String getType() {
		return "Keelfy Asset";
	}

	private String[] types = new String[] { "ka" };
	@Override
	public String[] getSuffixes() {
		return types;
	}

	@Override
	public IModelCustom loadInstance(ResourceLocation resource) throws ModelFormatException {
		return new KeelfyModel(resource);
	}
}
