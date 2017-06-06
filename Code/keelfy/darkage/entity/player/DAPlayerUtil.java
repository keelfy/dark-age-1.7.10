package keelfy.darkage.entity.player;

import keelfy.darkage.item.Armor;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * @author keelfy
 */
public class DAPlayerUtil {
	
	public static float getTotalBlockingPercent(final EntityPlayer player) {
		float r = 0;
		
		for(int i = 0; i < 4; i++) {
			if(player.getCurrentArmor(i) != null && player.getCurrentArmor(i).getItem() instanceof Armor)
				r += ((Armor)player.getCurrentArmor(i).getItem()).getBlockingPercent();
		}
		return r;
	}
	
	public static float getReceivedDamage(final EntityPlayer player, float damage) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			float resultDamage = damage;
			float r1 = resultDamage / 100;
			
			for(int i = 0; i < 4; i++) {
				if(player.getCurrentArmor(i) != null && player.getCurrentArmor(i).getItem() instanceof Armor)
					resultDamage -= r1 * ((Armor)player.getCurrentArmor(i).getItem()).getBlockingPercent();
			}
			
			return resultDamage;
		}
		return 0;
	}
	
	public static BiomeGenBase getBiome(final EntityPlayer player) {		
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
			return player.worldObj.getBiomeGenForCoordsBody((int)player.posX, (int)player.posZ);
		return null;
	}
	
	/**
	 * @author keelfy
	 */
	public enum Property {
		HEALTH(13, PropertyType.FLOAT),
		HEALTH_IN_TICK(26, PropertyType.FLOAT),
		INTOXICATION(27, PropertyType.FLOAT),
		ENERGY(20, 100F, PropertyType.FLOAT),
		ENERGY_IN_TICK(21, PropertyType.FLOAT),
		SATURATION(22, 100, PropertyType.FLOAT),
		WEIGHT(23, PropertyType.FLOAT),
		CURRENT_SIGN(15, 5, PropertyType.INTEGER);
		
		private int dataWatcherID;
		private float maxValue;
		private PropertyType type;
		
		private Property(int dwId, float maxValue, PropertyType type) {
			
			this.type = type;
			this.dataWatcherID = dwId;
			this.maxValue = maxValue;
		}
		
		private Property(int dwId, PropertyType type) {
			this(dwId, -1, type);
		}
		
		public PropertyType getType() {
			return type;
		}
		
		public int id() {
			return dataWatcherID;
		}
		
		public float getMaxValue() {
			return maxValue;
		}
		
		public static enum PropertyType {
			INTEGER, FLOAT, DOUBLE, STRING;
		}
	}

}
