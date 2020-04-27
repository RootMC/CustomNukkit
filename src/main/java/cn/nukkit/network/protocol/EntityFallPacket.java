package cn.nukkit.network.protocol;

import lombok.ToString;

@ToString
public class EntityFallPacket extends DataPacket {

    public long eid;
    public float fallDistance;
    public boolean isInVoid;

    @Override
    public void decode() {
        this.eid = this.getEntityRuntimeId();
        this.fallDistance = this.getLFloat();
        this.isInVoid = this.getBoolean();
    }

    @Override
    public void encode() {
    }

    @Override
    public byte pid() {
        return ProtocolInfo.ENTITY_FALL_PACKET;
    }
}
