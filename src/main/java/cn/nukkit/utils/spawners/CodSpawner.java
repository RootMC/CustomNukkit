package cn.nukkit.utils.spawners;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockWater;
import cn.nukkit.entity.passive.EntityCod;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.utils.AbstractEntitySpawner;
import cn.nukkit.utils.Spawner;

public class CodSpawner extends AbstractEntitySpawner {

    public CodSpawner(Spawner spawnTask) {
        super(spawnTask);
    }

    public void spawn(Player player, Position pos, Level level) {
        final int biomeId = level.getBiomeId((int) pos.x, (int) pos.z);
        final int blockId = level.getBlockIdAt((int) pos.x, (int) pos.y, (int) pos.z);

        if (blockId != Block.WATER && blockId != Block.STILL_WATER) {
        } else if (biomeId != 0 && biomeId != 7) {
        } else if (pos.y > 255 || pos.y < 1) {
        } else if (level.isNether || level.isEnd) {
        } else {
            if (level.getBlock(pos.add(0, -1, 0)) instanceof BlockWater) {
                this.spawnTask.createEntity("Cod", pos.add(0, -1, 0));
            }
        }
    }

    @Override
    public final int getEntityNetworkId() {
        return EntityCod.NETWORK_ID;
    }
}
