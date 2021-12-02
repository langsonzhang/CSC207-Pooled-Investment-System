package Containers.PerformanceHistories;

import java.util.Map;
import java.util.TreeMap;
import java.util.Date;

//TODO: add tests and serialize

public class AssetPerformanceHistory {
    private final TreeMap<Date, Object> treeQueue = new TreeMap<>();
    private final int size;
    private static final AssetPerformanceHistory instance = new AssetPerformanceHistory();



    private AssetPerformanceHistory() {
        size = 168; // Hours in a week
    }

    public void recordHistory(Map<String, Double> history) {
        treeQueue.put(new Date(), history);

        if (treeQueue.size() > size) {
            treeQueue.remove(treeQueue.firstKey());
        }
    }

    public static AssetPerformanceHistory getInstance() { return instance; }
}
