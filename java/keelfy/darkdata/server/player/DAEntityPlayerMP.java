/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.server.player;

import java.util.List;

import api.player.server.ServerPlayerAPI;
import api.player.server.ServerPlayerBase;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.managers.HealthManager;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.constants.EnumSwordMaterial;
import keelfy.darkdata.inventory.loot.DAContainerChest;
import keelfy.darkdata.inventory.player.DAPlayerInventory;
import keelfy.darkdata.items.Armor;
import keelfy.darkdata.items.IFastUsable;
import keelfy.darkdata.items.Sword;
import keelfy.darkdata.server.DAServerConfig;
import keelfy.darkdata.utils.DAEntityUtils;
import keelfyutils.KUtils;
import keelfyutils.server.KServer;
import keelfyutils.str.Brush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import noppes.npcs.NpcDamageSource;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.PlayerData;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.PlayerFactionData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

/**
 * @author keelfy
 * @created 13 июл. 2017 г.
 */
public final class DAEntityPlayerMP extends ServerPlayerBase {

	private DamageSource lastSource;

	public DAEntityPlayerMP(final ServerPlayerAPI playerAPI) {
		super(playerAPI);
	}

	public static final void register() {
		if (KUtils.PROTECT_SERVER) {
			ServerPlayerAPI.register(DarkData.MOD_ID, DAEntityPlayerMP.class);
		}
	}

