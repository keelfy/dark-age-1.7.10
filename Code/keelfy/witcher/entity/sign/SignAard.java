package keelfy.witcher.entity.sign;

import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.entity.player.DAPlayerUtil.Property;
import keelfy.witcher.util.DAUtil;
import keelfy.witcherBlocks.register.BlockRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SignAard extends EntityLivingBase {

   public SignAard(World world) {
      super(world);
   }

   @Override
   public void onUpdate() {
	   super.onUpdate();
	   
	   if(this.ticksExisted > 120) this.setDead();

	   if(this.ticksExisted < 10)
         this.worldObj.spawnParticle("largeexplode", this.posX, this.posY + 0.5D, this.posZ, 1.0D, 0.0D, 0.0D);
   }

   @Override
   protected void applyEntityAttributes() {
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed);
      
      if(!this.isAIEnabled()) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
   }

	@Override
	public ItemStack getHeldItem() {
		return null;
	}
	
	@Override
	public ItemStack getEquipmentInSlot(int slot) {
		return null;
	}
	
	@Override
	public void setCurrentItemOrArmor(int slot, ItemStack stack) {}
	
	@Override
	public ItemStack[] getLastActiveItems() {
		return null;
	}

	public void handle(EntityPlayerMP sender) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (!this.worldObj.isRemote) {
				EntityLiving e = ISign.getLookingEntity(4.0D, sender);
				DAPlayer wcp = DAPlayer.get(sender);
	
				if (wcp != null && e != null && wcp.get(Property.ENERGY) > wcp.getPlayerMaxEnergy() - 7) {
					double motion = 1.0D;
					double y = 0.15000000596046448D;
					if (sender.experienceLevel >= 7) {
						motion = 1.5D;
					} else if (sender.experienceLevel >= 80) {
						motion = 2000.0D;
						y = 100.0D;
					} else {
						motion = 1.0D;
						y = 0.15000000596046448D;
					}
	
					setPosition(sender.posX, sender.posY, sender.posZ);
					this.worldObj.spawnEntityInWorld(this);
					motionX = sender.getLookVec().xCoord * motion;
					motionY = sender.getLookVec().zCoord * motion;
					motionZ = y;
					e.motionX = sender.getLookVec().xCoord * motion;
					e.motionZ = sender.getLookVec().zCoord * motion;
					e.motionY = y;
					sender.worldObj.setBlock((int) sender.posX, (int) sender.posY + 2, (int) sender.posZ, BlockRegister.light, 0, 3);
					ISign.useSign("customnpcs:signs.aard", this.worldObj, sender);
					e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 60, 10));
					wcp.update(Property.ENERGY, 0F);
				}
			}
		}
	}
}
