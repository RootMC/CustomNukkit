package cn.nukkit.network.protocol;

import lombok.ToString;

@ToString
public class MoveEntityDeltaPacket extends DataPacket {

    public static final int FLAG_HAS_X = 0b1;
    public static final int FLAG_HAS_Y = 0b10;
    public static final int FLAG_HAS_Z = 0b100;
    public static final int FLAG_HAS_YAW = 0b1000;
    public static final int FLAG_HAS_HEAD_YAW = 0b10000;
    public static final int FLAG_HAS_PITCH = 0b100000;

    public long eid;
    public int flags = 0;
    public int xDelta = 0;
    public int yDelta = 0;
    public int zDelta = 0;
    public double yawDelta = 0;
    public double headYawDelta = 0;
    public double pitchDelta = 0;

    @Override
    public byte pid() {
        return ProtocolInfo.MOVE_ENTITY_DELTA_PACKET;
    }

    @Override
    public void decode() {
        this.getEntityRuntimeId();
        this.flags = this.getByte();
        this.xDelta = getCoordinate(FLAG_HAS_X);
        this.yDelta = getCoordinate(FLAG_HAS_Y);
        this.zDelta = getCoordinate(FLAG_HAS_Z);
        this.yawDelta = getRotation(FLAG_HAS_YAW);
        this.headYawDelta = getRotation(FLAG_HAS_HEAD_YAW);
        this.pitchDelta = getRotation(FLAG_HAS_PITCH);
    }

    @Override
    public void encode() {
        this.reset();
        this.putEntityRuntimeId(this.eid);
        this.putByte((byte) flags);
        putCoordinate(FLAG_HAS_X, this.xDelta);
        putCoordinate(FLAG_HAS_Y, this.yDelta);
        putCoordinate(FLAG_HAS_Z, this.zDelta);
        putRotation(FLAG_HAS_YAW, this.yawDelta);
        putRotation(FLAG_HAS_HEAD_YAW, this.headYawDelta);
        putRotation(FLAG_HAS_PITCH, this.pitchDelta);
    }

    private int getCoordinate(int flag) {
        if ((flags & flag) != 0) {
            return this.getVarInt();
        }
        return 0;
    }

    private double getRotation(int flag) {
        if ((flags & flag) != 0) {
            return this.getByte() * 1.40625;
        }
        return 0d;
    }

    private void putCoordinate(int flag, int value) {
        if ((flags & flag) != 0) {
            this.putVarInt(value);
        }
    }

    private void putRotation(int flag, double value) {
        if ((flags & flag) != 0) {
            this.putByte((byte) (value / 1.40625));
        }
    }
}
