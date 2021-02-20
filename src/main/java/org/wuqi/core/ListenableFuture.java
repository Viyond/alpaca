package org.wuqi.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public interface ListenableFuture<V> extends Future<V> {

    void addListener(Runnable listener);

    void addListener(Runnable listener, Executor executor);
}
