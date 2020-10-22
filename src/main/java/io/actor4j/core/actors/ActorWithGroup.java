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
package io.actor4j.core.actors;

import java.util.UUID;
import io.actor4j.core.utils.ActorGroup;

/**
 * {@link Actor} in a {@link ActorGroup group}
 */
public abstract class ActorWithGroup extends Actor implements ActorGroupMember {

    /**
     * ID of the group
     */
    protected UUID groupId;

    /**
     * Creates an {@link Actor} in a {@link ActorGroup group}
     *
     * @param group an actor group
     */
    public ActorWithGroup(ActorGroup group) {
        this(null, group);
    }

    /**
     * Creates a named {@link Actor} in a {@link ActorGroup group}
     *
     * @param name name of the actor
     * @param group an actor group
     */
    public ActorWithGroup(String name, ActorGroup group) {
        super(name);

        groupId = group.getId();
    }

    @Override
    public UUID getGroupId() {
        return groupId;
    }
}