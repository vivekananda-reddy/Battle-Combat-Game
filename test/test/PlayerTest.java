package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import gears.Belts;
import gears.Footwear;
import gears.Gear;
import gears.HeadGear;
import gears.Potions;
import org.junit.Before;
import org.junit.Test;
import players.GenericPlayer;
import players.Player;
import randoms.RandomControlled;
import randoms.RandomGenerate;
import weapons.Flails;
import weapons.Weapon;

import java.util.List;

/**
 * A class to test player class.
 */
public class PlayerTest {
  GenericPlayer testPlayer;
  RandomGenerate random;

  @Before
  public void setUp() throws Exception {
    random = new RandomControlled();
    testPlayer = new Player(random,"player1");
  }

  @Test
  public void testGetPlayerName() {
    assertEquals("player1", testPlayer.getPlayerName());
  }

  @Test
  public void testEquipGearsAndAllGears() {

    Gear testHeadGearLocal1 = new HeadGear(random,true,"headgear1");
    Gear testPotionsLocal1 = new Potions(random, true, "potion1");
    Gear testFootwearLocal1 = new Footwear(random, true, "footwear1");
    Gear testBeltsLocal1 = new Belts(random, true, "belt1");
    Gear testHeadGearLocal2 = new HeadGear(random,true,"headgear2");
    Gear testPotionsLocal2 = new Potions(random, true, "potion2");
    Gear testFootwearLocal2 = new Footwear(random, true, "footwear2");
    Gear testBeltsLocal2 = new Belts(random, true, "belt2");
    Gear testPotionsLocal3 = new Potions(random, true, "potion3");
    Gear testPotionsLocal4 = new Potions(random, true, "potion4");
    Gear testPotionsLocal5 = new Potions(random, true, "potion5");
    Gear testBeltsLocal3 = new Belts(random, true, "belt3");
    Gear testBeltsLocal4 = new Belts(random, true, "belt4");
    Gear testBeltsLocal5 = new Belts(random, true, "belt5");
    Gear testBeltsLocal6 = new Belts(random, true, "belt6");



    assertTrue(testPlayer.equipGear(testHeadGearLocal1));
    assertTrue(testPlayer.equipGear(testPotionsLocal1));
    assertTrue(testPlayer.equipGear(testFootwearLocal1));
    assertTrue(testPlayer.equipGear(testBeltsLocal1));

    assertFalse(testPlayer.equipGear(testHeadGearLocal2));
    assertTrue(testPlayer.equipGear(testPotionsLocal2));
    assertFalse(testPlayer.equipGear(testFootwearLocal2));
    assertTrue(testPlayer.equipGear(testBeltsLocal2));

    assertTrue(testPlayer.equipGear(testPotionsLocal3));
    assertTrue(testPlayer.equipGear(testPotionsLocal4));
    assertTrue(testPlayer.equipGear(testPotionsLocal5));

    assertTrue(testPlayer.equipGear(testBeltsLocal3));
    assertTrue(testPlayer.equipGear(testBeltsLocal4));
    assertTrue(testPlayer.equipGear(testBeltsLocal5));
    assertFalse(testPlayer.equipGear(testBeltsLocal6));

    List<Gear> allGearsList = testPlayer.getPlayerGearPack();
    assertEquals("headgear1", allGearsList.get(0).getName());
    assertEquals("potion1", allGearsList.get(1).getName());
    assertEquals("footwear1", allGearsList.get(2).getName());
    assertEquals("belt1", allGearsList.get(3).getName());

    assertEquals("headgear2", allGearsList.get(4).getName());
    assertEquals("potion2", allGearsList.get(5).getName());
    assertEquals("footwear2", allGearsList.get(6).getName());
    assertEquals("belt2", allGearsList.get(7).getName());

    assertEquals("potion3", allGearsList.get(8).getName());
    assertEquals("potion4", allGearsList.get(9).getName());
    assertEquals("potion5", allGearsList.get(10).getName());

    assertEquals("belt3", allGearsList.get(11).getName());
    assertEquals("belt4", allGearsList.get(12).getName());
    assertEquals("belt5", allGearsList.get(13).getName());
    assertEquals("belt6", allGearsList.get(14).getName());



    List<Gear> equipList = testPlayer.getAllEquippedGears();


    assertEquals(12, equipList.size());

    assertEquals("headgear1", equipList.get(0).getName());
    assertEquals("potion1", equipList.get(1).getName());
    assertEquals("footwear1", equipList.get(2).getName());
    assertEquals("belt1", equipList.get(3).getName());
    assertEquals("potion2", equipList.get(4).getName());
    assertEquals("belt2", equipList.get(5).getName());

    assertEquals("potion3", equipList.get(6).getName());
    assertEquals("potion4", equipList.get(7).getName());
    assertEquals("potion5", equipList.get(8).getName());

    assertEquals("belt3", equipList.get(9).getName());
    assertEquals("belt4", equipList.get(10).getName());
    assertEquals("belt5", equipList.get(11).getName());

    assertEquals(10, equipList.get(3).getGearSize() + equipList.get(5).getGearSize()
            + equipList.get(9).getGearSize() + equipList.get(10).getGearSize()
            + equipList.get(11).getGearSize());


    int headGearCount = 0;
    int footwearCount = 0;
    for ( Gear g: equipList) {
      if (g.getGearType().equals("headgear")) {
        headGearCount++;

      }
      if (g.getGearType().equals("footwear")) {
        footwearCount++;
      }
    }
    if (headGearCount > 1) {
      fail("Can't equip more than 1 headgear");
    }

    if (footwearCount > 1) {
      fail("Can't equip more than 1 footwear");
    }

  }

