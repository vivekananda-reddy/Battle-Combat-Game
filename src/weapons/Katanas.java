package weapons;

import randoms.RandomGenerate;

import java.util.Map;

/**
 * A class to create Katanas weapons and manage Katanas weapon related tasks in it.
 */
public final class Katanas extends AbstractWeapon {

  private final RandomGenerate random;
  private final int lowestDamage;
  private final int highestDamage;

  /**
   * Constructs a Katanas object.
   * @param random random generator object
   * @param weaponName weapon name
   */
  public Katanas(RandomGenerate random, String weaponName) {
    super(random,weaponName);
    this.random = random;
    this.lowestDamage = 4;
    this.highestDamage = 6;

  }

  @Override
  public int getDamage(Map<String,Integer> abilities) {
    return (random.getRandom(lowestDamage,highestDamage)
            + random.getRandom(lowestDamage,highestDamage)) ;
  }

  @Override
  public String toString() {
    StringBuilder tempString = new StringBuilder();
    tempString.append("Type of Weapon: Katanas");
    tempString.append(", Name of Weapon:");
    tempString.append(getWeaponName());
    return tempString.toString();
  }
}
