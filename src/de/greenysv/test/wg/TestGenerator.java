package de.greenysv.test.wg;

import java.util.logging.Logger;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class TestGenerator extends JavaPlugin {
	
    private Logger log = Logger.getLogger("Minecraft");
    PluginDescriptionFile pluginDescriptionFile;

    public void onEnable()
    {
        pluginDescriptionFile = getDescription();
        log.info("[TestGen] " + pluginDescriptionFile.getFullName() + " enabled");
    }

    public void onDisable()
    {
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
    {
        return new TestChunk();
    }
}
