package keelfy.witcher.client.renderer;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import keelfy.witcher.client.gui.GuiDAInventory;
import keelfy.witcher.client.models.entity.player.ModelHand;
import keelfy.witcher.handler.client.ResourceHandler.Model;
import keelfy.witcher.handler.client.ResourceHandler.Texture;
import keelfy.witcher.handler.client.ResourceHandler.Model.WCM;
import keelfy.witcher.handler.client.ResourceHandler.Texture.WCT;
import keelfy.witcher.item.Melee;
import keelfy.witcher.item.SwordSilver;
import keelfy.witcher.item.SwordSteel;
import keelfy.witcher.util.DAUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.IModelCustom;

/**
 * @author keelfy
 */
public class RendererSword implements IItemRenderer {

	private IModelCustom model;
	private ResourceLocation texture;
	private ResourceLocation texturelow;
	private Minecraft mc;
	
	private ModelHand MODEL_HAND;
	
	public float globalAnimationSpeed;
	public boolean KeybindAttack = false;
	private boolean FinishIdleAnimation = false;
	private int t1 = Math.abs(6) - 1;
	private Melee sword;
	
	private float handPosX = 1.049F, handPosY = 0.3F, handPosZ = -0.15F;
	private float handRotX = 139.25F, handRotY = -45.75F, handRotZ = -45.5F;
	private float handPosX2 = -0.124F, handPosY2 = 0.1F, handPosZ2 = -0.55F;
	private float handRotX2 = 64.25F, handRotY2 = 294.0F, handRotZ2 = -111.0F;
	private float fX = 0.0F, fY = 0.0F, fZ = 0.0F;
	private float tX = 0.0F, tY = 0.0F, tZ = 0.0F;
	private float posX = 0.0F, posY = 0.0F, posZ = 0.0F;
	private float tPosX = 0.75F, tPosY = 0.5F, tPosZ = 0F;
	private float scale = 4.0F;
	private float hmtx = 0.7F, hmty = 0.7F, hmtz = 0.4F;
	private float hmr1 = 25.0F, hmr2 = 10.0F;
	private float hctx, hcty, hctz;
	private float hcr1, hcr2;
	private float rotiX, rotiY, rotiZ;
	
