package us.zingalicio.cordstone.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import us.zingalicio.cordstone.exceptions.NoConsoleException;
import us.zingalicio.cordstone.exceptions.NoPlayerException;
import us.zingalicio.cordstone.exceptions.NoWorldException;

public class MatcherUtil 
{
	private static <T> void setSet(Set<T> set, T ob, boolean remove)
	{
		if(remove)
		{
			set.remove(ob);
		}
		else
		{
			set.add(ob);
		}
	}
	
	private static boolean filterPlayers(Player sender, Player player, int radius, World world) throws NoConsoleException
	{
		if(radius > 0)
		{
			if(world == null)
			{
				try
				{
					world = sender.getWorld();
				}
				catch(NullPointerException ex)
				{
					throw new NoConsoleException();
				}
			}
		}
		if(world != null)
		{
			if(world != player.getWorld())
			{
				return false;
			}
			else if(radius > 0 && sender.getLocation().distance(player.getLocation()) > radius)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return true;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static Set<Player> matchPlayers(Player sender, String arg) throws NumberFormatException, NoWorldException, NoPlayerException, NoConsoleException
	{
		Set<Player> players = new LinkedHashSet<Player>();
		int radius = 0;
		World world = null;
		for(String s : arg.split(","))
		{
			boolean remove = false;
			if(s.startsWith("-"))
			{
				remove = true;
				s = s.substring(1);
			}
			else if(s.startsWith("^r"))
			{
				radius = Integer.parseInt(s.substring(2));
			}
			else if(s.startsWith("^w"))
			{
				world = Bukkit.getWorld(s.substring(2));
				if(world == null)
				{
					throw new NoWorldException(s.substring(2));
				}
			}
			
			if(s.equals("*"))
			{
				for(Player p : Bukkit.getOnlinePlayers())
				{
					if(filterPlayers(sender, p, radius, world))
					{
						setSet(players, p, remove);
					}
				}
			}
			else
			{
				Player p;
				if((p = Bukkit.getPlayer(s)) != null)
				{
					if(filterPlayers(sender, p, radius, world))
					{
						setSet(players, p, remove);
					}
				}
				else
				{
					throw new NoPlayerException(s);
				}
			}
		}
		return players;
	}
	
	@SuppressWarnings("deprecation")
	public static Location matchLocation(Player sender, String arg) throws NoConsoleException, NoWorldException, NumberFormatException, NoPlayerException
	{
		World world = null;
		ArrayList<Double> coords = new ArrayList<Double>();
		if(sender != null)
		{
			world = sender.getWorld();
		}
		if(arg.contains(","))
		{
			for(String s : arg.split(","))
			{
				boolean relative = false;
				if(s.startsWith("^w"))
				{
					world = Bukkit.getWorld(s.substring(2));
					if(world == null)
					{
						throw new NoWorldException(s.substring(2));
					}
					break;
				}
				else if(s.equals("~"))
				{
					if(sender == null)
					{
						throw new NoConsoleException();
					}
					Location loc = sender.getLocation();
					if(coords.size() == 0)
					{
						coords.add(loc.getX());
					}
					if(coords.size() == 1)
					{
						coords.add(loc.getZ());
					}
					if(coords.size() == 2)
					{
						coords.add(loc.getY());
					}
					break;
				}
				else if(s.startsWith("~"))
				{
					if(sender == null)
					{
						throw new NoConsoleException();
					}
					s = s.substring(1);
					relative = true;
				}

				double mod = 0;
				if(relative)
				{
					Location loc = sender.getLocation();
					if(coords.size() == 0)
					{
						mod = loc.getX();
					}
					if(coords.size() == 1)
					{
						mod = loc.getZ();
					}
					if(coords.size() == 2)
					{
						mod = loc.getY();
					}
				}
				
				coords.add(Double.parseDouble(s) + mod);
			}
			
			if(world == null)
			{
				throw new NoConsoleException();
			}
			
			if(coords.size() < 2)
			{
				return null;
			}
			
			if(coords.size() < 3)
			{
				coords.add(world.getHighestBlockAt(new Location(world, coords.get(0), 0, coords.get(1))).getLocation().getY() + 1);
			}
		}
		else
		{
			Player p = Bukkit.getPlayer(arg);
			if(p == null)
			{
				throw new NoPlayerException(arg);
			}
			Location loc = p.getLocation();
			world = p.getWorld();
			coords.add(loc.getX());
			coords.add(loc.getZ());
			coords.add(loc.getY());
		}
		return new Location(world, coords.get(0), coords.get(2), coords.get(1));
	}
}
