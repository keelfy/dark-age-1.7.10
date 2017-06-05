package keelfy.darkage.entity.player;

import keelfy.api.network.PacketDispatcher;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.entity.player.DAPlayerUtil.Property.PropertyType;
import keelfy.darkage.entity.player.weight.PlayerWeight;
import keelfy.darkage.entity.sign.ISign.Sign;
import keelfy.darkage.inventory.player.ContainerDAInventory;
import keelfy.darkage.inventory.player.DAInventory;
import keelfy.darkage.item.Armor;
import keelfy.darkage.item.Armor.ArmorType;
import keelfy.darkage.network.client.SyncPlayerMessage;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * @author keelfy
 */
public class DAPlayer implements IExtendedEntityProperties {

	public static final String NAME = DAUtil.MODID + "_player";
	
	private EntityPlayer player;
	
	private PlayerClass playerClass;
	public DAInventory inventory;
	public ContainerDAInventory inventoryContainer;
	
	private float playerMaxHealth;
	private float playerHealth;
	private float PlayerHIT;
	private int HEALTH = Property.HEALTH.id();
	private int HEALTH_IN_TICK = Property.HEALTH_IN_TICK.id();
	
	private float witcherIntox;
	private float witcherMaxIntox;
	private float witcherIIT;
	private int INTOXICATION = Property.INTOXICATION.id();
	
	private float playerEnergy;
	private float playerMaxEnergy = Property.ENERGY.getMaxValue();
	private float playerEIT;
	private int ENERGY = Property.ENERGY.id();
	private int ENERGY_IN_TICK = Property.ENERGY_IN_TICK.id();
	
	private float weight;
	private int WEIGHT = Property.WEIGHT.id();
	
	private float saturation;
	private float satInTick = -0.00104166666F;
	private float maxSaturation = Property.SATURATION.getMaxValue();
	private int SATURATION = Property.SATURATION.id();
	
	private int witcherSign;
	private int SIGN = Property.CURRENT_SIGN.id();
	
	public DAPlayer(EntityPlayer player) {
		this.player = player;
			
		inventory = new DAInventory(player);
		inventoryContainer = new ContainerDAInventory(player, inventory);
			
		this.playerEnergy = this.playerMaxEnergy;
		this.playerEIT = 0F;
		this.player.getDataWatcher().addObject(ENERGY, playerEnergy);
		this.player.getDataWatcher().addObject(ENERGY_IN_TICK, playerEIT);
			
		this.witcherIntox = 0F;
		this.player.getDataWatcher().addObject(INTOXICATION, witcherIntox);
			
		this.witcherMaxIntox = 0F;
		this.witcherIIT = 0F;
			
		this.playerHealth = 1000F;
		this.player.getDataWatcher().addObject(HEALTH, playerHealth);
			
		this.playerMaxHealth = 1000F;
			
		this.PlayerHIT = 0F;
		this.player.getDataWatcher().addObject(HEALTH_IN_TICK, PlayerHIT);
			
		this.playerClass = PlayerClass.WITCHER;
			
		this.witcherSign = -1;
		this.player.getDataWatcher().addObject(SIGN, witcherSign);
			
		this.weight = 0F;
		this.player.getDataWatcher().addObject(WEIGHT, weight);
		
		this.saturation = 100;
		this.player.getDataWatcher().addObject(SATURATION, saturation);
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(NAME, new DAPlayer(player));
	}
	
