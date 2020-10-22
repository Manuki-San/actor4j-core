/*
 * Copyright (c) 2015-2018, David A. Bauer. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.actor4j.core.immutable;

import java.util.Collections;
import java.util.Map;

import io.actor4j.core.utils.Shareable;

/**
 * A {@link Shareable} immutable {@link Map}
 * @param <K>
 * @param <V> 
 */
public class ImmutableMap<K, V> implements Shareable {

    protected final Map<K, V> map;

    /**
     * Create an immutable {@link Map}
     * @param map 
     */
    public ImmutableMap(Map<K, V> map) {
        super();
        this.map = Collections.unmodifiableMap(map);
    }

    /**
     * Gets the immutable {@link Map}
     * @return immutable map
     */
    public Map<K, V> get() {
        return map;
    }
}
