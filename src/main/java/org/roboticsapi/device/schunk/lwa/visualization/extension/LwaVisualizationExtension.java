/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2013-2019 ISSE, University of Augsburg 
 */

package org.roboticsapi.device.schunk.lwa.visualization.extension;

import org.roboticsapi.core.RoboticsObject;
import org.roboticsapi.device.schunk.lwa.Lwa46;
import org.roboticsapi.device.schunk.lwa.visualization.Lwa46Models;
import org.roboticsapi.extension.RoboticsObjectListener;
import org.roboticsapi.facet.visualization.property.VisualizationProperty;
import org.roboticsapi.framework.multijoint.link.Link;

public class LwaVisualizationExtension implements RoboticsObjectListener {

	@Override
	public void onAvailable(RoboticsObject object) {
		if (object instanceof Lwa46) {
			Lwa46 lwa = (Lwa46) object;

			// ...
			for (int i = 0; i < 7; i++) {
				Link link = lwa.getLink(i);

				if (link == null) {
					continue;
				}
				link.addProperty(new VisualizationProperty(Lwa46Models.LINK_MODELS[i]));
			}

		}
	}

	@Override
	public void onUnavailable(RoboticsObject object) {
		// TODO Auto-generated method stub

	}

}
