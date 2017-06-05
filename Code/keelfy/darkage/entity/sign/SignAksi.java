package keelfy.darkage.entity.sign;

import keelfy.darkage.entity.player.DAPlayer;
import keelfy.darkage.entity.player.DAPlayerUtil.Property;
import keelfy.darkage.util.DAUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SignAksi extends EntityLivingBase {

	private EntityPlayer owner;
	
	public SignAksi(World world) {
		super(world);
	}
	
	public void setOwner(EntityPlayer owner) {
		this.owner = owner;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (this.ticksExisted > 2)
			this.setDead();
		
		if (this.ticksExisted > 1)
			this.worldObj.spawnParticle("heart", this.posX, this.posY + 1.0D, this.posZ, 255.0D, 255.0D, 255.0D);
	}

	@Override
	protected void applyEntityAttributes() {
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed);
		if (!this.isAIEnabled())
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
	}

	public void handle(int entityId) {
		if(DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (!this.worldObj.isRemote && entityId != -1) {
				Entity e1 = owner.worldObj.getEntityByID(entityId);
				DAPlayer wcp = DAPlayer.get(owner);
				
				if (wcp != null && e1 != null && e1 instanceof EntityLiving && wcp.get(Property.ENERGY) > wcp.getPlayerMaxEnergy() - 7) {
					EntityLiving e = (EntityLiving) e1;
					int rand = this.worldObj.rand.nextInt(100);
					setPosition(owner.posX, owner.posY, owner.posZ);
					this.worldObj.spawnEntityInWorld(this);
					double motion = 3.0D;
					double y = 0.15000000596046448D;
					motionX = owner.getLookVec().xCoord * motion;
					motionY = owner.getLookVec().zCoord * motion;
					motionZ = y;
					ISign.useSign("customnpcs:signs.aksi", this.worldObj, owner);
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
