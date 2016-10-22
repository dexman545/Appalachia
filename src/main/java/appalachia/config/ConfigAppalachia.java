package appalachia.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import appalachia.util.Logger;


public class ConfigAppalachia {

    public static Configuration config;
    public static int biomeIDCounter = 40;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Biome IDs
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static int biomeID_AppalachianMountains = biomeIDCounter++;
    public static int biomeID_BlueRidgeMountains = biomeIDCounter++;
    public static int biomeID_BlueRidgeForestAutumn = biomeIDCounter++;
    public static int biomeID_BlueRidgeForest = biomeIDCounter++;
    public static int biomeID_BlueRidgeBeach = biomeIDCounter++;
    public static int biomeID_BlueRidgeRiver = biomeIDCounter++;
    public static int biomeID_Prairie = biomeIDCounter++;
    public static int biomeID_SmokyMountains = biomeIDCounter++;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Biome Weights
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static int biomeWeight_AppalachianMountains = 20;
    public static int biomeWeight_BlueRidgeMountains = 20;
    public static int biomeWeight_BlueRidgeForestAutumn = 20;
    public static int biomeWeight_BlueRidgeForest = 20;
    public static int biomeWeight_BlueRidgeBeach = 0;
    public static int biomeWeight_BlueRidgeRiver = 0;
    public static int biomeWeight_Prairie = 20;
    public static int biomeWeight_SmokyMountains = 20;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Debugging
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static boolean enableDebugging = false;

    public static boolean allowTreesToGenerateOnSand = false;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static void init(File configFile) {

        config = new Configuration(configFile);

        try {

            config.load();

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Biome IDs
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            biomeID_AppalachianMountains = config.getInt("Appalachian Mountains Biome ID", "Biome IDs", biomeID_AppalachianMountains, 0, 255, "");
            biomeID_BlueRidgeMountains = config.getInt("Blue Ridge Mountains Biome ID", "Biome IDs", biomeID_BlueRidgeMountains, 0, 255, "");
            biomeID_BlueRidgeForestAutumn = config.getInt("Autumn Blue Ridge Forest Biome ID", "Biome IDs", biomeID_BlueRidgeForestAutumn, 0, 255, "");
            biomeID_BlueRidgeForest = config.getInt("Blue Ridge Forest Biome ID", "Biome IDs", biomeID_BlueRidgeForest, 0, 255, "");
            biomeID_BlueRidgeBeach = config.getInt("Blue Ridge Beach Biome ID", "Biome IDs", biomeID_BlueRidgeBeach, 0, 255, "");
            biomeID_BlueRidgeRiver = config.getInt("Blue Ridge River Biome ID", "Biome IDs", biomeID_BlueRidgeRiver, 0, 255, "");
            biomeID_Prairie = config.getInt("Prairie Biome ID", "Biome IDs", biomeID_Prairie, 0, 255, "");
            biomeID_SmokyMountains = config.getInt("Smoky Mountains Biome ID", "Biome IDs", biomeID_SmokyMountains, 0, 255, "");

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Biome Weights
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            biomeWeight_AppalachianMountains = config.getInt("Appalachian Mountains Biome Weight", "Biome Weights", biomeWeight_AppalachianMountains, 0, Integer.MAX_VALUE, "");
            biomeWeight_BlueRidgeMountains = config.getInt("Blue Ridge Mountains Biome Weight", "Biome Weights", biomeWeight_BlueRidgeMountains, 0, Integer.MAX_VALUE, "");
            biomeWeight_BlueRidgeForestAutumn = config.getInt("Autumn Blue Ridge Forest Biome Weight", "Biome Weights", biomeWeight_BlueRidgeForestAutumn, 0, Integer.MAX_VALUE, "");
            biomeWeight_BlueRidgeForest = config.getInt("Blue Ridge Forest Biome Weight", "Biome Weights", biomeWeight_BlueRidgeForest, 0, Integer.MAX_VALUE, "");
            biomeWeight_BlueRidgeBeach = config.getInt("Blue Ridge Beach Biome Weight", "Biome Weights", biomeWeight_BlueRidgeBeach, 0, Integer.MAX_VALUE, "");
            biomeWeight_BlueRidgeRiver = config.getInt("Blue Ridge River Biome Weight", "Biome Weights", biomeWeight_BlueRidgeRiver, 0, Integer.MAX_VALUE, "");
            biomeWeight_Prairie = config.getInt("Prairie Biome Weight", "Biome Weights", biomeWeight_Prairie, 0, Integer.MAX_VALUE, "");
            biomeWeight_SmokyMountains = config.getInt("Smoky Mountains Biome Weight", "Biome Weights", biomeWeight_SmokyMountains, 0, Integer.MAX_VALUE, "");

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Debugging
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            enableDebugging = config.getBoolean("Enable Debugging", "Debugging", enableDebugging, "WARNING: This should only be enabled if you know what you're doing." + Configuration.NEW_LINE);

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        }
        catch (Exception e) {

            Logger.error("Appalachia had a problem loading Appalachia configuration.");
        }
        finally {

            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}