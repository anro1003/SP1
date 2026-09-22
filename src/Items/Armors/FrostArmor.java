package Items.Armors;

import Characters.CharacterBase;
import EventSystem.EventSystem;
import EventSystem.Events.Event;
import Items.Bases.Hooks.OnTakeDamage;
import Items.Bases.ItemSlot;

public class FrostArmor extends ArmorBase implements OnTakeDamage {

    int frostDamageBase = 15;
    public FrostArmor() {
        int armorBonus = 6; //Just to avoid magicNumbers
        super("Frost Chestplate",ItemSlot.CHEST,armorBonus);
    }

    @Override
    public void onTakeDamage(CharacterBase wearer, CharacterBase attacker) {
        int frostDamage = (int)Math.floor((double)frostDamageBase+((double)wearer.getLevel()*1.75));
        EventSystem.globalEventSystemSingleton.dispatch(new Event("Combat.OnTakeDamageTrigger",this.getClass(), wearer.getName() +"'s Frost Chestplate is freezing over " + attacker.getName() + "'s hands"));

        attacker.takeDamageNoTrigger(frostDamage,wearer);
    }

}
