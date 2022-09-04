package mcscripts.mcmacro.netherwartMacro;
import mcscripts.mcmacro.Main;
import mcscripts.mcmacro.util.forgeUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class netherwartMain {
    public static String task;

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

    public static void getDirection(EntityPlayerSP player) {
        if (player.getHorizontalFacing() == EnumFacing.NORTH) {
            Main.direction = Main.mc.gameSettings.keyBindRight.getKeyCode();
        }
        if (player.getHorizontalFacing() == EnumFacing.SOUTH) {
            Main.direction = Main.mc.gameSettings.keyBindLeft.getKeyCode();
        }
        if (player.getHorizontalFacing() == EnumFacing.WEST) {
            Main.direction = Main.mc.gameSettings.keyBindRight.getKeyCode();
        }
        if (player.getHorizontalFacing() == EnumFacing.EAST) {
            Main.direction = Main.mc.gameSettings.keyBindLeft.getKeyCode();
        }
    }


    private void farmRow(EntityPlayerSP player) {
        KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), false);
        if (player.getHorizontalFacing() == EnumFacing.NORTH) {
            boolean running = true;
            doSmoothYawTurn(180,10, player);

            if (player.motionX <= 0.001D && player.motionX >= -0.001D && player.motionZ <= 0.001D && player.motionZ >= -0.001D) {
                running = false;
                forgeUtils.sendMessage("Found end of row", player);
                KeyBinding.setKeyBindState(Main.direction, false);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindAttack.getKeyCode(), false);
                //KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), false);
                task = "change_lane";
            }
        }
        if (player.getHorizontalFacing() == EnumFacing.SOUTH) {
            boolean running = true;
            doSmoothYawTurn(0,10, player);

            if (player.motionX <= 0.001D && player.motionX >= -0.001D && player.motionZ <= 0.001D && player.motionZ >= -0.001D) {
                running = false;
                forgeUtils.sendMessage("Found end of row", player);
                KeyBinding.setKeyBindState(Main.direction, false);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindAttack.getKeyCode(), false);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), false);
                task = "change_lane";
            }
        }
        if (player.getHorizontalFacing() == EnumFacing.EAST) {
            boolean running = true;
            doSmoothYawTurn(-90,10, player);

            if (player.motionX <= 0.001D && player.motionX >= -0.001D && player.motionZ <= 0.001D && player.motionZ >= -0.001D) {
                running = false;
                forgeUtils.sendMessage("Found end of row", player);
                KeyBinding.setKeyBindState(Main.direction, false);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindAttack.getKeyCode(), false);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), false);
                task = "change_lane";
            }
        }
        if (player.getHorizontalFacing() == EnumFacing.WEST) {
            boolean running = true;
            doSmoothYawTurn(90,10, player);

            if (player.motionX <= 0.001D && player.motionX >= -0.001D && player.motionZ <= 0.001D && player.motionZ >= -0.001D) {
                running = false;
                forgeUtils.sendMessage("Found end of row", player);
                KeyBinding.setKeyBindState(Main.direction, false);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindAttack.getKeyCode(), false);
                KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), false);
                task = "change_lane";
            }
        }
    }
    private int tick = 0;
    private void changeLane(EntityPlayerSP player) {
        BlockPos checker;

        if (player.getHorizontalFacing() == EnumFacing.NORTH) {
            KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), true);
            if (Main.direction == Main.mc.gameSettings.keyBindRight.getKeyCode()) {
                checker = player.getPosition().west(2);
                tick++;
                if (tick > 20) {
                    KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindSneak.getKeyCode(), true);
                    System.out.println(player.world.getBlockState(checker).getBlock().getLocalizedName());
                    if (player.world.getBlockState(checker).getBlock().getLocalizedName().equals("Air")) {
                        KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindSneak.getKeyCode(), false);
                        KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), false);
                        forgeUtils.sendMessage("Found lane", player);
                        Main.direction = Main.mc.gameSettings.keyBindLeft.getKeyCode();
                        KeyBinding.setKeyBindState(Main.direction, true);
                        tick = 0;
                        task = "farm_row";
                    }
                }
            }
            if (Main.direction == Main.mc.gameSettings.keyBindLeft.getKeyCode()) {
                checker = player.getPosition().east(2);
                tick++;
                if (tick > 20) {
                    KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindSneak.getKeyCode(), true);
                    System.out.println(player.world.getBlockState(checker).getBlock().getLocalizedName());
                    if (player.world.getBlockState(checker).getBlock().getLocalizedName().equals("Air")) {
                        KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindSneak.getKeyCode(), false);
                        KeyBinding.setKeyBindState(Main.mc.gameSettings.keyBindForward.getKeyCode(), false);
                        forgeUtils.sendMessage("Found lane", player);
                        Main.direction = Main.mc.gameSettings.keyBindRight.getKeyCode();
                        KeyBinding.setKeyBindState(Main.direction, true);
                        tick = 0;
                        task = "farm_row";
                    }
                }
            }
        }


    }


    @SubscribeEvent
    public void mainLoop(TickEvent.ClientTickEvent event) {
        if (macroHandler.macroState) {
            EntityPlayerSP player = Main.mc.player;
            if (task.equals("farm_row")) {
                farmRow(player);
            }
            if (task.equals("change_lane")) {
                changeLane(player);
            }
        }
    }
}
