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
package io.actor4j.core.service.discovery;

import java.util.List;
import java.util.UUID;

import io.actor4j.core.utils.Shareable;

public class Service implements Shareable {

    public final UUID id;
    public final String name; // or for nodeName
    public final String uri;  // or for path
    public final List<String> topics;
    public final String version;
    public final String description;

    /**
     * Creates a Service
     * @param name the name of the service
     * @param uri the uri of the service
     * @param topics the list of topics of the service
     * @param version the version of the service
     * @param description  the description of the service
     */
    public Service(String name, String uri, List<String> topics, String version, String description) {
        super();
        id = UUID.randomUUID();
        this.name = name;
        this.uri = uri;
        this.topics = topics;
        this.version = version;
        this.description = description;
    }

    public boolean isPath() {
        return uri != null ? uri.indexOf("http") == -1 || uri.indexOf("https") == -1 : false;
    }
}
