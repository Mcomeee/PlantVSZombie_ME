package ReadXML;

import Plants.Plant;
import Zombies.Zombie;

import java.util.Map;

public interface DataInterface {

    /**
     * 根据名称号查询Frame信息
     *
     * @param name
     * @return 如果不存在，返回null
     */
    Map findFrame(String name);

    Map findPlant(String name);

    Map findZombie(String name);
}
