package gears;

import randoms.RandomGenerate;

import java.util.HashMap;
import java.util.Map;


/**
 * A class to build belts which creates the belt gear and manages all operations related to
 * the belt.
 */
public class Belts extends AbstractGear {

  private final String[] abilitiesEffected;
  private final int[] effectedValues;
  private final int size;

  /**
   * Constructs a belt object.
   *
   * @param random random number generator object
   * @param positiveNegativeEffect the effect of gear is positive if true else negative
   * @param gearName Name of the gear
   */
  public Belts(RandomGenerate random, boolean positiveNegativeEffect, String gearName) {
    super(random, positiveNegativeEffect, gearName);

    final int[] availableSizes = {1, 2, 4};
    int effectSign;

    abilitiesEffected = new String[2];
    int chooseAbility1 = random.getRandom(0,3);
    int chooseAbility2 = random.getRandom(0,3);

    if (chooseAbility1 == chooseAbility2) {
      chooseAbility2 = (chooseAbility1 + 1) % 4;

    }
    abilitiesEffected[0] = super.abilities[chooseAbility1];
    abilitiesEffected[1] = super.abilities[chooseAbility2];
    effectedValues = new int[2];
    if (positiveNegativeEffect) {
      effectSign = 1;
    }
    else {
      effectSign = -1;
    }
    effectedValues[0] = effectSign * random.getRandom(lowerBound,higherBound);
    effectedValues[1] = effectSign * random.getRandom(lowerBound,higherBound);
    size = availableSizes[random.getRandom(0,2)];
  }

  @Override
  public Map<String,Integer> abilitiesEffected() {
    effects = new HashMap<>();
    effects.put(abilitiesEffected[0], effectedValues[0]);
    effects.put(abilitiesEffected[1], effectedValues[1]);
    return effects;
  }

  @Override
  public int getGearSize() {
    return this.size;
  }

  @Override
  public String getGearType() {
    return "belt";
  }



  @Override
  protected int compareToCallFromBelts(Gear that) {
    return super.compareTo(that);
  }


  @Override
  protected int compareToCallFromFootwear(Gear that) {
    return 1;
  }

  @Override
  public int compareTo(Gear other) {
    return ((AbstractGear) other).compareToCallFromBelts(this);
  }

  @Override
  public String toString() {
    StringBuilder tempString = new StringBuilder();
    tempString.append("Type of Gear:");
    tempString.append(getGearType());
    tempString.append(", Name of Gear:");
    tempString.append(getName());
    tempString.append(", Impact of Gear:");
    tempString.append(abilitiesEffected());
    tempString.append(", size/Unit:");
    tempString.append(getGearSize());
    return tempString.toString();
  }

}
