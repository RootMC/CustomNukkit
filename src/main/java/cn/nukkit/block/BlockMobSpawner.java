package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntitySpawner;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * Created by Pub4Game on 27.12.2015.
 */
public class BlockMobSpawner extends BlockSolid {

    @Override
    public String getName() {
        return "Monster Spawner";
    }

    @Override
    public int getId() {
        return MONSTER_SPAWNER;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public double getHardness() {
        return 5;
    }

    @Override
    public double getResistance() {
        return 25;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        if (!super.place(item, block, target, face, fx, fy, fz, player)) return false;
        new BlockEntitySpawner(block.getLevel().getChunk((int) this.x >> 4, (int) this.z >> 4), new CompoundTag());
        return true;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[0];
    }

    @Override
    public int getLightLevel() {
        return 3;
    }

    @Override
    public boolean canBePulled() {
        return false;
    }
}
