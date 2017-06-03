package keelfy.witcher.entity.sign;

import keelfy.witcher.entity.player.DAPlayer;
import keelfy.witcher.entity.player.DAPlayerUtil.Property;
import keelfy.witcher.util.DAUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SignAksi extends EntityLivingBase {

	public SignAksi(World world) {
		super(world);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.ticksExisted > 1)
			this.worldObj.spawnParticle("heart", this.posX, this.posY + 1.0D, this.posZ, 255.0D, 255.0D, 255.0D);

		if (this.ticksExisted > 2)
			this.setDead();
	}

	@Override
	protected void applyEntityAttributes() {
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed);
		if (!this.isAIEnabled())
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
	}

	public void handle(EntityPlayerMP sender) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (!this.worldObj.isRemote) {
				EntityLivingBase e = ISign.getLookingEntity(14.0D, sender);
				DAPlayer wcp = DAPlayer.get(sender);
				
				if (wcp != null && e != null && wcp.get(Property.ENERGY) > wcp.getPlayerMaxEnergy() - 7) {
					int rand = this.worldObj.rand.nextInt(100);
					setPosition(sender.posX, sender.posY, sender.posZ);
					this.worldObj.spawnEntityInWorld(this);
					double motion = 3.0D;
					double y = 0.15000000596046448D;
					motionX = sender.getLookVec().xCoord * motion;
					motionY = sender.getLookVec().zCoord * motion;
					motionZ = y;
					ISign.useSign("customnpcs:signs.aksi", this.worldObj, sender);
					e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100 + rand, 10, false));
					e.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 100 + rand, 10, false));
					wcp.update(Property.ENERGY, 0F);
				}
			}
		}
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
}
