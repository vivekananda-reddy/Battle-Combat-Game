package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import players.Abilities;
import randoms.RandomAuto;
import randoms.RandomControlled;
import randoms.RandomGenerate;


/**
 * A class to test Abilities class.
 */
public class AbilitiesTest {

  private RandomGenerate randomAuto;
  private RandomGenerate randomControlled;

  @Before
  public void setUp() throws Exception {
    randomAuto = new RandomAuto();
    randomControlled = new RandomControlled();
  }

  @Test
  public void testAllAbilitiesWithRandomControlled() {
    Abilities testAbility = new Abilities(randomControlled);
    assertEquals(9,testAbility.getStrength());
    assertEquals(9,testAbility.getCharisma());
    assertEquals(9,testAbility.getConstitution());
    assertEquals(9,testAbility.getDexterity());
  }

  @Test
  public void testAllAbilitiesWithRandomAuto() {
    Abilities testAbility1 = new Abilities(randomAuto);
    Abilities testAbility2 = new Abilities(randomAuto);
    Abilities testAbility3 = new Abilities(randomAuto);
    Abilities testAbility4 = new Abilities(randomAuto);

    //Testing for randomness
    if (testAbility1.getStrength() == testAbility2.getStrength() && testAbility2.getStrength()
            == testAbility3.getStrength() && testAbility3.getStrength()
            == testAbility4.getStrength()) {
      fail(" Randomness might not achieved in rolling dice");
    }
    if (testAbility1.getConstitution() == testAbility2.getConstitution()
            && testAbility2.getConstitution() == testAbility3.getConstitution()
            && testAbility3.getConstitution() == testAbility4.getConstitution()) {
      fail(" Randomness might not achieved in rolling dice");
    }
    if (testAbility1.getDexterity() == testAbility2.getDexterity()
            && testAbility2.getDexterity() == testAbility3.getDexterity()
            && testAbility3.getDexterity() == testAbility4.getDexterity()) {
      fail(" Randomness might not achieved in rolling dice");
    }
    if (testAbility1.getCharisma() == testAbility2.getCharisma()
            && testAbility2.getCharisma() == testAbility3.getCharisma()
            && testAbility3.getCharisma() == testAbility4.getCharisma()) {
      fail(" Randomness might not achieved in rolling dice");
    }

    //Testing if values are in range
    if (testAbility1.getStrength() > 18 || testAbility1.getStrength() < 6) {
      fail("Strength is out of bounds, it should be between 6-18");
    }

    if (testAbility1.getCharisma() > 18 || testAbility1.getCharisma() < 6) {
      fail("Charisma is out of bounds, it should be between 6-18");
    }
    if (testAbility1.getDexterity() > 18 || testAbility1.getDexterity() < 6) {
      fail("Dexterity is out of bounds, it should be between 6-18");
    }
    if (testAbility1.getConstitution() > 18 || testAbility1.getConstitution() < 6) {
      fail("Constitution is out of bounds, it should be between 6-18");
    }


    if (testAbility2.getStrength() > 18 || testAbility2.getStrength() < 6) {
      fail("Strength is out of bounds, it should be between 6-18");
    }
    if (testAbility2.getCharisma() > 18 || testAbility2.getCharisma() < 6) {
      fail("Charisma is out of bounds, it should be between 6-18");
    }
    if (testAbility2.getDexterity() > 18 || testAbility2.getDexterity() < 6) {
      fail("Dexterity is out of bounds, it should be between 6-18");
    }
    if (testAbility2.getConstitution() > 18 || testAbility2.getConstitution() < 6) {
      fail("Constitution is out of bounds, it should be between 6-18");
    }


    if (testAbility3.getStrength() > 18 || testAbility3.getStrength() < 6) {
      fail("Strength is out of bounds, it should be between 6-18");
    }
    if (testAbility3.getCharisma() > 18 || testAbility3.getCharisma() < 6) {
      fail("Charisma is out of bounds, it should be between 6-18");
    }
    if (testAbility3.getDexterity() > 18 || testAbility3.getDexterity() < 6) {
      fail("Dexterity is out of bounds, it should be between 6-18");
    }
    if (testAbility3.getConstitution() > 18 || testAbility3.getConstitution() < 6) {
      fail("Constitution is out of bounds, it should be between 6-18");
    }


    if (testAbility4.getStrength() > 18 || testAbility4.getStrength() < 6) {
      fail("Strength is out of bounds, it should be between 6-18");
    }
    if (testAbility4.getCharisma() > 18 || testAbility4.getCharisma() < 6) {
      fail("Charisma is out of bounds, it should be between 6-18");
    }
    if (testAbility4.getDexterity() > 18 || testAbility4.getDexterity() < 6) {
      fail("Dexterity is out of bounds, it should be between 6-18");
    }
    if (testAbility4.getConstitution() > 18 || testAbility4.getConstitution() < 6) {
      fail("Constitution is out of bounds, it should be between 6-18");
    }
    
  }

}