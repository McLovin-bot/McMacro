package mcmacro.updater.tweaker;

import mcmacro.updater.fileUtils;
import mcmacro.updater.httpUtil;
import mcmacro.updater.updateMod;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Map;
import java.util.Objects;

public class autoUpdater implements IFMLLoadingPlugin {
    public autoUpdater() {
        try {
            File modsFolder = new File(new File((autoUpdater.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath()).getParent());
            String version = fileUtils.readFile(new File(modsFolder.getParent() + "/config/McMacro/version.txt"));
            if (!Objects.equals(httpUtil.httpGet("https://raw.githubusercontent.com/McLovin-bot/McMacro/main/src/main/java/mcscripts/mcmacro/VERSION.txt"), version)) {
                //new Thread(updateMod::update).start();
                updateMod.update();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
