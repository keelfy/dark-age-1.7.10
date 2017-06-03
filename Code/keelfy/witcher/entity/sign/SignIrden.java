package keelfy.witcher.entity.sign;

import java.util.List;

import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.entity.player.DAPlayerUtil.Property;
import keelfy.witcher.util.DAUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.World;

public class SignIrden extends Entity {

   private EntityPlayer owner;
   
   public SignIrden(World world, EntityPlayer player) {
      super(world);
      this.setAngles(1.0F, 1.0F);
      this.owner = player;
   }

   public SignIrden(World world) {
      super(world);
      this.setAngles(1.0F, 1.0F);
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      this.worldObj.spawnParticle("happyVillager", posX + 2.0D, posY + 0.7D, posZ - 0.20000000298023224D, 2.0D, 2.0D, 2.0D);
      this.worldObj.spawnParticle("happyVillager", posX + 1.25D, posY + 0.7D, posZ + 1.100000023841858D, 2.0D, 2.0D, 2.0D);
      this.worldObj.spawnParticle("happyVillager", posX - 0.7D, posY + 0.7D, posZ + 1.0D, 2.0D, 2.0D, 2.0D);
      this.worldObj.spawnParticle("happyVillager", posX + 1.25D, posY + 0.7D, posZ - 1.600000023841858D, 2.0D, 2.0D, 2.0D);
      this.worldObj.spawnParticle("happyVillager", posX - 0.4D, posY + 0.7D, posZ - 1.600000023841858D, 2.0D, 2.0D, 2.0D);
      this.worldObj.spawnParticle("happyVillager", posX - 1.45D, posY + 0.7D, posZ - 0.20000000298023224D, 2.0D, 2.0D, 2.0D);
      
      if(this.ticksExisted > 1000) this.setDead();

      Object p = null;
      boolean id = false;
      List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(4.0D, 4.0D, 4.0D));

      for(int i = 0; i < list.size(); ++i) {
         if(list.get(i) instanceof EntityPlayerMP && this.owner != null) {
            EntityPlayerMP var10 = (EntityPlayerMP)list.get(i);
            Team ownerTeam = this.owner.getTeam();
            Team team = var10.getTeam();
            String groupOwner = null;
            if(ownerTeam != null) {
               groupOwner = ownerTeam.getRegisteredName();
            }

            String group = null;
            if(team != null) {
               group = team.getRegisteredName();
            }

            if(group != null) {
               if(groupOwner != null && groupOwner != group) {
                  var10.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 20, 2));
                  System.out.println("asa");
               }
            } else if(var10 != this.owner) {
               var10.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 20, 2));
            }
         } else if(list.get(i) instanceof EntityLivingBase) {
            EntityLivingBase e = (EntityLivingBase)list.get(i);
            e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 20, 2));
         }
      }

   }

	@Override
	protected void entityInit() {}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {}

	public void handle(EntityPlayerMP sender) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (!this.worldObj.isRemote) {
				
				DAPlayer wcp = DAPlayer.get(sender);
				if (wcp != null && wcp.get(Property.ENERGY) > wcp.getPlayerMaxEnergy() - 7) {
					setPosition(sender.posX, sender.worldObj.getTopSolidOrLiquidBlock((int) sender.posX, (int) sender.posZ), sender.posZ);
					this.worldObj.spawnEntityInWorld(this);
					ISign.useSign("customnpcs:signs.irden", this.worldObj, sender);
					wcp.update(Property.ENERGY, 0F);
				}
			}
		}
	}
}