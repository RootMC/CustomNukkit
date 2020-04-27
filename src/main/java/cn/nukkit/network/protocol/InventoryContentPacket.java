package cn.nukkit.network.protocol;

import cn.nukkit.item.Item;
import lombok.ToString;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
@ToString
public class InventoryContentPacket extends DataPacket {

    @Override
    public byte pid() {
        return ProtocolInfo.INVENTORY_CONTENT_PACKET;
    }

    public static final int SPECIAL_INVENTORY = 0;
    public static final int SPECIAL_OFFHAND = 0x77;
    public static final int SPECIAL_ARMOR = 0x78;
    public static final int SPECIAL_CREATIVE = 0x79;
    public static final int SPECIAL_HOTBAR = 0x7a;
    public static final int SPECIAL_FIXED_INVENTORY = 0x7b;

    public int inventoryId;
    public Item[] slots = new Item[0];

    @Override
    public DataPacket clean() {
        this.slots = new Item[0];
        return super.clean();
    }

    @Override
    public void decode() {
        /*this.inventoryId = (int) this.getUnsignedVarInt();
        int count = (int) this.getUnsignedVarInt();
        this.slots = new Item[count];

        for (int s = 0; s < count && !this.feof(); ++s) {
            this.slots[s] = this.getSlot();
        }*/
    }

    @Override
    public void encode() {
        this.reset();
        this.putUnsignedVarInt(this.inventoryId);
        this.putUnsignedVarInt(this.slots.length);
        for (Item slot : this.slots) {
            this.putSlot(protocol, slot);
        }
    }

    @Override
    public InventoryContentPacket clone() {
        InventoryContentPacket pk = (InventoryContentPacket) super.clone();
        pk.slots = this.slots.clone();
        return pk;
    }
}