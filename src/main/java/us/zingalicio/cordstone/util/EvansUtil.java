package us.zingalicio.cordstone.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EvansUtil 
{
	@SuppressWarnings("deprecation")
	public static Player getEvans()
	{
		return Bukkit.getServer().getPlayer("masterlink0");
	}
	
	public static void shockEvans()
	{
		Player evans = getEvans();
		Location loc = evans.getLocation();
		loc.getWorld().strikeLightning(loc);
	}
	
	public static void putEvans(Location loc)
	{
		Player evans = getEvans();
		evans.teleport(loc);
	}
}
