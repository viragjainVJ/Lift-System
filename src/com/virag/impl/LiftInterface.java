package com.virag.impl;

import java.util.Queue;

import com.virag.impl.Lift.LiftDirectionEnums;

public interface LiftInterface {
    int getMinFloor();
    int getMaxFloor();
    int getCurrentFloor();
    Queue<Integer> getDestinationQueue();
    void moveUp() throws Exception;
    void moveDown() throws Exception;
    void moveNext() throws Exception;
    void queueDestination(int floor);
    boolean isInPath(int floor);
    boolean isFull();
    boolean isIdle();
    LiftDirectionEnums direction();
}
