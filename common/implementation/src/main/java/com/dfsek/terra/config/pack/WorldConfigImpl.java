package com.dfsek.terra.config.pack;

import com.dfsek.terra.api.TerraPlugin;
import com.dfsek.terra.api.config.WorldConfig;
import com.dfsek.terra.api.registry.OpenRegistry;
import com.dfsek.terra.api.registry.Registry;
import com.dfsek.terra.api.world.TerraWorld;
import com.dfsek.terra.api.world.biome.TerraBiome;
import com.dfsek.terra.api.world.biome.generation.BiomeProvider;
import com.dfsek.terra.api.world.generator.SamplerCache;
import com.dfsek.terra.config.builder.BiomeBuilder;
import com.dfsek.terra.registry.LockedRegistryImpl;
import com.dfsek.terra.registry.OpenRegistryImpl;
import com.dfsek.terra.world.generation.math.SamplerCacheImpl;
import com.dfsek.terra.world.population.items.TerraStructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorldConfigImpl implements WorldConfig {
    private final SamplerCache samplerCache;

    private final BiomeProvider provider;

    private final TerraWorld world;
    private final ConfigPackImpl pack;

    private final Map<Class<?>, Registry<?>> registryMap = new HashMap<>();

    public WorldConfigImpl(TerraWorld world, ConfigPackImpl pack, TerraPlugin main) {
        this.world = world;
        this.pack = pack;
        this.samplerCache = new SamplerCacheImpl(main, world);

        pack.getRegistryMap().forEach((clazz, pair) -> registryMap.put(clazz, new LockedRegistryImpl<>(pair.getLeft())));

        OpenRegistry<TerraBiome> biomeOpenRegistry = new OpenRegistryImpl<>();
        pack.getRegistry(BiomeBuilder.class).forEach((id, biome) -> biomeOpenRegistry.add(id, biome.apply(world.getWorld().getSeed())));
        registryMap.put(TerraBiome.class, new LockedRegistryImpl<>(biomeOpenRegistry));

        this.provider = pack.getBiomeProviderBuilder().build(world.getWorld().getSeed());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Registry<T> getRegistry(Class<T> clazz) {
        return (LockedRegistryImpl<T>) registryMap.get(clazz);
    }

    @Override
    public TerraWorld getWorld() {
        return world;
    }

    @Override
    public SamplerCache getSamplerCache() {
        return samplerCache;
    }

    @Override
    public BiomeProvider getProvider() {
        return provider;
    }

    @Override
    public int elevationBlend() {
        return pack.getTemplate().getElevationBlend();
    }

    @Override
    public boolean disableTrees() {
        return pack.getTemplate().disableTrees();
    }

    @Override
    public boolean disableCarving() {
        return pack.getTemplate().disableCarvers();
    }

    @Override
    public boolean disableOres() {
        return pack.getTemplate().disableOres();
    }

    @Override
    public boolean disableFlora() {
        return pack.getTemplate().disableFlora();
    }

    @Override
    public boolean disableStructures() {
        return pack.getTemplate().disableStructures();
    }

    @Override
    public String getID() {
        return pack.getID();
    }

    @Override
    public String getAuthor() {
        return pack.getAuthor();
    }

    @Override
    public String getVersion() {
        return pack.getVersion();
    }

    public Set<TerraStructure> getStructures() {
        return new HashSet<>(getRegistry(TerraStructure.class).entries());
    }

    public ConfigPackTemplate getTemplate() {
        return pack.getTemplate();
    }
}
