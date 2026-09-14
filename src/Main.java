public class Main {

    //Hero
    static String heroName = "Ragnar";
    static int heroHP = 85;
    static int heroMaxHP = 100;
    static int heroLevel = 5;
    static int heroXP = 2300;
    static double heroGold = 156.50;
    static boolean heroIsAlive = true;
    static char heroClass = 'W';

    static final int heroXPPerLevel = 400;

    static double heroHealthWarningPercentage = 0.25;

    static String[] heroInventory = {"Sword", "Bow", "Arrow"};


    public static void main(String[] args) {

        int simulatorEnemies = 120;

        printCharacterSheet();
        printHeroStatus();

        if (!heroIsAlive) {
            System.out.println("The hero is dead.. - Skipping combat simulator");
            return;
        }
        combatSimulator(simulatorEnemies);
    }

    public static String[] generateEnemyTypes(int count)
    {
        String[] generated =  new String[count];
        for(int i = 0; i < count; i++)
        {
            int randomNumber = (int) (Math.random() * 3);
            switch (randomNumber)
            {
                case 0:
                    generated[i] = "Goblin";
                    break;
                case 1:
                    generated[i] = "Orc";
                    break;
                case 2:
                    generated[i] = "Dragon";
                    break;
                default:
                    System.out.println("Invalid random number - How did you mess this up Anton?");
            }
        }
        return generated;
    }

    //The 3 stats are ordered as "HP", "MP", "ARMOR", DMG, XPGAIN
    public static int[][] generateEnemyStats(String[] enemyTypes)
    {

        int statCount = 5;
        int[][] generated = new int[enemyTypes.length][statCount];
        for(int i = 0; i < enemyTypes.length; i++)
        {
            generated[i] = new int[statCount];
            switch(enemyTypes[i])
            {
                case "Goblin":
                    generated[i][0] = (int)(Math.random() * 15 + 10); //from 10 to 15 //HP
                    generated[i][1] = (int)(Math.random() * 10 + 0); //from 0 to 10 //MP
                    generated[i][2] = (int)(Math.random() * 2 + 0); //from 0 to 2 //ARMOR
                    generated[i][3] = (int)(Math.random() * 5 + 3); //from 3 to 5 //DMG
                    generated[i][4] = (int)(Math.random() * 100 + 15); //from 15 to 100 //XP GAIN
                    break;
                case "Orc":
                    generated[i][0] = (int)(Math.random() * 35 + 15); //from 15 to 35 //HP
                    generated[i][1] = (int)(Math.random() * 0 + 0); //from 0 to 0 //MP
                    generated[i][2] = (int)(Math.random() * 6 + 2); //from 2 to 6 //ARMOR
                    generated[i][3] = (int)(Math.random() * 20 + 10); //from 10 to 20 //DMG
                    generated[i][4] = (int)(Math.random() * 200 + 90); //from 90 to 200 //XP GAIN
                    break;
                case "Dragon":
                    generated[i][0] = (int)(Math.random() * 135 + 50); //from 50 to 135 //HP
                    generated[i][1] = (int)(Math.random() * 400 + 200); //from 200 to 400 //MP
                    generated[i][2] = (int)(Math.random() * 24 + 10); //from 10 to 24 //ARMOR
                    generated[i][3] = (int)(Math.random() * 50 + 25); //from 25 to 50 //DMG
                    generated[i][4] = (int)(Math.random() * 2500 + 1200); //from 1200 to 2500 //XP GAIN
                    break;
            }
        }
        return generated;
    }

    public static int variateDamage(int damage)
    {
        //Assume 10% less and 20% more
        return variateDamage(damage,0.10,0.20);
    }

    public static int variateDamage(int damage, double percentMin, double percentMax)
    {
        double variatedPercent = ((Math.random() * (percentMax*100) - (percentMin*100)))/100;
        int variatedDamage = 0;
        if(variatedPercent < 0.0)
        {
            variatedDamage = (int)((double)damage-(damage*(variatedPercent*-1.0)));
        }
        else
        {
            variatedDamage = (int)((double)damage+(damage*variatedPercent));
        }

        if(variatedDamage < 0)
        {
            variatedDamage = 0;
        }

        return variatedDamage;
    }

    public static int applyArmorReduction(int dmg, int armor)
    {
        //reduce by 0.25 per armor
        return (int)Math.floor(((double)dmg-((double)armor*0.25)));
    }

    public static void combatSimulator(int enemyCount)
    {
        System.out.println("\n\n==== COMBAT SIMULATOR ====");
        System.out.println("Starting combat simulator...");

        final int mHPIndex = 0;
        final int mMPIndex = 1;
        final int mArmorIndex = 2;
        final int mDmgIndex = 3;
        final int mXPGainIndex = 4;

        int survivedRounds = 0;

        String[] enemyTypes = generateEnemyTypes(enemyCount);
        int[][] enemyStats = generateEnemyStats(enemyTypes);

        for (int i = 0; i < enemyCount; i++) {
            boolean monsterAlive = true;
            int monsterCurrHp = enemyStats[i][mHPIndex];

            System.out.println("\n==== COMBAT ====");
            System.out.println("You met a " + enemyTypes[i] + "!!\n");
            System.out.println("We did a little analasys on them, and we saw these stats! :");
            System.out.println("HP: " + enemyStats[i][mHPIndex]+"\nMP: " + enemyStats[i][mMPIndex]+"\nArmor: " + enemyStats[i][mArmorIndex]+"\nBase Damage: "+enemyStats[i][mDmgIndex]+"\n\n");

            do
            {
                //Monster turn
                int monsterDmg = variateDamage(enemyStats[i][mDmgIndex]);
                System.out.println("The " + enemyTypes[i] + " attacks for: " + monsterDmg + " damage");
                takeDamage(monsterDmg);

                if(!heroIsAlive)
                {
                    System.out.println("You were killed by the " + enemyTypes[i] + " - Game over!");
                    System.out.println("============");
                    break;
                }

                //Heros turn
                int heroDmg = variateDamage(getHeroDmg());
                int heroDmgAfterArmor = applyArmorReduction(heroDmg,enemyStats[i][mArmorIndex]);
                System.out.println(heroName + " attacks for: " + heroDmg + " damage - Armor reduced attack to: " + heroDmgAfterArmor);
                monsterCurrHp -= heroDmg;
                if(monsterCurrHp <= 0)
                {
                    monsterAlive = false;
                }

            } while (monsterAlive && heroIsAlive);
            if(!heroIsAlive)
            {
                break;
            }
            survivedRounds++;
            System.out.println("You defeated the " + enemyTypes[i] + "!!! - You get to drink a small health potion!");
            heal(heroLevel+12); //hp potion
            System.out.println("\nCurrent health: " + heroHP + " HP\n");
            System.out.println("\n==== LOOT ====");
            System.out.println("You gained: " + enemyStats[i][mXPGainIndex] + " XP! [" + heroXP + "/" + heroXPPerLevel*heroLevel+"]");
            addXP(enemyStats[i][mXPGainIndex]);
            System.out.println("==============");

        }
        System.out.println("==== COMBAT SIM STATS ====");
        System.out.println("Total rounds: " + survivedRounds);
        System.out.println("Ended at level: " + heroLevel);
        System.out.println("Ended with: " + heroXP + "/" + heroXPPerLevel*heroLevel + " XP");
        System.out.println("Ended with: " + heroHP + "/" + heroMaxHP + " HP");
        System.out.println("\n==== COMBAT SIMULATOR ENDED ====");
    }

    public static void printCharacterSheet()
    {

        System.out.println("=== HERO SHEET ===");
        System.out.println("Hero: " + heroName);
        System.out.println("HP: " + heroHP + "/" + heroMaxHP);
        System.out.println("Level: " + heroLevel);
        System.out.println("Experience: " + heroXP);
        System.out.println("Gold: " + heroGold);

        System.out.print("Hero Class: ");
        switch (heroClass) {
            case 'W':
                System.out.println("Warrior");
                break;

            case 'M':
                System.out.println("Mage");
                break;
            case 'R':
                System.out.println("Rouge");
                break;
            default:
                System.out.println("Error - Unknown class: " + heroClass);
                break;

        }

        printInventory();

        System.out.println("=======================");

    }

    public static void printInventory()
    {
        System.out.println("Inventory:");
        System.out.println("You have " + heroInventory.length + " items in your inventory.");
        for (String item : heroInventory) {
            System.out.println(" - " + item);
        }
    }

    public static void printHeroStatus()
    {
        System.out.println("\n\n===== HERO STATUS =====");

        boolean anyNews = false;

        if (heroHP <= 0)
        {
            heroIsAlive = false;
            System.out.println("The hero is dead..");
            anyNews = true;
        }

        if(isHealthCritical())
        {
            System.out.println("WARNING: Health critical! - It's " + heroHealthWarningPercentage * 100 + "% of your max hp!");
            anyNews = true;
        }


        if(levelUpReady() && heroIsAlive)
        {
                System.out.println("Ready to level up!!");
                anyNews = true;
        }

        if(!anyNews)
        {
            System.out.println("Nothing new to note! :)");
        }
    }

    public static void takeDamage(int damage)
    {
        heroHP -= damage;
        if (heroHP <= 0) {
            heroIsAlive = false;
            heroHP = 0;
            System.out.println("You didn't survive the attack..");
            return;
        }
        System.out.println("You survived!! - Your new HP is: " + heroHP);
    }

    public static void heal(int amount)
    {
        heroHP += amount;
        if(heroHP > heroMaxHP)
        {
            heroHP = heroMaxHP;
        }
        if(!heroIsAlive)
        {
            System.out.println("The hero was revived!");
            heroIsAlive = true;
        }
    }

    public static void addGold(int gold)
    {
        heroGold += gold;
    }

    public static void removeGold(int gold)
    {
        heroGold -= gold;
    }

    public static boolean levelUpReady()
    {
        if (heroXP >= heroLevel * heroXPPerLevel) {
            return true;
        }
        return false;
    }
    public static void addXP(int xp)
    {
        heroXP += xp;
        while (levelUpReady()) {
            System.out.println("\n\n==== LEVEL UP! ====");
            levelUp();
            System.out.println("New max health: " + heroMaxHP + " HP");
            System.out.println("=======================\n\n");
        }
    }

    public static void levelUp()
    {
        heroLevel++;

        heroXP -= heroXPPerLevel;
        heroMaxHP += 20;
        heroHP = heroMaxHP;
    }

    public static boolean isHealthCritical()
    {
        if (getHealthPercentage() < heroHealthWarningPercentage*100 && heroIsAlive) {
            return true;
        }
        return false;
    }

    public static boolean isAlive()
    {
        return heroHP > 0;
    }

    public static double getHealthPercentage()
    {
        return (double)heroHP/heroMaxHP*100;
    }
    public static int getHeroDmg()
    {
        return (int)(Math.random() * (heroLevel*5) + (heroLevel*3));
    }
}