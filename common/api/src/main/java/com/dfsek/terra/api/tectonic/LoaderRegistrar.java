/*
 * Copyright (c) 2020-2024 Polyhedral Development
 *
 * The Terra API is licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in the common/api directory.
 */

package com.dfsek.terra.api.tectonic;

import com.dfsek.tectonic.api.TypeRegistry;


public interface LoaderRegistrar {
    void register(TypeRegistry registry);
}
