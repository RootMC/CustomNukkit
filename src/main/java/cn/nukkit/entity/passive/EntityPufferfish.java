package cn.nukkit.entity.passive;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.ByteEntityData;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.potion.Effect;

public class EntityPufferfish extends EntityFish {

    public static final int NETWORK_ID = 108;

    protected int puffed = 0;

    public EntityPufferfish(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public float getWidth() {
        return 0.35f;
    }

    @Override
    public float getHeight() {
        return 0.35f;
    }

    @Override
    public void initEntity() {
        super.initEntity();

        this.setMaxHealth(3);
    }

    @Override
    public Item[] getDrops() {
        return new Item[]{Item.get(Item.PUFFERFISH, 0, 1)};
    }

    @Override
    public int getKillExperience() {
        return 0;
    }

    @Override
    public boolean attack(EntityDamageEvent ev) {
        super.attack(ev);

        if (ev instanceof EntityDamageByEntityEvent) {
            Entity damager = ((EntityDamageByEntityEvent) ev).getDamager();
            if (damager instanceof Player) {
                if (this.puffed > 0) return true;
                this.puffed = 200;
                damager.addEffect(Effect.getEffect(Effect.POISON).setDuration(140));
                this.setDataProperty(new ByteEntityData(DATA_PUFFERFISH_SIZE, 2));
            }
        }

        return true;
    }

    @Override
    public boolean entityBaseTick(int tickDiff) {
        if (puffed == 0) {
            if (this.getDataPropertyByte(DATA_PUFFERFISH_SIZE) == 2) {
                this.setDataProperty(new ByteEntityData(DATA_PUFFERFISH_SIZE, 0));
            }
        }

        if (puffed > 0) {
            puffed--;
        }

        return super.entityBaseTick(tickDiff);
    }
}
