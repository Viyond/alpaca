package org.wuqi.core;

import com.google.common.util.concurrent.MoreExecutors;

public class Futures {

    public static <T> DefaultListenableFuture<T> createSettableFuture(){
        return new DefaultListenableFuture<>(MoreExecutors.directExecutor());
    }
}
