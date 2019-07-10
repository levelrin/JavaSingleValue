/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaSingleValue/blob/master/LICENSE
 */

package com.levelrin.javasinglevalue;

import java.util.function.Supplier;

/**
 * It allows you to execute the {@link Supplier} to produce the content.
 * The content will be cached.
 * It's not thread-safe.
 * It's not null-safe.
 *
 * @param <T> The type of content.
 */
public final class CachedValue<T> implements Supplier<T> {

    /**
     * The value will be determined by {@link CachedValue#supplier}.
     * The value will be cached.
     */
    private T content;

    /**
     * This flag is for caching.
     */
    private boolean cached;

    /**
     * It will determine the value of {@link CachedValue#content}.
     * It will be executed only once.
     */
    private final Supplier<T> supplier;

    /**
     * Primary constructor.
     * @param supplier Same as {@link CachedValue#supplier}.
     */
    public CachedValue(final Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (!this.cached) {
            this.content = this.supplier.get();
            this.cached = true;
        }

        return this.content;
    }

}
