package players;

import gears.Belts;
import gears.Footwear;
import gears.Gear;
import gears.HeadGear;
import gears.Potions;
import randoms.RandomGenerate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Constructs bag with gears and returns randomly when requested.
 */
public final class Bag {

  private final List<Gear> bag;
  private final RandomGenerate random;

  /**
   * constructs a bag object with random object.
   * @param random random generator object
   */
  public Bag(RandomGenerate random) {

    if (random == null) {
      throw new IllegalArgumentException("Random can't be null");
    }

    int bagSize = 40;
    this.random = random;
    bag = new LinkedList<>();

    boolean[] effectDeterminer = new boolean[bagSize];
    int intervals = 4;
    for (int i = intervals; i <= bagSize; i = i + intervals) {
      effectDeterminer[random.getRandom(i - intervals,i - 1)] = true;
    }


    int headGearMinCount = 5;
    for (int i = 0; i < headGearMinCount; i++) {
      bag.add(factoryMethod(0, effectDeterminer[bag.size()],"headgear"
              + (char)(65 + i)));
    }

    int footwearMinCount = 5;
    for (int i = 0; i < footwearMinCount; i++) {
      bag.add(factoryMethod(1, effectDeterminer[bag.size()],"footwear"
              + (char)(65 + i)));
    }

    int beltMinCount = 15;
    for (int i = 0; i < beltMinCount; i++) {
      bag.add(factoryMethod(2, effectDeterminer[bag.size()],"belt"
              + (char)(65 + i)));
    }

    int potionsMinCount = 15;
    for (int i = 0; i < potionsMinCount; i++) {
      bag.add(factoryMethod(3, effectDeterminer[bag.size()],"potion"
              + (char) (65 + i)));
    }

  }

  private Gear factoryMethod(int gear, boolean effect, String gearName) {

    if (gear == 0) {
      return new HeadGear(random, !effect, gearName);
    }

    if (gear == 1) {
      return new Footwear(random, !effect, gearName);
    }

    if (gear == 2) {
      return new Belts(random, !effect, gearName);
    }

    if (gear == 3) {
      return new Potions(random, !effect, gearName);
    }

    throw new IllegalArgumentException("Gear identifier not found");
  }


  /**
   * Gets a pack of 20 random gears form the bag.
   * @return List of 20 gears.
   */
  public List<Gear> getGearPack() {

    if (bag.size() < 20) {
      throw new IllegalStateException("Not enough items in the bag to generate 20 gear pack");
    }

    List<Gear> gearList = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      gearList.add(bag.remove(random.getRandom(0,bag.size() - 1)));
    }
    return gearList;
  }
}
