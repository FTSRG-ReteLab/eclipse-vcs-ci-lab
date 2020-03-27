package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

	TrainSensor     sensor;
	TrainController mockedController;
	TrainUser       mockedUser;

	@Before
	public void before()
	{
		mockedController = mock(TrainController.class);
		mockedUser       = mock(TrainUser.class);

		sensor = new TrainSensorImpl(mockedController, mockedUser);
	}
	
	@Test
	public void NegativeSpeedLimit_SetsAlarm()
	{
		sensor.overrideSpeedLimit(-1);

		verify(mockedUser, times(1)).setAlarmState(true);
	}
	
	@Test
	public void ZeroSpeedLimit_DoesNotSetAlarm()
	{
		sensor.overrideSpeedLimit(0);

		verify(mockedUser, times(0)).setAlarmState(true);
	}
	
	@Test
	public void MaxSpeedLimit_DoesNotSetAlarm()
	{
		sensor.overrideSpeedLimit(500);

		verify(mockedUser, times(0)).setAlarmState(true);
	}

	@Test
	public void TooHighSpeedLimit_SetsAlarm()
	{
		sensor.overrideSpeedLimit(501);

		verify(mockedUser, times(1)).setAlarmState(true);
	}
	
	@Test
	public void HalfOfReferenceSpeed_DoesNotSetAlarm()
	{
		when(mockedController.getReferenceSpeed()).thenReturn(100);

		sensor.overrideSpeedLimit(50);

		verify(mockedUser, times(0)).setAlarmState(true);
	}
	
	@Test
	public void LessThanHalfOfReferenceSpeed_SetsAlarm()
	{
		when(mockedController.getReferenceSpeed()).thenReturn(100);

		sensor.overrideSpeedLimit(49);

		verify(mockedUser, times(1)).setAlarmState(true);
	}

	@Test
	public void SpeedLimitGetter_ReturnsSetSpeedLimit()
	{
		sensor.overrideSpeedLimit(20);
		
		Assert.assertEquals(20, sensor.getSpeedLimit());
	}

}
