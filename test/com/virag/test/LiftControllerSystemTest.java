package com.virag.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import com.virag.impl.Lift;
import com.virag.impl.LiftControllerSystem;

public class LiftControllerSystemTest {
	
	private LiftControllerSystem liftControllerSystem;
	private List<Lift> lift;
	private int minFloor = 0;
	private int maxFloor = 20;
	
	@Before
	public void initialize() {
		liftControllerSystem = new LiftControllerSystem(initializeLifts());
	}
	
	private List<Lift> initializeLifts() {
		lift = new ArrayList<Lift>();
		for(int i=0; i<6; i++) {
			lift.add(new Lift(minFloor, maxFloor, 1));
		}
		return lift;
	}
	
	@Test
	public void testCurrentFloorOfLift() throws Exception {
		liftControllerSystem.addPickup(10);
		for(int i=0; i<10; i++)
		liftControllerSystem.step();

		assertEquals(10, lift.get(0).getCurrentFloor());
	}
	
	@Test
	public void testInPathLift() throws Exception {
		liftControllerSystem.addPickup(8);
		liftControllerSystem.addPickup(10);
		for(int i=0; i<10; i++)
			liftControllerSystem.step();
		assertEquals(8, lift.get(0).getCurrentFloor());
		assertEquals(10, lift.get(1).getCurrentFloor());
		
		liftControllerSystem.addPickup(7);
		liftControllerSystem.addPickup(12);
		for(int i=0; i<7; i++)
			liftControllerSystem.step();
		assertEquals(7, lift.get(0).getCurrentFloor());
		assertEquals(12, lift.get(1).getCurrentFloor());
		
		liftControllerSystem.addPickup(5);
		for(int i=0; i<5; i++)
			liftControllerSystem.step();
		assertEquals(5, lift.get(0).getCurrentFloor());
		assertEquals(12, lift.get(1).getCurrentFloor());
	}
	
	@Test
	public void testChoiceAndInPathLift() throws Exception {
		liftControllerSystem.addPickup(7);
		liftControllerSystem.addPickup(10);
		for(int i=0; i<10; i++) {
			liftControllerSystem.step();
		}
		assertEquals(7, lift.get(0).getCurrentFloor());
		assertEquals(10, lift.get(1).getCurrentFloor());
		assertEquals(0, lift.get(2).getCurrentFloor());
		
		liftControllerSystem.addPickup(6);
		for(int i=0; i<lift.get(0).getCurrentFloor()-6; i++)
			liftControllerSystem.step();
		
		assertEquals(6, lift.get(0).getCurrentFloor());
		assertEquals(10, lift.get(1).getCurrentFloor());
		assertEquals(0, lift.get(2).getCurrentFloor());
		
		liftControllerSystem.addPickup(5);
		for(int i=0; i<lift.get(0).getCurrentFloor()-5; i++)
			liftControllerSystem.step();
		assertEquals(5, lift.get(0).getCurrentFloor());
		assertEquals(10, lift.get(1).getCurrentFloor());
		assertEquals(0, lift.get(2).getCurrentFloor());
	}
	
	@Test
	public void testChoiceOfLifts() throws Exception {
		liftControllerSystem.addPickup(5);
		liftControllerSystem.addPickup(10);
		for(int i=0; i<10; i++)
			liftControllerSystem.step();
		
		assertEquals(5, lift.get(0).getCurrentFloor());
		assertEquals(10, lift.get(1).getCurrentFloor());
		assertEquals(0, lift.get(2).getCurrentFloor());
		
		liftControllerSystem.addPickup(7);
		liftControllerSystem.addPickup(12);
		for(int i=0; i<12; i++) {
			liftControllerSystem.step();
		}
		
		assertEquals(7, lift.get(0).getCurrentFloor());
		assertEquals(12, lift.get(1).getCurrentFloor());
		assertEquals(0, lift.get(2).getCurrentFloor());
		
		liftControllerSystem.addPickup(10);
		liftControllerSystem.addPickup(15);
		
		for(int i=0; i<15; i++)
			liftControllerSystem.step();
		
		assertEquals(10, lift.get(0).getCurrentFloor());
		assertEquals(15, lift.get(1).getCurrentFloor());
		assertEquals(0, lift.get(2).getCurrentFloor());
		
		
		liftControllerSystem.addPickup(5);
		for(int i=0; i<15; i++)
			liftControllerSystem.step();
		
		assertEquals(5, lift.get(0).getCurrentFloor());
		assertEquals(15, lift.get(1).getCurrentFloor());
		assertEquals(0, lift.get(2).getCurrentFloor());
	}

	@Test
	public void testLift() throws Exception {
		liftControllerSystem.addPickup(21);
		if(maxFloor >= 21) {
			for(int i=0; i<maxFloor; i++)
				liftControllerSystem.step();
		}
		assertEquals(0, lift.get(0).getCurrentFloor());
	}
}
