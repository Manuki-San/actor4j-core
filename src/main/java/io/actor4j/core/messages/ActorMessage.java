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
package io.actor4j.core.messages;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.actor4j.core.utils.Copyable;
import io.actor4j.core.utils.Shareable;

/**
 * Message that can be exchanged between {@link Actor}
 *
 * @param <T>
 */
public class ActorMessage<T> implements Copyable<ActorMessage<T>>, Comparable<ActorMessage<T>> {

    private static Set<Class<?>> SUPPORTED_TYPES;

    /**
     * Payload of the message
     */
    public T value;
    /**
     * Tag helping differentiation between messages
     */
    public int tag;
    /**
     * Source / Sender of the message
     */
    public UUID source;
    /**
     * Destination of the message
     */
    public UUID dest;
    /**
     * Interaction ID for messages related to an interaction protocol
     * (conversation)
     */
    public UUID interaction;
    /**
     * Interaction protocol
     */
    public String protocol;
    /**
     * Ontology, the knowledge model for the domain of the message
     */
    public String ontology;

    /**
     * Create an ActorMessage
     *
     * @param value payload of the message
     * @param tag tag helping differentiation between messages
     * @param source sender of the message
     * @param dest destination of the message
     * @param interaction interaction ID
     * @param protocol interaction protocol
     * @param ontology Ontology
     */
    public ActorMessage(T value, int tag, UUID source, UUID dest, UUID interaction, String protocol, String ontology) {
        this.value = value;
        this.tag = tag;
        this.source = source;
        this.dest = dest;
        this.interaction = interaction;
        this.protocol = protocol;
        this.ontology = ontology;
    }

    /**
     * Creates an ActorMessage
     *
     * @param value payload of the message
     * @param tag tag helping differentiation between messages
     * @param source sender of the message
     * @param dest destination of the message
     */
    public ActorMessage(T value, int tag, UUID source, UUID dest) {
        this(value, tag, source, dest, null, null, null);
    }

    /**
     * Creates an ActorMessage
     *
     * @param value payload of the message
     * @param tag tag helping differentiation between messages
     * @param source sender of the message
     * @param dest destination of the message
     */
    public ActorMessage(T value, Enum<?> tag, UUID source, UUID dest) {
        this(value, tag.ordinal(), source, dest);
    }

    /**
     * Gets the message payload
     *
     * @return payload of the message
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the message payload
     *
     * @param value message payload
     */
    public void setValue(T value) {
        this.value = value;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public UUID getSource() {
        return source;
    }

    public void setSource(UUID source) {
        this.source = source;
    }

    /**
     * Gets the message destination
     *
     * @return the destination
     */
    public UUID getDest() {
        return dest;
    }

    /**
     * Sets the message destination
     *
     * @param dest the destination
     */
    public void setDest(UUID dest) {
        this.dest = dest;
    }

    public UUID getInteraction() {
        return interaction;
    }

    public void setInteraction(UUID interaction) {
        this.interaction = interaction;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getOntology() {
        return ontology;
    }

    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    /**
     * Gets the message payload as a {@link Boolean}
     *
     * @return message payload as a {@link Boolean}
     */
    public boolean valueAsBoolean() {
        return (Boolean) value;
    }

    /**
     * Gets the message payload as a {@link Integer}
     *
     * @return message payload as a {@link Integer}
     */
    public int valueAsInt() {
        return (Integer) value;
    }

    /**
     * Gets the message payload as a {@link Long}
     *
     * @return message payload as a {@link Long}
     */
    public long valueAsLong() {
        return (Long) value;
    }

    /**
     * Gets the message payload as a {@link Double}
     *
     * @return message payload as a {@link Double}
     */
    public double valueAsDouble() {
        return (Double) value;
    }

    /**
     * Gets the message payload as a {@link String}
     *
     * @return message payload as a {@link String}
     */
    public String valueAsString() {
        return (String) value;
    }

    /**
     * Gets the message payload as a {@link UUID}
     *
     * @return message payload as a {@link UUID}
     */
    public UUID valueAsUUID() {
        return (UUID) value;
    }

    /**
     * Gets the message payload, serialized as a JSON string as an {@link Object}
     *
     * @param valueType
     * @return message payload as an {@link Object}
     */
    public T readValue(Class<T> valueType) {
        T result = null;

        if (value instanceof String) {
            try {
                result = new ObjectMapper().readValue((String) value, valueType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Gets the message payload, serialized as a JSON string as an {@link Object}
     *
     * @param valueTypeRef
     * @return message payload as an {@link Object}
     */
    public T readValue(TypeReference<T> valueTypeRef) {
        T result = null;

        if (value instanceof String) {
            try {
                result = new ObjectMapper().readValue((String) value, valueTypeRef);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Gets a weak copy of the message
     *
     * @return an ActorMessage
     */
    protected ActorMessage<T> weakCopy() {
        return new ActorMessage<T>(value, tag, source, dest, interaction, protocol, ontology);
    }

    /**
     * Gets a copy of the message
     *
     * @return an ActorMessage
     */
    @SuppressWarnings("unchecked")
    public ActorMessage<T> copy() {
        if (value != null) {
            if (isSupportedType(value.getClass()) || value instanceof Shareable) {
                return new ActorMessage<T>(value, tag, source, dest, interaction, protocol, ontology);
            } else if (value instanceof Copyable) {
                return new ActorMessage<T>(((Copyable<T>) value).copy(), tag, source, dest, interaction, protocol, ontology);
            } else if (value instanceof Exception) {
                return new ActorMessage<T>(value, tag, source, dest, interaction, protocol, ontology);
            } else {
                throw new IllegalArgumentException(value.getClass().getName());
            }
        } else {
            return new ActorMessage<T>(null, tag, source, dest, interaction, protocol, ontology);
        }
    }

    @Override
    public int compareTo(ActorMessage<T> message) {
        return Integer.compare(tag, message.tag); // tag - message.tag
    }

    @Override
    public String toString() {
        return "ActorMessage [value=" + value
                + ", tag=" + tag
                + ", source=" + source
                + ", dest=" + dest
                + ", interaction=" + interaction
                + ", protocol=" + protocol
                + ", ontology=" + ontology + "]";
    }

    public static boolean isSupportedType(Class<?> type) {
        return SUPPORTED_TYPES.contains(type);
    }

    static {
        SUPPORTED_TYPES = new HashSet<Class<?>>();
        SUPPORTED_TYPES.add(Byte.class);
        SUPPORTED_TYPES.add(Short.class);
        SUPPORTED_TYPES.add(Integer.class);
        SUPPORTED_TYPES.add(Long.class);
        SUPPORTED_TYPES.add(Float.class);
        SUPPORTED_TYPES.add(Double.class);
        SUPPORTED_TYPES.add(Character.class);
        SUPPORTED_TYPES.add(String.class);
        SUPPORTED_TYPES.add(Boolean.class);
        SUPPORTED_TYPES.add(Void.class);

        // IMMUTABLE
        SUPPORTED_TYPES.add(Object.class);
        SUPPORTED_TYPES.add(UUID.class);
    }
}
