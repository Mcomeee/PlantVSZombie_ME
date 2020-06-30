package ReadXML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

// 操作XML的工具类
// 工具类中的异常可以抛也可以处理
public class DocumentUtil {

    private static Document document;

    public static Document getDocument() { // 读取XML文档
        //创建DocumentBuilderFactory对象
        if (document == null){
            DocumentBuilder builder = null;
            try {
                builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                document = builder.parse("src/Data.xml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return document;
    }


}
