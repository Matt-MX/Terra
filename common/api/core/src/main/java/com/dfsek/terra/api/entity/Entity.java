/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra API is licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in the common/api directory.
 */

package com.dfsek.terra.api.entity;

import com.dfsek.terra.api.util.vector.Vector3;
import com.dfsek.terra.api.world.World;


public interface Entity extends CommandSender {
    Vector3 position();
    
    void position(Vector3 position);
    
    void world(World world);
    
    World world();
}
