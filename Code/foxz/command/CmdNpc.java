package foxz.command;

import java.util.ArrayList;
import java.util.List;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import foxz.commandhelper.permissions.PlayerOnly;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

@Command(
        name="npc",
        desc="NPC manipulation",
        usage="<name> <command>"
)
public class CmdNpc extends ChMcLogger {

    CmdNpc(Object ctorParm) {
        super (ctorParm);
    }
    
    public EntityNPCInterface selectedNpc;
  
    
    @SubCommand(
            desc="Set Home (respawn place)",
            usage=""
    )
    public void home(String[] args){
        double posX = pcParam.getPlayerCoordinates().posX;
        double posY = pcParam.getPlayerCoordinates().posY;
        double posZ = pcParam.getPlayerCoordinates().posZ;
        
        if(args.length == 3){
            posX = CommandBase.func_110666_a(pcParam, selectedNpc.posX, args[0]);
            posY = CommandBase.func_110665_a(pcParam, selectedNpc.posY, args[1].trim(), 0, 0);
            posZ = CommandBase.func_110666_a(pcParam, selectedNpc.posZ, args[2]);
        }
        
        selectedNpc.ai.startPos = new int[]{MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)};
    }
    
    @SubCommand(
            desc="Set npc visibility",
            usage=""
    )
    public void visible(String[] args){
    	if(args.length < 1)
    		return;
    	boolean bo = args[0].equalsIgnoreCase("true");
    	boolean semi = args[0].equalsIgnoreCase("semi");
    	
    	int current = selectedNpc.display.visible;
    	if(semi)
    		selectedNpc.display.visible = 2;
    	else if(bo)
    		selectedNpc.display.visible = 0;
    	else
    		selectedNpc.display.visible = 1;
    	
    	if(current != selectedNpc.display.visible)
			NoppesUtilServer.updateNpc(selectedNpc);
    		
    }

    
    @SubCommand(
            desc="Set npc name",
            usage=""
    )
    public void name(String[] args){
    	if(args.length < 1)
    		return;
    	
    	String name = args[0];
    	for(int i = 1; i < args.length; i++){
    		name += " " + args[i];
    	}
    	
    	if(!selectedNpc.display.name.equals(name)){
        	selectedNpc.display.name = name;
			NoppesUtilServer.updateNpc(selectedNpc);
    	}
    }
    
    @SubCommand(
            desc = "Creates an NPC",
            usage = "[name]",
            permissions = {PlayerOnly.class, OpOnly.class}
    )
    public void create(String[] args) {
        EntityPlayerMP player = (EntityPlayerMP) pcParam;
        World pw = player.getEntityWorld();
        EntityCustomNpc npc = new EntityCustomNpc(pw);
        if(args.length > 0)
        	npc.display.name = args[0];
        npc.setPositionAndRotation(player.posX, player.posY, player.posZ, player.cameraYaw, player.cameraPitch);
        npc.ai.startPos = new int[]{MathHelper.floor_double(player.posX),MathHelper.floor_double(player.posY),MathHelper.floor_double(player.posZ)};
        pw.spawnEntityInWorld(npc);
        npc.setHealth(npc.getMaxHealth());
        NoppesUtilServer.sendOpenGui(player, EnumGuiType.MainMenuDisplay, npc);
    }
    
    @Override
	public List addTabCompletion(ICommandSender par1, String[] args) {
    	return super.addTabCompletion(par1, args);
    }
}
