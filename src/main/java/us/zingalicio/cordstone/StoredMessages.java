package us.zingalicio.cordstone;

import java.util.List;
import java.util.Random;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.zingalicio.cordstone.ZingPlugin;
import us.zingalicio.cordstone.util.NameUtil;

public enum StoredMessages 
{
	BANNED("Banned"), //%target %reason
	BLINDED("Blind"), //%duration
	BUTCHERED("Butcher"), //%number
	CHECKED_BIOME("CheckBiome"), //%biome
	CHECKED_DISPLAY_NAME("CheckDisplayName"), //%current
	CHECKED_FLIGHT_SPEED("CheckFlightSpeed"), //%speed
	CHECKED_JOIN_MESSAGE("CheckJoinMessage"), //%current
	CHECKED_PREFIX("CheckPrefix"), //%current
	CHECKED_SUFFIX("CheckSuffix"), //%current
	CHECKED_THUNDERING("CheckThundering"), //%state
	CHECKED_TIME("CheckTime"), //%time
	CHECKED_WALK_SPEED("CheckWalkSpeed"), //%speed
	CLEARED_DISPLAY_NAME("ClearDisplayName"),
	CLEARED_EFFECTS("ClearEffects"), 
	CLEARED_FLIGHT_SPEED("ClearFlightSpeed"),
	CLEARED_INVENTORY("ClearInventory"),
	CLEARED_JOIN_MESSAGE("ClearJoinMessage"),
	CLEARED_KIT("ClearKit"), //%kit
	CLEARED_PREFIX("ClearPrefix"),
	CLEARED_SUFFIX("ClearSuffix"),
	CLEARED_WALK_SPEED("ClearWalkSpeed"),
	CLEARED_WARP("ClearWarp"), //%warp
	CREATED_KIT("CreateKit"), //%kit
	CREATED_WARP("CreateWarp"), //%warp
	EDITED_KIT("EditKit"), //%kit
	EDITED_WARP("EditWarp"), //%warp
	FROZEN("Frozen"), //%duration
	GENERAL_FAILURE("GeneralFailure"),
	GIVEN_EFFECT("GiveEffect"), //%effect %duration
	GIVEN_ITEM("GiveItem"), //%item
	GIVEN_ITEMS("GiveItems"), //%item %amount
	GIVEN_KIT("GiveKit"), //%kit
	GOD_MODE_DISABLED("GodDisabled"),
	GOD_MODE_ENABLED("GodEnabled"),
	HEAD("Head"), //%player
	HEAD_SELF("HeadSelf"),
	HEALED("Healed"),
	INVALID_AMOUNT("InvalidAmount"),
	INVALID_ITEM("InvalidItem"),
	KICKED("Kick"), //%target %reason
	KILLED("Kill"),
	MADE_DRUNK("MakeDrunk"), //%duration
	MADE_SOBER("MakeSober"),
	MUTED("Mute"), //%duration
	NO_CONSOLE("NoConsole"),
	NO_HELP("NoHelp"),
	NO_PLAYER("NoPlayer"),
	PING("Ping"), //%sender
	RAINY_WEATHER("RainyWeather"),
	RELOADED("Reload"),
	ROCKETED("Rocket"), //%power
	SENT_HOME("SendHome"),
	SENT_TO_SPAWN("SendSpawn"), 
	SET_BUILD_MODE("SetBuildMode"), //%state
	SET_DISPLAY_NAME("SetDisplayName"), //%new
	SET_FLIGHT_OFF("SetFlightOff"),
	SET_FLIGHT_ON("SetFlightOn"),
	SET_FLIGHT_SPEED("SetFlightSpeed"), //%speed
	SET_HOME("SetHome"),
	SET_JOIN_MESSAGE("SetJoinMessage"), //%new
	SET_PREFIX("SetPrefix"), //%new
	SET_SUFFIX("SetSuffix"), //%new
	SET_TIME("SetTime"), //%time
	SET_WALK_SPEED("SetWalkSpeed"), //%speed
	SET_WEATHER("SetWeather"), //%state
	SHOCKED("Shock"),
	SLAPPED("Slap"), //%power
	SPAWNED_MOB("SpawnMob"), //%mob
	SUNNY_WEATHER("SunnyWeather"),
	TELEPORT_CALLED("TeleportCall"),
	TELEPORT_DENIED("TeleportDeny"), //%reason
	TELEPORT_PRIVACY_HIGH("TeleprivacyHigh"),
	TELEPORT_PRIVACY_LOW("TeleprivacyLow"),
	TELEPORT_PRIVACY_OFF("TeleprivacyOff"),
	TELEPORT_REQUESTED("TeleportRequest"),
	TELEPORTED("Teleport"), //%destination
	UNBLINDED("Unblind"),
	UNFROZEN("Unfrozen"),
	UNMUTED("Unmute"),
	VERSION("Version"), //%version
	WARPED("Warp"); //%warp
	
	String s;
	
	private StoredMessages(String s)
	{
		this.s = s;
	}
	
	public String selfMessage(ZingPlugin plugin)
	{
		List<String> messageList = plugin.getMessages().getStringList("messages." + this.s + ".Self");
		if(messageList == null || messageList.size() == 0)
		{
			return "No Message Found";
		}
		return messageList.get(new Random().nextInt(messageList.size()));
	}	
	public String fromMessage(ZingPlugin plugin, CommandSender from)
	{
		List<String> messageList = plugin.getMessages().getStringList("messages." + this.s + ".FromOther");
		if(messageList == null || messageList.size() == 0)
		{
			return "No Message Found";
		}
		return messageList.get(new Random().nextInt(messageList.size())).replace("%sender", NameUtil.getSenderName(from));
	}	
	public String toMessage(ZingPlugin plugin, Player to)
	{
		List<String> messageList = plugin.getMessages().getStringList("messages." + this.s + ".ToOther");
		if(messageList == null || messageList.size() == 0)
		{
			return "No Message Found";
		}
		return messageList.get(new Random().nextInt(messageList.size())).replace("%target", to.getDisplayName());
	}
}
