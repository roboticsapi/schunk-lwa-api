/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.schunk.lwa.runtime;

import java.util.Map;

import org.roboticsapi.configuration.ConfigurationProperty;
import org.roboticsapi.core.exception.ConfigurationException;
import org.roboticsapi.runtime.fieldbus.canopen.CanOpenFieldbusDriver;
import org.roboticsapi.runtime.robot.driver.SoftRobotDHRobotArmDriver;
import org.roboticsapi.schunk.lwa.LWA46;

public final class CANBasedLwaDriver extends SoftRobotDHRobotArmDriver<LWA46> {

	private final static String DEVICE_TYPE = "schunk_lwa_can";

	private CanOpenFieldbusDriver canOpenDriver;

	public CANBasedLwaDriver() {
		super();
	}

	@Override
	public String getDeviceType() {
		return DEVICE_TYPE;
	}

	public CanOpenFieldbusDriver getCanOpenDriver() {
		return canOpenDriver;
	}

	@ConfigurationProperty(Optional = false)
	public void setCanOpenDriver(CanOpenFieldbusDriver canOpenDriver) {
		immutableWhenInitialized();
		this.canOpenDriver = canOpenDriver;
	}
	
	@Override
	protected void validateConfigurationProperties() throws ConfigurationException {
		super.validateConfigurationProperties();

		checkNotNullAndInitialized("canOpenDriver", canOpenDriver);
	}

	@Override
	protected void collectDriverSpecificParameters(Map<String, String> parameters) {
		parameters.put("schunklwa", canOpenDriver.getDeviceName());
	}

}
