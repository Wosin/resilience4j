/*
 *
 *  Copyright 2016 Robert Winkler
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package io.github.resilience4j.consumer;

import org.junit.Test;

import io.github.resilience4j.circuitbreaker.event.CircuitBreakerEvent;

import static org.assertj.core.api.Assertions.assertThat;

public class EventConsumerRegistryTest {

    @Test
    public void shouldCreateAnEventConsumer() {
        EventConsumerRegistry<CircuitBreakerEvent> registry = new DefaultEventConsumerRegistry<>();
        EventConsumer<CircuitBreakerEvent> eventEventConsumer = registry.createEventConsumer("testName", 5);

        assertThat(eventEventConsumer).isNotNull();
        assertThat(eventEventConsumer.getBufferedEvents()).hasSize(0);
    }

    @Test
    public void shouldReturnTheSameEventConsumer() {
        EventConsumerRegistry<CircuitBreakerEvent> registry = new DefaultEventConsumerRegistry<>();
        EventConsumer<CircuitBreakerEvent> eventEventConsumer1 = registry.createEventConsumer("testName", 5);
        EventConsumer<CircuitBreakerEvent> eventEventConsumer2 = registry.getEventConsumer("testName");

        assertThat(eventEventConsumer1).isEqualTo(eventEventConsumer2);
    }

    @Test
    public void shouldReturnAllEventConsumer() {
        EventConsumerRegistry<CircuitBreakerEvent> registry = new DefaultEventConsumerRegistry<>();
        registry.createEventConsumer("testName1", 5);
        registry.createEventConsumer("testName2", 2);

        assertThat(registry.getAllEventConsumer()).hasSize(2);
    }
}
