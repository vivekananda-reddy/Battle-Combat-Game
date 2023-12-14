package battle;

import gears.Gear;
import randoms.RandomAuto;
import randoms.RandomGenerate;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * Driver class to demonstrate the working of the game. This class is used to perform most
 * of the operations/ features of the game.
 */
public class Driver {


  // Declaring ANSI_RESET so that we can reset the color
  public static final String ANSI_RESET = "\u001B[0m";

  // Declaring the color
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";

  private void printPlayerAttributeInfo(Map<String, Map<String,Integer>> playerInfo) {


    for (Map.Entry<String, Map<String,Integer>> info : playerInfo.entrySet()) {
      System.out.println("***" + info.getKey() + "***");
      System.out.println("Attribute Details:");
      System.out.println(info.getValue());
    }

  }

  private void printGearInfo(Map<String, List<Gear>> gearInfo) {
    for (Map.Entry<String, List<Gear>> info : gearInfo.entrySet()) {
      System.out.println("***" + info.getKey() + "***");
      System.out.println("Equipped Gears and Impact:");
      for (Gear g:info.getValue()) {
        System.out.println(g);
      }
    }

  }

  private void printWeaponInfo(Map<String,String> weaponsList) {

    for (Map.Entry<String, String> info : weaponsList.entrySet()) {
      System.out.println("***" + info.getKey() + "***");
      System.out.print("Weapon Name: ");
      System.out.println(info.getValue());
    }
  }

  private void printPlayersInitialHealth(Map<String,Integer> healths) {
    for (Map.Entry<String, Integer> info : healths.entrySet()) {
      System.out.println("***" + info.getKey() + "***");
      System.out.print("Health: ");
      System.out.println(info.getValue());
    }
  }

  /**
   * Main method to run the driver.
   * @param args command line string arguments
   */
  public static void main(String[] args) {
    RandomGenerate randomAuto = new RandomAuto();
    final Arena arena = new Arena(randomAuto);
    final Driver driver = new Driver();

    System.out.println(ANSI_GREEN + "*********************Welcome to the Battle Arena!"
            + "*****************************" + ANSI_RESET);
    System.out.println();
    System.out.println("===========================Players info after creation======="
            + "================");
    driver.printPlayerAttributeInfo(arena.createPlayers("player1", "player2"));
    arena.equipGearsAndWeapon("weapon1", "weapon2");

    System.out.println("=========================Player info After Equipping Gears============="
            + "================");
    driver.printPlayerAttributeInfo(arena.getPlayersAttributeInfo());
    System.out.println("==================================Gears Equipped======================="
            + "================");
    driver.printGearInfo(arena.getPlayersGearInfo());
    System.out.println("==================================Weapons Equipped======================="
            + "==============");
    driver.printWeaponInfo(arena.getWeaponInfo());

    System.out.println(ANSI_PURPLE + "=================================Battle Begins============="
            + "======================" + ANSI_RESET);

    Scanner rematchDecision = new Scanner(System.in);
    boolean exitGame = false;

    while (!exitGame) {
      System.out.println("----Initial Health of Players----");
      driver.printPlayersInitialHealth(arena.getHealthOfPlayers());

      Map<String, String> roundInfo;
      //int count = 20;
      int roundCounter = 0;
      while ((!arena.isGameOver())) {
        roundCounter++;
        roundInfo = arena.playRound();
        System.out.println("=>Round" + roundCounter);
        System.out.println(roundInfo);
      }

      String result = arena.matchResult();
      System.out.println("============Game Result==============");
      if (result.equals("draw")) {
        System.out.println(ANSI_BLUE + "It is a Draw" + ANSI_RESET);
      }
      else {
        System.out.println("Winner is: " + ANSI_BLUE + result + ANSI_RESET);
      }

      System.out.println();
      while (true) {
        System.out.println("Press Y for rematch or N to exit and hit enter:");
        String input = rematchDecision.next();
        if (input.equalsIgnoreCase("n")) {
          exitGame = true;

          System.out.println(ANSI_PURPLE + "============Thank you for playing============="
                  + ANSI_RESET);
          break;
        }
        else if (input.equalsIgnoreCase("y")) {
          arena.rematch();
          System.out.println(ANSI_PURPLE + "=================Rematch Initiated=================="
                  + ANSI_RESET);
          break;
        }
      }
    }

  }
}
