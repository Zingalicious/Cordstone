package us.zingalicio.cordstone.util;

import org.bukkit.Material;
import org.bukkit.Sound;

import us.zingalicio.cordstone.Cordstone;

public class SoundUtil
{
	public static Sound getSound(Material material)
	{
		String soundString;
		if((soundString = Cordstone.getInstance().getMaterials().getString("blocks." + material.name() + ".sound")) != null)
		{
			return Sound.valueOf(soundString);
		}
		return Sound.DIG_STONE;
	}
}