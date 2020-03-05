package noppes.npcs.controllers;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.constants.EnumJobType;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobSpawner;

import org.apache.logging.log4j.LogManager;

public class PixelmonHelper {
	
	
	public static List<String> getPixelmonList(){
		List<String> list = new ArrayList<String>();
		if(!CustomNpcs.PixelMonEnabled)
			return list;
		try {
			Class c = Class.forName("com.pixelmonmod.pixelmon.enums.EnumPokemon");
			Object[] array = c.getEnumConstants();
			for(Object ob : array)
				list.add(ob.toString());
			
		} catch (Exception e) {
			LogManager.getLogger().error("getPixelmonList", e);
		}
		return list;
	}

	public static boolean isPixelmon(EntityLivingBase entity) {
		if(!CustomNpcs.PixelMonEnabled)
			return false;
		return EntityList.getEntityString(entity).equals("pixelmon.Pixelmon");
	}

	public static void setName(EntityLivingBase entity, String name) {
		if(!CustomNpcs.PixelMonEnabled || !isPixelmon(entity))
			return;
		try {
			Method m = entity.getClass().getMethod("init", String.class);
			m.invoke(entity, name);

			Class c = Class.forName("com.pixelmonmod.pixelmon.entities.pixelmon.Entity2HasModel");
			m = c.getDeclaredMethod("loadModel");
			m.setAccessible(true);
			m.invoke(entity);
		} catch (Exception e) {
			LogManager.getLogger().error("setName", e);
		}
	}
	public static String getName(EntityLivingBase entity) {
		if(!CustomNpcs.PixelMonEnabled || !isPixelmon(entity))
			return "";
		try {
			Method m = entity.getClass().getMethod("getName");
			return m.invoke(entity).toString();
		} catch (Exception e) {
			LogManager.getLogger().error("getName", e);
		}
		return "";
	}


	public static void debug(EntityLivingBase entity) {
		if(!CustomNpcs.PixelMonEnabled || !isPixelmon(entity))
			return;
		try {
			Method m = entity.getClass().getMethod("getModel");
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText((String) m.invoke(entity)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isTrainer(EntityLivingBase entity) {
		if(!CustomNpcs.PixelMonEnabled)
			return false;
		return EntityList.getEntityString(entity).equals("pixelmon.Trainer");
	}
	
	public static boolean isBattling(EntityPlayerMP player) {
		if(!CustomNpcs.PixelMonEnabled)
			return false;
		try {
			Class c = Class.forName("com.pixelmonmod.pixelmon.battles.BattleRegistry");
			Method m = c.getMethod("getBattle", EntityPlayer.class);
			if(m.invoke(null, player) != null)
				return false;
			
			return true;
		} catch (Exception e) {
			LogManager.getLogger().error("canBattle", e);
			return false;
		}
	}	
	
	public static boolean isBattling(EntityLivingBase trainer) {
		if(!CustomNpcs.PixelMonEnabled || !isTrainer(trainer))
			return false;
		try {
			Field f = trainer.getClass().getField("battleController");
			
			return f.get(trainer) != null;
		} catch (Exception e) {
			LogManager.getLogger().error("canBattle", e);
			return false;
		}
	}

	public static boolean canBattle(EntityPlayerMP player, EntityNPCInterface npc) {
		if(!CustomNpcs.PixelMonEnabled || npc.advanced.job != EnumJobType.Spawner || isBattling(player))
			return false;
		try {			
			JobSpawner spawner = (JobSpawner) npc.jobInterface;
			if(spawner.isOnCooldown(player.getCommandSenderName()))
				return false;
			
			Class c = Class.forName("com.pixelmonmod.pixelmon.storage.PixelmonStorage");
			Field f = c.getField("PokeballManager");
			Object ob = f.get(null);

			c = Class.forName("com.pixelmonmod.pixelmon.storage.PokeballManager");
			Method m = c.getMethod("getPlayerStorage", EntityPlayerMP.class);
			ob = m.invoke(ob, player);

			c = Class.forName("com.pixelmonmod.pixelmon.storage.PlayerStorage");
			m = c.getMethod("countAblePokemon");
			
			if((Integer) m.invoke(ob) == 0)
				return false;
			
			return true;
		} catch (Exception e) {
			LogManager.getLogger().error("canBattle", e);
			return false;
		}
	}

	public static boolean startBattle(EntityPlayerMP player, EntityLivingBase trainer) {
		if(!CustomNpcs.PixelMonEnabled)
			return false;
		try {			
			Class c = Class.forName("com.pixelmonmod.pixelmon.storage.PixelmonStorage");
			Field f = c.getField("PokeballManager");
			Object ob = f.get(null);

			c = Class.forName("com.pixelmonmod.pixelmon.storage.PokeballManager");
			Method m = c.getMethod("getPlayerStorage", EntityPlayerMP.class);
			ob = m.invoke(ob, player);

			c = Class.forName("com.pixelmonmod.pixelmon.storage.PlayerStorage");

			m = c.getMethod("getFirstAblePokemon", World.class);
			Entity pixelmon = (Entity) m.invoke(ob, player.worldObj);
			Class cEntity = Class.forName("com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon");
			m = c.getMethod("EntityAlreadyExists", cEntity);
			if(!(Boolean)m.invoke(ob, pixelmon)){
				m = cEntity.getMethod("releaseFromPokeball");
				pixelmon.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, 0);
			}

			c = Class.forName("com.pixelmonmod.pixelmon.battles.controller.participants.TrainerParticipant");
			Object parTrainer = c.getConstructor(new Class[] {trainer.getClass(), EntityPlayer.class, int.class}).newInstance(trainer, player, 1);

			Object[] pixelmonArray = (Object[]) Array.newInstance(cEntity, 1);
			pixelmonArray[0] = pixelmon;
			
			c = Class.forName("com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant");
			Object parPlayer = c.getConstructor(new Class[] {EntityPlayerMP.class, pixelmonArray.getClass()}).newInstance(player, pixelmonArray);
			
			cEntity = Class.forName("com.pixelmonmod.pixelmon.entities.pixelmon.Entity6CanBattle");
			c = Class.forName("com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant");
			m = cEntity.getMethod("StartBattle", c, c);
			m.invoke(pixelmon, parTrainer, parPlayer);
			
			return true;
		} catch (Exception e) {
			LogManager.getLogger().error("startBattle", e);
			return false;
		}
	}
}
