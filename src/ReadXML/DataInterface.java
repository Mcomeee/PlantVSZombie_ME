package ReadXML;

import Plants.Plant;

public interface DataInterface {

  /**
   * 根据Frame名称号查询Frame信息
   *
   * @param name
   * @return 如果frame不存在，返回null
   */
  Frame findFrame(String name) throws Exception;

  static Plant findPlant(String name) throws Exception {
    return null;
  }

}
