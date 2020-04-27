package cn.nukkit.entity.mob;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.utils.Utils;
import cn.nukkit.entity.EntityWalking;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;

public abstract class EntityWalkingMob extends EntityWalking implements EntityMob {

    private int[] minDamage;

    private int[] maxDamage;

    private boolean canAttack = true;

    public EntityWalkingMob(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public void setTarget(Entity target) {
        if (this instanceof EntityCaveSpider && !(target instanceof Player)) return; // A temporary fix
        this.setTarget(target, true);
    }

    public void setTarget(Entity target, boolean attack) {
        super.setTarget(target);
        this.canAttack = attack;
    }

    public int getDamage() {
        return getDamage(null);
    }

    public int getDamage(Integer difficulty) {
        return Utils.rand(this.getMinDamage(difficulty), this.getMaxDamage(difficulty));
    }

    public int getMinDamage() {
        return getMinDamage(null);
    }

    public int getMinDamage(Integer difficulty) {
        if (difficulty == null || difficulty > 3 || difficulty < 0) {
            difficulty = Server.getInstance().getDifficulty();
        }
        return this.minDamage[difficulty];
    }

    public int getMaxDamage() {
        return getMaxDamage(null);
    }

    public int getMaxDamage(Integer difficulty) {
        if (difficulty == null || difficulty > 3 || difficulty < 0) {
            difficulty = Server.getInstance().getDifficulty();
        }
        return this.maxDamage[difficulty];
    }

    public void setDamage(int damage) {
        this.setDamage(damage, Server.getInstance().getDifficulty());
    }

    public void setDamage(int damage, int difficulty) {
        if (difficulty >= 1 && difficulty <= 3) {
            this.minDamage[difficulty] = damage;
            this.maxDamage[difficulty] = damage;
        }
    }

    public void setDamage(int[] damage) {
        if (damage.length < 4) {
            return;
        }

        if (minDamage == null || minDamage.length < 4) {
            minDamage = Utils.emptyDamageArray;
        }

        if (maxDamage == null || maxDamage.length < 4) {
            maxDamage = Utils.emptyDamageArray;
        }

        for (int i = 0; i < 4; i++) {
            this.minDamage[i] = damage[i];
            this.maxDamage[i] = damage[i];
        }
    }

    public void setMinDamage(int[] damage) {
        if (damage.length < 4) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            this.setMinDamage(Math.min(damage[i], this.getMaxDamage(i)), i);
        }
    }

    public void setMinDamage(int damage) {
        this.setMinDamage(damage, Server.getInstance().getDifficulty());
    }

    public void setMinDamage(int damage, int difficulty) {
        if (difficulty >= 1 && difficulty <= 3) {
            this.minDamage[difficulty] = Math.min(damage, this.getMaxDamage(difficulty));
        }
    }

    public void setMaxDamage(int[] damage) {
        if (damage.length < 4)
            return;

        for (int i = 0; i < 4; i++) {
            this.setMaxDamage(Math.max(damage[i], this.getMinDamage(i)), i);
        }
    }

    public void setMaxDamage(int damage) {
        setMinDamage(damage, Server.getInstance().getDifficulty());
    }

    public void setMaxDamage(int damage, int difficulty) {
        if (difficulty >= 1 && difficulty <= 3) {
            this.maxDamage[difficulty] = Math.max(damage, this.getMinDamage(difficulty));
        }
    }

    public boolean onUpdate(int currentTick) {
        if (this.closed) {
            return false;
        }

        if (this.server.getDifficulty() < 1) {
            this.close();
            return false;
        }

        if (!this.isAlive()) {
            if (++this.deadTicks >= 23) {
                this.close();
                return false;
            }
            return true;
        }

        int tickDiff = currentTick - this.lastUpdate;
        this.lastUpdate = currentTick;
        this.entityBaseTick(tickDiff);

        Vector3 target = this.updateMove(tickDiff);
        if ((!this.isFriendly() || !(target instanceof Player)) && target instanceof Entity) {
            if (target != this.followTarget || this.canAttack) {
                this.attackEntity((Entity) target);
            }
        }
        return true;
    }
}
