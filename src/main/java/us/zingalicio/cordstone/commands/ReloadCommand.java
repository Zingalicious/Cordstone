package us.zingalicio.cordstone.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import us.zingalicio.cordstone.Cordstone;
import us.zingalicio.cordstone.util.ConfigUtil;

public class ReloadCommand implements CommandExecutor
{
	private final Cordstone plugin;
	
	public ReloadCommand(Cordstone plugin)
	{
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) 
	{
		ConfigUtil.reloadCommon();
		ConfigUtil.loadYaml(plugin.getConfig(), plugin.getConfigFile());
		Bukkit.getPluginManager().disablePlugin(plugin);
		Bukkit.getPluginManager().enablePlugin(plugin);
		return true;
	}
}
