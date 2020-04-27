package cn.nukkit.inventory;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public enum InventoryType {

    CHEST(27, "Chest", 0), //27 CONTAINER
    ENDER_CHEST(27, "Ender Chest", 0), //27 CONTAINER
    DOUBLE_CHEST(54, "Double Chest", 0), //27 + 27 CONTAINER
    PLAYER(40, "Player", -1), //36 CONTAINER, 4 ARMOR
    FURNACE(3, "Furnace", 2), //1 INPUT/OUTPUT, 1 FUEL
    TRADING(17, "Trade", 2), //15 SLOTS, 2 RESULTS
    CRAFTING(5, "Crafting", 1), //4 CRAFTING SLOTS, 1 RESULT
    WORKBENCH(10, "Crafting", 1), //9 CRAFTING SLOTS, 1 RESULT
    BREWING_STAND(5, "Brewing", 4), //1 INPUT, 3 POTION, 1 FUEL
    ANVIL(3, "Anvil", 5), //2 INPUT, 1 OUTPUT
    ENCHANT_TABLE(2, "Enchantment Table", 3), //1 INPUT/OUTPUT, 1 LAPIS
    DISPENSER(9, "Dispenser", 6), //9 CONTAINER
    DROPPER(9, "Dropper", 7), //9 CONTAINER
    HOPPER(5, "Hopper", 8), //5 CONTAINER
    UI(1, "UI", -1), //1 CONTAINER
    SHULKER_BOX(27, "Shulker Box", 0), //27 CONTAINER
    BEACON(1, "Beacon", 13), //1 INPUT
    ENTITY_ARMOR(4, "Entity Armor", -1), //4 ARMOR
    ENTITY_EQUIPMENT(36, "Entity Equipment", -1), //36 CONTAINER
    MINECART_CHEST(27, "Minecart with Chest", 0), //27 CONTAINER
    MINECART_HOPPER(5, "Minecart with Hopper", 8), //5 CONTAINER
    OFFHAND(1, "Offhand", -1); //1 CONTAINER

    private final int size;
    private final String title;
    private final int typeId;

    InventoryType(int defaultSize, String defaultBlockEntity, int typeId) {
        this.size = defaultSize;
        this.title = defaultBlockEntity;
        this.typeId = typeId;
    }

    public int getDefaultSize() {
        return size;
    }

    public String getDefaultTitle() {
        return title;
    }

    public int getNetworkType() {
        return typeId;
    }
}
