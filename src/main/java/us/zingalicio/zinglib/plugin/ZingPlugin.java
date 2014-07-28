package us.zingalicio.zinglib.plugin;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import us.zingalicio.zinglib.ZingLib;
import us.zingalicio.zinglib.util.ConfigUtil;

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
			ConfigUtil.saveDefault(this, configFile);
			ConfigUtil.saveDefault(this, materialFile);
		}
		
		materials = new YamlConfiguration();
		config = new YamlConfiguration();
		
		
		ConfigUtil.loadYaml(materials, materialFile);
		ConfigUtil.loadYaml(config, configFile);
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
