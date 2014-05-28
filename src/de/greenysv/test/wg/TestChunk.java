package de.greenysv.test.wg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import de.greenysv.test.wg.populatoren.PopPenis;

public class TestChunk extends ChunkGenerator {

	//private final int MAX_GEN_HEIGHT = 255;
	
	ArrayList<BlockPopulator> AllePopulatoren = new ArrayList<BlockPopulator>();
	
	public TestChunk() {
		AllePopulatoren.add(new PopPenis());
	}
	
	
	@Override
    public short[][] generateExtBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {
		
		int maxHeight = world.getMaxHeight();
        
		// Alle Biome auf PLAINS setzen
		//biomes.setBiome(x, z, Biome.PLAINS);
		
        short[][] result = new short[maxHeight / 16][4096]; // 16x16x16 chunks
        
		int inX = 0; // 0-16
		int inY = 0; // 0-255
		int inZ = 0; // 0-16
		
		for(inX = 0; inX < 16; inX++)
			for(inZ = 0; inZ < 16; inZ++) {
				
				// Sinuswelle errechnen
				inY = (int)(Math.sin((x*16 + inX) / Math.PI / 10) * 25 + 25) + (int)(Math.cos((z*16 + inZ) / Math.PI / 10) * 25 + 25);
				
				// und abspeichern
				result[inY >> 4][((inY & 0xF) << 8) | (inZ << 4) | inX] = (short)Material.GRASS.getId();
			}
		
        return result;
		
    }
	
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world)
    {
//        if (layerDataValues != null) {
//            return Arrays.asList((BlockPopulator)new CleanroomBlockPopulator(layerDataValues));
//        } else {
            // This is the default, but just in case default populators change to stock minecraft populators by default...
            return AllePopulatoren;
//        }
    }
	
    
    @Override
    public Location getFixedSpawnLocation(World world, Random random)
    {
        if (!world.isChunkLoaded(0, 0)) {
            world.loadChunk(0, 0);
        }

//        if ((world.getHighestBlockYAt(0, 0) <= 0) && (world.getBlockAt(0, 0, 0).getType() == Material.AIR)) { // SPACE!
//            return new Location(world, 0, 64, 0); // Lets allow people to drop a little before hitting the void then shall we?
//        }

        return new Location(world, 0, world.getHighestBlockYAt(0, 0), 0);
    }
}
