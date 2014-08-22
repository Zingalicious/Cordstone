package us.zingalicio.cordstone.util;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Location;

public class ChunkUtil 
{
	public static ArrayList<Chunk> getChunks(Location loc, int radius)
	{
		ArrayList<Chunk> chunks = new ArrayList<>(((radius * 2) + 1) * ((radius * 2) + 1));
		int x = loc.getBlockX();
		int z = loc.getBlockZ();
		int dX = radius * -1;
		while(dX <= radius)
		{
			int dZ = radius * -1;
			while(dZ <= radius)
			{
				chunks.add(new Location(loc.getWorld(), x + (dX * 16), 1, z + (dZ * 16)).getChunk());
				dZ++;
			}
			dX++;
		}
		return chunks;
	}
}
