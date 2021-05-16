package com.dfsek.terra.config.loaders.config.biome;

import com.dfsek.tectonic.exception.LoadException;
import com.dfsek.tectonic.loading.ConfigLoader;
import com.dfsek.tectonic.loading.TypeLoader;
import com.dfsek.terra.api.world.biome.provider.BiomeProvider;
import com.dfsek.terra.config.loaders.config.biome.templates.provider.BiomePipelineTemplate;
import com.dfsek.terra.config.loaders.config.biome.templates.provider.ImageProviderTemplate;
import com.dfsek.terra.config.loaders.config.biome.templates.provider.SingleBiomeProviderTemplate;

import java.lang.reflect.Type;
import java.util.Map;

@SuppressWarnings("unchecked")
public class BiomeProviderBuilderLoader implements TypeLoader<BiomeProvider.BiomeProviderBuilder> {

    public BiomeProviderBuilderLoader() {
    }

    @Override
    public BiomeProvider.BiomeProviderBuilder load(Type t, Object c, ConfigLoader loader) throws LoadException { // TODO: clean this up
        Map<String, Object> map = (Map<String, Object>) c;

        return switch(loader.loadClass(BiomeProvider.Type.class, map.get("type"))) {
            case IMAGE -> loader.loadClass(ImageProviderTemplate.class, map);
            case PIPELINE -> loader.loadClass(BiomePipelineTemplate.class, map);
            case SINGLE -> loader.loadClass(SingleBiomeProviderTemplate.class, map);
        };
    }
}
