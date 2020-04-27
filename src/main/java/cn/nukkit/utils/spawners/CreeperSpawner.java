package cn.nukkit.utils.spawners;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.mob.EntityCreeper;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.utils.AbstractEntitySpawner;
import cn.nukkit.utils.Spawner;

public class CreeperSpawner extends AbstractEntitySpawner {

    public CreeperSpawner(Spawner spawnTask) {
        super(spawnTask);
    }

    public void spawn(Player player, Position pos, Level level) {
        if (pos.y > 255 || pos.y < 1) {
        } else if (level.isNether || level.isEnd) {
        } else if (Block.transparent[level.getBlockIdAt((int) pos.x, (int) pos.y, (int) pos.z)]) {
        } else if (level.getBlockLightAt((int) pos.x, (int) pos.y, (int) pos.z) > 7) {
        } else if (level.isMobSpawningAllowedByTime()) {
            this.spawnTask.createEntity("Creeper", pos.add(0, 1, 0));
        }
    }

    @Override
    public final int getEntityNetworkId() {
        return EntityCreeper.NETWORK_ID;
    }
}
