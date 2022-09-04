package mcscripts.mcmacro.netherwartMacro;

import mcscripts.mcmacro.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.security.Key;

import static mcscripts.mcmacro.netherwartMacro.netherwartMain.getDirection;

public class laneChecker {
    private static void doSmoothYawTurn(float yaw, float pitch, EntityPlayerSP player) {
        if (player.rotationYaw <= 180F) {
            for (int i = (int)player.rotationYaw; i <= yaw ; i++) {
                player.rotationYaw = i;
            }
        } else {
            for (int i = (int)player.rotationYaw; i >= yaw ; i--) {
                player.rotationYaw = i;
            }
        }
        player.rotationPitch = pitch;
    }
    public static boolean checkLane(){
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        BlockPos pos = player.getPosition();
        System.out.println(pos);
        ITextComponent fail = new TextComponentString("Not In A Row");
        boolean inrow = false;
        World world = Main.mc.player.world;
        //Getting Blocks
        if (player.getHorizontalFacing() == EnumFacing.NORTH) {
            String zBlock1 = world.getBlockState(pos.add(0,0,-1)).getBlock().getLocalizedName();
            String zBlock2 = world.getBlockState(pos.add(0,0,-2)).getBlock().getLocalizedName();
            if (zBlock1.equals("Soul Sand") || zBlock2.equals("Soul Sand")) {
                inrow = true;
                getDirection(player);
                KeyBinding.setKeyBindState(Main.direction, true);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindAttack.getKeyCode(), true);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), true);
                doSmoothYawTurn(180, 10, Main.mc.player);
            }
        }
        if (player.getHorizontalFacing() == EnumFacing.SOUTH) {
            String zBlock1 = world.getBlockState(pos.add(0,0,1)).getBlock().getLocalizedName();
            String zBlock2 = world.getBlockState(pos.add(0,0,2)).getBlock().getLocalizedName();
            if (zBlock1.equals("Soul Sand") || zBlock2.equals("Soul Sand")) {
                inrow = true;
                doSmoothYawTurn(0, 10, Main.mc.player);
                getDirection(player);
                KeyBinding.setKeyBindState(Main.direction, true);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindAttack.getKeyCode(), true);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), true);
            }
        }
        if (player.getHorizontalFacing() == EnumFacing.EAST) {
            String zBlock1 = world.getBlockState(pos.add(1,0,0)).getBlock().getLocalizedName();
            String zBlock2 = world.getBlockState(pos.add(2,0,0)).getBlock().getLocalizedName();
            if (zBlock1.equals("Soul Sand") || zBlock2.equals("Soul Sand")) {
                inrow = true;
                doSmoothYawTurn(-90, 10, Main.mc.player);
                getDirection(player);
                KeyBinding.setKeyBindState(Main.direction, true);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindAttack.getKeyCode(), true);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), true);
            }
        }
        if (player.getHorizontalFacing() == EnumFacing.WEST) {
            String zBlock1 = world.getBlockState(pos.add(-1,0,0)).getBlock().getLocalizedName();
            String zBlock2 = world.getBlockState(pos.add(-2,0,0)).getBlock().getLocalizedName();
            if (zBlock1.equals("Soul Sand") || zBlock2.equals("Soul Sand")) {
                inrow = true;
                doSmoothYawTurn(90, 10, Main.mc.player);
                getDirection(player);
                KeyBinding.setKeyBindState(Main.direction, true);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindAttack.getKeyCode(), true);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), true);
            }
        }
        if (!inrow) {
            player.sendMessage(fail);
            macroHandler.macroState = false;
        }
        return inrow;
    }
}
