/*
 * Copyright (c) 2020-2025 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.feature.locator.config;

import com.dfsek.tectonic.api.config.template.annotations.Value;
import com.dfsek.tectonic.api.config.template.object.ObjectTemplate;

import com.dfsek.terra.addons.feature.locator.locators.PatternLocator;
import com.dfsek.terra.addons.feature.locator.patterns.Pattern;
import com.dfsek.terra.api.config.meta.Meta;
import com.dfsek.terra.api.structure.feature.Locator;
import com.dfsek.terra.api.util.Range;


public class PatternLocatorTemplate implements ObjectTemplate<Locator> {
    @Value("range")
    private @Meta Range range;

    @Value("pattern")
    private @Meta Pattern pattern;

    @Override
    public Locator get() {
        return new PatternLocator(pattern, range);
    }
}
