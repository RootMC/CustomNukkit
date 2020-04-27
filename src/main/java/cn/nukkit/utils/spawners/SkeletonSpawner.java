package cn.nukkit.utils.spawners;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.mob.EntitySkeleton;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.utils.AbstractEntitySpawner;
import cn.nukkit.utils.Spawner;

public class SkeletonSpawner extends AbstractEntitySpawner {

    public SkeletonSpawner(Spawner spawnTask) {
        super(spawnTask);
    }

    @Override
    public void spawn(Player player, Position pos, Level level) {
        if (level.isNether || level.isEnd) {
        } else if (pos.y > 255 || pos.y < 1) {
        } else if (Block.transparent[level.getBlockIdAt((int) pos.x, (int) pos.y, (int) pos.z)]) {
        } else if (level.getBlockLightAt((int) pos.x, (int) pos.y, (int) pos.z) > 7) {
        } else if (level.isMobSpawningAllowedByTime()) {
            this.spawnTask.createEntity("Skeleton", pos.add(0, 1, 0));
        }
    }

    @Override
    public final int getEntityNetworkId() {
        return EntitySkeleton.NETWORK_ID;
    }
}
