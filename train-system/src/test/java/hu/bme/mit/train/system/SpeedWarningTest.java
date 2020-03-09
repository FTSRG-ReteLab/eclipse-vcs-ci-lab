package hu.bme.mit.train.system;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

public class TrainSystemTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;

	private final ByteArrayOutputStream outContent =
		new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@Before
	public void before()
	{
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();

		System.setOut(new PrintStream(outContent));
	}

	@After
	public void after()
	{
		System.setOut(originalOut);
	}

	@Test
	public void ApprachingSpeedLimit_SendsWarning()
	{
		sensor.overrideSpeedLimit(10);
		Assert.assertEquals(0, controller.getReferenceSpeed());

		user.overrideJoystickPosition(5);
		controller.followSpeed();
		controller.followSpeed();
		Assert.assertEquals(
			"You are going fast\nApproaching speed limit!\n",
			outContent.toString());
	}

}
