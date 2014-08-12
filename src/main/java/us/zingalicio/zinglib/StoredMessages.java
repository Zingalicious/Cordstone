package us.zingalicio.zinglib;

import java.util.List;
import java.util.Random;

import us.zingalicio.zinglib.plugin.ZingPlugin;

public enum StoredMessages 
{
	GENERAL_FAILURE("GeneralFailure"),
	NO_CONSOLE("NoConsole"),
	BANNED("Banned"), //%target %reason
	KICKED("Kicked"), //%target %reason
	BIOME("Biome"), //%biome
	SET_BUILD_MODE("BuildMode"), //%state
	BUTCHER("Butcher"), //%number
	SET_FLIGHT_ON("FlightOn"),
	SET_FLIGHT_OFF("FlightOff"),
	SET_FLIGHT_SPEED("FlightSpeed"), //%speed
	SET_WALK_SPEED("WalkSpeed"), //%speed
	CHECKED_FLIGHT_SPEED("CheckFlightSpeed"), //%speed
	CHECKED_WALK_SPEED("CheckWalkSpeed"), //%speed
	GIVEN_ITEM("GiveOne"), //%item
	GIVEN_ITEMS("GiveMany"), //%item %amount
	CLEARED_INVENTORY("Clear"),
	GIVEN_KIT("Kit"), //%kit
	EDITED_KIT("EditKit"), //%kit
	CREATED_KIT("CreateKit"), //%kit
	REMOVED_KIT("RemoveKit"), //%kit
	HEAD("Head"), //%player
	HEAD_SELF("HeadSelf"),
	SENT_HOME("Home"),
	SET_HOME("SetHome"),
	GOD_MODE_ENABLED("GodEnabled"),
	GOD_MODE_DISABLED("GodDisabled"),
	HEALED("Healed"),
	INVALID_ITEM("InvalidItem"),
	INVALID_AMOUNT("InvalidAmount"),
	NO_PLAYER("NoPlayer"),
	PING("Ping"), //%sender
	ROCKETED("Rocket"), //%power
	SLAPPED("Slap"), //%power
	FROZEN("Frozen"), //%duration
	UNFROZEN("Unfrozen"),
	SHOCKED("Shock"),
	MADE_DRUNK("Drunk"), //%duration
	MADE_SOBER("Sober"),
	GIVEN_EFFECT("Effect"), //%effect %duration
	CLEARED_EFFECTS("ClearedEffects"), 
	BLINDED("Blind"), //%duration
	UNBLINDED("Unblind"),
	MUTED("Mute"), //%duration
	UNMUTED("Unmute"),
	KILLED("Kill"),
	SENT_TO_SPAWN("Spawn"), 
	SPAWNED_MOB("SpawnMob"), //%mob
	TELEPORTED("Teleport"), //%destination
	TELEPORT_REQUEST("TeleportRequest"),
	TELEPORT_CALL("TeleportCall"),
	TELEPORT_PRIVACY_OFF("TeleprivacyOff"),
	TELEPORT_PRIVACY_LOW("TeleprivacyLow"),
	TELEPORT_PRIVACY_HIGH("TeleprivacyHigh"),
	TELEPORT_DENIED("TeleportDenied"), //%reason
	WARPED("Warp"), //%warp
	EDITED_WARP("EditWarp"), //%warp
	CREATED_WARP("CreateWarp"), //%warp
	REMOVED_WARP("RemoveWarp"), //%warp
	TIME("Time"), //%time
	SET_TIME("SetTime"), //%time
	SUNNY_WEATHER("SunnyWeather"),
	RAINY_WEATHER("RainyWeather"),
	THUNDERING("Thundering"), //%state
	SET_WEATHER("SetWeather"), //%state
	SET_PREFIX("SetPrefix"), //%new
	SET_SUFFIX("SetSuffix"), //%new
	SET_DISPLAY_NAME("SetDisplayName"), //%new
	SET_JOIN_MESSAGE("SetJoinMessage"), //%new
	CHECKED_PREFIX("CheckPrefix"), //%current
	CHECKED_SUFFIX("CheckSuffix"), //%current
	CHECKED_DISPLAY_NAME("CheckDisplayName"), //%current
	CHECKED_JOIN_MESSAGE("CheckJoinMessage"), //%current
	CLEARED_PREFIX("ClearPrefix"),
	CLEARED_SUFFIX("ClearSuffix"),
	CLEARED_DISPLAY_NAME("ClearDisplayName"),
	CLEARED_JOIN_MESSAGE("ClearJoinMessage"),
	NO_HELP("NoHelp"),
	RELOADED("Reload"),
	VERSION("Version"); //%version
	
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
	public String fromMessage(ZingPlugin plugin)
	{
		List<String> messageList = plugin.getMessages().getStringList("messages." + this.s + ".FromOther");
		if(messageList == null || messageList.size() == 0)
		{
			return "No Message Found";
		}
		return messageList.get(new Random().nextInt(messageList.size()));
	}	
	public String toMessage(ZingPlugin plugin)
	{
		List<String> messageList = plugin.getMessages().getStringList("messages." + this.s + ".ToOther");
		if(messageList == null || messageList.size() == 0)
		{
			return "No Message Found";
		}
		return messageList.get(new Random().nextInt(messageList.size()));
	}
}
