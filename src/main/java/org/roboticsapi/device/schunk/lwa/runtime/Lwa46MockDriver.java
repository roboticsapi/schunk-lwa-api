/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2013-2019 ISSE, University of Augsburg 
 */

package org.roboticsapi.device.schunk.lwa.runtime;

import org.roboticsapi.device.schunk.lwa.Lwa46;
import org.roboticsapi.framework.robot.runtime.rpi.driver.DHRobotArmGenericDriver;

public final class Lwa46MockDriver extends DHRobotArmGenericDriver<Lwa46> {

	private final static String DEVICE_TYPE = "schunk_lwa_sim";

	public Lwa46MockDriver() {
		super();
	}

	@Override
	public String getRpiDeviceType() {
		return DEVICE_TYPE;
	}

}
