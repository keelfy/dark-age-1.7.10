package keelfy.darkage.entity.sign;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkage.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * @author keelfy
 * @created 30 мая 2017 г.
 * 
 * TODO: ФИКС ЗНАКОВ!
 * TODO: Анимация взмаха руки при активации знака
 * TODO: Круговое меню со знаками
 */
public class ISign {
	
	@SideOnly(Side.CLIENT)
	public static EntityLiving getLookingEntity() {
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityLiving) {
			return (EntityLiving) mc.objectMouseOver.entityHit;
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
		AARD, AKSI, IGNI, IRDEN, KVEN, NONE;
	}
}
