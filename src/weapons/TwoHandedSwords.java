package weapons;

import randoms.RandomGenerate;

import java.util.Map;

/**
 * A class to create Two handed swords weapons and manage two handed swords weapon related
 * tasks in it.
 */
public final class TwoHandedSwords extends AbstractWeapon {
  private final RandomGenerate random;
  private final int lowestDamage;
  private final int highestDamage;

  /**
   * Constructs an TwoHandedSwords object.
   * @param random random generator object
   * @param weaponName weapon name
   */
  public TwoHandedSwords(RandomGenerate random, String weaponName) {
    super(random, weaponName);
    this.random = random;
    this.lowestDamage = 8;
    this.highestDamage = 12;
  }

  @Override
  public int getDamage(Map<String,Integer> abilities) {
    int damage = random.getRandom(lowestDamage,highestDamage);
    if (abilities.get("strength") > 14) {
      return damage;
    }
    return damage / 2;
  }

  @Override
  public String toString() {
    StringBuilder tempString = new StringBuilder();
    tempString.append("Type of Weapon: TwoHandedSwords");
    tempString.append(", Name of Weapon:");
    tempString.append(getWeaponName());
    return tempString.toString();
  }

}
