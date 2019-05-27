/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.schunk.lwa.javarcc.devices;

import org.roboticsapi.runtime.core.javarcc.devices.JDevice;
import org.roboticsapi.runtime.multijoint.javarcc.devices.JMockMultijointDevice;
import org.roboticsapi.runtime.multijoint.javarcc.interfaces.JMultijointInterface;
import org.roboticsapi.runtime.robot.javarcc.interfaces.JArmKinematicsInterface;
import org.roboticsapi.runtime.world.types.RPIFrame;

public class JMockLWA extends JMockMultijointDevice implements JDevice, JArmKinematicsInterface, JMultijointInterface {
	private LWAKin kin;

	public JMockLWA(double[] d, double[] theta, double[] a, double[] alpha, double[] min, double[] max) {
		super(6, min, max, new double[] { 0, 0, 0, 0, 0, 0 });
		kin = new LWAKin(d, theta, a, alpha, min, max);
	}

	@Override
	public RPIFrame kin(double[] joints, RPIFrame ret) {
		return kin.kin(joints, ret);
	}

	@Override
	public double[] invKin(double[] hintJoints, RPIFrame frame) {
		return kin.invKin(frame, hintJoints);
	}

}
