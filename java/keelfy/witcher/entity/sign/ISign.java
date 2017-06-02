package keelfy.witcher.entity.sign;

import java.util.List;

import keelfy.witcher.util.DAUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 30 мая 2017 г.
 */
public class ISign {
	
	public void handle(EntityPlayerMP sender) {}
	
	public static EntityLiving getLookingEntity(double range, EntityPlayer player) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(range, range, range));
			if (list != null) {
				for (int i = 0; i < list.size(); ++i) {
					Entity entity = (Entity) list.get(i);
					if (!entity.isDead && entity instanceof EntityLiving) {
						Vec3 vec3d = player.getLook(1.0F).normalize();
						Vec3 vec3d1 = Vec3.createVectorHelper(entity.posX - player.posX, entity.boundingBox.minY + entity.height / 2.0F - (player.posY + player.getEyeHeight()), entity.posZ - player.posZ);
						double d = vec3d1.lengthVector();
						vec3d1 = vec3d1.normalize();
						double d1 = vec3d.dotProduct(vec3d1);
						
						if (d1 > 1.0D - 0.025D / d) {
							return (EntityLiving) entity;
						}
					}
				}
			}
		} return null;
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
