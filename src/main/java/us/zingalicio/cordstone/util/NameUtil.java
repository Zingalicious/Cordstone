package us.zingalicio.cordstone.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import us.zingalicio.cordstone.Cordstone;

public class NameUtil
{	
	public static Material getMaterial(String name)
	{
		YamlConfiguration materials = Cordstone.getInstance().getMaterials();
		if(materials.contains("lookup." + name))
		{
			return Material.getMaterial(materials.getString("lookup." + name));
		}
		else
		{
			try
			{
				return Material.getMaterial(name);
			}
			catch(Exception ex)
			{
				return null;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static MaterialData getMaterialData(Material material, String name)
	{
		YamlConfiguration materials = Cordstone.getInstance().getMaterials();
		if(materials.contains("blocks." + material.name() + ".names." + name))
		{
			MaterialData data = new MaterialData(material, Byte.parseByte(materials.getString("blocks." + material.name() + ".names." + name)));
			return data;
		}
		else if(materials.contains("items." + material.name() + ".names." + name))
		{
			MaterialData data = new MaterialData(material, Byte.parseByte(materials.getString("items." + material.name() + ".names." + name)));
			return data;
		}
		else
		{
			return null;
		}
	}
	
	public static String getName(Material material)
	{
		YamlConfiguration materials = Cordstone.getInstance().getMaterials();
		if(materials.contains("blocks." + material.name() + ".name"))
		{
			return materials.getString("blocks." + material.name() + ".name");
		}
		else if(materials.contains("items." + material.name() + ".name"))
		{
			return materials.getString("items." + material.name() + ".name");
		}
		else
		{
			return format(material.name());
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String getDataName(MaterialData data)
	{
		Material material = data.getItemType();
		YamlConfiguration materials = Cordstone.getInstance().getMaterials();
		if(materials.contains("blocks." + material.name() + ".data." + data.getData()))
		{
			String name = materials.getString("blocks." + material.name() + ".data." + data.getData());
			return name;
		}
		else if(materials.contains("items." + material.name() + ".data." + data.getData()))
		{
			String name = materials.getString("items." + material.name() + ".data." + data.getData());
			return name;
		}
		else
		{
			return null;
		}
	}

	
	public static String getFullName(Material material, MaterialData data)
	{
		String dataName;
		if((dataName = getDataName(data)) != null)
		{
			return getName(material) + ":" + dataName;
		}
		return getName(material);
	}
	
	public static String getItemName(Material material, MaterialData data)
	{
		YamlConfiguration materials = Cordstone.getInstance().getMaterials();
		if(materials.getConfigurationSection("blocks." + material.name() + ".names") == null
				&& materials.getConfigurationSection("items." + material.name() + ".names") == null)
		{
			return getName(material);
		}
		else
		{
			String dataName;
			if((dataName = getDataName(data)) != null)
			{
				return getName(material) + ":" + dataName;
			}
			return getName(material);
		}
	}
	
	public static String format(String s)
	{
	    String[] fullNameSplit = s.split("_| ");
	    List<String> fullNameList = new ArrayList<String>();

	    for (String f : fullNameSplit)
	    {
	      fullNameList.add(f);
	    }

	    String name = "";

	    for (String f : fullNameList)
	    {
	      name = name + f.replace(f.substring(1), f.substring(1).toLowerCase());
	      name = name + " ";
	    }
	    name = name.trim();
	    return name;
	}
	
	public static String getSenderName(CommandSender sender)
	{
		if(sender instanceof Player)
		{
			return ((Player) sender).getDisplayName();
		}
		return sender.getName();
	}
}
