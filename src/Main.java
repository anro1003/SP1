public static void main(String[] args)
{
    //Hero
    String heroName = "Ragnar";
    int heroHP = 85;
    int heroMaxHP = 100;
    int heroLevel = 5;
    int heroXP = 2300;
    double heroGold = 156.50;
    boolean heroIsAlive = true;
    char heroClass = 'W';

    double heroHealthWarningPercentage = 0.25;

    String[] heroInventory = {"Sword", "Bow", "Arrow"};

    System.out.println("=== HERO SHEET ===");
    System.out.println("Hero: " + heroName);
    System.out.println("HP: " + heroHP + "/" + heroMaxHP);
    System.out.println("Level: " + heroLevel);
    System.out.println("Experience: " + heroXP);
    System.out.println("Gold: " + heroGold);

    System.out.print("Hero Class: ");
    switch (heroClass)
    {
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

    System.out.println("Inventory:");
    System.out.println("You have " + heroInventory.length + " items in your inventory.");
    for(String item : heroInventory)
    {
        System.out.println(" - " + item);
    }

    System.out.println("=======================");

    System.out.println("\n\n===== HERO STATUS =====");

    if(heroHP <= 0)
    {
        heroIsAlive = false;
        System.out.println("The hero is dead..");
    }

    if(heroHP < (heroMaxHP*heroHealthWarningPercentage) && heroIsAlive)
    {
        System.out.println("WARNING: Health critical! - It's " +  heroHealthWarningPercentage*100 + "% of your max hp!");
    }



    if(heroXP >= heroXP*1250 && heroIsAlive)
    {
        System.out.println("Ready to level up!!");
    }


    if(!heroIsAlive)
    {
        System.out.println("The hero is dead.. - Skipping combat simulator");
        return;
    }

    System.out.println("\n\n==== COMBAT SIMULATOR ====");
    System.out.println("Starting combat simulator...");

    int monsterDmg = (int)(Math.random()*100);

    System.out.println("The monster attacks for: " + monsterDmg);

    heroHP -= monsterDmg;
    if (heroHP <= 0)
    {
        heroIsAlive = false;
        heroHP = 0;
        System.out.println("You didn't survive the attack..");
        return;
    }
    System.out.println("You survived!! - Your new HP is: " + heroHP);


}