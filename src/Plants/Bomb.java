package Plants;

import Zombies.Zombie;

import java.util.List;

public interface Bomb {
    /**
     * 判断是否会爆炸,如果会,则调用boom
     * @param zombies 棋盘上的所有僵尸
     */
    void judgeToBoom(List<Zombie> zombies);

    /**
     * 爆炸!!!
     */
    void boom();
}
