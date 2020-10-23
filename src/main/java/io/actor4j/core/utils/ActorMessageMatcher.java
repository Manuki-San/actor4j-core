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

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

import io.actor4j.core.messages.ActorMessage;

/**
 * Pattern matcher on {@link ActorMessage} The message can be checked to match a
 * tag, source or class of the payload
 */
public class ActorMessageMatcher {

    protected static class MatchTuple {

        public Predicate<ActorMessage<?>> predicate;
        public Consumer<ActorMessage<?>> action;
    }

    protected List<MatchTuple> matches;
    protected List<MatchTuple> matchesElse;
    protected List<MatchTuple> matchesAny;

    /**
     * Creates an ActorMessageMatcher
     */
    public ActorMessageMatcher() {
        matches = new LinkedList<>();
        matchesElse = new LinkedList<>();
        matchesAny = new LinkedList<>();
    }

    /**
     * Creates an ActorMessageMatcher matching one source
     *
     * @param source the source to match
     * @param action the action to perform
     * @return the message matcher
     */
    public ActorMessageMatcher match(final UUID source, Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.predicate = new Predicate<ActorMessage<?>>() {
            @Override
            public boolean test(ActorMessage<?> message) {
                return message.source != null ? message.source.equals(source) : false;
            }
        };
        tuple.action = action;
        matches.add(tuple);

        return this;
    }

