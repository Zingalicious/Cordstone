package us.zingalicio.cordstone;

import us.zingalicio.cordstone.commands.ReloadCommand;

public class Cordstone extends ZingPlugin
{
	ReloadCommand reloadCommand;
	public Cordstone()
	{
		this.reloadCommand = new ReloadCommand(this);
	}
	
	public void onEnable()
	{
		getCommand("cordstone").setExecutor(reloadCommand);
	}
}
