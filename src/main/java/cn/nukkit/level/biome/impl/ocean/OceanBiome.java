package cn.nukkit.level.biome.impl.ocean;

import cn.nukkit.level.biome.type.WateryBiome;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class OceanBiome extends WateryBiome {

    public OceanBiome() {
        this.setBaseHeight(-1.0f);
        this.setHeightVariation(0.1f);
    }

    @Override
    public String getName() {
        return "Ocean";
    }

    @Override
    public int getGroundId(int x, int y, int z) {
        return 208;
    }
}
