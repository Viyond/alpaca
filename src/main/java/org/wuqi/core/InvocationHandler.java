package org.wuqi.core;

public interface InvocationHandler {

    ListenableFuture<Result> invoke(Invocation invocation) throws Throwable;
}
