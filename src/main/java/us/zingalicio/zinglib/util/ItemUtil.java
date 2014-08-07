package us.zingalicio.zinglib.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.zingalicio.zinglib.util.NumberUtil;
import us.zingalicio.zinglib.plugin.ZingPlugin;

public class ItemUtil 
{	
	@SuppressWarnings("deprecation")
	public static ItemStack getItem(ZingPlugin plugin, String name)
	{
		ItemStack item;
		YamlConfiguration items = plugin.getItems();
		String itemKey = "items." + name.toLowerCase();
		if(items.contains(itemKey))
		{
			//Create item
			Material mat;
			if(items.contains(itemKey + ".material"))
			{
				mat = Material.valueOf(items.getString(itemKey + ".material"));
			}
			else
			{
				Bukkit.getLogger().log(Level.INFO, "Material");
				return null;
			}
			Short data = 0;
			if(items.contains(itemKey + ".data"))
			{
				data = (short) items.getInt(itemKey + ".data");
			}
			
			item = new ItemStack(mat, 1, data);
			
			//Add name + lore
			if(items.contains(itemKey + ".name"))
			{
				String displayName = items.getString(itemKey + ".name");
				ItemMeta newMeta = item.getItemMeta();
				newMeta.setDisplayName(displayName);
				item.setItemMeta(newMeta);
			}
			if(items.contains(itemKey + ".lore"))
			{
				List<String> lore = items.getStringList(itemKey + ".lore");
				ItemMeta newMeta = item.getItemMeta();
				newMeta.setLore(lore);
				item.setItemMeta(newMeta);
			}
			
			//Add extra nbt data
			if(items.contains(itemKey + ".extra"))
			{
				File nbtFile = new File("plugins/common/itemnbt/" + name.toLowerCase() + ".nbt");
				NBTCompound tag;
				try
				{
					tag = NBTManager.getInstance().readCompressed(nbtFile);
				}
				catch (IOException e1) 
				{
					Bukkit.getLogger().log(Level.INFO, "No file found!");
					e1.printStackTrace();
					return null;
				}
				NBTManager.getInstance().write(item, tag);
			}
			return item;
		}
		YamlConfiguration materials = plugin.getMaterials();
		Material mat = null;
		String dataString = "0";
		Short data = 0;
		List<String> enchantments = new ArrayList<String>();
		
		if(name.contains("|"))
		{
			String[] parts = name.split("\\|");
			name = parts[0];
						
			boolean i = false;
			for(String e : parts)
			{
				if(i)
				{
					enchantments.add(e);
				}
				else
				{
					i = true;
				}
			}
		}
		
		if(name.contains(":"))
		{
			String[] parts = name.split(":");
			name = parts[0];
			dataString = parts[1];
		}
		
		if(NumberUtil.getInt(name) != null)
		{
			mat = Material.getMaterial(Integer.parseInt(name));
		}
		else if(materials.contains("lookup." + name.toLowerCase().replace("_", "")))
		{
			mat = Material.valueOf(materials.getString("lookup." + name.toLowerCase().replace("_", "")));
		}
		else
		{
			return null;
		}
		
		if(NumberUtil.getInt(dataString) != null)
		{
			data = Short.parseShort(dataString);
		}
		else if(mat.getId() < 256)
		{
			if(materials.contains("blocks." + mat.name() + ".names." + dataString.toLowerCase().replace("_", "")))
			{
				data = (short) materials.getInt("blocks." + mat.name() + ".names." + dataString.toLowerCase().replace("_", ""));
			}
			else
			{
				return null;
			}
		}
		else
		{
			if(materials.contains("items." + mat.name() + ".names." + dataString.toLowerCase().replace("_", "")))
			{
				data = (short) materials.getInt("items." + mat.name() + ".names." + dataString.toLowerCase().replace("_", ""));
			}
			else 
			{
				return null;
			}
		}
		
		item = new ItemStack(mat, 1, data);
		
		for(String e : enchantments)
		{
			Enchantment enchantment;
			int enchantLevel = 1;
			if(e.contains(":"))
			{
				String[] enchantParts = e.split(":");
				if(NumberUtil.getInt(enchantParts[0]) != null)
				{
					enchantment = Enchantment.getById(Integer.parseInt(enchantParts[0]));
				}
				else if(materials.contains("enchantments." + enchantParts[0].toLowerCase()))
				{
					enchantment = Enchantment.getById(materials.getInt("enchantments." + enchantParts[0].toLowerCase()));
				}
				else
				{
					enchantment = Enchantment.getByName(enchantParts[0]);
				}
				try
				{
					enchantLevel = Integer.parseInt(enchantParts[1]);
				}
				catch(NumberFormatException ex)
				{
					return null;
				}
			}
			else if(NumberUtil.getInt(e) != null)
			{
				enchantment = Enchantment.getById(NumberUtil.getInt(e));
			}
			else if(materials.contains("enchantments." + e.toLowerCase()))
			{
				enchantment = Enchantment.getById(materials.getInt("enchantments." + e.toLowerCase()));
			}
			else
			{
				enchantment = Enchantment.getByName(e);
			}
			
			if(enchantment != null)
			{
				item.addUnsafeEnchantment(enchantment, enchantLevel);
			}
		}
		
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public static byte saveItem(ZingPlugin plugin, ItemStack item, String name)
	{
		String sectionString = "items." + name.toLowerCase();
		if(plugin.getItems().contains(sectionString))
		{
			return 1;
		}
		ConfigurationSection section = plugin.getItems().createSection(sectionString);
		section.set("material", item.getType().name());
		section.set("data", item.getData().getData());
		ItemStack theoreticalItem = new ItemStack(item.getType(), item.getAmount(), item.getDurability());
		ItemMeta theoreticalMeta = theoreticalItem.getItemMeta();
		if(item.hasItemMeta())
		{
			if(item.getItemMeta().hasDisplayName())
			{
				section.set("name", item.getItemMeta().getDisplayName());
				theoreticalMeta.setDisplayName(item.getItemMeta().getDisplayName());
			}
			if(item.getItemMeta().hasLore())
			{
				section.set("lore", item.getItemMeta().getLore());
				theoreticalMeta.setLore(item.getItemMeta().getLore());
			}
			if(item.getItemMeta().hasEnchants())
			{
				Map<Enchantment, Integer> e = item.getItemMeta().getEnchants();
				int i = 0;
				for(Map.Entry<Enchantment, Integer> en : e.entrySet())
				{
					section.set("enchantment." + i + ".type", en.getKey().getName());
					section.set("enchantment." + i + ".level", en.getValue());
					theoreticalMeta.addEnchant(en.getKey(), en.getValue(), true);
					i++;
				}
			}
		}
		try 
		{
			plugin.getItems().save(plugin.getItemFile());
		} 
		catch (IOException e1) 
		{
			Bukkit.getLogger().log(Level.INFO, "Failed to save items.yml.");
			e1.printStackTrace();
			return 3;
		}
		theoreticalItem.setItemMeta(theoreticalMeta);
		if(item == theoreticalItem)
		{
			return 0;
		}
		NBTCompound tag = NBTManager.getInstance().read(item);
		File itemFile = new File("plugins/common/itemnbt/" + name.toLowerCase() + ".nbt");
		try 
		{
			NBTManager.getInstance().write(itemFile, tag);
			return 0;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return 2;
		}
	}
	
	public static int giveMany(ItemStack item, Player player, int amount)
	{
		int stackSize = item.getMaxStackSize();
		item.setAmount(stackSize);
		int extra = 0;
		while(amount > stackSize)
		{
			HashMap<Integer, ItemStack> i = player.getInventory().addItem(item);
			if(i.containsKey(0))
			{
				extra = extra + i.get(0).getAmount();
			}
			amount -= stackSize;
		}
		item.setAmount(amount);
		
		HashMap<Integer, ItemStack> i = player.getInventory().addItem(item);
		if(i.containsKey(0))
		{
			extra = extra + i.get(0).getAmount();
		}
		
		return extra;
	}
}
