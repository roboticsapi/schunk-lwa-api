/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.schunk.lwa.collada.extension;

import java.net.URL;

import org.roboticsapi.core.RoboticsObject;
import org.roboticsapi.extension.RoboticsObjectListener;
import org.roboticsapi.multijoint.link.Link;
import org.roboticsapi.schunk.lwa.LWA46;
import org.roboticsapi.visualization.property.Visualisation;
import org.roboticsapi.visualization.property.VisualizationProperty;

public class LWA46ColladaExtension implements RoboticsObjectListener {

	private static final Visualisation[] LINK_MODELS = new Visualisation[] {
			new Visualisation("COLLADA", getResource("LWA46_Link0.dae")),
			new Visualisation("COLLADA", getResource("LWA46_Link1.dae")),
			new Visualisation("COLLADA", getResource("LWA46_Link2.dae")),
			new Visualisation("COLLADA", getResource("LWA46_Link3.dae")),
			new Visualisation("COLLADA", getResource("LWA46_Link4.dae")),
			new Visualisation("COLLADA", getResource("LWA46_Link5.dae")),
			new Visualisation("COLLADA", getResource("LWA46_Link6.dae")), };

	@Override
	public void onAvailable(RoboticsObject object) {
		if (object instanceof LWA46) {
			LWA46 lwa = (LWA46) object;

			// ...
			for (int i = 0; i < 7; i++) {
				Link link = lwa.getLink(i);

				if (link == null) {
					continue;
				}
				link.addProperty(new VisualizationProperty(LINK_MODELS[i]));
			}

		}
	}

	@Override
	public void onUnavailable(RoboticsObject object) {
		// TODO Auto-generated method stub

	}

	private static final URL getResource(String resource) {
		return LWA46ColladaExtension.class.getResource("/models/" + resource);
	}

}
