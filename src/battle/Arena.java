package battle;

import gears.Gear;
import players.Bag;
import players.GenericPlayer;
import players.Player;
import randoms.RandomGenerate;
import weapons.Axes;
import weapons.Broadswords;
import weapons.Flails;
import weapons.Katanas;
import weapons.TwoHandedSwords;
import weapons.Weapon;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A class which manages the entire game. Contains methods to initialize all the components
 * in the game and play the game till the game is over and also initiate a rematch.
 */

public final class Arena {

  private final Bag bag;
  GenericPlayer player1;
  GenericPlayer player2;
  private final RandomGenerate random;
  private boolean trackTurn;
  private int gameCounter;


  /**
   * Constructs a Arena object with the provided random number generator object.
   *
   * @param random random number generator object.
   * @throws IllegalArgumentException when random object is null
   */
  public Arena(RandomGenerate random) {
    if (random == null) {
      throw new IllegalArgumentException("Random object can't be null");
    }
    this.random = random;
    bag = new Bag(random);
    player1 = null;
    player2 = null;
    gameCounter = 0;

  }

  /**
   * Created player objects with the player names provided.
   *
   * @param playerName1 name of player 1
   * @param playerName2 name of player 2
   * @return Map of player attributes player names as key player name
   * @throws IllegalArgumentException if player names are null or blank and both player names are
   *                                  same
   */

  public Map<String, Map<String, Integer>> createPlayers(String playerName1, String playerName2) {

    if (playerName1 == null || playerName2 == null || playerName1.equals("")
            || playerName2.equals("")) {
      throw new IllegalArgumentException("Player names can't be null or blank");
    }
    if (playerName1.equals(playerName2)) {
      throw new IllegalArgumentException("Two player names can't be the same");
    }

    player1 = new Player(random, playerName1);
    player2 = new Player(random, playerName2);

    return getPlayersAttributeInfo();

  }

  private Weapon createWeapon(int id, String weaponName) {
    if (id == 0) {
      return new Katanas(random, weaponName);
    }

    if (id == 1) {
      return new Broadswords(random, weaponName);
    }

    if (id == 2) {
      return new TwoHandedSwords(random, weaponName);
    }

    if (id == 3) {
      return new Axes(random, weaponName);
    }

    if (id == 4) {
      return new Flails(random, weaponName);
    } else {
      throw new IllegalArgumentException("Identifier not in range");
    }
  }

  private void setInitialHealth() {
    player1.updatePlayerHealth(player1.getPlayerStrength() + player1.getPlayerConstitution()
            + player1.getPlayerDexterity() + player1.getPlayerCharisma());
    player2.updatePlayerHealth(player2.getPlayerStrength() + player2.getPlayerConstitution()
            + player2.getPlayerDexterity() + player2.getPlayerCharisma());

  }

  private void setFirstTurn() {

    if (player1.getPlayerCharisma() >= player2.getPlayerCharisma()) {
      this.trackTurn = true;
    } else {
      this.trackTurn = false;
    }
  }

  /**
   * Equips both players with gears and weapons. Names the weapons equipped with the names provided.
   *
   * @param weaponNamePlayer1 Name of weapon 1
   * @param weaponNamePlayer2 Name of weapon 2
   * @throws IllegalArgumentException If weapon names are same or weapon names are null or blank.
   * @throws IllegalStateException if players are not created
   */
  public void equipGearsAndWeapon(String weaponNamePlayer1, String weaponNamePlayer2) {

    if (weaponNamePlayer2 == null || weaponNamePlayer1 == null || weaponNamePlayer1.equals("")
            || weaponNamePlayer2.equals("")) {
      throw new IllegalArgumentException("Weapon name can't be null or empty");
    }

    if (weaponNamePlayer2.equals(weaponNamePlayer1)) {
      throw new IllegalArgumentException("Two player weapon names can't be the same");
    }

    if (player1 == null || player2 == null) {
      throw new IllegalStateException("Both players should be created first before equip "
              + "gears and weapon");
    }

    List<Gear> gearPack = bag.getGearPack();

    for (Gear g : gearPack) {
      player1.equipGear(g);
    }

    gearPack = bag.getGearPack();

    for (Gear g : gearPack) {
      player2.equipGear(g);
    }

    int weaponsID = random.getRandom(0, 3);
    player1.assignWeapon(createWeapon(weaponsID, weaponNamePlayer1));
    weaponsID = random.getRandom(0, 3);
    player2.assignWeapon(createWeapon(weaponsID, weaponNamePlayer2));

    setInitialHealth();
    setFirstTurn();


  }

