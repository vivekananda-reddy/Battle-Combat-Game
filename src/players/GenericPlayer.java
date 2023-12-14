package players;

import gears.Gear;
import weapons.Weapon;

import java.util.List;


/**
 * A generic player interface which handles all the player related tasks for the game.
 * It has method to create, maintain and get player details.
 */
public interface GenericPlayer {

  /**
   * Gets player name.
   * @return player name
   */
  String getPlayerName();

  /**
   * Equip gear to the player by checking of the gear can fit the player based on existing gears.
   * @param equipGear A gear object which is to be equipped to the player
   * @return true if successfully equipped else false
   */
  boolean equipGear(Gear equipGear);

  /**
   * Gets all the gears equipped by the player.
   * @return List of gears equipped by player
   */
  List<Gear> getAllEquippedGears();

  /**
   * Gets all gears which are assigned to the player(including unequipped ones).
   * @return List of gears assigned to player
   */
  List<Gear> getPlayerGearPack();

  /**
   * Assigns a weapon to the player.
   * @param weapon Weapon to be assigned to the player
   */
  void assignWeapon(Weapon weapon);

  /**
   * Gets the assigned weapon to player.
   * @return Weapon assigned ot player
   */
  Weapon getAssignedWeapon();

  /**
   * Gets player strength.
   * @return player strength
   */
  int getPlayerStrength();

  /**
   * Gets player Constitution.
   * @return player constitution
   */
  int getPlayerConstitution();

  /**
   * Gets player dexterity.
   * @return dexterity of player
   */
  int getPlayerDexterity();

  /**
   * Gets layer charisma.
   * @return player charisma
   */
  int getPlayerCharisma();

  /**
   * Updates health of the player.
   * @param health new health to be updated
   */
  void updatePlayerHealth(int health);

  /**
   * Gets health of the player.
   * @return player health
   */
  int getPlayerHealth();

}