	public static final DAPlayer get(EntityPlayer player) {
		return (DAPlayer) player.getExtendedProperties(NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			NBTTagCompound properties = new NBTTagCompound();
				
			inventory.writeToNBT(properties);
			
			properties.setInteger("PlayerClass", playerClass.ordinal());
					
			properties.setFloat("PlayerHealth", player.getDataWatcher().getWatchableObjectFloat(HEALTH));
			properties.setFloat("PlayerMaxHealth", playerMaxHealth);
			properties.setFloat("PlayerHIT", player.getDataWatcher().getWatchableObjectFloat(HEALTH_IN_TICK));
					
			properties.setFloat("WitcherIntox", player.getDataWatcher().getWatchableObjectFloat(INTOXICATION));
			properties.setFloat("WitcherMaxIntox", witcherMaxIntox);
			properties.setFloat("WitcherIIT", witcherIIT);
					
			properties.setFloat("PlayerEnergy", player.getDataWatcher().getWatchableObjectFloat(ENERGY));
			properties.setFloat("PlayerEIT", player.getDataWatcher().getWatchableObjectFloat(ENERGY_IN_TICK));
				
			properties.setInteger("WitcherSign", player.getDataWatcher().getWatchableObjectInt(SIGN));
			
			properties.setFloat("Weight", player.getDataWatcher().getWatchableObjectFloat(WEIGHT));
				
			properties.setFloat("Saturation", player.getDataWatcher().getWatchableObjectFloat(SATURATION));
				
			compound.setTag(NAME, properties);
		}
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(NAME);
		
		this.inventory.readFromNBT(properties);
		
		playerClass = playerClass.values()[properties.getInteger("PlayerClass")];
		
		player.getDataWatcher().updateObject(HEALTH, properties.getFloat("PlayerHealth"));
		playerMaxHealth = properties.getFloat("PlayerMaxHealth");
		player.getDataWatcher().updateObject(HEALTH_IN_TICK, properties.getFloat("PlayerHIT"));
		
		player.getDataWatcher().updateObject(INTOXICATION, properties.getFloat("WitcherIntox"));
		witcherMaxIntox = properties.getFloat("WitcherMaxIntox");
		witcherIIT = properties.getFloat("WitcherIIT");
		
		player.getDataWatcher().updateObject(ENERGY, properties.getFloat("PlayerEnergy"));
		player.getDataWatcher().updateObject(ENERGY_IN_TICK, properties.getFloat("PlayerEIT"));
		
		player.getDataWatcher().updateObject(SIGN, properties.getInteger("WitcherSign"));
		
		player.getDataWatcher().updateObject(WEIGHT, properties.getFloat("Weight"));
		
		player.getDataWatcher().updateObject(SATURATION, properties.getFloat("Saturation"));
	}
	
