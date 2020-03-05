/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.entities;

import java.util.List;

import keelfyutils.KUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public final class EntityIrden extends Entity {

	private EntityPlayer owner;

	public EntityIrden(final World world) {
		super(world);
	}

	public EntityIrden(final World world, final EntityPlayer owner) {
		super(world);

		this.owner = owner;
	}

	public final void setPosition(final EntityPlayer player) {
		if (KUtils.PROTECT_SERVER) {
			final double posX = player.posX;
			final double posY = player.worldObj.getTopSolidOrLiquidBlock((int) player.posX, (int) player.posZ);
			final double posZ = player.posZ;
			super.setPosition(posX, posY, posZ);
		}
	}

	@Override
	public final void onEntityUpdate() {
		super.onEntityUpdate();

		particle(2.0D, -0.20000000298023224D);
		particle(1.25D, 1.100000023841858D);
		particle(-0.7D, 1.0D);
		particle(1.25D, -1.600000023841858D);
		particle(-0.4D, -1.600000023841858D);
		particle(-1.45D, -0.20000000298023224D);

		if (this.ticksExisted > 1000) {
			this.setDead();
		}

		if (KUtils.PROTECT_SERVER) {
			final List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
					boundingBox.expand(4.0D, 4.0D, 4.0D));

			for (Entity entity : list) {
				if (entity instanceof EntityLivingBase) {
					if (owner == null || owner.getUniqueID() != entity.getUniqueID()) {
						((EntityLivingBase) entity)
								.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 20, 2));
					}
				}
			}
		}
	}

	private final void particle(final double x, final double z) {
		worldObj.spawnParticle("happyVillager", posX + x, posY + 0.4D, posZ + z, 2.0D, 2.0D, 2.0D);
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(final NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(final NBTTagCompound compound) {}
}
