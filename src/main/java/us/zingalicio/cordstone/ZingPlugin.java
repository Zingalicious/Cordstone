package us.zingalicio.cordstone;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import us.zingalicio.cordstone.util.ConfigUtil;

public class ZingPlugin extends JavaPlugin
{
	protected final YamlConfiguration materials;
	protected final YamlConfiguration messages;
	protected final YamlConfiguration items;
	protected final YamlConfiguration config;
	
	protected final File materialFile;
	protected final File messageFile;
	protected final File itemFile;
	protected final File configFile;

	public ChatColor nameColour = ChatColor.GOLD;
	public ChatColor textColour = ChatColor.YELLOW;
	
	public ZingPlugin()
	{
		if(this instanceof Cordstone)
		{
			itemFile = new File("plugins/common/items.yml");
			items = new YamlConfiguration();
			ConfigUtil.saveDefault(this, itemFile);
			ConfigUtil.loadYaml(items, itemFile);

			materialFile = new File("plugins/common/materials.yml");
			materials = new YamlConfiguration();
			ConfigUtil.saveDefault(this, materialFile);
			ConfigUtil.loadYaml(materials, materialFile);
			
			messageFile = new File("plugins/common/messages.yml");
			messages = new YamlConfiguration();
			ConfigUtil.saveDefault(this, messageFile);
			ConfigUtil.loadYaml(messages, messageFile);
		}
		else
		{
			Cordstone cordstone = (Cordstone) Bukkit.getPluginManager().getPlugin("Cordstone");
			
			itemFile = cordstone.itemFile;
			items = cordstone.items;
			
			materialFile = cordstone.materialFile;
			materials = cordstone.materials;
			
			messageFile = cordstone.messageFile;
			messages = cordstone.messages;
		}

		configFile = new File(this.getDataFolder() + "/config.yml");
		config = new YamlConfiguration();
		ConfigUtil.saveDefault(this, configFile);
		ConfigUtil.loadYaml(config, configFile);
	}

	public File getConfigFile()
	{
		return configFile;
	}
	public YamlConfiguration getConfig()
	{
		return config;
	}
	
	public File getItemFile()
	{
		return itemFile;
	}
	public YamlConfiguration getItems()
	{
		return items;
	}
	
	public File getMaterialFile()
	{
		return materialFile;
	}
	public YamlConfiguration getMaterials()
	{
		return materials;
	}
	
	public File getMessageFile()
	{
		return messageFile;
	}
	public YamlConfiguration getMessages()
	{
		return messages;
	}
}
