package weapons;

import randoms.RandomGenerate;

import java.util.Map;

/**
 * A class to create Flails weapons and manage Flails weapon related tasks in it.
 */
public final class Flails extends AbstractWeapon {

  private final RandomGenerate random;
  private final int lowestDamage;
  private final int highestDamage;


  /**
   * Constructs an Flails object.
   * @param random random generator object
   * @param weaponName weapon name
   */
  public Flails(RandomGenerate random, String weaponName) {
    super(random,weaponName);
    this.random = random;
    lowestDamage = 8;
    highestDamage = 12;

  }

  @Override
  public int getDamage(Map<String,Integer> abilities) {
    int damage = random.getRandom(lowestDamage,highestDamage);
    if (abilities.get("dexterity") > 14) {
      return damage;
    }
    return damage / 2;
  }

  @Override
  public String toString() {
    StringBuilder tempString = new StringBuilder();
    tempString.append("Type of Weapon: Flails");
    tempString.append(", Name of Weapon:");
    tempString.append(getWeaponName());
    return tempString.toString();
  }
}
