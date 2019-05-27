/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.schunk.lwa.runtime;

import java.util.Map;

import org.roboticsapi.runtime.robot.driver.SoftRobotDHRobotArmDriver;
import org.roboticsapi.schunk.lwa.LWA46;

public final class SimulatedLwaDriver extends SoftRobotDHRobotArmDriver<LWA46> {

	private final static String DEVICE_TYPE = "schunk_lwa_sim";

	public SimulatedLwaDriver() {
		super();
	}

	@Override
	public String getDeviceType() {
		return DEVICE_TYPE;
	}

	@Override
	protected void collectDriverSpecificParameters(Map<String, String> parameters) {
		// empty
	}

}
