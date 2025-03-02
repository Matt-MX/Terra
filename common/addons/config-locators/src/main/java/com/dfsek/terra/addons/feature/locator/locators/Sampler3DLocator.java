/*
 * Copyright (c) 2020-2025 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.feature.locator.locators;

import com.dfsek.seismic.type.sampler.Sampler;
import com.dfsek.terra.api.structure.feature.BinaryColumn;
import com.dfsek.terra.api.structure.feature.Locator;
import com.dfsek.terra.api.world.chunk.generation.util.Column;


public class Sampler3DLocator implements Locator {
    private final Sampler sampler;

    public Sampler3DLocator(Sampler sampler) {
        this.sampler = sampler;
    }

    @Override
    public BinaryColumn getSuitableCoordinates(Column<?> column) {
        long seed = column.getWorld().getSeed();
        int x = column.getX();
        int z = column.getZ();
        return column.newBinaryColumn(y -> sampler.getSample(seed, x, y, z) > 0);
    }
}
