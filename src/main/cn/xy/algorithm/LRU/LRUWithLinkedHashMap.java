package cn.xy.algorithm.LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author XiangYu
 * @create2021-03-29-11:23 使用 LinkedHashMap 实现的一个 LRU 缓存：
 */
public class LRUWithLinkedHashMap<k, v> extends LinkedHashMap<k, v> {
    //最大长度
    private static final int MAX_ENTRIES = 3;

    @Override
    protected boolean removeEldestEntry(Map.Entry<k, v> eldest) {
        return size() > MAX_ENTRIES;
    }

    LRUWithLinkedHashMap() {
        super(MAX_ENTRIES, 0.75f, true);
    }
}
