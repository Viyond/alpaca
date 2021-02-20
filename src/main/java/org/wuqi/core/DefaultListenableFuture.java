package org.wuqi.core;

import java.util.concurrent.Executor;

import com.google.common.util.concurrent.AbstractFuture;

public class DefaultListenableFuture<V> extends AbstractFuture<V> implements SettableFuture<V> {
    private final Executor executor;

    public DefaultListenableFuture(Executor executor){
        this.executor = executor;
    }

    @Override
    public boolean set(V v) {
        boolean result = super.set(v);
        if (!result){
            throw new IllegalStateException("failed to set result");
        }
        return result;
    }

    @Override
    public boolean setException(Throwable throwable) {
        return super.setException(throwable);
    }

    @Override
    public void addListener(Runnable listener) {
        super.addListener(listener, executor);
    }
}
