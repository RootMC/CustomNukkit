package cn.nukkit.utils.spawners;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.mob.EntityWitherSkeleton;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.utils.AbstractEntitySpawner;
import cn.nukkit.utils.Spawner;

public class WitherSkeletonSpawner extends AbstractEntitySpawner {

    public WitherSkeletonSpawner(Spawner spawnTask) {
        super(spawnTask);
    }

    @Override
    public void spawn(Player player, Position pos, Level level) {
        if (!level.isNether) {
        } else if (pos.y > 127 || pos.y < 1) {
        } else if (level.getBlockIdAt((int) pos.x, (int) pos.y, (int) pos.z) != Block.NETHERRACK) {
        } else {
            this.spawnTask.createEntity("WitherSkeleton", pos.add(0, 1, 0));
        }
    }

    @Override
    public final int getEntityNetworkId() {
        return EntityWitherSkeleton.NETWORK_ID;
    }
}
