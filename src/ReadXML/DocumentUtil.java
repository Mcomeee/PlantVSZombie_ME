package ReadXML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
// 操作XML的工具类
// 工具类中的异常可以抛也可以处理
public class DocumentUtil {
  public static Document getDocument() throws Exception { // 读取XML文档
    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    return builder.parse("src/Data.xml");
  }
}