	@Override
	public void displayGUIChest(final IInventory inventory) {
		if (KUtils.PROTECT_SERVER) {
			if (player.openContainer != player.inventoryContainer) {
				player.closeScreen();
			}

			player.getNextWindowId();
			player.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(player.currentWindowId, 0, inventory.getInventoryName(), inventory.getSizeInventory(), inventory.hasCustomInventoryName()));
			player.openContainer = new DAContainerChest(player.inventory, inventory, player.capabilities.isCreativeMode);
			player.openContainer.windowId = player.currentWindowId;
			player.openContainer.addCraftingToCrafters(player);
		}
	}

	@Override
	public final void onUpdate() {
		super.onUpdate();
		if (KUtils.PROTECT_SERVER) {

			if (player.capabilities.isCreativeMode) {
				return;
			}

			int i = 0;
			final DAPlayerInventory inv = DADataManager.getPlayer(player).inventory;

			ItemStack slot = player.inventory.mainInventory[0];
			if (slot != null) {
				inv.addItemStackToInventory(player.inventory.mainInventory[0]);
				player.inventory.mainInventory[0] = null;
			}

			slot = player.inventory.mainInventory[1];
			if (slot != null && (!(slot.getItem() instanceof Sword) || ((Sword) slot.getItem()).getType() == EnumSwordMaterial.SILVER)) {
				inv.addItemStackToInventory(player.inventory.mainInventory[1]);
				player.inventory.mainInventory[1] = null;
			}

			slot = player.inventory.mainInventory[2];
			if (slot != null && (!(slot.getItem() instanceof Sword) || ((Sword) slot.getItem()).getType() == EnumSwordMaterial.STEEL)) {
				inv.addItemStackToInventory(player.inventory.mainInventory[2]);
				player.inventory.mainInventory[2] = null;
			}

			slot = player.inventory.mainInventory[3];
			boolean flag = slot == null || slot.getItem() instanceof Armor || slot.getItem() instanceof Sword && slot.getItem() instanceof ItemArmor && slot.getItem() instanceof ItemSword && slot.getItem() instanceof ItemPotion;
			if (flag) {
				inv.addItemStackToInventory(player.inventory.mainInventory[3]);
				player.inventory.mainInventory[3] = null;
			}

			for (i = 4; i <= 5; i++) {
				slot = player.inventory.mainInventory[i];
				if (slot != null) {
					inv.addItemStackToInventory(player.inventory.mainInventory[i]);
					player.inventory.mainInventory[i] = null;
				}
			}

			slot = player.inventory.mainInventory[6];
			flag = slot == null || slot.getItem() instanceof Armor || slot.getItem() instanceof Sword && slot.getItem() instanceof ItemArmor && slot.getItem() instanceof ItemSword && slot.getItem() instanceof ItemPotion;
			if (flag) {
				inv.addItemStackToInventory(player.inventory.mainInventory[6]);
				player.inventory.mainInventory[6] = null;
			}

			slot = player.inventory.mainInventory[7];
			if (slot != null) {
				inv.addItemStackToInventory(player.inventory.mainInventory[7]);
				player.inventory.mainInventory[7] = null;
			}

			for (i = 8; i <= 11; i++) {
				slot = player.inventory.mainInventory[i];
				if (slot != null && !(slot.getItem() instanceof IFastUsable)) {
					inv.addItemStackToInventory(player.inventory.mainInventory[i]);
					player.inventory.mainInventory[i] = null;
				}
			}
		}
	}

	@Override
	public final void jump() {
		if (KUtils.PROTECT_SERVER) {
			final DAPlayerData dap = DADataManager.getPlayer(player);

			if (dap.energy.get() <= dap.energy.getMin())
				return;

			super.jump();
			dap.energy.change(-3);
		}
	}

	@Override
	public final void heal(float amount) {
		if (KUtils.PROTECT_SERVER) {
			amount = ForgeEventFactory.onLivingHeal(player, amount);
			if (amount <= 0)
				return;

			final HealthManager health = DADataManager.getPlayer(player).health;

			if (health.get() > 0.0F) {
				health.change(amount);
			}
		}
	}

	@Override
	public final boolean attackEntityFrom(final DamageSource source, float amount) {
		if (KUtils.PROTECT_SERVER) {
			if (!DAEntityUtils.isDamageEnabled(player, source.getEntity(), amount))
				return false;

			if (ForgeHooks.onLivingAttack(player, source, amount))
				return false;

			if (player.isEntityInvulnerable())
				return false;
			else if (source == DamageSource.outOfWorld)
				return super.attackEntityFrom(source, amount);
			else if (player.mcServer.isDedicatedServer() && (!player.mcServer.isPVPEnabled()))
				return false;
			else if (player.capabilities.disableDamage && !source.canHarmInCreative())
				return false;

			final DAPlayerData dap = DADataManager.getPlayer(player);
			final HealthManager health = dap.health;

			if (player.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
				amount = 0;
			}

			if (amount <= 0)
				return false;

			if (health.get() <= 0)
				return false;

			if (player.isPlayerSleeping()) {
				player.wakeUpPlayer(true, true, false);
			}

			Entity entity = source.getEntity();
			if (entity != null) {

				if (entity instanceof EntityArrow && ((EntityArrow) entity).shootingEntity != null) {
					entity = ((EntityArrow) entity).shootingEntity;

				}

				if (entity instanceof EntityPlayer) {
					if (!player.canAttackPlayer((EntityPlayer) entity))
						return false;
				}

			}

			player.worldObj.setEntityState(player, (byte) 2);

			if (source == DamageSource.fall) {
				DAEntityUtils.attack(player, health, amount * 60, false);
			} else if (source == DamageSource.inFire) {
				DAEntityUtils.attack(player, health, 50, false);
			}

			if (source instanceof NpcDamageSource && entity != null && entity instanceof EntityNPCInterface) {
				final EntityNPCInterface npc = (EntityNPCInterface) entity;

				if (npc.stats.knockback > 0) {
					double d1 = entity.posX - player.posX;
					double d0;

					for (d0 = entity.posZ - player.posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
						d1 = (Math.random() - Math.random()) * 0.01D;
					}

					player.attackedAtYaw = (float) (Math.atan2(d0, d1) * 180.0D / Math.PI) - player.rotationYaw;
					player.knockBack(player, amount / 10, d1, d0);

					npc.motionX *= 0.6D;
					npc.motionZ *= 0.6D;
				}

				DAEntityUtils.attack(player, health, amount, true);
			} else if (source != DamageSource.inFire && source != DamageSource.fall) {
				if (source instanceof EntityDamageSourceIndirect) {
					DAEntityUtils.attack(player, health, 200, true);
				} else {
					DAEntityUtils.attack(player, health, amount, true);
				}
			}

			dap.inventory.damageArmor();
			DAPlayerDataHandler.Instance.setWitcherHealingTimer(player, 100);
			DAPlayerDataHandler.Instance.lastSource = source;

			if (health.get() <= 0.0F) {
				final String s = "game.player.die";
				final float soundPitch = (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.2F + 1.0F;
				player.playSound(s, 1.0F, soundPitch);

				player.onDeath(source);
			} else {
				final String s = "game.player.hurt";
				final float soundPitch = (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.2F + 1.0F;
				player.playSound(s, 1.0F, soundPitch);
			}
			dap.sync();
			return true;
		}
		return false;
	}

	@Override
	public final void afterOnDeath(final DamageSource source) {
		super.afterOnDeath(source);
		if (KUtils.PROTECT_SERVER) {
			final DAPlayerData dap = DADataManager.getPlayer(player);

			dap.inventory.dropAllItems();

			if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer) {
				final EntityPlayer killer = (EntityPlayer) source.getEntity();
				final int cfi = DAServerConfig.Instance.carrerasFactionId;
				final double r = DAServerConfig.Instance.radiusOfReputationDecreasing;

				if (!FactionController.getInstance().factions.containsKey(cfi))
					return;

				final AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(killer.posX - r, killer.posY - r, killer.posZ - r, killer.posX + r, killer.posY + r, killer.posZ + r);
				final List<EntityCustomNpc> entities = killer.worldObj.getEntitiesWithinAABB(EntityCustomNpc.class, aabb);

				if (entities.size() > 0) {
					for (final EntityCustomNpc npc : entities) {
						if (npc.getFaction().id == cfi) {
							final PlayerData data = PlayerDataController.instance.getPlayerData(killer);
							final PlayerFactionData factionData = data.factionData;
							factionData.factionData.put(cfi, 10);
							data.saveNBTData(null);
							KServer.sendMessage(killer, Brush.RED + "Вы убили игрока на глазах у стражи!");
							break;
						}
					}
				}
			}
		}
	}
}
