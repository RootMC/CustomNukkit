package cn.nukkit.entity.passive;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.utils.Utils;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.particle.PunchBlockParticle;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;

import java.util.ArrayList;
import java.util.List;

public class EntityRabbit extends EntityJumpingAnimal {

    public static final int NETWORK_ID = 18;

    public EntityRabbit(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public float getWidth() {
        if (this.isBaby()) {
            return 0.2f;
        }
        return 0.4f;
    }

    @Override
    public float getHeight() {
        if (this.isBaby()) {
            return 0.25f;
        }
        return 0.5f;
    }

    @Override
    public double getSpeed() {
        return 1.2;
    }

    @Override
    public void initEntity() {
        super.initEntity();

        this.setMaxHealth(3);
    }

    @Override
    public boolean targetOption(EntityCreature creature, double distance) {
        if (creature instanceof Player) {
            Player player = (Player) creature;
            return player.spawned && player.isAlive() && !player.closed && player.getInventory().getItemInHand().getId() == Item.CARROT && distance <= 40;
        }
        return false;
    }

    @Override
    public Item[] getDrops() {
        List<Item> drops = new ArrayList<>();

        if (!this.isBaby()) {
            drops.add(Item.get(Item.RABBIT_HIDE, 0, Utils.rand(0, 1)));
            drops.add(Item.get(this.isOnFire() ? Item.COOKED_RABBIT : Item.RAW_RABBIT, 0, Utils.rand(0, 1)));

            for (int i = 0; i < (Utils.rand(0, 101) <= 9 ? 1 : 0); i++) {
                drops.add(Item.get(Item.RABBIT_FOOT, 0, 1));
            }
        }

        return drops.toArray(new Item[0]);
    }

    @Override
    public int getKillExperience() {
        return this.isBaby() ? 0 : Utils.rand(1, 3);
    }
    
    @Override
    public boolean onUpdate(int currentTick) {
        boolean hasUpdate = super.onUpdate(currentTick);
        try {
            if (this.isOnGround()) this.level.addParticle(new PunchBlockParticle(this, this.level.getBlock((int) x, (int) y - 1, (int) z), BlockFace.UP));
        } catch (Exception ignored) {}
        return hasUpdate;
    }
}
