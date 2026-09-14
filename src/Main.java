public static void main(String[] args)
{
    //Hero
    String heroName = "Ragnar";
    int heroHP = 85;
    int heroMaxHP = 100;
    int heroLevel = 5;
    int heroExperiencePoints = 2300;
    double heroGold = 156.50;
    boolean isAlive = true;
    char heroClass = 'W';

    String[] heroInventory = {"Sword", "Bow", "Arrow"};

    System.out.println("=== HERO SHEET ===");
    System.out.println("Hero: " + heroName);
    System.out.println("HP: " + heroHP + "/" + heroMaxHP);
    System.out.println("Level: " + heroLevel);
    System.out.println("Experience: " + heroExperiencePoints);
    System.out.println("Gold: " + heroGold);

    System.out.println("Inventory:");
    System.out.println(" - " + heroInventory[0]);
    System.out.println(" - " + heroInventory[1]);
    System.out.println(" - " + heroInventory[2]);

    System.out.println("=======================");
}