package com.dfsek.terra.api.util;

import com.dfsek.terra.api.block.BlockType;
import com.dfsek.terra.api.block.state.BlockState;

import java.io.Serial;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MaterialSet extends HashSet<BlockType> {
    @Serial
    private static final long serialVersionUID = 3056512763631017301L;

    public static MaterialSet singleton(BlockType material) {
        MaterialSet set = new MaterialSet();
        set.add(material);
        return set;
    }

    public static MaterialSet get(BlockType... materials) {
        MaterialSet set = new MaterialSet();
        set.addAll(Arrays.asList(materials));
        return set;
    }

    public static MaterialSet get(BlockState... materials) {
        MaterialSet set = new MaterialSet();
        Arrays.stream(materials).forEach(set::add);
        return set;
    }

    public static MaterialSet empty() {
        return new MaterialSet();
    }

    private void add(BlockState data) {
        add(data.getBlockType());
    }
}
