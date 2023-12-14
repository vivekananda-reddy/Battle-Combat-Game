package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import battle.Arena;
import gears.Gear;
import org.junit.Before;
import org.junit.Test;
import randoms.RandomAuto;
import randoms.RandomControlled;
import randoms.RandomControlledChoices;
import randoms.RandomGenerate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A class to test Arena class.
 */
public class ArenaTest {

  RandomGenerate randomControlled;
  RandomGenerate randomAuto;
  RandomGenerate randomControlledChoices;
  Arena arena;

  @Before
  public void setUp() throws Exception {
    randomControlled = new RandomControlled();
    randomAuto = new RandomAuto();
    randomControlledChoices = new RandomControlledChoices(1,2,3,4,5,6,7);
    arena = new Arena(randomControlled);
  }

  @Test
  public void testCreatePlayersAndAttributesInfo() {
    Map<String, Map<String,Integer>> playersDetails = new HashMap<>();

    playersDetails = arena.createPlayers("player1","player2");

    Integer attributeValue = 9;
    assertEquals(attributeValue, playersDetails.get("player1").get("strength"));
    assertEquals(attributeValue, playersDetails.get("player1").get("constitution"));
    assertEquals(attributeValue, playersDetails.get("player1").get("dexterity"));
    assertEquals(attributeValue, playersDetails.get("player1").get("charisma"));

    playersDetails = arena.getPlayersAttributeInfo();
    attributeValue = 9;
    assertEquals(attributeValue, playersDetails.get("player1").get("strength"));
    assertEquals(attributeValue, playersDetails.get("player1").get("constitution"));
    assertEquals(attributeValue, playersDetails.get("player1").get("dexterity"));
    assertEquals(attributeValue, playersDetails.get("player1").get("charisma"));


  }

  @Test(expected = IllegalStateException.class)
  public void testGetWeaponWhenWeaponNotAssigned() {
    arena.createPlayers("player1","player2");
    arena.getWeaponInfo();
  }

