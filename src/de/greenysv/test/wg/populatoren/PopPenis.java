package de.greenysv.test.wg.populatoren;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class PopPenis extends BlockPopulator {

	@Override
	public void populate(World w, Random r, Chunk c) {
		//w.generateTree(new Location(w, c.getX() * 16, w.getHighestBlockYAt(c.getX() * 16, c.getZ() * 16) ,c.getZ() * 16), TreeType.TREE);
		
		if(r.nextBoolean()) // 50-50
			return; // no Penis
		
		// Centered in the Chunk
		int x = c.getX() * 16 + 8;
		int z = c.getZ() * 16 + 8;
		int y = w.getHighestBlockYAt(x, z);
		
		// nach oben
		w.getBlockAt(x, y  , z).setTypeId(Material.ICE.getId());
		w.getBlockAt(x, y+1, z).setTypeId(Material.ICE.getId());
		w.getBlockAt(x, y+2, z).setTypeId(Material.ICE.getId());
		
		// seitlich
		if(r.nextBoolean()) {
			w.getBlockAt(x+1, y, z).setTypeId(Material.ICE.getId());
			w.getBlockAt(x-1, y, z).setTypeId(Material.ICE.getId());
		} else {
			w.getBlockAt(x, y, z+1).setTypeId(Material.ICE.getId());
			w.getBlockAt(x, y, z-1).setTypeId(Material.ICE.getId());
		}
	}

}
