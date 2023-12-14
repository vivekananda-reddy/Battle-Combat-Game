package weapons;

import java.util.Map;

/**
 * Weapon interface to maintain all the details and damage calculations related to weapon.
 */
public interface Weapon {

  /**
   * Gets damage caused by a weapon. Damage is a random value in the damage range of the weapon
   * @param abilities Map of abilities with key as ability name used to calculate
   *                  damage for few weapons
   * @return damage value
   */
  int getDamage(Map<String,Integer> abilities);

  /**
   * Gets name of the weapon.
   * @return name of the weapon
   */
  String getWeaponName();

}
