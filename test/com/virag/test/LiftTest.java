package com.virag.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.virag.impl.Lift;
import com.virag.impl.Lift.LiftDirectionEnums;

public class LiftTest {
	public Lift elevator;
	
	@Before
	public void intilialize() {
		elevator = new Lift(0, 5, 2);
	}
	
	@Test
	public void checkAddingDestintion() {
		elevator.queueDestination(5);
		assertEquals(5, elevator.getDestinationQueue().peek());
	}
	
	@Test
	public void checkLiftUpDirection() {
		elevator.queueDestination(3);
		assertEquals(LiftDirectionEnums.LIFT_UP, elevator.direction());
	}
	
	@Test
	public void checkLiftDownDirection() {
		elevator.queueDestination(-1);
		assertEquals(LiftDirectionEnums.LIFT_DOWN, elevator.direction());
	}
	
	@Test
	public void checkLiftIsFull() {
		elevator.queueDestination(2);
		elevator.queueDestination(1);
		elevator.queueDestination(3);
		assertEquals(true, elevator.isFull());
	}
	
	@Test
	public void checkCurrentFloor() throws Exception {
		elevator.queueDestination(4);
		for(int i=0; i<4; i++)
			elevator.moveNext();
		
		assertEquals(4, elevator.getCurrentFloor());
	}
	
	@Test
	public void checkCurrentFloorUpAndDown() throws Exception {
		elevator.queueDestination(4);
		for(int i=0; i<4; i++)
			elevator.moveNext();
		elevator.queueDestination(3);
		for(int i=0; i<3; i++)
			elevator.moveNext();
		
		assertEquals(3, elevator.getCurrentFloor());
	}
	
	@Test
	public void checkLiftRunningDirection() throws Exception {
		elevator.queueDestination(4);
		assertEquals(LiftDirectionEnums.LIFT_UP, elevator.direction());
		for(int i=0; i<4; i++)
			elevator.moveNext();
		
		assertEquals(LiftDirectionEnums.LIFT_HOLD, elevator.direction());
		
		elevator.queueDestination(3);
		assertEquals(LiftDirectionEnums.LIFT_DOWN, elevator.direction());
		for(int i=0; i<3; i++)
			elevator.moveNext();
		
		assertEquals(LiftDirectionEnums.LIFT_HOLD, elevator.direction());
	}
	
	@Test
	public void checkDirectionHold() {
		assertEquals(LiftDirectionEnums.LIFT_HOLD, elevator.direction());
	}
	
	@Test
	public void checkLiftIsEmpty() {
		assertEquals(true, !elevator.isFull());
	}
}
