package us.zingalicio.cordstone.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatUtil 
{
	private final static String CHAT_PLUGIN = "songlantern";
	
	public static String getPrefix(Player player)
	{
		PermissionUser user = PermissionsEx.getUser(player);
		if(user.getPrefix() != null)
		{
			return user.getPrefix();
		}
		else
		{
			return "";
		}
	}
	
	public static String getSuffix(Player player)
	{
		PermissionUser user = PermissionsEx.getUser(player);
		if(user.getSuffix() != null)
		{
			return user.getSuffix();
		}
		else
		{
			return "";
		}
	}
	
	public static String formatColours(String message)
	{
		message = message.replace("&0", ChatColor.BLACK.toString());
		message = message.replace("&1", ChatColor.DARK_BLUE.toString());
		message = message.replace("&2", ChatColor.DARK_GREEN.toString());
		message = message.replace("&3", ChatColor.DARK_AQUA.toString());
		message = message.replace("&4", ChatColor.DARK_RED.toString());
		message = message.replace("&5", ChatColor.DARK_PURPLE.toString());
		message = message.replace("&6", ChatColor.GOLD.toString());
		message = message.replace("&7", ChatColor.GRAY.toString());
		message = message.replace("&8", ChatColor.DARK_GRAY.toString());
		message = message.replace("&9", ChatColor.BLUE.toString());
		message = message.replace("&a", ChatColor.GREEN.toString());
		message = message.replace("&b", ChatColor.AQUA.toString());
		message = message.replace("&c", ChatColor.RED.toString());
		message = message.replace("&d", ChatColor.LIGHT_PURPLE.toString());
		message = message.replace("&e", ChatColor.YELLOW.toString());
		message = message.replace("&f", ChatColor.WHITE.toString());
		message = message.replace("&k", ChatColor.MAGIC.toString());
		message = message.replace("&l", ChatColor.BOLD.toString());
		message = message.replace("&m", ChatColor.STRIKETHROUGH.toString());
		message = message.replace("&n", ChatColor.UNDERLINE.toString());
		message = message.replace("&o", ChatColor.ITALIC.toString());
		message = message.replace("&r", ChatColor.RESET.toString());
		return message;
	}
	
	public static String permFormatColours(Player player, String message)
	{
		if(PermissionsUtil.checkPermission(player, CHAT_PLUGIN + ".chat.format.colors", true))
		{
			message = message.replace("&0", ChatColor.BLACK.toString());
			message = message.replace("&1", ChatColor.DARK_BLUE.toString());
			message = message.replace("&2", ChatColor.DARK_GREEN.toString());
			message = message.replace("&3", ChatColor.DARK_AQUA.toString());
			message = message.replace("&4", ChatColor.DARK_RED.toString());
			message = message.replace("&5", ChatColor.DARK_PURPLE.toString());
			message = message.replace("&6", ChatColor.GOLD.toString());
			message = message.replace("&7", ChatColor.GRAY.toString());
			message = message.replace("&8", ChatColor.DARK_GRAY.toString());
			message = message.replace("&9", ChatColor.BLUE.toString());
			message = message.replace("&a", ChatColor.GREEN.toString());
			message = message.replace("&b", ChatColor.AQUA.toString());
			message = message.replace("&c", ChatColor.RED.toString());
			message = message.replace("&d", ChatColor.LIGHT_PURPLE.toString());
			message = message.replace("&e", ChatColor.YELLOW.toString());
			message = message.replace("&f", ChatColor.WHITE.toString());
		}
		else
		{
			message = message.replace("&0", "");
			message = message.replace("&1", "");
			message = message.replace("&2", "");
			message = message.replace("&3", "");
			message = message.replace("&4", "");
			message = message.replace("&5", "");
			message = message.replace("&6", "");
			message = message.replace("&7", "");
			message = message.replace("&8", "");
			message = message.replace("&9", "");
			message = message.replace("&a", "");
			message = message.replace("&b", "");
			message = message.replace("&c", "");
			message = message.replace("&d", "");
			message = message.replace("&e", "");
			message = message.replace("&f", "");
		}
		if(PermissionsUtil.checkPermission(player, CHAT_PLUGIN + ".chat.format.magic", true))
		{
			message = message.replace("&k", ChatColor.MAGIC.toString());
		}
		else
		{
			message = message.replace("&k", "");
		}
		if(PermissionsUtil.checkPermission(player, CHAT_PLUGIN + ".chat.format.bold", true))
		{
			message = message.replace("&l", ChatColor.BOLD.toString());
		}
		else
		{
			message = message.replace("&l", "");
		}
		if(PermissionsUtil.checkPermission(player, CHAT_PLUGIN + ".chat.format.lines", true))
		{
			message = message.replace("&m", ChatColor.STRIKETHROUGH.toString());
			message = message.replace("&n", ChatColor.UNDERLINE.toString());
		}
		else
		{
			message = message.replace("&m", "");
			message = message.replace("&n", "");
		}
		if(PermissionsUtil.checkPermission(player, CHAT_PLUGIN + ".chat.format.italic", true))
		{
			message = message.replace("&o", ChatColor.ITALIC.toString());
		}
		else
		{
			message = message.replace("&o", "");
		}
		if(PermissionsUtil.checkPermission(player, CHAT_PLUGIN + ".chat.format.reset", true))
		{
			message = message.replace("&r", ChatColor.RESET.toString());
		}
		else
		{
			message = message.replace("&r", "");
		}
		return message;
	}
	
	public static String formatMessage(Player player, String message)
	{
		PermissionUser user = PermissionsEx.getUser(player);
		message = message.replace("%prefix", user.getPrefix());
		message = message.replace("%suffix", user.getSuffix());
		message = message.replace("%player", player.getDisplayName());
		message = formatColours(message);
		
		return message;
	}
	
	public static String permFormatMessage(Player player, String message)
	{
		PermissionUser user = PermissionsEx.getUser(player);
		message = message.replace("%prefix", user.getPrefix());
		message = message.replace("%suffix", user.getSuffix());
		message = message.replace("%player", player.getDisplayName());
		message = permFormatColours(player, message);
		
		return message;
	}
}
