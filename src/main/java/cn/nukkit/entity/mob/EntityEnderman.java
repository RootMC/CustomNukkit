package cn.nukkit.entity.mob;

import cn.nukkit.Player;
import cn.nukkit.block.BlockWater;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.sound.EndermanTeleportSound;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.Utils;

import java.util.HashMap;

public class EntityEnderman extends EntityWalkingMob {

    public static final int NETWORK_ID = 38;

    private boolean angry = false;

    public EntityEnderman(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public float getWidth() {
        return 0.6f;
    }

    @Override
    public float getHeight() {
        return 2.9f;
    }

    @Override
    public double getSpeed() {
        return 1.21;
    }

    @Override
    protected void initEntity() {
        super.initEntity();

        this.setMaxHealth(40);
        this.setDamage(new int[]{0, 4, 7, 10});
    }

    @Override
    public void attackEntity(Entity player) {
        if (this.attackDelay > 23 && this.distanceSquared(player) < 1) {
            this.attackDelay = 0;
            HashMap<EntityDamageEvent.DamageModifier, Float> damage = new HashMap<>();
            damage.put(EntityDamageEvent.DamageModifier.BASE, (float) this.getDamage());

            if (player instanceof Player) {
                HashMap<Integer, Float> armorValues = new ArmorPoints();

                float points = 0;
                for (Item i : ((Player) player).getInventory().getArmorContents()) {
                    points += armorValues.getOrDefault(i.getId(), 0f);
                }

                damage.put(EntityDamageEvent.DamageModifier.ARMOR,
                        (float) (damage.getOrDefault(EntityDamageEvent.DamageModifier.ARMOR, 0f) - Math.floor(damage.getOrDefault(EntityDamageEvent.DamageModifier.BASE, 1f) * points * 0.04)));
            }
            player.attack(new EntityDamageByEntityEvent(this, player, EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage));
        }
    }

    @Override
    public boolean attack(EntityDamageEvent ev) {
        super.attack(ev);

        if (!ev.isCancelled()) {
            if (ev.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                if (!angry) {
                    setAngry(true);
                }
            }

            if (ev.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
                if (!angry) {
                    setAngry(true);
                }
                ev.setCancelled(true);
                tp();
                return false;
            } else if (Utils.rand(1, 10) == 1) {
                tp();
            }
        }
        return true;
    }

    @Override
    public Item[] getDrops() {
        return new Item[]{Item.get(Item.ENDER_PEARL, 0, Utils.rand(0, 1))};
    }

    @Override
    public int getKillExperience() {
        return 5;
    }

    @Override
    public boolean entityBaseTick(int tickDiff) {
        if (getServer().getDifficulty() == 0) {
            this.close();
            return true;
        }

        if (this.level.getBlock(new Vector3(NukkitMath.floorDouble(this.x), (int) this.y, NukkitMath.floorDouble(this.z))) instanceof BlockWater) {
            this.attack(new EntityDamageEvent(this, EntityDamageEvent.DamageCause.DROWNING, 2));
            if (angry) {
                setAngry(false);
            }
            tp();
        } else if (this.getLevel().isRaining() && this.getLevel().canBlockSeeSky(this) && Utils.rand(1, 5) == 1) {
            this.attack(1f);
            tp();
        } else if (Utils.rand(0, 500) == 20) {
            tp();
        }

        if (this.age % 20 == 0 && this.level.isRaining() && this.level.canBlockSeeSky(this)) {
            this.attack(new EntityDamageEvent(this, EntityDamageEvent.DamageCause.DROWNING, 2));
            if (angry) {
                setAngry(false);
            }
            tp();
        }

        return super.entityBaseTick(tickDiff);
    }

    private void tp() {
        this.level.addSound(new EndermanTeleportSound(this));
        this.move(Utils.rand(-10, 10), 0, Utils.rand(-10, 10));
        this.level.addSound(new EndermanTeleportSound(this));
    }

    @Override
    public boolean canDespawn() {
        if (this.getLevel().isEnd) {
            return false;
        }

        return super.canDespawn();
    }

    public boolean isAngry() {
        return angry;
    }

    public void setAngry(boolean bool) {
        this.angry = bool;
        this.setDataFlag(DATA_FLAGS, DATA_FLAG_ANGRY, bool);
    }

    @Override
    public boolean targetOption(EntityCreature creature, double distance) {
        if (!angry) return false;
        return super.targetOption(creature, distance);
    }

    public void stareToAngry() {
        if (!angry) {
            setAngry(true);
        }
    }
}