	public void copy(DAPlayer wcp) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				playerClass = wcp.playerClass;
				playerMaxHealth = wcp.getPlayerMaxHealth();
				update(Property.HEALTH_IN_TICK, wcp.get(Property.HEALTH_IN_TICK));
				witcherIIT = wcp.witcherIIT;
				witcherMaxIntox = wcp.witcherMaxIntox;
				witcherSign = wcp.witcherSign;
				restoreHealth();
			}
		}
	}

	int var1 = 0, var2 = 0;
	public void onUpdate() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) { if(var2 == 0) {
			if(!player.capabilities.isCreativeMode) {
				float sat = get(Property.SATURATION);
				if(sat > 0) {
					if(sat - satInTick >= 0) {
						changeSaturation(satInTick);
					} else if(sat - satInTick < 0) {
						update(SATURATION, 0);
					} 
					
					if(get(Property.HEALTH_IN_TICK) == 0F && getPlayerClass() == PlayerClass.WITCHER) {
						update(Property.HEALTH_IN_TICK, 0.04F);
					}
				}
				
				if(sat < 0) {
					update(SATURATION, 0);
				} else if(sat <= 0) {
					changeHealth(-0.03F);
					update(Property.HEALTH_IN_TICK, 0F);
				}
						
				float curh = get(Property.HEALTH);
				float maxh = getPlayerMaxHealth();
				if(curh <= 0F) {
					player.attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE);
				}
						
				if(curh > maxh) restoreHealth();
				if(player.getFoodStats().getFoodLevel() != 20) player.getFoodStats().addStats(20, 1);
				
				float hint = get(Property.HEALTH_IN_TICK);
				if(curh > 0f && curh < maxh && curh + hint <= maxh) 
					changeHealth(hint);
				else if(curh + hint >= maxh) 
					restoreHealth();
					
				update(WEIGHT, PlayerWeight.inventory(player));
						
				if(var1 > 0) var1--;
					
				if(player.isBurning()) changeHealth(-1F);
				
				float maxi = getWitcherMaxIntox();
				float curi = get(Property.INTOXICATION);
				float iit = getWitcherIIT();
				
				if(getPlayerClass() == PlayerClass.WITCHER) {
					if(maxi == 0F) setWitcherMaxIntox(80);
					if(hint == 0F) update(HEALTH_IN_TICK, 0.04F); 
				} else if(maxi != 0) {
					setWitcherMaxIntox(0F);
					resetWitcherIntox();
				}
						
				if(curi > 0.0F && curi - 0.0005F > 0F) {
					changeIntox(-0.0005F);
				} else if(curi - 0.0005F <= 0F) {
					resetWitcherIntox();
				}
						
				if(curi > 50) {
					changeHealth((float)Math.pow(Math.E, (curi - 50) / 31));
				}
						
				update(ENERGY_IN_TICK, checkEnergyLose());
				
				float cure = get(Property.ENERGY);
				float eit = get(Property.ENERGY_IN_TICK);
						
				if(player.isSprinting()) {
					if(weight > 60F || cure <= 10) 
						player.setSprinting(false); 
							
					if(cure + eit > 10F) {
						changeEnergy(eit);
					} else if(cure + eit <= 10F) {
						update(ENERGY, 10F);
						player.setSprinting(false);
					}
							
					var1 = 100;
				} else if(var1 == 0) {
					if(cure < playerMaxEnergy && cure + 0.2F < playerMaxEnergy) {
						changeEnergy(0.2F);
					} else if(cure + 0.2F >= playerMaxEnergy){
						update(ENERGY, playerMaxEnergy);
					}
				}
			}
			
			var2 = 1;
		} else if(var2 == 1) {
			var2 = 0;
		}}
	}
	
	private final float checkEnergyLose() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				float lRegen = 0.0075F;
				float hRegen = 0.025F;
				float mRegen = 0.015F;
				float eRegen = 0.005F;
				float fRegen = 0;
				
				if(player != null) {
					for(int i = 0; i < 4; ++i) {
						if(player.getCurrentArmor(i) != null) {
							Item cura = player.getCurrentArmor(i).getItem();
							
							if(cura instanceof Armor && ((Armor)cura).getArmorType() == ArmorType.HEAVY) {
								fRegen += hRegen;
							} else if(cura instanceof Armor && ((Armor)cura).getArmorType() == ArmorType.LIGHT) {
								fRegen += lRegen;
							} else if(cura instanceof Armor && ((Armor)cura).getArmorType() == ArmorType.MIDDLE){
								fRegen += mRegen;
							} else {
								fRegen += lRegen;
							}
						} else {
							fRegen += eRegen;
						}
					}
				}
				return -fRegen * 2;
			}
		}
		return 0;
	}
	
	//Saturation
	public float getSaturation() {
		return saturation;
	}
	
	public void resetSaturation() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote)
				update(SATURATION, Property.SATURATION.getMaxValue());
		}
	}
	
	public void changeSaturation(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				float curs = get(Property.SATURATION);
				if(curs + amount >= 0 && curs + amount < Property.SATURATION.getMaxValue())
					update(SATURATION, curs + amount);
				else if(curs + amount >= Property.SATURATION.getMaxValue()) this.resetSaturation();
			}
		}
	}
	
	//Sign
	public Sign getWitcherSign() {
		if(get(Property.CURRENT_SIGN) < 0) return Sign.NONE;
		return Sign.values() [(int) get(Property.CURRENT_SIGN)];
	}
	
	//Energy
	public float getPlayerMaxEnergy() {
		return playerMaxEnergy;
	}
	
	public final boolean changeEnergy(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				boolean r = false;
				
				float cur = get(Property.ENERGY);
				if(cur + amount > 0 && cur + amount < playerMaxEnergy) {
					update(ENERGY, cur + amount);
					r = true;
				} else if(cur + amount < 0) 
					r = false;
				else if(cur + amount > playerMaxEnergy) {
					update(ENERGY, cur + amount);
					r = true;
				}
				
				return r;
			}
		}
		return false;
	}
	
	public final void resetPlayerEnergy() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote)
				update(ENERGY, playerMaxEnergy);	
		}
	}
	
	//MAX INTOX
	public final void setWitcherMaxIntox(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				if(amount > 0F) witcherMaxIntox = amount;
				else witcherMaxIntox = 0F;
				sync();
			}
		}
	}
	
	
	public final void addWitcherMaxIntox(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				if(this.getWitcherMaxIntox() + amount > 0F)
					witcherMaxIntox += amount;
				else witcherMaxIntox = 0;
				sync();
			}
		}
	}
	
	public final float getWitcherMaxIntox() {
		return witcherMaxIntox;
	}
	
	//INTOX
	public final void setWitcherIntox(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				if(amount > 0F) {
					if(amount > getWitcherMaxIntox()) {
						update(INTOXICATION, getWitcherMaxIntox());
					} else
						update(INTOXICATION, amount);
				}
			}
		}
	}
	
	public final void changeIntox(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				float curi = get(Property.INTOXICATION);
				if(curi + amount > getWitcherMaxIntox())
					update(INTOXICATION, getWitcherMaxIntox());
				else if(curi + amount > 0F)
					update(INTOXICATION, curi + amount);
				else update(INTOXICATION, 0F);
			}
		}
	}
	
	public final void resetWitcherIntox() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote)
				update(INTOXICATION, 0F);
		}
	}
	
	//IIT
	public final void setWitcherIIT(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			witcherIIT = amount;
			sync();
		}
	}
	
	public final void addWitcherIIT(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				witcherIIT += amount;
				sync();
			}
		}
	}
	
	public final void resetWitcherIIT() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				witcherIIT = 0;
				sync();
			}
		}
	}
	
	public final float getWitcherIIT() {
		return witcherIIT;
	}
	
	//HEALTH
	public final void changeHealth(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				float curh = get(Property.HEALTH);
				float maxh = getPlayerMaxHealth();
				
				if(curh + amount <= maxh)
					update(HEALTH, curh + amount);
				else update(HEALTH, maxh);
			}
		}
	}
	
	public final void restoreHealth() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote)
				update(HEALTH, getPlayerMaxHealth());
		}
	}
	
	//
	//MAX HEALTH
	public final float getPlayerMaxHealth() {
		return playerMaxHealth;
	}
	
	public final void setPlayerMaxHealth(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				if(amount > 0) {
					this.playerMaxHealth = amount;
					sync();
				}
			}
		}
	}
	
	public final void addPlayerMaxHealth(float amount) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				if(playerMaxHealth + amount > 0) {
					this.playerMaxHealth += amount;
					sync();
				}
			}
		}
	}
	
	//CLASS
	public final PlayerClass getPlayerClass() {
		return playerClass;
	}
	
	public final void setPlayerClass(PlayerClass newClass) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				if(playerClass != newClass) {
					playerClass = newClass;
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Ваш новый класс: " + newClass.getLocalizedName()));
					sync();
					
					if(newClass == PlayerClass.WITCHER) {
						setPlayerMaxHealth(1200F);
						update(HEALTH_IN_TICK, 0.04F);
						update(HEALTH, 1200F);
						setWitcherMaxIntox(80F);
						setWitcherIIT(-(5E-4F));
						update(SIGN, 0);
					}
					
					if(newClass == PlayerClass.HUMAN) {
						setPlayerMaxHealth(1000F);
						update(HEALTH_IN_TICK, 0F);
						setWitcherMaxIntox(0F);
						setWitcherIIT(0F);
						resetWitcherIntox();
						update(SIGN, Sign.NONE.ordinal());
					}
					sync();
				} else 
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Это уже ваш класс!"));
			}
		}
	}
	
	//
	//STUFF
	public final void update(Property prop, float f) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				if(prop == Property.ENERGY && f == 0) {
					player.getDataWatcher().updateObject(prop.id(), 7F);
				}
				player.getDataWatcher().updateObject(prop.id(), f);
			}
		}
	}
	
	public final void update(int id, float f) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
			if(!player.worldObj.isRemote)
				player.getDataWatcher().updateObject(id, f);
	}
	
	public final void update(Property prop, int i) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
			if(!player.worldObj.isRemote)
				player.getDataWatcher().updateObject(prop.id(), i);
	}
	
	public final void update(int id, int i) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE)
			if(!player.worldObj.isRemote)
				player.getDataWatcher().updateObject(id, i);
	}

	public final float get(Property prop) {
		if(prop.getType() == PropertyType.FLOAT)
			return player.getDataWatcher().getWatchableObjectFloat(prop.id());
		else if(prop.getType() == PropertyType.INTEGER)
			return player.getDataWatcher().getWatchableObjectInt(prop.id());
		return Float.MAX_VALUE;
	}
	
	private final void sync() {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(!player.worldObj.isRemote) {
				PacketDispatcher.getInstance().sendTo(new SyncPlayerMessage(player), (EntityPlayerMP)player);
			}
		}
	}

	@Override
	public void init(Entity entity, World world) {}
}