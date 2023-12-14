package gears;

import randoms.RandomGenerate;

/**
 * A class to build headgear which creates the headgear and manages all operations related to
 * it.
 */
public class HeadGear extends AbstractGear {

  /**
   * Constructs a headgear object.
   *
   * @param random random number generator object
   * @param positiveNegativeEffect the effect of gear is positive if true else negative
   * @param gearName Name of the gear
   */
  public HeadGear(RandomGenerate random, boolean positiveNegativeEffect, String gearName) {
    super(random, positiveNegativeEffect, gearName);
    super.abilityEffected = super.abilities[1];
  }

  @Override
  public String getGearType() {
    return "headgear";
  }

  @Override
  protected int compareToCallFromHeadGear(Gear that) {
    return super.compareTo(that);
  }

  @Override
  protected int compareToCallFromBelts(Gear that) {
    return 1;
  }

  @Override
  protected int compareToCallFromPotions(Gear that) {
    return 1;
  }

  @Override
  protected int compareToCallFromFootwear(Gear that) {
    return 1;
  }

  @Override
  public int compareTo(Gear other) {
    return ((AbstractGear) other).compareToCallFromHeadGear(this);
  }

}