  /**
   * Gets the player abilities after equipping gears.
   *
   * @return Map of player abilities with key values as player names
   * @throws IllegalStateException if players are not created
   */
  public Map<String, Map<String, Integer>> getPlayersAttributeInfo() {

    if (player1 == null || player2 == null) {
      throw new IllegalStateException("Players attributes can't be provided without "
              + "creating players");
    }

    Map<String, Integer> playerAttributes = new HashMap<>();

    playerAttributes.put("strength", player1.getPlayerStrength());
    playerAttributes.put("constitution", player1.getPlayerConstitution());
    playerAttributes.put("dexterity", player1.getPlayerDexterity());
    playerAttributes.put("charisma", player1.getPlayerCharisma());

    Map<String, Map<String, Integer>> playersDetails = new HashMap<>();

    playersDetails.put(player1.getPlayerName(), playerAttributes);

    playerAttributes = new HashMap<>();
    playerAttributes.put("strength", player2.getPlayerStrength());
    playerAttributes.put("constitution", player2.getPlayerConstitution());
    playerAttributes.put("dexterity", player2.getPlayerDexterity());
    playerAttributes.put("charisma", player2.getPlayerCharisma());

    playersDetails.put(player2.getPlayerName(), playerAttributes);

    return playersDetails;

  }

  /**
   * Gets the details og gears equipped by each player.
   * @return Map with key was player names and list of equipped gear objects for respective player
   */
  public Map<String, List<Gear>> getPlayersGearInfo() {

    if (player1 == null || player2 == null) {
      throw new IllegalStateException("Players gears can't be provided without creating players");
    }
    Map<String, List<Gear>> playerGearInfo = new HashMap<>();
    List<Gear> gearList1;
    gearList1 = player1.getAllEquippedGears();
    Collections.sort(gearList1);

    List<Gear> gearList2;
    gearList2 = player2.getAllEquippedGears();
    Collections.sort(gearList2);

    playerGearInfo.put(player1.getPlayerName(), gearList1);
    playerGearInfo.put(player2.getPlayerName(), gearList2);
    return playerGearInfo;

  }

  /**
   * Gets weapons infor for each player.
   * @return Map of weapon name and key as player name
   * @throws IllegalStateException if players are not created
   */
  public Map<String, String> getWeaponInfo() {

    if (player1 == null || player2 == null) {
      throw new IllegalStateException("Players weapons can't be provided without creating players");
    }


    Map<String, String> weaponsInfo = new HashMap<>();

    weaponsInfo.put(player1.getPlayerName(), player1.getAssignedWeapon().getWeaponName());
    weaponsInfo.put(player2.getPlayerName(), player2.getAssignedWeapon().getWeaponName());

    return weaponsInfo;
  }

  /**
   * Gets health of both players after equipping gears.
   * @return Map of health value with key as player name
   * @throws IllegalStateException if players are not created
   */
  public Map<String, Integer> getHealthOfPlayers() {

    if (player1 == null || player2 == null) {
      throw new IllegalStateException("Players health can't be provided without creating players");
    }

    Map<String, Integer> healths = new HashMap<>();
    healths.put(player1.getPlayerName(), player1.getPlayerHealth());
    healths.put(player2.getPlayerName(), player2.getPlayerHealth());
    return healths;
  }

  private int getStrikingPower(GenericPlayer player) {

    return player.getPlayerStrength() + random.getRandom(1, 10);
  }

