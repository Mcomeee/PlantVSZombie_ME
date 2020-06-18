package Background;

import java.awt.Rectangle;

public class Grass extends Rectangle {

  //
  private boolean planted = Util.NOPLANT;

  public Grass(int x, int y, int width, int height) {
    super.x = x;
    super.y = y;
    super.height = height;
    super.width = width;
  }

  public boolean getPlanted() {
    return planted;
  }

  public void setPlanted(boolean planted) {
    this.planted = planted;
  }
}
