/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.entities.player;

import keelfy.darkage.constants.EnumArmorClass;
import keelfy.darkage.constants.EnumPlayerClass;
import keelfy.darkage.constants.EnumProperty;
import keelfy.darkage.constants.EnumPropertyObject;
import keelfy.darkage.constants.EnumSign;
import keelfy.darkage.entities.player.weight.PlayerWeight;
import keelfy.darkage.inventory.player.DAContainerPlayerInventory;
import keelfy.darkage.inventory.player.DAPlayerInventory;
import keelfy.darkage.items.Armor;
import keelfy.darkage.network.ServerPacketHandler;
import keelfy.darkage.utils.DAEntityUtils;
import keelfy.darkage.utils.DAUtils;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * @author keelfy
 */
public class DAPlayer implements IExtendedEntityProperties {

	public static final String NAME = DAUtils.MODID + "_player";
	
	private EntityPlayer player;
	
	private EnumPlayerClass playerClass;
	public DAPlayerInventory inventory;
	public DAContainerPlayerInventory inventoryContainer;
	
	private float playerMaxHealth;
	private float playerHealth;
	private float playerHIT;
	private int HEALTH = EnumProperty.HEALTH.id();
	
	private float witcherIntox;
	private float witcherMaxIntox;
	private float witcherIIT;
	private int INTOXICATION = EnumProperty.INTOXICATION.id();
	
	private float playerEnergy;
	private float playerMaxEnergy = EnumProperty.ENERGY.getMaxValue();
	private float playerMinEnergy;
	private float playerEIT;
	private int ENERGY = EnumProperty.ENERGY.id();
	
	private float weight;
	private int WEIGHT = EnumProperty.WEIGHT.id();
	
	private float saturation;
	private float satInTick = -0.00104166666F;
	private float maxSaturation = EnumProperty.SATURATION.getMaxValue();
	private int SATURATION = EnumProperty.SATURATION.id();
	
	private int witcherSign;
	private int SIGN = EnumProperty.CURRENT_SIGN.id();
	
