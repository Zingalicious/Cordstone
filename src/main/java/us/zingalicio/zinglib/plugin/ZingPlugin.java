package us.zingalicio.zinglib.plugin;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import us.zingalicio.zinglib.ZingLib;
import us.zingalicio.zinglib.util.ConfigUtil;

public abstract class ZingPlugin extends JavaPlugin
{
	protected final YamlConfiguration materials;
	protected final YamlConfiguration messages;
	protected final YamlConfiguration items;
	protected final YamlConfiguration config;
	
	protected final File materialFile;
	protected final File messageFile;
	protected final File itemFile;
	protected final File configFile;

	public final String name;
	public ChatColor nameColour = ChatColor.GOLD;
	public ChatColor textColour = ChatColor.YELLOW;
	
	public ZingPlugin()
	{
		if(!(this instanceof ZingLib))
		{
			ZingLib zingLib = (ZingLib) Bukkit.getPluginManager().getPlugin("ZingLib");
			
			materialFile = zingLib.materialFile;
			messageFile = zingLib.messageFile;
			itemFile = zingLib.itemFile;
			
			materials = zingLib.materials;
			messages = zingLib.messages;
			items = zingLib.items;
		}
		else
		{
			materialFile = new File("plugins/common/materials.yml");
			messageFile = new File("plugins/common/messages.yml");
			itemFile = new File("plugins/common/items.yml");
			
			materials = new YamlConfiguration();
			messages = new YamlConfiguration();
			items = new YamlConfiguration();

			ConfigUtil.saveDefault(this, materialFile);
			ConfigUtil.saveDefault(this, messageFile);
			ConfigUtil.saveDefault(this, itemFile);
			
			ConfigUtil.loadYaml(materials, materialFile);
			ConfigUtil.loadYaml(messages, messageFile);
			ConfigUtil.loadYaml(items, itemFile);
		}
		configFile = new File(this.getDataFolder() + "/config.yml");
		
		config = new YamlConfiguration();
		
		ConfigUtil.saveDefault(this, configFile);
		
		ConfigUtil.loadYaml(config, configFile);

		name = this.getName();
	}

	@Override
	public void onDisable()
	{
		ConfigUtil.saveYaml(config, configFile);
		ConfigUtil.saveYaml(materials, materialFile);
		ConfigUtil.saveYaml(messages, messageFile);
		ConfigUtil.saveYaml(items, itemFile);
	}
	
	public YamlConfiguration getMaterials()
	{
		return materials;
	}
	public YamlConfiguration getMessages()
	{
		return messages;
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
	public File getMessageFile()
	{
		return messageFile;
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
