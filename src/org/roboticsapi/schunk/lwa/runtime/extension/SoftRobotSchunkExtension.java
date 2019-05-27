/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.schunk.lwa.runtime.extension;

import java.util.HashMap;
import java.util.Map;

import org.roboticsapi.core.RoboticsObject;
import org.roboticsapi.robot.RobotArmDriver;
import org.roboticsapi.runtime.SoftRobotRuntime;
import org.roboticsapi.runtime.driver.DeviceBasedInstantiator;
import org.roboticsapi.runtime.extension.AbstractSoftRobotRoboticsBuilder;
import org.roboticsapi.runtime.robot.driver.SoftRobotDHRobotArmDriver;
import org.roboticsapi.schunk.lwa.LWA46;
import org.roboticsapi.schunk.lwa.runtime.CANBasedLwaDriver;
import org.roboticsapi.schunk.lwa.runtime.SimulatedLwaDriver;

public class SoftRobotSchunkExtension extends AbstractSoftRobotRoboticsBuilder {

	private static final String EXTENSION = "schunk_lwa";
	private static final String EXTENSION_SIM = "schunk_lwa_sim";
	private static final String EXTENSION_CAN = "schunk_lwa_can";

	private final Map<SoftRobotDHRobotArmDriver<?>, DeviceBasedInstantiator<LWA46>> map = new HashMap<SoftRobotDHRobotArmDriver<?>, DeviceBasedInstantiator<LWA46>>();

	public SoftRobotSchunkExtension() {
		super(SimulatedLwaDriver.class, CANBasedLwaDriver.class);
	}

	@Override
	protected String[] getRuntimeExtensions() {
		return new String[] { EXTENSION, EXTENSION_SIM, EXTENSION_CAN };
	}

	@Override
	protected void onRoboticsObjectAvailable(RoboticsObject object) {
		if (object instanceof LWA46) {
			final LWA46 device = (LWA46) object;
			RobotArmDriver d = device.getDriver();

			if (d instanceof SimulatedLwaDriver) {
				final SimulatedLwaDriver driver = (SimulatedLwaDriver) d;
				final DeviceBasedInstantiator<LWA46> loader = new DeviceBasedInstantiator<LWA46>(device, driver);

				map.put(driver, loader);
				driver.addOperationStateListener(loader);
			}
			
			if (d instanceof CANBasedLwaDriver) {
				final CANBasedLwaDriver driver = (CANBasedLwaDriver) d;
				final DeviceBasedInstantiator<LWA46> loader = new DeviceBasedInstantiator<LWA46>(device, driver);

				map.put(driver, loader);
				driver.addOperationStateListener(loader);
			}

		}
	}

	@Override
	protected void onRoboticsObjectUnavailable(RoboticsObject object) {
		if (object instanceof SimulatedLwaDriver) {
			final SimulatedLwaDriver driver = (SimulatedLwaDriver) object;
			final DeviceBasedInstantiator<LWA46> loader = map.remove(driver);

			driver.removeOperationStateListener(loader);
		}
		
		if (object instanceof CANBasedLwaDriver) {
			final CANBasedLwaDriver driver = (CANBasedLwaDriver) object;
			final DeviceBasedInstantiator<LWA46> loader = map.remove(driver);

			driver.removeOperationStateListener(loader);
		}
	}

	@Override
	protected void onRuntimeAvailable(SoftRobotRuntime runtime) {
		// do nothing...
	}

	@Override
	protected void onRuntimeUnavailable(SoftRobotRuntime runtime) {
		// do nothing...
	}

}
