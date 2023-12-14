package gears;

import randoms.RandomGenerate;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class to implement gear interface.
 */
abstract class AbstractGear implements  Gear {

  protected int lowerBound;
  protected int higherBound;
  protected String gearName;
  protected RandomGenerate random;
  protected int effectValue;
  protected final String[] abilities = {"strength", "constitution", "dexterity", "charisma"};
  protected Map<String,Integer> effects;
  protected String abilityEffected;

  /**
   * Constructs a gear object with random object.
   *
   * @param random random number generator object
   * @param positiveNegativeEffect the effect of gear is positive if true else negative
   * @param gearName NAme of the gear
   * @throws IllegalArgumentException if random object is null or gear name is null or blank
   */
  public AbstractGear(RandomGenerate random, boolean positiveNegativeEffect, String gearName) {
    if (gearName == null || gearName.equals("")) {
      throw new IllegalArgumentException("Gear name can't be null or blank");
    }

    if (random == null) {
      throw new IllegalArgumentException("Random can't be null");
    }

    int effectSign;

    if (positiveNegativeEffect) {
      effectSign = 1;
    }
    else {
      effectSign = -1;
    }
    this.random = random;
    lowerBound = 1;
    higherBound = 6;
    this.effectValue = effectSign * random.getRandom(lowerBound, higherBound);
    this.gearName = gearName;


  }

  @Override
  public String getName() {
    return gearName;
  }

  @Override
  public Map<String,Integer> abilitiesEffected() {
    effects = new HashMap<>();
    effects.put(abilityEffected, effectValue);
    return new HashMap<>(effects);
  }

  @Override
  public int getGearSize() {
    return 1;
  }

  @Override
  public String getGearType() {
    return null;
  }

  protected int compareToCallFromHeadGear(Gear that) {
    return -1;
  }

  protected int compareToCallFromBelts(Gear that) {
    return -1;
  }

  protected int compareToCallFromPotions(Gear that) {
    return -1;
  }

  protected int compareToCallFromFootwear(Gear that) {
    return -1;
  }

  @Override
  public int compareTo(Gear that) {
    return  that.getName().compareTo(this.getName());
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
    return tempString.toString();
  }

}
