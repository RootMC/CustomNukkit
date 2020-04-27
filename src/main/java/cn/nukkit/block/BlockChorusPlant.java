package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

public class BlockChorusPlant extends BlockTransparent {

    @Override
    public int getId() {
        return CHORUS_PLANT;
    }

    @Override
    public String getName() {
        return "Chorus Plant";
    }

    @Override
    public double getHardness() {
        return 0.4;
    }

    @Override
    public double getResistance() {
        return 2;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        if (block.down().isTransparent() &&
        !(block.down() instanceof BlockChorusPlant) &&
        !(block.north() instanceof BlockChorusPlant) &&
        !(block.east() instanceof BlockChorusPlant) &&
        !(block.south() instanceof BlockChorusPlant) &&
        !(block.west() instanceof BlockChorusPlant)) {
            return false;
        }

        this.level.setBlock(block, this, true, true);
        return true;
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (this.down().isTransparent() &&
            !(this.down() instanceof BlockChorusPlant) &&
            !(this.north() instanceof BlockChorusPlant) &&
            !(this.east() instanceof BlockChorusPlant) &&
            !(this.south() instanceof BlockChorusPlant) &&
            !(this.west() instanceof BlockChorusPlant)) {
                this.getLevel().useBreakOn(this);

                return Level.BLOCK_UPDATE_NORMAL;
            }
        }

        return 0;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.PURPLE_BLOCK_COLOR;
    }

    @Override
    public boolean breaksWhenMoved() {
        return true;
    }

    @Override
    public boolean sticksToPiston() {
        return false;
    }
}
