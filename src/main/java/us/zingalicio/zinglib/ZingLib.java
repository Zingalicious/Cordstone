package us.zingalicio.zinglib;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

import us.zingalicio.zinglib.plugin.ZingPlugin;
import us.zingalicio.zinglib.util.ConfigUtil;
import us.zingalicio.zinglib.util.MessageUtil;
import us.zingalicio.zinglib.util.PermissionsUtil;

public class ZingLib extends ZingPlugin implements CommandExecutor
{
	public ZingLib()
	{
		super();
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(args.length == 0)
		{
			return false;
		}
		else if(args[0].equals("reload"))
		{
			try
			{
				if(PermissionsUtil.checkPermission(sender, "zinglib.reload", false))
				{
					ConfigUtil.loadYaml(this.getItems(), this.getItemFile());
					ConfigUtil.loadYaml(this.getConfig(), this.getConfigFile());
					ConfigUtil.loadYaml(this.getMaterials(), this.getMaterialFile());
					ConfigUtil.loadYaml(this.getMessages(), this.getMessageFile());
					PluginManager pluginManager = Bukkit.getPluginManager();
					pluginManager.disablePlugin(this);
					pluginManager.enablePlugin(this);
					MessageUtil.sendMessage(this, sender, StoredMessages.RELOADED.selfMessage(this));
				}
				return true;
			}
			catch(Throwable t)
			{
				MessageUtil.sendError(this, sender, StoredMessages.GENERAL_FAILURE.selfMessage(this));
				return true;
			}
		}
		else if(args[0].equals("version"))
		{
			MessageUtil.sendMessage(this, sender, StoredMessages.VERSION.selfMessage(this).replace("%version", this.getDescription().getVersion()));
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
