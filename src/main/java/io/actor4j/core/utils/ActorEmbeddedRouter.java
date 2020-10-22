/*
 * Copyright (c) 2015-2017, David A. Bauer. All rights reserved.
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
package io.actor4j.core.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.actor4j.core.actors.EmbeddedActor;

public class ActorEmbeddedRouter extends HashMap<UUID, EmbeddedActor> {

    protected static final long serialVersionUID = 1L;

    /**
     * Creates an empty ActorEmbeddedRouter with the default initial capacity (16) and the default load factor (0.75)
     */
    public ActorEmbeddedRouter() {
        super();
    }

    /**
     * Creates an empty ActorEmbeddedRouter with the specified initial capacity and load factor
     * @param initialCapacity the initial capacity
     * @param loadFactor the load factor
     */
    public ActorEmbeddedRouter(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Creates an empty ActorEmbeddedRouter with the specified initial capacity and the default load factor (0.75)
     * @param initialCapacity the initial capacity
     */
    public ActorEmbeddedRouter(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates an ActorEmbeddedRouter with the same mappings as the specified Map.
     * The ActorEmbeddedRouter is created with the default load factor (0.75) and 
     * an initial capacity sufficient to hold the mappings in the specified {@link Map}
     * @param m 
     */
    public ActorEmbeddedRouter(Map<? extends UUID, ? extends EmbeddedActor> m) {
        super(m);
    }
}
