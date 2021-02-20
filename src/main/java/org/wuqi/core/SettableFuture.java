package org.wuqi.core;

public interface SettableFuture<V> extends ListenableFuture<V> {

    boolean set(V v);

    boolean setException(Throwable throwable);
}
