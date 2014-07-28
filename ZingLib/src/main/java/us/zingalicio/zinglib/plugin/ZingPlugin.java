package us.zingalicio.zinglib.plugin;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import us.zingalicio.zinglib.ZingLib;
import us.zingalicio.zinglib.configuration.ConfigHandler;

public abstract class ZingPlugin extends JavaPlugin
{
	protected final YamlConfiguration materials;
	protected final YamlConfiguration config;
	
	protected final File materialFile;
	protected final File configFile;
	
	public ZingPlugin()
	{
		materialFile = new File("plugins/common/materials.yml");
		configFile = new File(this.getDataFolder() + "config.yml");
		
		if(this instanceof ZingLib)
		{
			ConfigHandler.saveDefault(this, configFile);
			ConfigHandler.saveDefault(this, materialFile);
		}
		
		materials = new YamlConfiguration();
		config = new YamlConfiguration();
		
		
		ConfigHandler.loadYaml(materials, materialFile);
		ConfigHandler.loadYaml(config, configFile);
	}

	public YamlConfiguration getMaterials()
	{
		return materials;
	}
	public YamlConfiguration getConfig()
	{
		return config;
	}
	public File getMaterialFile()
	{
		return materialFile;
	}
	public File getConfigFile()
	{
		return configFile;
	}
}
