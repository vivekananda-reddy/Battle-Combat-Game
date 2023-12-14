package weapons;

import randoms.RandomGenerate;

import java.util.Map;

/**
 * An abstract class to implement weapon interface.
 */
abstract class AbstractWeapon implements Weapon {

  private final RandomGenerate random;
  private final int lowestDamage;
  private final int highestDamage;
  private final String weaponName;

  /**
   * Constructs an abstract weapon object from random object.
   * @param random random generator object
   * @param weaponName weapon name
   */
  public AbstractWeapon(RandomGenerate random, String weaponName) {
    if (weaponName == null || weaponName.equals("")) {
      throw new IllegalArgumentException("Weapon name can't be null");
    }

    if (random == null) {
      throw new IllegalArgumentException("Random can't be null");
    }
    this.random = random;
    this.lowestDamage = 6;
    this.highestDamage = 10;
    this.weaponName = weaponName;

  }

  @Override
  public int getDamage(Map<String,Integer> abilities) {
    return random.getRandom(lowestDamage,highestDamage);
  }

  @Override
  public String getWeaponName() {
    return this.weaponName;
  }

  @Override
  public String toString() {
    return null;
  }

}
