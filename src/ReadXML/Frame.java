package ReadXML;

public class Frame {
  private String name;
  private int frameWidth;
  private int frameHeight;

  /** XML文件读写 */
  public void setName(String name) {
    this.name = name;
  }

  public int getFrameWidth() {
    return frameWidth;
  }

  public void setFrameWidth(int frameHeight) {
    this.frameWidth = frameWidth;
  }

  public int getFrameHeight() {
    return frameHeight;
  }

  public void setFrameHeight(int frameHeight) {
    this.frameHeight = frameHeight;
  }
}
