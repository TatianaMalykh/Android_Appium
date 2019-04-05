package config;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Configuration {
    public AndroidConfiguration android;
    public UserConfiguration user;


    public void load() throws IOException {
        try {
            android = read("src/test/resources/config.android.yml", AndroidConfiguration.class);
            user = read("src/test/resources/config.user.yml", UserConfiguration.class);
        } catch (IOException e) {
            System.out.println("ERROR: cannot parse config file: " + e.getMessage());
            throw e;
        }
    }

    public <T> T read(String path, Class<T> c) throws IOException {
        Yaml yaml = new Yaml();
        try (InputStream in = Files.newInputStream(Paths.get(path))) {
            return yaml.loadAs(in, c);
        }
    }
}