  private int getAvoidanceAbility(GenericPlayer player) {
    return player.getPlayerDexterity() + random.getRandom(1, 6);
  }

  private void loadMapAttributes(Map<String, Integer> attributes, GenericPlayer player) {
    attributes.put("strength", player.getPlayerStrength());
    attributes.put("constitution", player.getPlayerConstitution());
    attributes.put("dexterity", player.getPlayerDexterity());
    attributes.put("charisma", player.getPlayerCharisma());
  }

  private Map<String, Integer> getMapOfAttributes(GenericPlayer player) {
    Map<String, Integer> attributes = new HashMap<>();
    loadMapAttributes(attributes, player);
    return attributes;
  }

  private void loadMapPlayRoundWithDefaults(Map<String, String> roundResults,
                                            GenericPlayer attacker) {
    roundResults.put("Attacker", attacker.getPlayerName());
    roundResults.put("hit", "No Hit Happened");
    roundResults.put("Damage opponents took", "0");
    roundResults.put("Health Player1", String.valueOf(player1.getPlayerHealth()));
    roundResults.put("Health Player2", String.valueOf(player2.getPlayerHealth()));
  }

  /**
   * Plays the round with one iteration of attacking the opponent.
   * @return round details like attacker, hit happened or not, damage, health of players in a Map.
   */
  public Map<String, String> playRound() {

    GenericPlayer attacker;
    GenericPlayer defender;
    boolean hit = true;
    Map<String, String> roundResults = new HashMap<>();

    if (trackTurn) {
      attacker = player1;
      defender = player2;
    } else {
      attacker = player2;
      defender = player1;
    }
    int strikingPower = getStrikingPower(attacker);
    int avoidanceAbility = getAvoidanceAbility(defender);

    if (strikingPower <= avoidanceAbility) {
      hit = false;

      gameCounter++;
      loadMapPlayRoundWithDefaults(roundResults, attacker);
      trackTurn = !trackTurn;
      return roundResults;

    }

    int potentialStrikingDamage = attacker.getPlayerStrength() + attacker.getAssignedWeapon()
            .getDamage(getMapOfAttributes(attacker));
    int actualDamage = potentialStrikingDamage - defender.getPlayerConstitution();
    if (actualDamage > 0) {
      defender.updatePlayerHealth(defender.getPlayerHealth() - actualDamage);
    }
    else {
      hit = false;
      gameCounter++;
      loadMapPlayRoundWithDefaults(roundResults, attacker);
      trackTurn = !trackTurn;
      return roundResults;
    }

    roundResults.put("Attacker", attacker.getPlayerName());
    roundResults.put("hit", "Hit Happened");
    roundResults.put("Damage opponents took", String.valueOf(actualDamage));
    roundResults.put("Health Player1", String.valueOf(player1.getPlayerHealth()));
    roundResults.put("Health Player2", String.valueOf(player2.getPlayerHealth()));
    gameCounter = 0;
    trackTurn = !trackTurn;
    return roundResults;
  }

  /**
   * Returns if a game is over or not.
   * @return true if game is over else false
   */
  public boolean isGameOver() {
    return player1.getPlayerHealth() <= 0 || player2.getPlayerHealth() <= 0 || gameCounter >= 10;
  }

  /**
   * Returns match result with winner name or draw if the game is a draw.
   * @return winner name or draw
   */
  public String matchResult() {
    if (player1.getPlayerHealth() <= 0 && player2.getPlayerHealth() <= 0 || gameCounter >= 10) {
      return "draw";
    }

    if (player1.getPlayerHealth() <= 0) {
      return player2.getPlayerName();
    }

    if (player2.getPlayerHealth() <= 0) {
      return player1.getPlayerName();
    }
    return "draw";
  }

  /**
   * Initiates a rematch and restores the turn and health of players to initial positions before
   * battle.
   */
  public void rematch() {
    setInitialHealth();
    setFirstTurn();
    gameCounter = 0;

  }

}
