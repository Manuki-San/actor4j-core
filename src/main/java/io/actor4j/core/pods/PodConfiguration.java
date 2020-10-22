/*
 * Copyright (c) 2015-2020, David A. Bauer. All rights reserved.
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
package io.actor4j.core.pods;

public class PodConfiguration {

    protected final String domain;
    protected final String className;
    protected final int shardCount;
    protected final int minReplica;
    protected final int maxReplica;

    public PodConfiguration() {
        super();

        domain = null;
        className = null;
        shardCount = 1;
        minReplica = 1;
        maxReplica = 1;
    }

    public PodConfiguration(String domain, String className, int minReplica, int maxReplica) {
        super();
        this.domain = domain;
        this.className = className;
        this.shardCount = 1;
        this.minReplica = minReplica;
        this.maxReplica = maxReplica;
    }

    public PodConfiguration(String domain, String className, int shardCount, int minReplica, int maxReplica) {
        super();
        this.domain = domain;
        this.className = className;
        this.shardCount = shardCount;
        this.minReplica = minReplica;
        this.maxReplica = maxReplica;
    }

    public String getDomain() {
        return domain;
    }

    public String getClassName() {
        return className;
    }

    public int getShardCount() {
        return shardCount;
    }

    public int getMinReplica() {
        return minReplica;
    }

    public int getMaxReplica() {
        return maxReplica;
    }
}
