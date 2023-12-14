package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import gears.Belts;
import gears.Footwear;
import gears.Gear;
import gears.HeadGear;
import gears.Potions;
import org.junit.Before;
import org.junit.Test;
import randoms.RandomAuto;
import randoms.RandomControlledChoices;
import randoms.RandomGenerate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * A class to test gear interface and its related classes.
 */
public class GearsTest {

  RandomGenerate random;
  RandomGenerate random0to4;
  Gear testHeadGear;
  Gear testPotions;
  Gear testFootwear;
  Gear testBelts;
  String[] ability;
  int[] effectValues;


  @Before
  public void setUp() throws Exception {

    random = new RandomControlledChoices(1,2,3,4);
    random0to4 = new RandomControlledChoices(0,1,2,3);
    testHeadGear = new HeadGear(random,true,"headgear1");
    testPotions = new Potions(random, true, "potion1");
    testFootwear = new Footwear(random, true, "footwear1");
    testBelts = new Belts(random, true, "belt1");
    ability = new String[2];
    effectValues = new int[2];

  }

  @Test
  public void testGetName() {
    assertEquals("headgear1", testHeadGear.getName());
    assertEquals("potion1", testPotions.getName());
    assertEquals("footwear1", testFootwear.getName());
    assertEquals("belt1", testBelts.getName());
  }

  @Test
  public void testGetGearType() {
    assertEquals("headgear", testHeadGear.getGearType());
    assertEquals("potion", testPotions.getGearType());
    assertEquals("footwear", testFootwear.getGearType());
    assertEquals("belt", testBelts.getGearType());
  }

  @Test
  public void testGetGearSize() {
    assertEquals(1, testHeadGear.getGearSize());
    assertEquals(1, testPotions.getGearSize());
    assertEquals(1, testFootwear.getGearSize());

    RandomGenerate randomLocal = new RandomControlledChoices(0,1,2);
    Gear localTestBelt = new Belts(randomLocal, true, "belt1");
    assertEquals(4, localTestBelt.getGearSize());


  }

  private void mapTraversal(Map<String,Integer> list) {

    int i = 0;
    for (Map.Entry<String,Integer> temp : list.entrySet()) {
      ability[i] = temp.getKey();
      effectValues[i] = temp.getValue();
      i++;

    }

  }

  @Test
  public void testHeadGear() {

    RandomGenerate randomLocal = new RandomControlledChoices(1,2,3,4);

    Gear testHeadGearPositive = new HeadGear(randomLocal,true,"headgear1");
    mapTraversal(testHeadGearPositive.abilitiesEffected());

    assertEquals("constitution", ability[0]);
    assertEquals(1, effectValues[0]);

    Gear testHeadGearNegative = new HeadGear(randomLocal,false,
            "headgear2");
    mapTraversal(testHeadGearNegative.abilitiesEffected());

    assertEquals("constitution", ability[0]);
    assertEquals(-2, effectValues[0]);

    assertEquals("Type of Gear:headgear, Name of Gear:headgear1, "
            + "Impact of Gear:{constitution=1}", testHeadGearPositive.toString());
    assertEquals("Type of Gear:headgear, Name of Gear:headgear2, "
            + "Impact of Gear:{constitution=-2}", testHeadGearNegative.toString());

  }

  @Test
  public void testPotions() {

    RandomGenerate randomLocal = new RandomControlledChoices(0,1,2,3);
    Gear testPotionsPositive = new Potions(randomLocal, true, "potion1");

    mapTraversal(testPotionsPositive.abilitiesEffected());

    assertEquals("constitution", ability[0]);
    assertEquals(1, effectValues[0]);

    Gear testPotionsNegative = new Potions(randomLocal,false,"potion2");
    mapTraversal(testPotionsNegative.abilitiesEffected());

    assertEquals("charisma", ability[0]);
    assertEquals(-2, effectValues[0]);

    Gear testPotionsNegative2 = new Potions(randomLocal,false,"potion3");
    mapTraversal(testPotionsNegative2.abilitiesEffected());

    assertEquals("constitution", ability[0]);
    assertEquals(-1, effectValues[0]);


    assertEquals("Type of Gear:potion, Name of Gear:potion1, "
            + "Impact of Gear:{constitution=1}", testPotionsPositive.toString());
    assertEquals("Type of Gear:potion, Name of Gear:potion2, "
            + "Impact of Gear:{charisma=-2}", testPotionsNegative.toString());


  }

