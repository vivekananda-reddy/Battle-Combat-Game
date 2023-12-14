package gears;

import randoms.RandomGenerate;

/**
 * A class to build potions which creates the potions gear and manages all operations related to
 * it.
 */
public class Potions extends AbstractGear {

  /**
   * Constructs a potions object.
   *
   * @param random random number generator object
   * @param positiveNegativeEffect the effect of gear is positive if true else negative
   * @param gearName Name of the gear
   */
  public Potions(RandomGenerate random, boolean positiveNegativeEffect, String gearName) {
    super(random, positiveNegativeEffect, gearName);
    super.abilityEffected = super.abilities[random.getRandom(0,3)];
  }

  @Override
  public String getGearType() {
    return "potion";
  }


  @Override
  protected int compareToCallFromBelts(Gear that) {
    return 1;
  }

  @Override
  protected int compareToCallFromPotions(Gear that) {
    return super.compareTo(that);
  }

  @Override
  protected int compareToCallFromFootwear(Gear that) {
    return 1;
  }

  @Override
  public int compareTo(Gear other) {
    return ((AbstractGear) other).compareToCallFromPotions(this);
  }


}
