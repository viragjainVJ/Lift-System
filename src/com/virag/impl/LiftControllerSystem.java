package com.virag.impl;

import java.util.*;

public class LiftControllerSystem {
    private List<Lift> lifts;

    private Queue<Integer> pickupQueue;

    public LiftControllerSystem(List<Lift> lifts) {
        this.lifts = lifts;
        pickupQueue = new LinkedList<>();
    }

    // Represents pick up (origin floor)
    public void addPickup(int floor) {
        if (!pickupQueue.isEmpty()) {
            pickupQueue.add(floor);
        } else {
            for (Lift lift : lifts) {
                if (lift.isIdle()) {
                    lift.queueDestination(floor);
                    return;
                } else if (lift.isInPath(floor)) {
                    lift.queueDestination(floor);
                }
            }
        }
    }

    // Move lifts.
    public void step() throws Exception {
        for (Lift lift : lifts) {
            if (lift.isIdle()) {
                if (!pickupQueue.isEmpty()) {
                    lift.queueDestination(pickupQueue.poll());
                }
            } else {
                lift.moveNext();
                lift.direction();
            }
        }
    } }
