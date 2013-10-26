package org.marathon.finalmatch.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxMinAlg {

    private Map<UserPoint, List<UserPoint>> typeDiffMap = new HashMap<UserPoint, List<UserPoint>>();

    private Map<UserPoint, Double> userPointMapCache = new HashMap<UserPoint, Double>();

    private UserPoint currentMaxDisPoint = null;

    public Map<UserPoint, List<UserPoint>> process(List<UserPoint> allUserPoint) {
        if (allUserPoint == null || allUserPoint.isEmpty()) {
            return typeDiffMap;
        }
        UserPoint newCenter = null;
        if (typeDiffMap.isEmpty()) {
            List<UserPoint> typeList = new ArrayList<UserPoint>();
            newCenter = allUserPoint.get(0);
            typeList.add(newCenter);
            typeDiffMap.put(newCenter, typeList);
            allUserPoint.remove(newCenter); 
        }
        for (UserPoint point : allUserPoint) {
            double distance = Math.pow(point.getX() - newCenter.getX(), 2)
                    + Math.pow(point.getY() - newCenter.getY(), 2);
            if (currentMaxDisPoint == null || userPointMapCache.get(currentMaxDisPoint) < distance) {
                currentMaxDisPoint = point;
            }
            userPointMapCache.put(point, distance);
        }
        typeDiffMap.put(currentMaxDisPoint, new ArrayList<UserPoint>());
        userPointMapCache.remove(currentMaxDisPoint);
        return null;
    }

}
