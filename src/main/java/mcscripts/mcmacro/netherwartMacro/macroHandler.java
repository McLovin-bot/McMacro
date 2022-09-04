package mcscripts.mcmacro.netherwartMacro;

import mcscripts.mcmacro.Main;
import mcscripts.mcmacro.util.forgeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;

public class macroHandler {
    public static boolean macroState = false;
    public static boolean netherWartMacro = false;

    public static void netherwartToggle() {
        EntityPlayerSP player = Minecraft.getMinecraft().player;


        if(!macroState && laneChecker.checkLane()) {
            macroState = true;
            forgeUtils.sendMessage("Macro Started", player);
            netherwartMain.task = "farm_row";

            netherWartMacro = true;
        } else if (macroState) {
            macroState = false;
            netherWartMacro = false;
            forgeUtils.sendMessage("Macro Stopped", player);
            KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindSneak.getKeyCode(), false);
            KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), false);
            KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindAttack.getKeyCode(), false);
            KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindRight.getKeyCode(), false);
            KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindLeft.getKeyCode(), false);
        }
    }
}
