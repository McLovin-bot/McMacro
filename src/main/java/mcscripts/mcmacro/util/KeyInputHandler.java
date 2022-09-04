package mcscripts.mcmacro.util;

import mcscripts.mcmacro.Main;
import mcscripts.mcmacro.netherwartMacro.macroHandler;
import mcscripts.mcmacro.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;


@Mod.EventBusSubscriber
public class KeyInputHandler {
    @SubscribeEvent (priority = EventPriority.LOWEST)
    public void onEvent(InputEvent.KeyInputEvent event){
        if (ClientProxy.keyBindings[0].isKeyDown()){
            macroHandler.netherwartToggle();
        }
    }
}
