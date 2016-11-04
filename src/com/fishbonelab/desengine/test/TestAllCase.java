package com.fishbonelab.desengine.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAllCase {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//
		// run TestSample1
		TestSimple1 sample1 = new TestSimple1();
		sample1.main(null);

		//
		// run TestSample2
		TestSimple2 sample2 = new TestSimple2();
		sample2.main(null);

		//
		// run TestSample1
		TestSimple3 sample3 = new TestSimple3();
		sample3.main(null);

	}

}
