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
package io.actor4j.core;

import java.util.ArrayDeque;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.jctools.queues.MpscArrayQueue;
import org.jctools.queues.MpscLinkedQueue;

/**
 * A BoundedActorTread is a bounded {@link DefaultActorThread}
 */
public class BoundedActorThread extends DefaultActorThread {

    public BoundedActorThread(ThreadGroup group, String name, ActorSystemImpl system) {
        super(group, name, system);
    }

    @Override
    public void configQueues() {
        /* unbounded */        
        directiveQueue = new MpscLinkedQueue<>();
        /* unbounded */
        priorityQueue = new PriorityBlockingQueue<>(system.getQueueSize());

        /* bounded */
        serverQueueL2 = new MpscArrayQueue<>(system.getQueueSize());
        /* unbounded */
        serverQueueL1 = new ArrayDeque<>(system.getBufferQueueSize());

        /* bounded */
        outerQueueL2 = new MpscArrayQueue<>(system.getQueueSize());
        /* unbounded */
        outerQueueL1 = new ArrayDeque<>(system.getBufferQueueSize());

        /* bounded */
        innerQueue = new CircularFifoQueue<>(system.getQueueSize());
    }
}
