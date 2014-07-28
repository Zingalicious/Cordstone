package us.zingalicio.zinglib.util;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import us.zingalicio.zinglib.plugin.ZingPlugin;

public final class MessageUtil 
{
	private static final int PAGE_LENGTH = 7;
	
	public static void paginate(CommandSender sender, List<String> s, int p)
	{
		int pages = s.size() / PAGE_LENGTH + (s.size() % PAGE_LENGTH == 0 ? 0 : 1);
		if(pages == 0)
		{
			sender.sendMessage(ChatColor.RED + "Nothing to see here, apparently...");
			return;
		}
		else if(p > pages)
		{
			sender.sendMessage(ChatColor.RED + "There are only " + pages + " pages, brother.");
			return;
		}
		sender.sendMessage(ChatColor.GOLD + "========| " + ChatColor.WHITE + "Page " + ChatColor.YELLOW + "" + 
				ChatColor.BOLD + p + ChatColor.WHITE + " of " + ChatColor.YELLOW + "" + ChatColor.BOLD + pages + 
				ChatColor.GOLD + " |========");
		int c = 0;
		while(c < PAGE_LENGTH)
		{
			try
			{
				sender.sendMessage(ChatColor.YELLOW + s.get((p - 1) * PAGE_LENGTH + c));
			}
			catch(IndexOutOfBoundsException ex)
			{
				return;
			}
			c += 1;
		}
	}
	
	public static void sendMessage(ZingPlugin plugin, CommandSender sender, String message)
	{
		sender.sendMessage(plugin.nameColour + "[" + plugin.name + "] " + plugin.textColour + message);
	}
	public static void sendError(ZingPlugin plugin, CommandSender sender, String message)
	{
		sender.sendMessage(ChatColor.DARK_RED + "[" + plugin.name + "] " + ChatColor.RED + message);
	}
}
