package com.virag.impl;

import java.util.Queue;
import java.util.LinkedList;

public class Lift implements LiftInterface {
    private final int minFloor;
    private final int maxFloor;
    private final int maxCapacity;

    private int currentFloor;
    private Queue<Integer> destinationQueue;

    public enum LiftDirectionEnums {
    	LIFT_UP,
    	LIFT_DOWN,
    	LIFT_HOLD
    }
    public Lift(int minFloor, int maxFloor, int maxCapacity) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.maxCapacity = maxCapacity;

        currentFloor = 0;
        destinationQueue = new LinkedList<>();
    }

    @Override
    public int getMinFloor() {
        return minFloor;
    }

    @Override
    public int getMaxFloor() {
        return maxFloor;
    }

    @Override
    public int getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public Queue<Integer> getDestinationQueue() {
        return destinationQueue;
    }

    @Override
    public void queueDestination(int floor) {
        if (!destinationQueue.contains(floor)) {
            destinationQueue.add(floor);
        }
    }

    @Override
    public void moveNext() throws Exception {
        if (destinationQueue.isEmpty()) {
            return;
        }
        int destination = destinationQueue.peek();
        if (currentFloor < destination) {
            moveUp();
        } else if (currentFloor > destination) {
            moveDown();
        }

        if (currentFloor == destination) {
            destinationQueue.poll();
        }
    }

    @Override
    public LiftDirectionEnums direction() {
		if(destinationQueue.size() > 0) {
			if(currentFloor < destinationQueue.peek()) {
				return LiftDirectionEnums.LIFT_UP;
			}
			if(currentFloor > destinationQueue.peek()) {
				return LiftDirectionEnums.LIFT_DOWN;
			}
		}
		return LiftDirectionEnums.LIFT_HOLD;
	}
    
    @Override
    public void moveUp() throws Exception {
        if (currentFloor == maxFloor) {
            throw new Exception("cannot move above max currentFloor");
        }

        if (!isFull()) {
            currentFloor++;
        }
    }

    @Override
    public void moveDown() throws Exception {
        if (currentFloor == minFloor) {
            throw new Exception("cannot move below minimum currentFloor");
        }

        if (!isFull()) {
            currentFloor--;
        }
    }

    @Override
    public boolean isInPath(int floor) {
        if (destinationQueue.isEmpty()) {
            return false;
        }
        int destination = destinationQueue.peek();
        return (floor >= currentFloor && floor <= destination) || (floor <= currentFloor && floor >= destination);
    }

    @Override
    public boolean isFull() {
    	if(destinationQueue.size() > maxCapacity) {
    		return true;
    	}
        return false;
    }

    @Override
    public boolean isIdle() {
        return destinationQueue.isEmpty();
    }
}
