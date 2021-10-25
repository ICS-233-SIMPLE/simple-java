package edu.manipal.icas.simple.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	MsAccessApplicationDatabaseTest.class,
	MsAccessCitizenDatabaseTest.class
})
public class TestSuite {

}
