package ReadXML;

import Plants.Plant;
import Zombies.Zombie;

public interface DataInterface {

  /**
   * 根据名称号查询Frame信息
   *
   * @param name
   * @return 如果不存在，返回null
   */
  Frame findFrame(String name) throws Exception;

  static Plant findPlant(String name) throws Exception {
    return null;
  }

  static Zombie findZombie(String name) throws Exception {
    return null;
  }
}