  @Test(expected = IllegalStateException.class)
  public void testEquippingMoreThan20Items() {

    Gear testHeadGearLocal1 = new HeadGear(random,true,"headgear1");
    Gear testPotionsLocal1 = new Potions(random, true, "potion1");
    Gear testFootwearLocal1 = new Footwear(random, true, "footwear1");
    Gear testBeltsLocal1 = new Belts(random, true, "belt1");
    Gear testHeadGearLocal2 = new HeadGear(random,true,"headgear2");
    Gear testPotionsLocal2 = new Potions(random, true, "potion2");
    Gear testFootwearLocal2 = new Footwear(random, true, "footwear2");
    Gear testBeltsLocal2 = new Belts(random, true, "belt2");
    Gear testPotionsLocal3 = new Potions(random, true, "potion3");
    Gear testPotionsLocal4 = new Potions(random, true, "potion4");
    Gear testPotionsLocal5 = new Potions(random, true, "potion5");
    Gear testBeltsLocal3 = new Belts(random, true, "belt3");
    Gear testBeltsLocal4 = new Belts(random, true, "belt4");
    Gear testBeltsLocal5 = new Belts(random, true, "belt5");
    Gear testBeltsLocal6 = new Belts(random, true, "belt6");

    Gear testBeltsLocal7 = new Belts(random, true, "belt3");
    Gear testBeltsLocal8 = new Belts(random, true, "belt4");
    Gear testBeltsLocal9 = new Belts(random, true, "belt5");
    Gear testBeltsLocal10 = new Belts(random, true, "belt6");
    Gear testBeltsLocal11 = new Belts(random, true, "belt3");


    Gear testPotionsLocal6 = new Potions(random, true, "potion3");
    Gear testPotionsLocal7 = new Potions(random, true, "potion4");
    Gear testPotionsLocal8 = new Potions(random, true, "potion5");

    testPlayer.equipGear(testBeltsLocal1);
    testPlayer.equipGear(testBeltsLocal2);
    testPlayer.equipGear(testBeltsLocal3);
    testPlayer.equipGear(testBeltsLocal4);
    testPlayer.equipGear(testBeltsLocal5);
    testPlayer.equipGear(testBeltsLocal6);
    testPlayer.equipGear(testBeltsLocal7);
    testPlayer.equipGear(testBeltsLocal8);
    testPlayer.equipGear(testBeltsLocal9);
    testPlayer.equipGear(testBeltsLocal10);
    testPlayer.equipGear(testBeltsLocal11);
    testPlayer.equipGear(testPotionsLocal1);
    testPlayer.equipGear(testPotionsLocal2);
    testPlayer.equipGear(testPotionsLocal3);
    testPlayer.equipGear(testPotionsLocal4);
    testPlayer.equipGear(testPotionsLocal5);
    testPlayer.equipGear(testPotionsLocal6);
    testPlayer.equipGear(testPotionsLocal7);
    testPlayer.equipGear(testPotionsLocal8);

    testPlayer.equipGear(testHeadGearLocal1);
    testPlayer.equipGear(testFootwearLocal1);
    testPlayer.equipGear(testHeadGearLocal2);
    testPlayer.equipGear(testFootwearLocal2);

  }


