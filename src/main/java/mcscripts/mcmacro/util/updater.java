package mcscripts.mcmacro.util;

import mcscripts.mcmacro.Main;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class updater {
    private static void downloadMod(String url, File location) throws IOException {
        URLConnection con = (new URL(url)).openConnection();
        con.setRequestProperty("User-Agent", "Auto-Updater");
        InputStream in = con.getInputStream();
        Files.copy(in, location.toPath(), StandardCopyOption.REPLACE_EXISTING);
        in.close();
    }
    public static void createConfigFolder() {
        Path folder = Paths.get(Loader.instance().getConfigDir() + "/McMacro");
        File configFolder = new File(String.valueOf(folder));
        if (configFolder.mkdirs()) {
            System.out.println("McMacro-Init: Config folder created");
        }
    }
    public static void createVersion() {
        File versionFile = new File(Loader.instance().getConfigDir() + "/McMacro/version.txt");
        try {
            if (versionFile.createNewFile()) {
                System.out.println("McMacro-Init: Config created");
                try {
                    FileWriter myWriter = new FileWriter(versionFile);
                    myWriter.write(Main.VERSION);
                    myWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("McMacro-Init: Config exists");
                if (!fileUtils.readFile(versionFile).equals(Main.VERSION)) {
                    FileWriter myWriter = new FileWriter(versionFile);
                    myWriter.write(Main.VERSION);
                    myWriter.close();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkUpdater() {
        createConfigFolder();
        createVersion();
        /*try {
            Path updater = Paths.get((updater.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath()).getParent();
            if (Files.exists(Paths.get(updater + "/McMacro-Updater.jar")))
            downloadMod("https://github.com/McLovin-bot/McMacro/releases/download/McMacro-Download/McMacro.jar", new File(updater.getParent() + "/McMacro-Updater.jar"));
            System.out.println("Mod Updated");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }*/
    }
}
