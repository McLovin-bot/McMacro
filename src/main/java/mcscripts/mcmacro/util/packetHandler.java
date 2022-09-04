package mcscripts.mcmacro.util;


import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class packetHandler {
    private packetHandler() {

    }

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    public static void init() {
        //int index = 0;
        //INSTANCE.registerMessage(UpdateMovement.class, index++, Side.SERVER).add();
    }

}