  @Test
  public void testGetGearsWhenGearNotAssigned() {
    arena.createPlayers("player1","player2");
    assertEquals(0, arena.getPlayersGearInfo().get("player1").size());
    assertEquals(0, arena.getPlayersGearInfo().get("player2").size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSamePlayerNames() {
    arena.createPlayers("player1","player1");
  }

  @Test
  public void testGetPlayersGearInfo() {
    arena.createPlayers("player1", "player2");
    arena.equipGearsAndWeapon("w1","w2");

    Map<String, List<Gear>> playerGearInfo = arena.getPlayersGearInfo();

    assertEquals("potionA" , playerGearInfo.get("player1").get(0).getName());
    assertEquals("potionB" , playerGearInfo.get("player1").get(1).getName());
    assertEquals("potionC" , playerGearInfo.get("player1").get(2).getName());
    assertEquals("potionD" , playerGearInfo.get("player1").get(3).getName());
    assertEquals("potionE" , playerGearInfo.get("player1").get(4).getName());
    assertEquals("beltH" , playerGearInfo.get("player1").get(5).getName());
    assertEquals("beltI" , playerGearInfo.get("player1").get(6).getName());
    assertEquals("beltJ" , playerGearInfo.get("player1").get(7).getName());
    assertEquals("beltK" , playerGearInfo.get("player1").get(8).getName());
    assertEquals("beltL" , playerGearInfo.get("player1").get(9).getName());

    assertEquals("headgearE" , playerGearInfo.get("player2").get(0).getName());
    assertEquals("potionF" , playerGearInfo.get("player2").get(1).getName());
    assertEquals("potionG" , playerGearInfo.get("player2").get(2).getName());
    assertEquals("potionH" , playerGearInfo.get("player2").get(3).getName());
    assertEquals("potionI" , playerGearInfo.get("player2").get(4).getName());
    assertEquals("potionJ" , playerGearInfo.get("player2").get(5).getName());
    assertEquals("potionK" , playerGearInfo.get("player2").get(6).getName());
    assertEquals("potionL" , playerGearInfo.get("player2").get(7).getName());
    assertEquals("potionM" , playerGearInfo.get("player2").get(8).getName());
    assertEquals("potionN" , playerGearInfo.get("player2").get(9).getName());
    assertEquals("potionO" , playerGearInfo.get("player2").get(10).getName());
    assertEquals("footwearE" , playerGearInfo.get("player2").get(11).getName());

  }

  @Test
  public void testWeaponAndHealthInfo() {
    arena.createPlayers("player1", "player2");
    arena.equipGearsAndWeapon("weapon1","weapon2");

    Map<String,String> weaponList = arena.getWeaponInfo();
    assertEquals("weapon1", weaponList.get("player1"));
    assertEquals("weapon2", weaponList.get("player2"));

    Integer healthValue = 45;
    assertEquals(healthValue,arena.getHealthOfPlayers().get("player1"));
    healthValue = 54;
    assertEquals(healthValue,arena.getHealthOfPlayers().get("player2"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSameWeaponNames() {
    Arena arenaLocal = new Arena(randomControlledChoices);
    arenaLocal.createPlayers("player1","player2");

    arenaLocal.equipGearsAndWeapon("weapon1","weapon1");
  }

  @Test(expected = IllegalStateException.class)
  public void testEquipWeaponsAndGearsWithoutCreatingPlayers() {
    Arena arenaLocal = new Arena(randomControlledChoices);
    arenaLocal.equipGearsAndWeapon("weapon1","weapon2");
  }

  @Test(expected = IllegalStateException.class)
  public void testGettingAbilityInfoWithoutCreatingPlayers() {
    Arena arenaLocal = new Arena(randomControlledChoices);
    arenaLocal.getPlayersAttributeInfo();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetGearInfoWithoutCreatingPlayers() {
    Arena arenaLocal = new Arena(randomControlledChoices);
    arenaLocal.getPlayersGearInfo();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWeaponInfoWithoutCreatingPlayers() {
    Arena arenaLocal = new Arena(randomControlledChoices);
    arenaLocal.getWeaponInfo();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetHealthWithoutCreatingPlayers() {
    Arena arenaLocal = new Arena(randomControlledChoices);
    arenaLocal.getHealthOfPlayers();
  }


  @Test
  public void testPlayRoundPlayer1Won() {
    Arena arenaLocal = new Arena(randomControlledChoices);
    arenaLocal.createPlayers("player1","player2");

    arenaLocal.equipGearsAndWeapon("weapon1","weapon2");

    final Integer initialHealthPlayer1 = arenaLocal.getHealthOfPlayers().get("player1");
    final Integer initialHealthPlayer2 = arenaLocal.getHealthOfPlayers().get("player2");

    Map<String,String> roundInfo = arenaLocal.playRound() ;
    assertEquals("No Hit Happened", roundInfo.get("hit"));
    assertEquals("0", roundInfo.get("Damage opponents took") );
    assertEquals("player2", roundInfo.get("Attacker"));
    assertEquals("82", roundInfo.get("Health Player1"));
    assertEquals("107", roundInfo.get("Health Player2"));

    assertEquals(String.valueOf(initialHealthPlayer1),roundInfo.get("Health Player1"));
    assertEquals(String.valueOf(initialHealthPlayer2),roundInfo.get("Health Player2"));

    arenaLocal.playRound();
    arenaLocal.playRound();
    arenaLocal.playRound();
    arenaLocal.playRound();
    roundInfo = arenaLocal.playRound();

    assertEquals("Hit Happened", roundInfo.get("hit"));
    assertEquals("5", roundInfo.get("Damage opponents took") );
    assertEquals("player1", roundInfo.get("Attacker"));
    assertEquals("82", roundInfo.get("Health Player1"));
    assertEquals("102", roundInfo.get("Health Player2"));

    while (!arenaLocal.isGameOver()) {
      arenaLocal.playRound();
    }


    //Verifying game over and match result
    if (arenaLocal.matchResult().equals("player1")) {
      if (arenaLocal.getHealthOfPlayers().get("player2") > 0) {
        fail("If player 1 wins, player 2 health should be lessthan 0");
      }
    }

    else if (arenaLocal.matchResult().equals("player2")) {
      if (arenaLocal.getHealthOfPlayers().get("player1") > 0) {
        fail("If player 2 wins, player 1 health should be lessthan 0");
      }
    }

    else {
      if (!(arenaLocal.getHealthOfPlayers().get("player1") <= 0
              && arenaLocal.getHealthOfPlayers().get("player2") <= 0)) {
        fail("If both players healths are zero then it should be a draw");
      }
    }

    assertEquals("player2", arenaLocal.matchResult());

    arenaLocal.rematch();

    //verifying rematch config for health is reset
    assertEquals(initialHealthPlayer1, arenaLocal.getHealthOfPlayers().get("player1"));
    assertEquals(initialHealthPlayer2, arenaLocal.getHealthOfPlayers().get("player2"));

  }

  @Test
  public void testPlayRoundPlayer2Won() {
    RandomGenerate randomControlledChoicesLocal = new RandomControlledChoices(
            1,2,3,4,5,6,7,8,9);
    Arena arenaLocal = new Arena(randomControlledChoicesLocal);
    arenaLocal.createPlayers("player1","player2");

    arenaLocal.equipGearsAndWeapon("weapon1","weapon2");

    final Integer initialHealthPlayer1 = arenaLocal.getHealthOfPlayers().get("player1");
    final Integer initialHealthPlayer2 = arenaLocal.getHealthOfPlayers().get("player2");

    while (!arenaLocal.isGameOver()) {
      arenaLocal.playRound();
    }


    //Verifying game over and match result
    if (arenaLocal.matchResult().equals("player1")) {
      if (arenaLocal.getHealthOfPlayers().get("player2") > 0) {
        fail("If player 1 wins, player 2 health should be lessthan 0");
      }
    }

    else if (arenaLocal.matchResult().equals("player2")) {
      if (arenaLocal.getHealthOfPlayers().get("player1") > 0) {
        fail("If player 2 wins, player 1 health should be lessthan 0");
      }
    }

    else {
      if (!(arenaLocal.getHealthOfPlayers().get("player1") <= 0 && arenaLocal.getHealthOfPlayers()
              .get("player2") <= 0)) {
        fail("If both players healths are zero then it should be a draw");
      }
    }

    assertEquals("player1", arenaLocal.matchResult());

    arenaLocal.rematch();

    //verifying rematch config for health is reset
    assertEquals(initialHealthPlayer1, arenaLocal.getHealthOfPlayers().get("player1"));
    assertEquals(initialHealthPlayer2, arenaLocal.getHealthOfPlayers().get("player2"));

  }

  @Test
  public void testPlayRoundDraw() {
    RandomGenerate randomControlledChoicesLocal = new RandomControlledChoices(
            2,3,4,5,6,7,8);
    Arena arenaLocal = new Arena(randomControlledChoicesLocal);
    arenaLocal.createPlayers("player1","player2");

    arenaLocal.equipGearsAndWeapon("weapon1","weapon2");

    final Integer initialHealthPlayer1 = arenaLocal.getHealthOfPlayers().get("player1");
    final Integer initialHealthPlayer2 = arenaLocal.getHealthOfPlayers().get("player2");

    int noHitCount = 0;
    while (!arenaLocal.isGameOver()) {
      if (arenaLocal.playRound().get("hit").equals("No Hit Happened")) {
        noHitCount++;
      }
      else {
        noHitCount = 0;
      }
    }

    //Verifying game over and match result

    if (arenaLocal.matchResult().equals("player1")) {
      if (arenaLocal.getHealthOfPlayers().get("player2") > 0) {
        fail("If player 1 wins, player 2 health should be lessthan 0");
      }
    }

    else if (arenaLocal.matchResult().equals("player2")) {
      if (arenaLocal.getHealthOfPlayers().get("player1") > 0) {
        fail("If player 2 wins, player 1 health should be lessthan 0");
      }
    }

    else {
      if (noHitCount != 10) {
        fail("If both players healths are zero or there was no hit for 10 consecutive rounds,"
                + " it should be a draw");
      }
    }

    assertEquals("draw", arenaLocal.matchResult());

    arenaLocal.rematch();

    //verifying rematch config for health is reset
    assertEquals(initialHealthPlayer1, arenaLocal.getHealthOfPlayers().get("player1"));
    assertEquals(initialHealthPlayer2, arenaLocal.getHealthOfPlayers().get("player2"));


  }

}