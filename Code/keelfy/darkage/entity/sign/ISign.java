package keelfy.darkage.entity.sign;

import java.util.List;

import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 30 мая 2017 г.
 * TODO: ФИКС ЗНАКОВ!
 */
public class ISign {
	
	public static Entity getLookingEntity(double range, EntityPlayer player) {
		EntityPlayer thePlayer = player;
		List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(range, range, range));
		if(list != null) {
			for(int i = 0; i < list.size(); ++i) {
				Entity entity = (Entity)list.get(i);
				if(!entity.isDead && entity instanceof EntityLiving) {
	 	             Vec3 vec3d = thePlayer.getLook(1.0F).normalize();
	 	             Vec3 vec3d1 = Vec3.createVectorHelper(entity.posX - thePlayer.posX, entity.boundingBox.minY + entity.height / 2.0F - (thePlayer.posY + thePlayer.getEyeHeight()), entity.posZ - thePlayer.posZ);
	 	             double d = vec3d1.lengthVector();
	 	             vec3d1 = vec3d1.normalize();
	 	             double d1 = vec3d.dotProduct(vec3d1);
	 	             if(d1 > 1.0D - 0.025D / d) {
	 	            	 DAUtil.LOGGER.debug("Looking at " + entity);
	 	            	 return entity;
	 	             }
				}
			}
		}
		return null;
	}
	
	public static void useSign(String s, World world, EntityPlayer sender) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
			world.playSoundAtEntity(sender, s, 1.0F, 1.0F);
	}

	public static List getEntitiesAround(EntityPlayer player, double range, boolean includePlayer) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			List list;
			if (includePlayer) {
				list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player,
						player.boundingBox.expand(range, range, range));
			} else {
				list = player.worldObj.getEntitiesWithinAABB(Entity.class, player.boundingBox.expand(range, range, range));
			}
			return list;
		}
		return null;
	}
	
	public static enum Sign {
		AARD,
		AKSI,
		IGNI,
		IRDEN,
		KVEN,
		NONE;
	}
}
