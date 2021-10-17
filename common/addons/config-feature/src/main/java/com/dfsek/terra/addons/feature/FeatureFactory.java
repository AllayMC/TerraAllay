package com.dfsek.terra.addons.feature;

import com.dfsek.tectonic.exception.LoadException;

import com.dfsek.terra.api.Platform;
import com.dfsek.terra.api.config.ConfigFactory;
import com.dfsek.terra.api.structure.feature.Feature;


public class FeatureFactory implements ConfigFactory<FeatureTemplate, Feature> {
    @Override
    public Feature build(FeatureTemplate config, Platform platform) throws LoadException {
        return new ConfiguredFeature(config.getStructures(), config.getStructureNoise(), config.getDistributor(), config.getLocator());
    }
}
