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
import java.util.List;

import io.actor4j.core.utils.Shareable;

public class ImmutableList<T> implements Shareable {

    /**
     * Immutable {@link List}
     */
    protected final List<T> list;

    /**
     * Creates an immutable list
     * @param list 
     */
    public ImmutableList(List<T> list) {
        super();
        this.list = Collections.unmodifiableList(list);
    }

    /**
     * Get the list
     * @return the list
     */
    public List<T> get() {
        return list;
    }
}