  @Test
  public void testPlayerAbilities() {

    GenericPlayer testplayerLocal = new Player(random,"player1");
    final RandomGenerate randomLocal = new RandomControlled();

    assertEquals(9,testplayerLocal.getPlayerStrength());
    assertEquals(9,testplayerLocal.getPlayerConstitution());
    assertEquals(9,testplayerLocal.getPlayerDexterity());
    assertEquals(9,testplayerLocal.getPlayerCharisma());
    final Gear testHeadGearLocal1 = new HeadGear(randomLocal,true,"headgear1");
    final Gear testPotionsLocal1 = new Potions(randomLocal, false, "potion1");
    final Gear testFootwearLocal1 = new Footwear(randomLocal, true, "footwear1");
    final Gear testBeltsLocal1 = new Belts(randomLocal, true, "belt1");

    testplayerLocal.equipGear(testHeadGearLocal1);
    //Only constitution will change rest all will remain the same
    assertEquals(9,testplayerLocal.getPlayerStrength());
    assertEquals(12,testplayerLocal.getPlayerConstitution());
    assertEquals(9,testplayerLocal.getPlayerDexterity());
    assertEquals(9,testplayerLocal.getPlayerCharisma());

    testplayerLocal.equipGear(testFootwearLocal1);
    //Only dexterity will change rest all will remain the same
    assertEquals(9,testplayerLocal.getPlayerStrength());
    assertEquals(12,testplayerLocal.getPlayerConstitution());
    assertEquals(12,testplayerLocal.getPlayerDexterity());
    assertEquals(9,testplayerLocal.getPlayerCharisma());

    testplayerLocal.equipGear(testPotionsLocal1);
    assertEquals(9,testplayerLocal.getPlayerStrength());
    assertEquals(9,testplayerLocal.getPlayerConstitution());
    assertEquals(12,testplayerLocal.getPlayerDexterity());
    assertEquals(9,testplayerLocal.getPlayerCharisma());

    testplayerLocal.equipGear(testBeltsLocal1);
    assertEquals(9,testplayerLocal.getPlayerStrength());
    assertEquals(12,testplayerLocal.getPlayerConstitution());
    assertEquals(15,testplayerLocal.getPlayerDexterity());
    assertEquals(9,testplayerLocal.getPlayerCharisma());

  }

  @Test
  public void testAssignWeapon() {
    Weapon weapon = new Flails(random,"flails1");

    testPlayer.assignWeapon(weapon);
    assertEquals("flails1",testPlayer.getAssignedWeapon().getWeaponName());
  }

  @Test (expected = IllegalStateException.class)
  public void testWeaponNotAssigned() {
    testPlayer.getAssignedWeapon();
  }

  @Test (expected = IllegalStateException.class)
  public void testWeaponAlreadyAssigned() {
    Weapon weapon = new Flails(random,"flails1");
    testPlayer.assignWeapon(weapon);
    testPlayer.assignWeapon(weapon);

  }

  @Test
  public void testPlayerHealth() {
    testPlayer.updatePlayerHealth(10);
    assertEquals(10,testPlayer.getPlayerHealth());
  }

}