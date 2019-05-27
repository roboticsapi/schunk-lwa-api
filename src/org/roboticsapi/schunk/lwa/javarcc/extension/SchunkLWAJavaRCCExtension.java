/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.schunk.lwa.javarcc.extension;

import java.util.Map;

import org.roboticsapi.runtime.core.types.RPIdoubleArray;
import org.roboticsapi.runtime.javarcc.extension.JavaRCCExtension;
import org.roboticsapi.runtime.javarcc.extension.JavaRCCExtensionPoint;
import org.roboticsapi.schunk.lwa.javarcc.devices.JMockLWA;

public class SchunkLWAJavaRCCExtension extends JavaRCCExtension {

	@Override
	public void extend(JavaRCCExtensionPoint ep) {
		ep.registerDevice("schunk_lwa_sim", (p, d) -> new JMockLWA(param(p, "dh_d"), param(p, "dh_t"),
				param(p, "dh_a"), param(p, "dh_al"), param(p, "min_joint"), param(p, "max_joint")));
	}

	private double[] param(Map<String, String> p, String name) {
		RPIdoubleArray val = new RPIdoubleArray(p.get(name).replace('{', '[').replace('}', ']'));
		double[] ret = new double[val.getSize()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = val.get(i).get();
		return ret;
	}
}
