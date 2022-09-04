package mcscripts.mcmacro.util;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class forgeUtils {
    public static void sendMessage(String message, EntityPlayerSP player) {
        player.sendMessage((ITextComponent) new TextComponentString(TextFormatting.GRAY + "[" + TextFormatting.DARK_AQUA + "McMacro" + TextFormatting.GRAY + "]" + TextFormatting.RESET + message));
    }
}
