package Zombies;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class NewspaperZombie extends Zombie{
  private static BufferedImage[] imgs;

  static {
    try{
      imgs=new BufferedImage[12];
      for (int i=0;i<12;i++){
        File file=new File("graphics/Zombies/NewspaperZombie/NewspaperZombie_"+i+".png");
        imgs[i]= ImageIO.read(file);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  };

  public NewspaperZombie(){
    super(166,144);
    this.frameNum=12;
    this.attack=100;
    this.HitPoint=270;
    this.blood=this.HitPoint;
    this.speed=2;
  }

  int index=1;
  public BufferedImage getImage(){
    return imgs[index++%12];
  }
}
