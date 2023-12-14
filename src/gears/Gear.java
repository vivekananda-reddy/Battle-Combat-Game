package gears;

import java.util.Map;

/**
 * Gear interface to maintain all the details related to the gears like name, abilities effected,
 * size.
 */
public interface Gear extends Comparable<Gear> {

  /**
   * Gets name of the gear.
   * @return name of gear
   */
  String getName();

  /**
   * Gets abilities effected by the gear.
   * @return Map with ability name as key and the effect value
   */
  Map<String,Integer> abilitiesEffected();

  /**
   * Gets the size of the gear.
   * @return gear size
   */
  int getGearSize();

  /**
   * Gets type of the gear.
   * @return type of the gear
   */
  String getGearType();

}
