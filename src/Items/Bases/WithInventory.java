package Items.Bases;

import java.util.ArrayList;
import java.util.List;

public abstract class WithInventory {
    public ArrayList<ItemBase> items = new ArrayList<>();
    public ArrayList<ItemBase> equippedItems = new ArrayList<>();

    public ItemBase getEqippedItemInSlot(ItemSlot slot)
    {
        return equippedItems.stream().filter(inventoryItem -> inventoryItem.itemSlot == slot).findFirst().orElse(null);
    }

    public ArrayList<ItemBase> getInventory()
    {
        return items;
    }
    public void addToInventory(ItemBase item)
    {
        items.add(item);
    }
    public void addToInventory(ItemBase item, boolean autoEquip)
    {
        items.add(item);
        equip(item);
    }
    public void removeFromInventory(String name)
    {
        if(items != null)
        {
            items.stream().filter(item -> item.getName().equals(name)).forEach(item -> items.remove(item));
        }
    }

    public List<ItemBase> getEquippableItems()
    {
        return items.stream().filter(item -> Equipable.class.isAssignableFrom(item.getClass())).toList();
    }

    public ArrayList<ItemBase> getEquipedItems()
    {
        return equippedItems;
    }
    public abstract void unEquip(ItemBase item);

    public abstract void equip(ItemBase item);

}