  @Test
  public void testFootwear() {

    RandomGenerate randomLocal = new RandomControlledChoices(1,2,3,4);
    Gear testFootwearPositive = new Footwear(randomLocal, true, "potion1");

    mapTraversal(testFootwearPositive.abilitiesEffected());

    assertEquals("dexterity", ability[0]);
    assertEquals(1, effectValues[0]);

    Gear testFootwearNegative = new Footwear(randomLocal,false,"potion2");
    mapTraversal(testFootwearNegative.abilitiesEffected());

    assertEquals("dexterity", ability[0]);
    assertEquals(-2, effectValues[0]);

    assertEquals("Type of Gear:footwear, Name of Gear:potion1, "
            + "Impact of Gear:{dexterity=1}", testFootwearPositive.toString());
    assertEquals("Type of Gear:footwear, Name of Gear:potion2, "
            + "Impact of Gear:{dexterity=-2}", testFootwearNegative.toString());

  }

  @Test
  public void testBelts() {

    RandomGenerate randomLocal = new RandomControlledChoices(0,1,2);
    Gear testBeltsPositive = new Belts(randomLocal, true, "belt1");

    Map<String,Integer> effectedMap = testBeltsPositive.abilitiesEffected();
    Integer expectedEffectValue = 1;
    assertEquals(expectedEffectValue, effectedMap.get("dexterity"));
    expectedEffectValue = 1;
    assertEquals(expectedEffectValue, effectedMap.get("constitution"));
    assertNull(effectedMap.get("strength"));
    assertNull(effectedMap.get("charisma"));

    Gear testBeltsNegative = new Belts(randomLocal, false, "belt2");

    effectedMap = testBeltsNegative.abilitiesEffected();
    expectedEffectValue = -1;
    assertEquals(expectedEffectValue, effectedMap.get("dexterity"));
    expectedEffectValue = -1;
    assertEquals(expectedEffectValue, effectedMap.get("constitution"));

    assertEquals("Type of Gear:belt, Name of Gear:belt1, Impact of Gear:{dexterity=1, "
            + "constitution=1}, size/Unit:4", testBeltsPositive.toString());
    assertEquals("Type of Gear:belt, Name of Gear:belt2, Impact of Gear:{dexterity=-1, "
            + "constitution=-1}, size/Unit:4", testBeltsNegative.toString());

  }


  @Test public void testSortGears() {
    RandomGenerate randomLocal = new RandomAuto();
    Gear testHeadGearLocal1 = new HeadGear(randomLocal,true,"headgear1");
    Gear testPotionsLocal1 = new Potions(randomLocal, true, "potion1");
    Gear testFootwearLocal1 = new Footwear(randomLocal, true, "footwear1");
    Gear testBeltsLocal1 = new Belts(randomLocal, true, "belt1");

    Gear testHeadGearLocal2 = new HeadGear(randomLocal,true,"headgear2");
    Gear testPotionsLocal2 = new Potions(randomLocal, true, "potion2");
    Gear testFootwearLocal2 = new Footwear(randomLocal, true, "footwear2");
    Gear testBeltsLocal2 = new Belts(randomLocal, true, "belt2");

    Gear testHeadGearLocal3 = new HeadGear(randomLocal,true,"headgear3");
    Gear testFootwearLocal3 = new Footwear(randomLocal, true, "footwear3");

    List<Gear> list = new ArrayList<>();

    list.add(testBeltsLocal1);
    list.add(testFootwearLocal2);
    list.add(testFootwearLocal1);
    list.add(testBeltsLocal2);
    list.add(testHeadGearLocal1);
    list.add(testHeadGearLocal2);
    list.add(testPotionsLocal2);
    list.add(testFootwearLocal3);
    list.add(testPotionsLocal1);
    list.add(testHeadGearLocal3);

    Collections.sort(list);

    assertEquals("headgear1", list.get(0).getName());
    assertEquals("headgear2", list.get(1).getName());
    assertEquals("headgear3", list.get(2).getName());

    assertEquals("potion1", list.get(3).getName());
    assertEquals("potion2", list.get(4).getName());

    assertEquals("belt1", list.get(5).getName());
    assertEquals("belt2", list.get(6).getName());

    assertEquals("footwear1", list.get(7).getName());
    assertEquals("footwear2", list.get(8).getName());
    assertEquals("footwear3", list.get(9).getName());

  }

}