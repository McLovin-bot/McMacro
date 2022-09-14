package mcmacro.updater;

import mcmacro.updater.tweaker.autoUpdater;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.*;

public class updateMod extends Thread {
    private static void downloadMod(String url, File location) throws IOException {
        URLConnection con = (new URL(url)).openConnection();
        con.setRequestProperty("User-Agent", "Auto-Updater");
        InputStream in = con.getInputStream();
        Files.copy(in, location.toPath(), StandardCopyOption.REPLACE_EXISTING);
        in.close();
    }

    public static void update() {

        try {
            File jar = new File((autoUpdater.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath());
            downloadMod("https://github.com/McLovin-bot/McMacro/releases/download/McMacro-Download/McMacro.jar", new File(jar.getParent() + "/McMacro.jar"));

            System.out.println("Mod Updated");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}

