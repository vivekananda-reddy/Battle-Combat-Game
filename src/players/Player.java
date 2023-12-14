package players;

import gears.Gear;
import randoms.RandomGenerate;
import weapons.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Creates the player for the game and maintains all attributes and operations related to
 * the player.
 */
public final class Player implements GenericPlayer {

  private final String playerName;
  private final List<Gear> totalGears;
  private final List<Gear> equippedGears;
  private Weapon weapon;
  private int playerStrength;
  private int playerConstitution;
  private int playerDexterity;
  private int playerCharisma;

  private int headGearsEquippedUnits;
  private int beltsEquippedUnits;
  private int potionsEquippedUnits;
  private int footwearEquippedUnits;

  private int health;


  /**
   * Contructs  player object with player name and random object.
   * @param random random generator object
   * @param playerName player name
   * @throws IllegalArgumentException if player name is null or blank or ranodm is null
   */
  public Player(RandomGenerate random, String playerName) {
    if (playerName == null || playerName.equals("")) {
      throw new IllegalArgumentException("Player name can't be null or blank");
    }

    if (random == null) {
      throw new IllegalArgumentException("Random can't be null");
    }

    final Abilities abilities;
    this.playerName = playerName;
    headGearsEquippedUnits = 0;
    beltsEquippedUnits = 0;
    potionsEquippedUnits = 0;
    footwearEquippedUnits = 0;

    abilities = new Abilities(random);
    totalGears = new ArrayList<>();
    equippedGears = new ArrayList<>();
    weapon = null;

    playerStrength = abilities.getStrength();
    playerConstitution = abilities.getConstitution();
    playerDexterity = abilities.getDexterity();
    playerCharisma = abilities.getCharisma();

    health = 0;

  }

  @Override
  public String getPlayerName() {
    return playerName;
  }

  private void adjustAbilities() {

    Map<String,Integer> mp = equippedGears.get(equippedGears.size() - 1).abilitiesEffected();

    if (mp.containsKey("strength")) {
      playerStrength = playerStrength + mp.get("strength");
    }

    if (mp.containsKey("constitution")) {
      playerConstitution = playerConstitution + mp.get("constitution");
    }

    if (mp.containsKey("dexterity")) {
      playerDexterity = playerDexterity + mp.get("dexterity");
    }

    if (mp.containsKey("charisma")) {
      playerCharisma = playerCharisma + mp.get("charisma");
    }

  }

  @Override
  public boolean equipGear(Gear equipGear) {

    if (totalGears.size() >= 20) {
      throw new IllegalStateException("A player can only take 20 items");
    }

    if (equipGear.getGearType().equals("headgear")) {
      totalGears.add(equipGear);
      if (headGearsEquippedUnits > 0) {
        return false;
      }
      else {
        headGearsEquippedUnits++;
        equippedGears.add(equipGear);
        adjustAbilities();
        return true;
      }

    }

    if (equipGear.getGearType().equals("potion")) {
      totalGears.add(equipGear);
      potionsEquippedUnits++;
      equippedGears.add(equipGear);
      adjustAbilities();
      return true;
    }

    if (equipGear.getGearType().equals("footwear")) {
      totalGears.add(equipGear);
      if (footwearEquippedUnits > 0) {
        return false;
      }
      else {
        footwearEquippedUnits++;
        equippedGears.add(equipGear);
        adjustAbilities();
        return true;
      }
    }

    if (equipGear.getGearType().equals("belt")) {
      totalGears.add(equipGear);
      if (beltsEquippedUnits + equipGear.getGearSize() > 10) {
        return false;
      }
      else {
        beltsEquippedUnits = beltsEquippedUnits + equipGear.getGearSize();
        equippedGears.add(equipGear);
        adjustAbilities();
        return true;
      }
    }
    throw new IllegalArgumentException("Gear unidentified");
  }

  @Override
  public List<Gear> getAllEquippedGears() {
    return new ArrayList<>(equippedGears);
  }

  @Override
  public List<Gear> getPlayerGearPack() {
    return new ArrayList<>(totalGears);
  }

  @Override
  public void assignWeapon(Weapon weapon) {
    if (this.weapon != null) {
      throw new IllegalStateException("Weapon already assigned to player:" + getPlayerName());
    }
    this.weapon = weapon;
  }

  @Override
  public Weapon getAssignedWeapon() {
    if (this.weapon == null) {
      throw new IllegalStateException("No weapon assigned to player:" + getPlayerName());
    }
    return weapon;
  }

  @Override
  public int getPlayerStrength() {
    if (playerStrength < 0) {
      return 0;
    }
    return playerStrength;
  }

  @Override
  public int getPlayerConstitution() {
    if (playerConstitution < 0) {
      return 0;
    }
    return playerConstitution;
  }

  @Override
  public int getPlayerDexterity() {

    if (playerDexterity < 0) {
      return 0;
    }
    return playerDexterity;
  }

  @Override
  public int getPlayerCharisma() {
    if (playerCharisma < 0) {
      return 0;
    }
    return playerCharisma;
  }

  @Override
  public void updatePlayerHealth(int health) {
    this.health = health;
  }

  @Override
  public int getPlayerHealth() {
    return health;
  }

}
