package ReadXML;


import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 用于解析xml文件,获得相应的参数值
 */
public class DataDom implements DataInterface {

    private Document document;

    {
        // 得到Document对象
        document = DocumentUtil.getDocument();
        if (document == null) {
            System.out.println("Error:Haven't read XML file");
        }
    }


    public Map<String, Integer> findFrame(String name) {

        Map<String, Integer> res = new HashMap<>();

        // 得到所有的Frame元素
        NodeList nl = document.getElementsByTagName("Frame");
        // 遍历Frame元素，判断他的name属性的取值是否与参数匹配
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (!(node instanceof Element)) continue;
            Element e = (Element) node;
            if (e.getAttribute("name").equals(name)) {
                // 如果匹配：说明找到了Frame；创建Frame对象
                int height = Integer.parseInt(e.getElementsByTagName("frameHeight").item(0).getTextContent());
                int width = Integer.parseInt(e.getElementsByTagName("frameWidth").item(0).getTextContent());
                res.put("height", height);
                res.put("width", width);
                break;
            }
        }

        return res;
    }

    public Map<String, Integer> findPlant(String name) {

        Map<String, Integer> res = new HashMap<>();

        // 得到所有的Plant元素
        NodeList nl = document.getElementsByTagName("Plant");
        // 遍历Plant元素，判断他的name属性的取值是否与参数匹配
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (!(node instanceof Element)) continue;
            Element e = (Element) node;
            if (e.getAttribute("name").equals(name)) {
                // 如果匹配：说明找到了Plant；创建Plant对象
                // 设置Plant对象的各个属性取值
                int blood = Integer.parseInt(e.getElementsByTagName("HitPoint").item(0).getTextContent());
                int cost = Integer.parseInt(e.getElementsByTagName("Cost").item(0).getTextContent());
                res.put("blood", blood);
                res.put("cost", cost);
                break;
            }
        }

        return res;
    }

    public Map<String, Integer> findZombie(String name) {

        Map<String, Integer> res = new HashMap<>();

        // 得到所有的Zombie元素
        NodeList nl = document.getElementsByTagName("Zombie");
        // 遍历Zombie元素，判断他的name属性的取值是否与参数匹配
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (!(node instanceof Element)) continue;
            Element e = (Element) node;
            if (e.getAttribute("name").equals(name)) {
                int attack = Integer.parseInt(e.getElementsByTagName("attack").item(0).getTextContent());
                int blood = Integer.parseInt(e.getElementsByTagName("HitPoint").item(0).getTextContent());
                int speed = Integer.parseInt(e.getElementsByTagName("speed").item(0).getTextContent());
                res.put("attack", attack);
                res.put("blood", blood);
                res.put("speed", speed);
            }
        }

        return res;
    }
}
