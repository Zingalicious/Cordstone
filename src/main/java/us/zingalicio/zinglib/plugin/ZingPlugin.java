package us.zingalicio.zinglib.plugin;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import us.zingalicio.zinglib.ZingLib;
import us.zingalicio.zinglib.util.ConfigUtil;

public abstract class ZingPlugin extends JavaPlugin
{
	protected final YamlConfiguration materials;
	protected final YamlConfiguration items;
	protected final YamlConfiguration config;
	
	protected final File materialFile;
	protected final File itemFile;
	protected final File configFile;

	public final String name;
	public ChatColor nameColour = ChatColor.GOLD;
	public ChatColor textColour = ChatColor.YELLOW;
	
	public ZingPlugin()
	{
		materialFile = new File("plugins/common/materials.yml");
		itemFile = new File("plugins/common/items.yml");
		configFile = new File(this.getDataFolder() + "/config.yml");
		
		materials = new YamlConfiguration();
		items = new YamlConfiguration();
		config = new YamlConfiguration();
		
		if(this instanceof ZingLib)
		{
			ConfigUtil.saveDefault(this, materialFile);
			ConfigUtil.saveDefault(this, itemFile);
		}
		ConfigUtil.saveDefault(this, configFile);
		
		name = this.getName();
		
		ConfigUtil.loadYaml(materials, materialFile);
		ConfigUtil.loadYaml(items, itemFile);
		ConfigUtil.loadYaml(config, configFile);
	}

	@Override
	public void onDisable()
	{
		ConfigUtil.saveYaml(config, configFile);
		ConfigUtil.saveYaml(materials, materialFile);
		ConfigUtil.saveYaml(items, itemFile);
	}
	
	public YamlConfiguration getMaterials()
	{
		return materials;
	}
	public YamlConfiguration getItems()
	{
		return items;
	}
	public YamlConfiguration getConfig()
	{
		return config;
	}
	public File getMaterialFile()
	{
		return materialFile;
	}
	public File getItemFile()
	{
		return itemFile;
	}
	public File getConfigFile()
	{
		return configFile;
	}
}
