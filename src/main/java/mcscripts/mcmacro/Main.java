package mcscripts.mcmacro;

import mcscripts.mcmacro.init.RegistryHandler;
import mcscripts.mcmacro.netherwartMacro.netherwartMain;
import mcscripts.mcmacro.proxy.ClientProxy;
import mcscripts.mcmacro.proxy.CommonProxy;
import mcscripts.mcmacro.util.KeyInputHandler;
import mcscripts.mcmacro.util.Reference;
import mcscripts.mcmacro.util.httpUtil;
import mcscripts.mcmacro.util.javaUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.input.Keyboard;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

@Mod(
        modid = Reference.MODID,
        name = Reference.NAME,
        version = Reference.VERSION
)
public class Main {
    public static String version = "0.2";
    public static Minecraft mc = Minecraft.getMinecraft();
    public static EntityPlayerSP player = mc.player;
    public static int tick = 0;
    public static int direction;


    @Mod.Instance(Reference.MODID)
    public static Main INSTANCE;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws Exception {
        if (!Objects.equals(httpUtil.httpGet("https://raw.githubusercontent.com/McLovin-bot/McMacro/main/src/main/java/mcscripts/mcmacro/VERSION.txt"), version)) {
            updateMod
        }
        proxy.init(event);
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        MinecraftForge.EVENT_BUS.register(new netherwartMain());
        MinecraftForge.EVENT_BUS.register(new Main());
        ClientProxy.keyBindings = new KeyBinding[2];
        ClientProxy.keyBindings[0] = new KeyBinding("key.macro", Keyboard.KEY_J, "key.categories.McMacro");
        //for (int i = 0; i < ClientProxy.keyBindings.length; i++)
        //ClientRegistry.registerKeyBinding(ClientProxy.keyBindings[i]);
        ClientRegistry.registerKeyBinding(ClientProxy.keyBindings[0]);
    }


    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
    @Mod.EventHandler
    public static void severInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }

    @SubscribeEvent
    public final void tick(TickEvent.ClientTickEvent event) {
        player = mc.player;
        tick++;
        if (tick>30) {
            tick = 0;
        }
    }
}