	public DAPlayer(EntityPlayer player) {
		this.player = player;
			
		inventory = new DAPlayerInventory(player);
		inventoryContainer = new DAContainerPlayerInventory(player, inventory);
		
		this.playerMinEnergy = 10F;
		this.playerEnergy = this.playerMaxEnergy;
		this.playerEIT = 0F;
		this.player.getDataWatcher().addObject(ENERGY, playerEnergy);
			
		this.witcherIntox = 0F;
		this.player.getDataWatcher().addObject(INTOXICATION, witcherIntox);
			
		this.witcherMaxIntox = 80F;
		this.witcherIIT = 0F;
			
		this.playerHealth = 1000F;
		this.player.getDataWatcher().addObject(HEALTH, playerHealth);
		this.playerHIT = 0F;
			
		this.playerMaxHealth = 1000F;
			
		this.playerClass = EnumPlayerClass.WITCHER;
			
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
		if(KeelfyUtils.isServerSide()) {
			NBTTagCompound properties = new NBTTagCompound();
				
			inventory.writeToNBT(properties);
			
			properties.setInteger("PlayerClass", playerClass.ordinal());
					
			properties.setFloat("PlayerHealth", player.getDataWatcher().getWatchableObjectFloat(HEALTH));
			properties.setFloat("PlayerMaxHealth", playerMaxHealth);
					
			properties.setFloat("WitcherIntox", player.getDataWatcher().getWatchableObjectFloat(INTOXICATION));
			properties.setFloat("WitcherIIT", witcherIIT);
					
			properties.setFloat("PlayerEnergy", player.getDataWatcher().getWatchableObjectFloat(ENERGY));
				
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
		
		player.getDataWatcher().updateObject(INTOXICATION, properties.getFloat("WitcherIntox"));
		witcherIIT = properties.getFloat("WitcherIIT");
		
		player.getDataWatcher().updateObject(ENERGY, properties.getFloat("PlayerEnergy"));
		
		player.getDataWatcher().updateObject(SIGN, properties.getInteger("WitcherSign"));
		
		player.getDataWatcher().updateObject(WEIGHT, properties.getFloat("Weight"));
		
		player.getDataWatcher().updateObject(SATURATION, properties.getFloat("Saturation"));
	}
	
	public void attackPlayer(float damage, boolean par2) {
		if(!player.worldObj.isRemote) {
			float newDamage = damage;
			if(par2) newDamage = DAEntityUtils.getReceivedDamage(player, damage);
			changeHealth(-newDamage);
			player.worldObj.playSoundAtEntity(player, "game.player.hurt", 1, 1);
			setWitcherHealingTimer(100);
			if(par2) inventory.damageArmor();
		}
	}
	
	public void copy(EntityPlayer player) {
		if(KeelfyUtils.isServerSide()) {
			DAPlayer dap = DAPlayer.get(player);
			
			playerClass = dap.playerClass;
			playerMaxHealth = dap.getPlayerMaxHealth();
			witcherIIT = dap.witcherIIT;
			witcherMaxIntox = dap.witcherMaxIntox;
			witcherSign = dap.witcherSign;
			player.inventory.copyInventory(player.inventory);
			restoreHealth();
		}
	}

	int var1 = 0, var2 = 0, var3 = 0;
	public void onUpdate() {
		if(KeelfyUtils.isServerSide()) { if(var2 == 0) {
			if(!player.capabilities.isCreativeMode && !player.worldObj.isRemote) {
				playerHIT = checkHeal();
				playerEIT = checkEnergyLose();
				float newSat = checkSaturation();
				float newWeight = PlayerWeight.inventory(player);
				
				update(SATURATION, newSat);
				
				if(get(EnumProperty.WEIGHT) !=  newWeight)
					update(WEIGHT, newWeight);
				
				if(var3 == 0) {
					changeHealth(playerHIT);
				}	
					
				if(getPlayerClass() == EnumPlayerClass.WITCHER) {
					changeIntox(-0.005F);
				} else {
					update(INTOXICATION, 0F);
				}
					
				float curi = get(EnumProperty.INTOXICATION);
				if(curi > 50) {
					changeHealth((float)Math.pow(Math.E, (curi - 50) / 31));
				}
					
				playerBurning(player.isBurning());
				playerSprinting(player.isSprinting());
			}
			
			updateTimers();
		} else if(var2 == 1) {
			var2 = 0;
		}}
	}
	
	private final void updateTimers() {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				if(var1 > 0) {
					var1--;
				}
					
				if(var3 > 0) {
					var3--;
				}
				
				var2 = 1;
			}
		}
	}
	
	private void playerBurning(boolean burning) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				if(burning) {
					changeHealth(-1F);
				}
			}
		}
	}

	private final void playerSprinting(boolean isSprinting) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				float cure = get(EnumProperty.ENERGY);
				
				if(isSprinting) {
					if(weight > 60F || cure <= playerMinEnergy) 
						player.setSprinting(false); 
					
					changeEnergy(playerEIT);
					setEnergyTimer(100);
				} else if(var1 == 0) {
					changeEnergy(0.2F);
				}
			}
		}
	}

	private final float checkMaxIntox() {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				float maxi = getWitcherMaxIntox();
				
				if(getPlayerClass() == EnumPlayerClass.WITCHER && maxi == 0F) {
					return 80f;
				} else if(getPlayerClass() == EnumPlayerClass.HUMAN && maxi != 0) {
					resetWitcherIntox();
					return 0f;
				}
			}
		}
		return 0;
	}

	public final void setWitcherHealingTimer(int t) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				this.var3 = t;
			}
		}
	}
	
	public final void setEnergyTimer(int t) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				this.var1 = t;
			}
		}
	}
	
	private final float checkSaturation() {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				float sat = get(EnumProperty.SATURATION);
				
				if(player.getFoodStats().getFoodLevel() != 20) 
					player.getFoodStats().addStats(20, 1);
				
				if(sat < 0) return 0;
				else if(sat > 100) return 100;
				
				if(sat == 0) {
					changeHealth(-0.03F);
				}
				
				return sat + satInTick;
			}
		}
		return 0;
	}
	
	private final float checkHeal() {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				float sat = get(EnumProperty.SATURATION);
				EnumPlayerClass playerClass = getPlayerClass();
				float intox = get(EnumProperty.INTOXICATION);
				
				if((playerClass == EnumPlayerClass.HUMAN || ((sat < 40 || intox > 50) && playerClass == EnumPlayerClass.WITCHER))) {
					return 0;
				}
				
				if(sat > 40 && intox < 50 && playerClass == EnumPlayerClass.WITCHER) {
					return 0.04f;
				}
			}
		}
		return 0f;
	}
	
	private final float checkEnergyLose() {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				if(this.get(EnumProperty.SATURATION) < 15) 
					return 0;
				
				float lRegen = 0.0075F;
				float hRegen = 0.025F;
				float mRegen = 0.015F;
				float eRegen = 0.005F;
				float fRegen = 0;
				
				if(player != null) {
					for(int i = 0; i < 4; ++i) {
						if(player.getCurrentArmor(i) != null) {
							Item cura = player.getCurrentArmor(i).getItem();
							
							if(cura instanceof Armor && ((Armor)cura).getArmorType() == EnumArmorClass.HEAVY) {
								fRegen += hRegen;
							} else if(cura instanceof Armor && ((Armor)cura).getArmorType() == EnumArmorClass.LIGHT) {
								fRegen += lRegen;
							} else if(cura instanceof Armor && ((Armor)cura).getArmorType() == EnumArmorClass.MIDDLE){
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
	public void resetSaturation() {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote)
				update(SATURATION, EnumProperty.SATURATION.getMaxValue());
		}
	}
	
	public void changeSaturation(float amount) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				float curs = get(EnumProperty.SATURATION);
				if(curs + amount >= 0 && curs + amount < EnumProperty.SATURATION.getMaxValue())
					update(SATURATION, curs + amount);
				else if(curs + amount >= EnumProperty.SATURATION.getMaxValue()) this.resetSaturation();
			}
		}
	}
	
	//Sign
	public EnumSign getWitcherSign() {
		if(get(EnumProperty.CURRENT_SIGN) < 0) return EnumSign.NONE;
		return EnumSign.values() [(int) get(EnumProperty.CURRENT_SIGN)];
	}
	
	//Energy
	public float getPlayerMaxEnergy() {
		return playerMaxEnergy;
	}
	
	public float getPlayerMinEnergy() {
		return playerMinEnergy;
	}
	
	public final void changeEnergy(float amount) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				float cur = get(EnumProperty.ENERGY);
				if(cur + amount > playerMinEnergy && cur + amount < playerMaxEnergy) {
					update(ENERGY, cur + amount);
				} else if(cur + amount < playerMinEnergy) {
					update(ENERGY, playerMinEnergy);
				} else if(cur + amount > playerMaxEnergy) {
					update(ENERGY, playerMaxEnergy);
				}
			}
		}
	}
	
	public final void resetPlayerEnergy() {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote)
				update(ENERGY, playerMaxEnergy);	
		}
	}
	
	//MAX INTOX
	public final float getWitcherMaxIntox() {
		return witcherMaxIntox;
	}
	
	//INTOX
	public final void setWitcherIntox(float amount) {
		if(KeelfyUtils.isServerSide()) {
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
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				float curi = get(EnumProperty.INTOXICATION);
				if(curi + amount > getWitcherMaxIntox())
					update(INTOXICATION, getWitcherMaxIntox());
				else if(curi + amount > 0F)
					update(INTOXICATION, curi + amount);
				else if(curi + amount < 0F) 
					update(INTOXICATION, 0F);
			}
		}
	}
	
	public final void resetWitcherIntox() {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote)
				update(INTOXICATION, 0F);
		}
	}
	
	//IIT
	public final void setWitcherIIT(float amount) {
		if(KeelfyUtils.isServerSide()) {
			witcherIIT = amount;
			sync();
		}
	}
	
	public final void addWitcherIIT(float amount) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				witcherIIT += amount;
				sync();
			}
		}
	}
	
	public final void resetWitcherIIT() {
		if(KeelfyUtils.isServerSide()) {
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
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				float curh = get(EnumProperty.HEALTH);
				float maxh = getPlayerMaxHealth();
					
				if(!(curh <= 0F)) {
					if(curh + amount <= maxh && curh + amount > 0) {
						update(HEALTH, curh + amount);
					} else if(curh + amount <= 0) {
						update(HEALTH, 0F);
					} else if(curh + amount > maxh) {
						update(HEALTH, maxh);
					}
				}
			}
		}
	}
	
	public final void restoreHealth() {
		if(KeelfyUtils.isServerSide()) {
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
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				if(amount > 0) {
					this.playerMaxHealth = amount;
					sync();
				}
			}
		}
	}
	
	public final void addPlayerMaxHealth(float amount) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				if(playerMaxHealth + amount > 0) {
					this.playerMaxHealth += amount;
					sync();
				}
			}
		}
	}
	
	//CLASS
	public final EnumPlayerClass getPlayerClass() {
		return playerClass;
	}
	
	public final void setPlayerClass(EnumPlayerClass newClass) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				if(playerClass != newClass) {
					playerClass = newClass;
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Ваш новый класс: " + newClass.getLocalizedName()));
					sync();
					
					if(newClass == EnumPlayerClass.WITCHER) {
						setPlayerMaxHealth(1200F);
						update(HEALTH, 1200F);
						setWitcherIIT(-(5E-4F));
						update(SIGN, 0);
					}
					
					if(newClass == EnumPlayerClass.HUMAN) {
						setPlayerMaxHealth(1000F);
						setWitcherIIT(0F);
						resetWitcherIntox();
						update(SIGN, EnumSign.NONE.ordinal());
					}
					sync();
				} else 
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Это уже ваш класс!"));
			}
		}
	}
	
	//
	//STUFF
	public final void update(EnumProperty prop, float f) {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				if(prop == EnumProperty.ENERGY && f == 0) {
					player.getDataWatcher().updateObject(prop.id(), 7F);
				}
				player.getDataWatcher().updateObject(prop.id(), f);
			}
		}
	}
	
	public final void update(int id, float f) {
		if(KeelfyUtils.isServerSide())
			if(!player.worldObj.isRemote)
				player.getDataWatcher().updateObject(id, f);
	}
	
	public final void update(EnumProperty prop, int i) {
		if(KeelfyUtils.isServerSide())
			if(!player.worldObj.isRemote)
				player.getDataWatcher().updateObject(prop.id(), i);
	}
	
	public final void update(int id, int i) {
		if(KeelfyUtils.isServerSide())
			if(!player.worldObj.isRemote)
				player.getDataWatcher().updateObject(id, i);
	}

	public final float get(EnumProperty prop) {
		if(prop.getType() == EnumPropertyObject.FLOAT)
			return player.getDataWatcher().getWatchableObjectFloat(prop.id());
		else if(prop.getType() == EnumPropertyObject.INTEGER)
			return player.getDataWatcher().getWatchableObjectInt(prop.id());
		return Float.MAX_VALUE;
	}
	
	private final void sync() {
		if(KeelfyUtils.isServerSide()) {
			if(!player.worldObj.isRemote) {
				ServerPacketHandler.syncPlayer((EntityPlayerMP)player);
			}
		}
	}

	@Override
	public void init(Entity entity, World world) {}
}