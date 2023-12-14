package gears;

import randoms.RandomGenerate;

/**
 * A class to build footwear which creates the footwear gear and manages all operations related to
 * it.
 */

public class Footwear extends AbstractGear {

  /**
   * Constructs a footwear object.
   *
   * @param random random number generator object
   * @param positiveNegativeEffect the effect of gear is positive if true else negative
   * @param gearName Name of the gear
   */
  public Footwear(RandomGenerate random, boolean positiveNegativeEffect, String gearName) {
    super(random, positiveNegativeEffect, gearName);
    super.abilityEffected = super.abilities[2];
  }

  @Override
  public String getGearType() {
    return "footwear";
  }

  @Override
  protected int compareToCallFromFootwear(Gear that) {
    return super.compareTo(that);
  }

  @Override
  public int compareTo(Gear other) {
    return ((AbstractGear) other).compareToCallFromFootwear(this);
  }

}
