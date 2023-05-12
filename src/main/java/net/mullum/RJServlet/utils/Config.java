package net.mullum.RJServlet.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Config {
    private File config_file;
    private File config_folder;

    private static final Config INSTANCE;

    static {
        INSTANCE = initializeConfig();
    }

    private Config(File cfile, File cfolder) {
        this.config_file = cfile;
        this.config_folder = cfolder;
    }

    public static Config initializeConfig() {
        final File f = new File("tree");
        File cs = new File(f + "\\" + "config.txt");;

        if (!f.exists()) {
            f.mkdir();
        }

        if (!cs.exists()) {
            try(FileWriter fw = new FileWriter(f + "\\" + "config.txt")) {
                fw.write("#THE BASE OF THE CONFIG FILE!");
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return new Config(cs, f);
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    public String get(String varname) {
        if (!config_file.canRead()) {
            config_file.setReadable(true);
        }

        try(BufferedReader br = new BufferedReader(new FileReader(config_file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }

                if (line.contains(varname)) {
                    return line.split(" = ")[1];
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return " ";
    }

    public <T> void set(String varname, T value) {
        try {
            File tempFile = File.createTempFile("config", ".tmp", config_folder);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            BufferedReader reader = new BufferedReader(new FileReader(config_file));
            String line;
    
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(varname + "=")) {
                    line = varname + "=" + String.valueOf(value);
                }

                writer.write(line);
                writer.newLine();
            }
            writer.write(varname + " = " + String.valueOf(value));
    
            writer.close();
            reader.close();
    
            Files.move(tempFile.toPath(), config_file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException IOe) {
            IOe.printStackTrace();
        }
    }
}