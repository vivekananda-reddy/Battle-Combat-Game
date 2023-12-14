package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import players.Abilities;
import randoms.RandomAuto;
import randoms.RandomControlled;
import randoms.RandomGenerate;
import weapons.Axes;
import weapons.Broadswords;
import weapons.Flails;
import weapons.Katanas;
import weapons.TwoHandedSwords;
import weapons.Weapon;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to test Weapons related classes.
 */
public class WeaponsTest {

  private RandomGenerate randomControlled;
  private Weapon testAxe;
  private Weapon testBroadsword;
  private Weapon testKatanas;
  private Weapon testTwoHandedSwords;
  private Weapon testFlails;
  private Map<String,Integer> testAbility;


  @Before
  public void setUp() throws Exception {
    randomControlled = new RandomControlled();
    testAxe = new Axes(randomControlled,"axe1");
    testBroadsword = new Broadswords(randomControlled, "broadsword1");
    testKatanas = new Katanas(randomControlled,"katanas1");
    testTwoHandedSwords = new TwoHandedSwords(randomControlled,"twohandedswords1");
    testFlails = new Flails(randomControlled,"flails1");
    testAbility = new HashMap<>();
    testAbility.put("strength", 8);
    testAbility.put("dexterity", 9);
    testAbility.put("constitution", 10);
    testAbility.put("charisma", 9);

  }

  @Test
  public void testGetWeaponName() {
    assertEquals("axe1",testAxe.getWeaponName());
    assertEquals("broadsword1",testBroadsword.getWeaponName());
    assertEquals("katanas1",testKatanas.getWeaponName());
    assertEquals("twohandedswords1",testTwoHandedSwords.getWeaponName());
    assertEquals("flails1",testFlails.getWeaponName());

  }

  @Test public void testToString() {
    assertEquals("Type of Weapon: Axes, Name of Weapon:axe1",testAxe.toString());
    assertEquals("Type of Weapon: Broadswords, Name of Weapon:broadsword1",
            testBroadsword.toString());
    assertEquals("Type of Weapon: Flails, Name of Weapon:flails1",testFlails.toString());
    assertEquals("Type of Weapon: Katanas, Name of Weapon:katanas1",testKatanas.toString());
    assertEquals("Type of Weapon: TwoHandedSwords, Name of Weapon:twohandedswords1",
            testTwoHandedSwords.toString());

  }

  @Test
  public void testGetDamageAxesBroadswordKatanas() {
    int autoRandomValue = 0;

    Abilities testAbilityWith14Dexterity;
    assertEquals(8,testAxe.getDamage(testAbility));
    assertEquals(8,testBroadsword.getDamage(testAbility));
    assertEquals(10,testKatanas.getDamage(testAbility));

  }
  
  @Test
  public void testGetDamageTwoHandedSword() {
    Map<String,Integer> testAbilityWith14Strength;
    //when strength is less than 14
    assertEquals(5, testTwoHandedSwords.getDamage(testAbility));

    testAbilityWith14Strength = new HashMap<>();
    testAbilityWith14Strength.put("strength", 15);
    testAbilityWith14Strength.put("dexterity", 9);
    testAbilityWith14Strength.put("constitution", 10);
    testAbilityWith14Strength.put("charisma", 9);
    //int testStrengthMoreThan14Value = testAbilityWith14Strength.get("strength");

    //When strength is greater than 14
    Weapon testTwoHandedSwordHighStrength = new TwoHandedSwords(randomControlled,
            "twohandedsowrds2");
    assertEquals(10, testTwoHandedSwordHighStrength.getDamage(testAbilityWith14Strength));
  }

  @Test
  public void testGetDamageFlails() {
    Map<String, Integer> testAbilityWith14Dexterity;
    //when Dexterity is less than 14
    assertEquals(5, testFlails.getDamage(testAbility));

    testAbilityWith14Dexterity = new HashMap<>();
    testAbilityWith14Dexterity.put("strength", 9);
    testAbilityWith14Dexterity.put("dexterity", 16);
    testAbilityWith14Dexterity.put("constitution", 10);
    testAbilityWith14Dexterity.put("charisma", 9);
    //int testDexterityMoreThan14Value = testAbilityWith14Dexterity.get("dexterity");

    //When Dexterity is greater than 14
    Weapon testFlailsHighDexterity = new Flails(randomControlled,"flails2");
    assertEquals(10, testFlailsHighDexterity.getDamage(testAbilityWith14Dexterity));
  }

  private boolean repeatedDamageRuns(Weapon testAxeLocal, Weapon testBroadswordLocal,
                                  Weapon testKatanasLocal, Weapon testTwoHandedSwordsLocal,
                                  Weapon testFlailsLocal) {
    int damage = testAxeLocal.getDamage(testAbility);
    if (damage < 6 || damage > 10) {
      return false;

    }

    damage = testBroadswordLocal.getDamage(testAbility);
    if (damage < 6 || damage > 10) {
      return false;
    }

    //Dexterity is <14
    damage = testFlailsLocal.getDamage(testAbility);
    if (damage < 4 || damage > 6) {
      return false;
    }

    //Strength is <14
    damage = testTwoHandedSwordsLocal.getDamage(testAbility);
    if (damage < 4 || damage > 6) {
      return false;
    }

    damage = testKatanasLocal.getDamage(testAbility);
    return damage >= 8 && damage <= 12;
  }

  @Test
  public void testWeaponDamageRange() {

    RandomGenerate randomLocal = new RandomAuto();

    Weapon testAxeLocal = new Axes(randomLocal,"axe1");
    Weapon testBroadswordLocal = new Broadswords(randomLocal, "broadsword1");
    Weapon testKatanasLocal = new Katanas(randomLocal,"katanas1");
    Weapon testTwoHandedSwordsLocal = new TwoHandedSwords(randomLocal,"twohandedswords1");
    Weapon testFlailsLocal = new Flails(randomLocal,"flails1");


    //running multiple times to check the range of damage

    int count = 10;
    while (count-- > 0) {
      if (!repeatedDamageRuns(testAxeLocal, testBroadswordLocal,testKatanasLocal,
              testTwoHandedSwordsLocal, testFlailsLocal)) {
        fail("Damage value of weapon is out of range");
      }
    }

  }

}