package Items.Bases;

public class ItemBase {
    protected String name;
    protected ItemSlot itemSlot;

    public ItemBase(String name) {
        this.name = name;
        this.itemSlot = ItemSlot.NONE;
    }

    public ItemBase(String name, ItemSlot itemSlot) {
        this.name = name;
        this.itemSlot = itemSlot;
    }


    public String getName() {
        return name;
    }

    public ItemSlot getItemSlot() {
        return itemSlot;
    }
}
