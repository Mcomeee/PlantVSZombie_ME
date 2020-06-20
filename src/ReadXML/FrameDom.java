package ReadXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// 异常：抛的话，上层能解决才行
// 上一层如果处理不了，自己必须处理。

public class FrameDom implements FrameInterface {

  /**
   * 根据name查询Frame信息
   *
   * @param name
   * @return 如果学生不存在，返回null
   */
  public Frame findFrame(String name) {
    Frame frame  = new Frame();;

    try {
      // 得到Document对象
      Document document = DocumentUtil.getDocument();
      // 得到所有的Frame元素
      NodeList nl = document.getElementsByTagName("name");
      // 遍历Frame元素，判断他的name属性的取值是否与参数匹配
      for (int i = 0; i < nl.getLength(); i++) {
        Node node = nl.item(i);
        //                if(node.getNodeType()==Node.ELEMENT_NODE){
        //                    Element e = (Element)node;
        if (node instanceof Element) {
          Element e = (Element) node;
          if (e.getAttribute("name").equals(name)) {
            // 如果匹配：说明找到了Frame；创建Fra e对象

            // 设置学生对象的各个属性取值
            // s.setIdcard(e.getAttribute("PeaShooter"));
            // s.setName(e.getElementsByTagName("name").item(0).getTextContent());
            // s.setLocation(e.getElementsByTagName("location").item(0).getTextContent());
            // s.setGrade(Float.parseFloat(e.getElementsByTagName("grade").item(0).getTextContent()));
            // System.out.println(s.getExamid());
            frame.setFrameHeight(
                Integer.parseInt(e.getElementsByTagName("height").item(0).getNodeValue()));
            frame.setFrameWidth(
                Integer.parseInt(e.getElementsByTagName("width").item(0).getNodeValue()));

          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return frame;
  }
}
