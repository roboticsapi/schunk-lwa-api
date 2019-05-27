/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.schunk.lwa;

import org.roboticsapi.cartesianmotion.parameter.CartesianParameters;
import org.roboticsapi.core.DeviceParameters;
import org.roboticsapi.core.InvalidParametersException;
import org.roboticsapi.multijoint.Joint;
import org.roboticsapi.multijoint.JointDriver;
import org.roboticsapi.multijoint.link.BaseLink;
import org.roboticsapi.multijoint.link.FlangeLink;
import org.roboticsapi.multijoint.link.JointLink;
import org.roboticsapi.multijoint.link.Link;
import org.roboticsapi.robot.AbstractRobotArm;
import org.roboticsapi.robot.DHRobotArm;
import org.roboticsapi.robot.RobotArmDriver;
import org.roboticsapi.robot.parameter.RobotTool;
import org.roboticsapi.robot.parameter.RobotToolParameter;
import org.roboticsapi.world.Transformation;

public class LWA46 extends AbstractRobotArm<Joint, RobotArmDriver> implements DHRobotArm {

	private static final int NUMBER_OF_JOINTS = 6;
	private static final double D0 = 0.205d, D3 = 0.305d, D5 = 0.075d, A1 = 0.35d;
	private static final double T1 = -1.5708d, T2 = -1.5708d;
	private static final double AL0 = -1.5708d, AL1 = 3.1416d, AL2 = -1.5708d, AL3 = 1.5708d, AL4 = -1.5708d;

	public LWA46() {
		super(NUMBER_OF_JOINTS);
	}

	@Override
	protected Joint createJoint(int number, String name) {
		JointDriver driver = getDriver().createJointDriver(number);

		switch (number) {
		case 0:
			return createRevoluteJoint(driver, name, Math.toRadians(-170), Math.toRadians(170), Math.toRadians(72),
					Math.toRadians(300));
		case 1:
			return createRevoluteJoint(driver, name, Math.toRadians(-170), Math.toRadians(170), Math.toRadians(72),
					Math.toRadians(300));
		case 2:
			return createRevoluteJoint(driver, name, Math.toRadians(-156.5), Math.toRadians(156.5), Math.toRadians(72),
					Math.toRadians(300));
		case 3:
			return createRevoluteJoint(driver, name, Math.toRadians(-170), Math.toRadians(170), Math.toRadians(72),
					Math.toRadians(300));
		case 4:
			return createRevoluteJoint(driver, name, Math.toRadians(-170), Math.toRadians(170), Math.toRadians(72),
					Math.toRadians(300));
		case 5:
			return createRevoluteJoint(driver, name, Math.toRadians(-170), Math.toRadians(170), Math.toRadians(72),
					Math.toRadians(300));
		default:
			return null;
		}
	}

	@Override
	protected Link createLink(int number) {
		switch (number) {
		case 0:
			return new BaseLink(getBase(), getJoint(0), new Transformation());
		case 1:
			return new JointLink(getJoint(0), getJoint(1), new Transformation(0.0, 0.0, D0, 0.0, 0.0, AL0));
		case 2:
			return new JointLink(getJoint(1), getJoint(2), new Transformation(0, -A1, 0.0, T1, 0.0, AL1));
		case 3:
			return new JointLink(getJoint(2), getJoint(3), new Transformation(0.0, 0.0, 0.0, T2, 0.0, AL2));
		case 4:
			return new JointLink(getJoint(3), getJoint(4), new Transformation(0.0, 0.0, D3, 0.0, 0.0, AL3));
		case 5:
			return new JointLink(getJoint(4), getJoint(5), new Transformation(0.0, 0.0, 0.0, 0.0, 0.0, AL4));
		case 6:
			return new FlangeLink(getJoint(5), getFlange(), new Transformation(0.0, 0.0, D5, 0.0, 0.0, 0.0));
		}
		return null;
	}

	public final double[] getA() {
		double[] a = new double[NUMBER_OF_JOINTS];
		a[1] = A1;
		return a;
	}

	public final double[] getAlpha() {
		double[] alpha = new double[NUMBER_OF_JOINTS];
		alpha[0] = AL0;
		alpha[1] = AL1;
		alpha[2] = AL2;
		alpha[3] = AL3;
		alpha[4] = AL4;
		return alpha;
	}

	public final double[] getD() {
		double[] d = new double[NUMBER_OF_JOINTS];
		d[0] = D0;
		d[3] = D3;
		d[5] = D5;
		return d;
	}

	public final double[] getTheta() {
		double[] theta = new double[NUMBER_OF_JOINTS];
		theta[1] = T1;
		theta[2] = T2;
		return theta;
	}

	@Override
	public void validateParameters(DeviceParameters parameters) throws InvalidParametersException {
	}

	@Override
	protected void defineMaximumParameters() throws InvalidParametersException {
		addMaximumParameters(new CartesianParameters(1, 2, Math.PI / 3f, Math.PI * 2 / 3f));
		addMaximumParameters(getJointDeviceParameters(0, 0));

		// TODO: some values are guessed
		addMaximumParameters(new RobotToolParameter(new RobotTool(6, getFlange(), 2.5, 2.5, 2.5)));
	}

}
