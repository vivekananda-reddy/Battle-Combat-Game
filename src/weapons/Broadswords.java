package weapons;

import randoms.RandomGenerate;

/**
 * A class to create Broadswords weapons and manage broadswords weapon related tasks in it.
 */
public final class Broadswords extends AbstractWeapon {

  /**
   * Constructs a broadsword object.
   * @param random random generator object
   * @param weaponName weapon name
   */
  public Broadswords(RandomGenerate random, String weaponName) {
    super(random, weaponName);
  }

  @Override
  public String toString() {
    StringBuilder tempString = new StringBuilder();
    tempString.append("Type of Weapon: Broadswords");
    tempString.append(", Name of Weapon:");
    tempString.append(getWeaponName());
    return tempString.toString();
  }

}
