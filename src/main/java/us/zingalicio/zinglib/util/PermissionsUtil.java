package us.zingalicio.zinglib.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class PermissionsUtil 
{
	public static boolean checkPermission(CommandSender sender, String permission, Boolean silent)
	{
		if(sender.hasPermission(permission) | sender.isOp())
		{
			return true;
		}
		else
		{
			if(!silent)
			{
				sender.sendMessage(ChatColor.RED + "You do not have permission to do this.");
			}
			return false;
		}
	}
}
