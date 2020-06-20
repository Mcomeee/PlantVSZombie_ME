package ReadXML;

public interface FrameInterface {

  /**
   * 根据Frame名称号查询Frame信息
   *
   * @param name
   * @return 如果frame不存在，返回null
   */
  Frame findFrame(String name);
}
