/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2013-2019 ISSE, University of Augsburg 
 */

package org.roboticsapi.device.schunk.lwa.visualization;

import org.roboticsapi.facet.visualization.property.Visualisation;

public class Lwa46Models {

	public static Visualisation[] LINK_MODELS = new Visualisation[] {
			new Visualisation("COLLADA", Lwa46Models.class.getResource("LWA46_Link0.dae")),
			new Visualisation("COLLADA", Lwa46Models.class.getResource("LWA46_Link1.dae")),
			new Visualisation("COLLADA", Lwa46Models.class.getResource("LWA46_Link2.dae")),
			new Visualisation("COLLADA", Lwa46Models.class.getResource("LWA46_Link3.dae")),
			new Visualisation("COLLADA", Lwa46Models.class.getResource("LWA46_Link4.dae")),
			new Visualisation("COLLADA", Lwa46Models.class.getResource("LWA46_Link5.dae")),
			new Visualisation("COLLADA", Lwa46Models.class.getResource("LWA46_Link6.dae")), };
}