    /**
     * Creates an ActorMessageMatcher matching one source from a list
     *
     * @param sources a list of sources
     * @param action the action to perform
     * @return the message matcher
     */
    public ActorMessageMatcher match(final UUID[] sources, Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.predicate = new Predicate<ActorMessage<?>>() {
            @Override
            public boolean test(ActorMessage<?> message) {
                boolean result = false;
                if (message.source != null) {
                    for (UUID source : sources) {
                        if (message.source.equals(source)) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }
        };
        tuple.action = action;
        matches.add(tuple);

        return this;
    }

    /**
     * Creates an ActorMessageMatcher matching one tag
     *
     * @param tag the tag to match
     * @param action the action to perform
     * @return the message matcher
     */
    public ActorMessageMatcher match(final int tag, Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.predicate = new Predicate<ActorMessage<?>>() {
            @Override
            public boolean test(ActorMessage<?> message) {
                return message.tag == tag;
            }
        };
        tuple.action = action;
        matches.add(tuple);

        return this;
    }

    /**
     * Creates an ActorMessageMatcher matching one tag from a list
     *
     * @param tags a list of tags
     * @param action the action to perform
     * @return the message matcher
     */
    public ActorMessageMatcher match(final int[] tags, Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.predicate = new Predicate<ActorMessage<?>>() {
            @Override
            public boolean test(ActorMessage<?> message) {
                boolean result = false;
                for (int tag : tags) {
                    if (message.tag == tag) {
                        result = true;
                        break;
                    }
                }
                return result;
            }
        };
        tuple.action = action;
        matches.add(tuple);

        return this;
    }

    /**
     * Creates an ActorMessageMatcher matching one source and one tag
     *
     * @param source the source to match
     * @param tag the tag to match
     * @param action the action to perform
     * @return the message matcher
     */
    public ActorMessageMatcher match(final UUID source, final int tag, Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.predicate = new Predicate<ActorMessage<?>>() {
            @Override
            public boolean test(ActorMessage<?> message) {
                // TODO : check if the tag is not null ?
                return message.source != null ? message.source.equals(source) && message.tag == tag : false;
            }
        };
        tuple.action = action;
        matches.add(tuple);

        return this;
    }

    /**
     * Creates an ActorMessageMatcher matching one source from a list and one
     * tag
     *
     * @param sources a list of sources
     * @param tag the tag to match
     * @param action the action to perform
     * @return the message matcher
     */
    public ActorMessageMatcher match(final UUID[] sources, final int tag, Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.predicate = new Predicate<ActorMessage<?>>() {
            @Override
            public boolean test(ActorMessage<?> message) {
                boolean result = false;
                if (message.source != null && message.tag == tag) {
                    for (UUID source : sources) {
                        if (message.source.equals(source)) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }
        };
        tuple.action = action;
        matches.add(tuple);

        return this;
    }

    /**
     * Creates an ActorMessageMatcher matching one source and one tag from a
     * list
     *
     * @param source the source
     * @param tags a list of tags
     * @param action the action to perform
     * @return the message matcher
     */
    public ActorMessageMatcher match(final UUID source, final int[] tags, Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.predicate = new Predicate<ActorMessage<?>>() {
            @Override
            public boolean test(ActorMessage<?> message) {
                boolean result = false;
                if (message.source != null && message.source.equals(source)) {
                    for (int tag : tags) {
                        if (message.tag == tag) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }
        };
        tuple.action = action;
        matches.add(tuple);

        return this;
    }

    /**
     * Creates an ActorMessageMatcher matching one source from a list and one
     * tag from a list
     *
     * @param sources the list of sources
     * @param tags the list of tags
     * @param action the action to perform
     * @return the message matcher
     */
    public ActorMessageMatcher match(final UUID[] sources, final int[] tags, Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.predicate = new Predicate<ActorMessage<?>>() {
            @Override
            public boolean test(ActorMessage<?> message) {
                boolean result = false;
                if (message.source != null) {
                    for (UUID source : sources) {
                        if (message.source.equals(source)) {
                            result = true;
                            break;
                        }
                    }
                }
                if (result) {
                    result = false;
                    for (int tag : tags) {
                        if (message.tag == tag) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }
        };
        tuple.action = action;
        matches.add(tuple);

        return this;
    }

    /**
     * Creates an ActorMessageMatcher matching the class of the payload
     *
     * @param clazz the class of the message payload
     * @param action the action to perform
     * @return the message matcher
     */
    public ActorMessageMatcher match(final Class<?> clazz, Consumer<ActorMessage<?>> action) {
        return match(clazz, null, action);
    }

    /**
     * Creates an ActorMessageMatcher matching the class of the payload and a
     * {@link Predicate}
     *
     * @param clazz the class of the payload
     * @param predicate the predicate to apply (in addition to the matching
     * class)
     * @param action the action to perform
     * @return the message mapper
     */
    public ActorMessageMatcher match(final Class<?> clazz, Predicate<ActorMessage<?>> predicate, Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.predicate = new Predicate<ActorMessage<?>>() {
            @Override
            public boolean test(ActorMessage<?> message) {
                boolean result = false;
                if (message.value != null) {
                    result = message.value.getClass().equals(clazz);
                    if (predicate != null) {
                        result = result && predicate.test(message);
                    }
                }

                return result;
            }
        };
        tuple.action = action;
        matches.add(tuple);

        return this;
    }

    /**
     * Creates an ActorMessageMatcher matching a {@link Predicate}
     *
     * @param predicate the predicate to use
     * @param action the action to perform
     * @return the message matcher
     */
    public ActorMessageMatcher match(Predicate<ActorMessage<?>> predicate, Consumer<ActorMessage<?>> action) {
        checkPredicate(predicate);
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.predicate = predicate;
        tuple.action = action;
        matches.add(tuple);

        return this;
    }

    public ActorMessageMatcher matchElse(Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.action = action;
        matchesElse.add(tuple);

        return this;
    }

    public ActorMessageMatcher matchAny(Consumer<ActorMessage<?>> action) {
        checkAction(action);

        MatchTuple tuple = new MatchTuple();
        tuple.action = action;
        matchesAny.add(tuple);

        return this;
    }

    public <T> boolean apply(ActorMessage<T> message) {
        boolean result = false;

        for (MatchTuple tuple : matches) {
            if (tuple.predicate.test(message)) {
                tuple.action.accept(message);
                // TODO: TBC: the method returns true after a single match, implying a logical OR ?
                result = true;
            }
        }
        if (!result) {
            for (MatchTuple tuple : matchesElse) {
                tuple.action.accept(message);
                result = true;
            }
        }
        for (MatchTuple tuple : matchesAny) {
            tuple.action.accept(message);
            result = true;
        }

        return result;
    }

    /**
     * Check if the predicate is null
     *
     * @param predicate
     */
    protected void checkPredicate(Predicate<ActorMessage<?>> predicate) {
        if (predicate == null) {
            throw new NullPointerException("predicate is null");
        }
    }

    /**
     * Check if the action is null
     *
     * @param action
     */
    protected void checkAction(Consumer<ActorMessage<?>> action) {
        if (action == null) {
            throw new NullPointerException("action is null");
        }
    }
}
