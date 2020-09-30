package edu.brown.cs.student.game;

import org.junit.Test;

public class GameTest {

  @Test
  public void test() {
    Game game = new Game("data/testObjects.sqlite3");
    System.out.println(game.getLevel());
    assert (game.getLevel().getDimension() == 2);
    assert (game.getLevel().getPlaceables().size() == 2);
    game.incrementLevel();
    assert (game.getLevel().getDimension() == 3);
    assert (game.getLevel().getPlaceables().size() == 3);
    game.setLevel(0);
    assert (game.getLevel().getDimension().equals(2));
    game.refreshLevel();
    assert(game.getLevel().getDimension().equals(2));
    game.resetLevel();
    assert(game.getLevel().getDimension().equals(2));

  }

}
