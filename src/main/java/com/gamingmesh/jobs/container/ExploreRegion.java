package com.gamingmesh.jobs.container;

import com.gamingmesh.jobs.Jobs;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Chunk;

public class ExploreRegion {

	int x;
	int z;

	private final HashMap<Short, ExploreChunk> chunks = new HashMap<>();

	public ExploreRegion(int x, int z) {
		this.x = x;
		this.z = z;
	}

	public void addChunk(int x, int z, ExploreChunk chunk) {
		Jobs.getPluginLogger().warning("ADDING chunk value " + x + "/" + z);

		short place = getPlace(x, z);

		chunks.put(place, chunk);

		Jobs.getPluginLogger().warning("Returning would be " + getChunkX(place) + "/" + getChunkZ(place));
	}

	public HashMap<Short, ExploreChunk> getChunks() {
		return chunks;
	}

	public ExploreChunk getChunk(int x, int z) {
		Jobs.getPluginLogger().warning("Getting chunk for pos " + x + "/" + z);

		return getChunk(getPlace(x, z));
	}

	public ExploreChunk getChunk(Chunk chunk) {
		return getChunk(getPlace(chunk));
	}

	public ExploreChunk getChunk(short place) {
		Jobs.getPluginLogger().warning("Getting chunk " + place);

		return chunks.get(place);
	}

	private static short getPlace(Chunk chunk) {
		Jobs.getPluginLogger().warning("Getting chunk value " + chunk.getX() + "/" + chunk.getZ());

		return getPlace(chunk.getX(), chunk.getZ());
	}

	private static short getPlace(int x, int z) {
		return (short) ((x - ((x >> 5) * 32)) + ((z - ((z >> 5) * 32)) * 32));
	}

	public int getChunkX(short place) {
		int endX = place % 32;
		if (x < 0)
			endX = -endX;
		endX = x * 32 + endX;
		endX = endX < 0 ? endX + 32 : endX;
		return endX;
	}

	public int getChunkZ(short place) {
		int endZ = (place - (place % 32)) / 32;
		if (z < 0)
			endZ = -endZ;
		endZ = z * 32 + endZ;
		endZ = endZ < 0 ? endZ + 32 : endZ;
		return endZ;
	}
}
