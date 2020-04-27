package cn.nukkit.entity.passive;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.utils.Utils;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class EntityOcelot extends EntityTameableAnimal {

    public static final int NETWORK_ID = 22;

    public EntityOcelot(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public float getWidth() {
        if (this.isBaby()) {
            return 0.3f;
        }
        return 0.6f;
    }

    @Override
    public float getHeight() {
        if (this.isBaby()) {
            return 0.35f;
        }
        return 0.7f;
    }

    @Override
    public double getSpeed() {
        return 1.4;
    }

    @Override
    protected void initEntity() {
        super.initEntity();

        this.setMaxHealth(10);
    }

    @Override
    public boolean targetOption(EntityCreature creature, double distance) {
        if (creature instanceof Player) {
            Player player = (Player) creature;
            return player.spawned && player.isAlive() && !player.closed && (player.getInventory().getItemInHand().getId() == Item.RAW_FISH || player.getInventory().getItemInHand().getId() == Item.RAW_SALMON) && distance <= 40;
        }
        return false;
    }

    public int getKillExperience() {
        return this.isBaby() ? 0 : Utils.rand(1, 3);
    }

    public String getOwnerUUID() {
        return "";
    }

    public void setOwner(Player owner) {}
    public void setOwnerUUID(String uuid) {}
}