	public RendererSword(Item item) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if(item instanceof Melee) {
				this.sword = (Melee)item;
				this.MODEL_HAND = new ModelHand();
				this.mc = Minecraft.getMinecraft();
				
				model = Model.get(WCM.SWORD, sword.getSet() + "/models/" + sword.getTextureName() + ".model");
				texture = Texture.get(WCT.SWORD, sword.getSet() + "/models/" + sword.getTextureName() + ".tex");
				texturelow = Texture.get(WCT.SWORD, sword.getSet() + "/models/" + sword.getTextureName() + ".texlow");
			}
		}
	}
	
	public void setResources(IModelCustom model, ResourceLocation texture, ResourceLocation textureLow) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.model = model;
			this.texture = texture;
			this.texturelow = textureLow;
		}
	}
	
	private float entTPosX = -0.32F, entTPosY = -1.18F, entTPosZ = 1.08F;
	private float entScale = 1.1215F;
	public void setEntityRender(float tPosX, float tPosY, float tPosZ, float scale) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.entTPosX += tPosX;
			this.entTPosY += tPosY;
			this.entTPosZ += tPosZ;
			this.entScale = scale;
		}
		
	}
	
	private float eqTPosX = 0.82F, eqTPosY = -0.93F, eqTPosZ = -0.18F;
	private float eqScale = 0.6215F;
	public void setEquippedRender(float tPosX, float tPosY, float tPosZ, float scale) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.eqTPosX += tPosX;
			this.eqTPosY += tPosY;
			this.eqTPosZ += tPosZ;
			this.eqScale = scale;
		}
	}
	
	private float efpTPosX = 0F, efpTPosY = 0F, efpTPosZ = 0F;
	private float efpScale = 1F;
	public void setEFPRender(float tPosX, float tPosY, float tPosZ, float scale) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			this.efpTPosX = tPosX;
			this.efpTPosY = tPosY;
			this.efpTPosZ = tPosZ;
			this.efpScale = scale;
		}
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			switch (type) {
			case ENTITY:
				return true;
			case EQUIPPED:
				return true;
			case EQUIPPED_FIRST_PERSON:
				return true;
			case INVENTORY:
				return true;
			default:
				return false;
			}
		} else 
			return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			globalAnimationSpeed = 5.08F;
			
			if (mc.gameSettings.keyBindings[0].getKeyCode() > 0) {
				if (Keyboard.isKeyDown(mc.gameSettings.keyBindings[0].getKeyCode())) {
					KeybindAttack = true;
				} else {
					KeybindAttack = false;
				}
			} else if (Mouse.isButtonDown(100 + mc.gameSettings.keyBindings[0].getKeyCode())) {
				KeybindAttack = true;
			} else {
				KeybindAttack = false;
			}
	
			if (this.t1 > 100) {
				this.t1 = 100;
			}
	
			switch (type) {
			case INVENTORY:
				if(sword instanceof SwordSilver) RendererRarity.renderSilverSword(item); 
				else if(sword instanceof SwordSteel) RendererRarity.renderSteelSword(item);
				break;
			case ENTITY:
				GL11.glPushMatrix();
					GL11.glTranslatef(tPosX + entTPosX, tPosY + entTPosY, tPosZ + entTPosZ);
					mc.renderEngine.bindTexture(texturelow);
					GL11.glScalef(entScale, entScale, entScale);
					GL11.glRotatef(tX, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(tY + 180.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(tZ + 40.0F, 0.0F, 0.0F, 1.0F);
					if(!((EntityItem)data[1]).getEntityItem().isOnItemFrame()) {
			            if(((Entity)data[1]).rotationPitch == 0.0F) {
			               Random rnd = new Random();
			               ((Entity)data[1]).rotationPitch = rnd.nextFloat() * 360.0F;
			            }
	
			            GL11.glRotatef(((Entity)data[1]).rotationPitch, 0.0F, 1.0F, 0.0F);
			         } else {
			            GL11.glTranslatef(0.1F, 0.5F, 0.0F);
			            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			         }
					this.model.renderAll();
				GL11.glPopMatrix();
				break;
			case EQUIPPED:
				GL11.glPushMatrix();
				tZ = -40.0F;
				tPosX = -0.1F;
				tPosY = 0.9F;
				tPosZ = 0.1F;
				GL11.glTranslatef(tPosX + eqTPosX, tPosY + eqTPosY, tPosZ + eqTPosZ);
				GL11.glScalef(eqScale, eqScale, eqScale);
				GL11.glRotatef(tX + 100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(tY + 210.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(tZ + 40.0F, 0.0F, 0.0F, 1.0F);
				mc.renderEngine.bindTexture(this.texturelow);
				if (Mouse.isButtonDown(1) && !(mc.currentScreen instanceof GuiDAInventory)) {
					GL11.glTranslatef(tPosX + 0.1F, tPosY - 0.73F, tPosZ - 0.24F);
					GL11.glRotatef(tX - 160.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(tY - 140.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(tZ, 0.0F, 0.0F, 1.0F);
				}
	
				this.model.renderAll();
				GL11.glPopMatrix();
				break;
			case EQUIPPED_FIRST_PERSON:
				this.attackAnim(1);
				scale = 2.0F;
				posX = -0.1F;
				posY = 3.0F;
				posZ = 1.4F;
				fX = 10.0F;
				fY = 0.0F;
				fZ = -55.0F;
				tZ = -40.0F;
				tPosX = -0.1F;
				tPosY = 0.9F;
				tPosZ = 0.1F;
				handPosX = 1.0F;
				handPosY = -0.2F;
				handPosZ = 0.6F;
				handRotX = 160.0F;
				handRotY = -30.0F;
				handRotZ = -30.0F;
				handPosX2 = 0.3F;
				handPosY2 = -0.8F;
				handPosZ2 = 0.6F;
				handRotX2 = -100.0F;
				handRotY2 = 230.0F;
				handRotZ2 = -270.0F;
				GL11.glPushMatrix();
				GL11.glRotatef(rotiX, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(rotiY, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(rotiZ, 0.0F, 0.0F, 1.0F);
				if (mc.thePlayer.moveForward == 0.0F && mc.thePlayer.moveStrafing == 0.0F && this.rotiY < 2.0F) {
					if (!this.FinishIdleAnimation) {
						this.rotiX += 0.01F;
						this.rotiY += 0.01F;
						this.rotiZ += 0.01F;
						if (this.rotiY >= 1.8D) {
							this.FinishIdleAnimation = true;
						}
					} else {
						if (this.rotiY >= 0.0F) {
							this.rotiX -= 0.01F;
							this.rotiY -= 0.01F;
							this.rotiZ -= 0.01F;
						}
	
						if (this.rotiY <= 0.1D) {
							this.FinishIdleAnimation = false;
						}
					}
				}
	
				GL11.glPushMatrix();
				GL11.glTranslatef(posX + efpTPosX, posY + efpTPosY, posZ + efpTPosZ);
				GL11.glRotatef(tX + 90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(tY + 210.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(tZ + 20.0F, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(efpScale, efpScale, efpScale);
				mc.renderEngine.bindTexture(this.texture);
				this.model.renderAll();
				GL11.glPopMatrix();
				if (Mouse.isButtonDown(1)) {
					GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
				}
	
				GL11.glPushMatrix();
				GL11.glTranslatef(handPosX2 + 0.4F, handPosY2 + 0.35F, handPosZ2);
				GL11.glRotatef(handRotX2 + 70.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(handRotY2 - 20.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(handRotZ2 - 95.0F, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(scale, scale, scale + 1.0F);
				MODEL_HAND.render(mc.thePlayer, 1);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glTranslatef(handPosX2, handPosY2, handPosZ2);
				GL11.glRotatef(handRotX2, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(handRotY2, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(handRotZ2, 0.0F, 0.0F, 1.0F);
				GL11.glScalef(scale, scale, scale + 1.5F);
				MODEL_HAND.render(mc.thePlayer, 1);
				GL11.glPopMatrix();
				GL11.glPopMatrix();
			default:
				break;
			}
		}
	}

	public void attackAnim(int i) {
		if(!DAUtil.SERVER || DAUtil.DEBUG_MODE) {
			if (this.KeybindAttack && mc.currentScreen == null) {
				;
			}
	
			if (i == 1) {
				if (this.KeybindAttack && mc.currentScreen == null) {
					this.hmtx = 10.0F;
					this.hmty = 45.0F;
					this.hmtz = 50.0F;
					if (this.hctx < this.hmtx) {
						this.hctx += 30.0F / (this.globalAnimationSpeed * 2.0F);
					}
	
					if (this.hctz < this.hmtz) {
						this.hctz += 30.0F / (this.globalAnimationSpeed * 2.0F);
					}
	
					if (this.hcty < this.hmty) {
						this.hcty += 30.0F / (this.globalAnimationSpeed * 2.0F);
					}
				} else {
					if (this.hctx > 0.0F) {
						this.hctx -= 30.0F / (this.globalAnimationSpeed * 2.0F);
					}
	
					if (this.hctz > 0.0F) {
						this.hctz -= 30.0F / (this.globalAnimationSpeed * 2.0F);
					}
	
					if (this.hcty > 0.0F) {
						this.hcty -= 30.0F / (this.globalAnimationSpeed * 2.0F);
					}
				}
	
				GL11.glRotatef(this.hctx, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(this.hcty, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-this.hctz, 0.0F, 0.0F, 1.0F);
			}
		}
	}
}
