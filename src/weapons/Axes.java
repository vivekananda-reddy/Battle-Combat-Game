package weapons;

import randoms.RandomGenerate;

/**
 * A class to create Axes weapons and manage axe related tasks in it.
 */
public final class Axes extends AbstractWeapon {

  /**
   * Constructs an Axes object.
   * @param random random generator object
   * @param weaponName weapon name
   */
  public Axes(RandomGenerate random, String weaponName) {
    super(random,weaponName);
  }

  @Override
  public String toString() {
    StringBuilder tempString = new StringBuilder();
    tempString.append("Type of Weapon: Axes");
    tempString.append(", Name of Weapon:");
    tempString.append(getWeaponName());
    return tempString.toString();
  }
}
