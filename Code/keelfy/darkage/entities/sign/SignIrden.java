/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.entities.sign;

import java.util.List;

import keelfy.darkage.constants.EnumProperty;
import keelfy.darkage.entities.player.DAPlayer;
import keelfy.darkage.utils.DAEntityUtils;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.World;

public class SignIrden extends Entity {

	private EntityPlayer owner;

	public SignIrden(World world) {
		super(world);
		
		if(KeelfyUtils.isClientSide())
			this.setAngles(1.0F, 1.0F);
	}
	
	public void setOwner(EntityPlayer owner) {
		this.owner = owner;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.worldObj.spawnParticle("happyVillager", posX + 2.0D, posY + 0.7D, posZ - 0.20000000298023224D, 2.0D, 2.0D,2.0D);
		this.worldObj.spawnParticle("happyVillager", posX + 1.25D, posY + 0.7D, posZ + 1.100000023841858D, 2.0D, 2.0D,2.0D);
		this.worldObj.spawnParticle("happyVillager", posX - 0.7D, posY + 0.7D, posZ + 1.0D, 2.0D, 2.0D, 2.0D);
		this.worldObj.spawnParticle("happyVillager", posX + 1.25D, posY + 0.7D, posZ - 1.600000023841858D, 2.0D, 2.0D,2.0D);
		this.worldObj.spawnParticle("happyVillager", posX - 0.4D, posY + 0.7D, posZ - 1.600000023841858D, 2.0D, 2.0D,2.0D);
		this.worldObj.spawnParticle("happyVillager", posX - 1.45D, posY + 0.7D, posZ - 0.20000000298023224D, 2.0D, 2.0D,2.0D);

		if(KeelfyUtils.isServerSide()) {
			if (this.ticksExisted > 1000)
				this.setDead();
	
			Object p = null;
			boolean id = false;
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(4.0D, 4.0D, 4.0D));
	
			for (int i = 0; i < list.size(); ++i) {
				if (list.get(i) instanceof EntityPlayerMP && this.owner != null) {
					EntityPlayerMP var10 = (EntityPlayerMP) list.get(i);
					Team ownerTeam = this.owner.getTeam();
					Team team = var10.getTeam();
					String groupOwner = null;
					if (ownerTeam != null) {
						groupOwner = ownerTeam.getRegisteredName();
					}
	
					String group = null;
					if (team != null) {
						group = team.getRegisteredName();
					}
	
					if (group != null) {
						if (groupOwner != null && groupOwner != group) {
							var10.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 20, 2));
						}
					} else if (var10 != this.owner) {
						var10.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 20, 2));
					}
				} else if (list.get(i) instanceof EntityLiving) {
					EntityLiving e = (EntityLiving) list.get(i);
					e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 20, 2));
				}
			}
		}
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}

	public void handle() {
		if (KeelfyUtils.isServerSide()) {
			if (!this.worldObj.isRemote) {
				DAPlayer dap = DAPlayer.get(owner);
				
				if (dap != null && dap.get(EnumProperty.ENERGY) > dap.getPlayerMaxEnergy() - 10) {
					setPosition(owner.posX, owner.worldObj.getTopSolidOrLiquidBlock((int) owner.posX, (int) owner.posZ), owner.posZ);
					this.worldObj.spawnEntityInWorld(this);
					DAEntityUtils.useSign("irden", this.worldObj, owner);
					dap.update(EnumProperty.ENERGY, dap.getPlayerMinEnergy());
				}
			}
		}
	}
}
