/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaSingleValue/blob/master/LICENSE
 */

package com.levelrin.javasinglevalue;

import java.util.concurrent.atomic.AtomicInteger;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class CachedValueTest {

    @Test
    public void contentShouldBeCached() {
        final AtomicInteger counter = new AtomicInteger(0);
        final CachedValue<String> fruit = new CachedValue<>(() -> {
            counter.incrementAndGet();
            return "Apple";
        });
        fruit.get();
        fruit.get();

        MatcherAssert.assertThat(
            counter.get(),
            CoreMatchers.equalTo(1)
        );
    }

    @Test
    public void contentComesFromSupplier() {
        final CachedValue<String> fruit = new CachedValue<>(() -> "Banana");

        MatcherAssert.assertThat(
            fruit.get(),
            CoreMatchers.equalTo("Banana")
        );
    }

}
