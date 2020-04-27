package cn.nukkit.utils.spawners;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.passive.EntityParrot;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.utils.AbstractEntitySpawner;
import cn.nukkit.utils.Utils;
import cn.nukkit.utils.Spawner;

public class ParrotSpawner extends AbstractEntitySpawner {

    public ParrotSpawner(Spawner spawnTask) {
        super(spawnTask);
    }

    public void spawn(Player player, Position pos, Level level) {
        if (Utils.rand(1, 3) == 1) {
            return;
        }

        final int biomeId = level.getBiomeId((int) pos.x, (int) pos.z);
        final int blockId = level.getBlockIdAt((int) pos.x, (int) pos.y, (int) pos.z);

        if (biomeId != 21 && biomeId != 149 && biomeId != 23 && biomeId != 151) {
        } else if (level.isNether || level.isEnd) {
        } else if (blockId != Block.GRASS && blockId != Block.LEAVES) {
        } else if (pos.y > 255 || pos.y < 1) {
        } else if (level.isAnimalSpawningAllowedByTime()) {
            this.spawnTask.createEntity("Parrot", pos.add(0, 1, 0));
        }
    }

    @Override
    public final int getEntityNetworkId() {
        return EntityParrot.NETWORK_ID;
    }
}
