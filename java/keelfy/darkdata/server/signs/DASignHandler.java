package keelfy.darkdata.server.signs;

import cpw.mods.fml.common.registry.EntityRegistry;
import keelfy.darkcore.common.player.DADataManager;
import keelfy.darkcore.common.player.DAPlayerData;
import keelfy.darkcore.common.player.EnergyManager;
import keelfy.darkcore.common.player.SignsManager;
import keelfy.darkdata.DarkData;
import keelfy.darkdata.constants.EnumEffect;
import keelfy.darkdata.constants.EnumSign;
import keelfy.darkdata.entities.EntityIrden;
import keelfy.darkdata.utils.DAEntityUtils;
import keelfyutils.KUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;

/**
 * @author keelfy
 * @created 26 июл. 2017 г.
 */
public final class DASignHandler {

	public static final void registerSigns() {
		EntityRegistry.registerModEntity(EntityIrden.class, "EntitySignIrden", EntityRegistry.findGlobalUniqueEntityId(), DarkData.instance, 64, 5, false);
	}

	public static final void handle(final EntityPlayerMP owner, final EnumSign sign, final int entityId) {
		if (KUtils.PROTECT_SERVER) {
			if (DAEntityUtils.isDamageEnabled(owner, owner.worldObj.getEntityByID(entityId), 1)) {
				switch (sign) {
				case Aard:
					handleAard(owner, entityId);
					break;
				case Aksi:
					handleAksi(owner, entityId);
					break;
				case Igni:
					handleIgni(owner, entityId);
					break;
				case Irden:
					handleIrden(owner);
					break;
				case Kven:
					handleKven(owner);
					break;
				default:
					break;
				}
			}
		}
	}

	public static final void handleIrden(final EntityPlayerMP owner) {
		if (KUtils.PROTECT_SERVER) {
			final EntityIrden irden = new EntityIrden(owner.worldObj, owner);
			final EnergyManager energy = DADataManager.getPlayer(owner).energy;

			if (energy.get() >= energy.getMax() - energy.getMin()) {
				irden.setPosition(owner);
				System.out.println(irden.getEntityId());
				owner.worldObj.spawnEntityInWorld(irden);
				SignsManager.useSign("irden", owner);
				energy.tire();
			}
		}
	}

	public static final void handleAard(final EntityPlayerMP owner, final int entityId) {
		if (KUtils.PROTECT_SERVER) {
			if (entityId != -1) {
				final Entity e1 = owner.worldObj.getEntityByID(entityId);
				final EnergyManager energy = DADataManager.getPlayer(owner).energy;

				if (e1 == null || !(e1 instanceof EntityLivingBase))
					return;

				if (energy.get() > energy.getMax() - energy.getMin()) {
					final EntityLivingBase e = (EntityLivingBase) e1;
					final double motion = 1.0D;

					if (e.ridingEntity != null) {
						e.mountEntity(null);
					}

					e.addVelocity(-MathHelper.sin(owner.rotationYaw * (float) Math.PI / 180.0F) * 0.5F, 0.1D, MathHelper.cos(owner.rotationYaw * (float) Math.PI / 180.0F) * 0.5F);

					// e.motionX = owner.getLookVec().xCoord * motion;
					// e.motionZ = owner.getLookVec().zCoord * motion;
					// e.motionY = 0.15000000596046448D * motion;

					SignsManager.useSign("aard", owner);
					e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 60, 10));
					energy.tire();
				}
			}
		}
	}

	public static final void handleAksi(final EntityPlayerMP owner, final int entityId) {
		if (KUtils.PROTECT_SERVER) {
			if (entityId != -1) {
				final Entity e1 = owner.worldObj.getEntityByID(entityId);
				final DAPlayerData dap = DADataManager.getPlayer(owner);

				if (e1 == null || !(e1 instanceof EntityLivingBase))
					return;

				if (dap.energy.get() > dap.energy.getMax() - dap.energy.getMin()) {
					final EntityLivingBase e = (EntityLivingBase) e1;
					SignsManager.useSign("aksi", owner);

					if (e instanceof EntityPlayer) {
						DADataManager.getPlayer((EntityPlayer) e).energy.tire();
					}

					e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 40, 10, false));
					e.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 40, 10, false));
					dap.energy.tire();
				}
			}
		}
	}

	public static final void handleIgni(final EntityPlayerMP owner, final int entityId) {
		if (KUtils.PROTECT_SERVER) {
			if (entityId != -1) {
				final Entity e1 = owner.worldObj.getEntityByID(entityId);
				final DAPlayerData dap = DADataManager.getPlayer(owner);

				if (e1 == null || !(e1 instanceof EntityLivingBase))
					return;

				if (dap.energy.get() > dap.energy.getMax() - dap.energy.getMin()) {
					final EntityLivingBase e = (EntityLivingBase) e1;

					SignsManager.useSign("igni", owner);
					e.setFire(3 + owner.worldObj.rand.nextInt(10));
					dap.energy.tire();
				}
			}
		}
	}

	public static final void handleKven(final EntityPlayerMP owner) {
		if (KUtils.PROTECT_SERVER) {
			final DAPlayerData dap = DADataManager.getPlayer(owner);

			if (dap.energy.get() > dap.energy.getMax() - dap.energy.getMin()) {
				SignsManager.useSign("kven", owner);
				dap.effects.perform(EnumEffect.Resistance, 400, 1.0F, false);
				dap.energy.tire();
			}
		}
	}

}
