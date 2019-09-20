/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.device.schunk.lwa.systemtest;

import org.junit.runner.RunWith;
import org.roboticsapi.core.exception.RoboticsException;
import org.roboticsapi.device.schunk.lwa.Lwa46;
import org.roboticsapi.device.schunk.lwa.runtime.Lwa46MockDriver;
import org.roboticsapi.framework.multijoint.activity.JointPtpInterface;
import org.roboticsapi.framework.multijoint.activity.JointPtpInterfaceTests;
import org.roboticsapi.framework.multijoint.activity.SimulatedJointMotionInterface;
import org.roboticsapi.framework.robot.activity.RobotPtpInterfaceTests;
import org.roboticsapi.systemtest.Prepare;
import org.roboticsapi.systemtest.RoboticsTestSuite;
import org.roboticsapi.systemtest.RoboticsTestSuite.DeviceInterfaceTests;
import org.roboticsapi.systemtest.WithDevice;
import org.roboticsapi.systemtest.WithRcc;
import org.roboticsapi.systemtest.WithRcc.Rcc;

@RunWith(RoboticsTestSuite.class)
@DeviceInterfaceTests({ JointPtpInterfaceTests.class, RobotPtpInterfaceTests.class })
@WithDevice(device = Lwa46.class, deviceDrivers = { Lwa46MockDriver.class })
@WithRcc(Rcc.DedicatedJavaRcc)
public class LwaSystemTest {

	@Prepare(Lwa46.class)
	public void prepare(Lwa46 device) throws RoboticsException {
		// Jump to home if possible
		SimulatedJointMotionInterface jump = device.use(SimulatedJointMotionInterface.class);
		if (jump != null) {
			jump.resetJoints(device.getHomePosition()).execute();
		} else {
			device.use(JointPtpInterface.class).ptpHome().execute();
		}
	}

}
