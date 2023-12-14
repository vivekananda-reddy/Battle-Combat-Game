package test;

import static org.junit.Assert.assertEquals;

import gears.Gear;
import org.junit.Before;
import org.junit.Test;
import players.Bag;
import randoms.RandomControlled;
import randoms.RandomGenerate;

import java.util.List;


/**
 * A class to test bag class.
 */
public class BagTest {

  private RandomGenerate random;

  @Before
  public void setUp() throws Exception {
    random = new RandomControlled();

  }

  @Test
  public void testGetGearPack() {
    Bag bag = new Bag(random);

    List<Gear> list = bag.getGearPack();

    assertEquals("beltJ",list.get(0).getName());
    assertEquals("beltK",list.get(1).getName());
    assertEquals("beltI",list.get(2).getName());
    assertEquals("beltL",list.get(3).getName());
    assertEquals("beltH",list.get(4).getName());
    assertEquals("beltM",list.get(5).getName());
    assertEquals("beltG",list.get(6).getName());
    assertEquals("beltN",list.get(7).getName());
    assertEquals("beltF",list.get(8).getName());
    assertEquals("beltO",list.get(9).getName());
    assertEquals("beltE",list.get(10).getName());
    assertEquals("potionA",list.get(11).getName());
    assertEquals("beltD",list.get(12).getName());
    assertEquals("potionB",list.get(13).getName());
    assertEquals("beltC",list.get(14).getName());
    assertEquals("potionC",list.get(15).getName());
    assertEquals("beltB",list.get(16).getName());
    assertEquals("potionD",list.get(17).getName());
    assertEquals("beltA",list.get(18).getName());
    assertEquals("potionE",list.get(19).getName());


  }




}