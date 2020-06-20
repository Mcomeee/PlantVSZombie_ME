package ReadXML;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
// 异常：抛的话，上层能解决才行
// 上一层如果处理不了，自己必须处理。

public class FrameDom {

  /**
   * 根据name查询Frame信息
   *
   * @param name
   * @return 如果学生不存在，返回null
   */
  public Frame findFrame(String name) {
    Frame frame = null;
    ;

    try {
      // 得到Document对象
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse("src/Data.xml");
      if (document == null) {
        System.out.println("Error:Haven't read XML file");
      }
      // 得到所有的Frame元素
      NodeList nl = document.getElementsByTagName("Frame");
      // 遍历Frame元素，判断他的name属性的取值是否与参数匹配
      for (int i = 0; i < nl.getLength(); i++) {
        Node node = nl.item(i);
        //                if(node.getNodeType()==Node.ELEMENT_NODE){
        //                    Element e = (Element)node;
        if (node instanceof Element) {
          Element e = (Element) node;
          if (e.getAttribute("name").equals(name)) {
            // 如果匹配：说明找到了Frame；创建Frame对象
            frame = new Frame();

            // 设置Frame对象的各个属性取值
            // s.setIdcard(e.getAttribute("PeaShooter"));
            // s.setName(e.getElementsByTagName("name").item(0).getTextContent());
            // s.setLocation(e.getElementsByTagName("location").item(0).getTextContent());
            // s.setGrade(Float.parseFloat(e.getElementsByTagName("grade").item(0).getTextContent()));
            // System.out.println(s.getExamid());
            frame.setName(name);
            frame.setFrameHeight(
                Integer.parseInt(e.getElementsByTagName("frameHeight").item(0).getTextContent()));
            frame.setFrameWidth(
                Integer.parseInt(e.getElementsByTagName("frameWidth").item(0).getTextContent()));
          }
        }
      }
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (NullPointerException e) {
      e.printStackTrace();
    } finally {
      System.out.println("Error: Read XML");
    }

    return frame;
  }
}
