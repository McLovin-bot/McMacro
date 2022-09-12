package mcscripts.mcmacro.util;

import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class updateMod {

    private static void downloadMod(String url, File location) throws IOException {
        URLConnection con = (new URL(url)).openConnection();
        con.setRequestProperty("User-Agent", "Auto-Updater");
        InputStream in = con.getInputStream();
        Files.copy(in, location.toPath(), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
        in.close();
    }

    public void update() {
        downloadMod();
    }
}
