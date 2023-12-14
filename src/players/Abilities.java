package players;

import randoms.RandomGenerate;

/**
 * A class to create abilities for a player and maintains all the operations related to
 * the abilities.
 */
public final class Abilities {

  private final int strength;
  private final int constitution;
  private final int dexterity;
  private final int charisma;
  private final RandomGenerate random;

  /**
   * Contructs abilities object with random object.
   * @param random random number generator object
   * @throws IllegalArgumentException if random object is null
   */
  public Abilities(RandomGenerate random) {
    if (random == null) {
      throw new IllegalArgumentException("Random can't be null");
    }
    this.random = random;
    this.strength = calculateAbility();
    this.constitution = calculateAbility();
    this.dexterity = calculateAbility();
    this.charisma = calculateAbility();
  }

  /**
   * Gets strength of player.
   * @return strength of player
   */
  public int getStrength() {
    return this.strength;
  }

  /**
   * Gets constitution of a player.
   * @return constitution of a player
   */
  public  int getConstitution() {
    return this.constitution;
  }

  /**
   * Gets dexterity of a player.
   * @return dexterity of player
   */
  public  int getDexterity() {
    return this.dexterity;
  }

  /**
   * Gets charisma of a player.
   * @return charisma of player
   */
  public int getCharisma() {
    return this.charisma;
  }

  private int calculateAbility() {

    int min = 7;
    int count = 4;
    int diceValue;
    int sum = 0;

    while (count > 0) {
      diceValue = rollDice();
      sum = sum + diceValue;
      if (diceValue < min) {
        min = diceValue;
      }
      count--;
    }

    return sum - min;

  }

  private int rollDice() {
    int diceValue = 1;
    while (diceValue == 1) {
      diceValue = random.getRandom(1,6);
    }
    return diceValue;
  }

}
