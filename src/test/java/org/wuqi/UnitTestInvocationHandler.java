package org.wuqi;

import org.wuqi.core.Futures;
import org.wuqi.core.Invocation;
import org.wuqi.core.InvocationHandler;
import org.wuqi.core.ListenableFuture;
import org.wuqi.core.Result;
import org.wuqi.core.SettableFuture;

public class UnitTestInvocationHandler implements InvocationHandler {
    @Override
    public ListenableFuture<Result> invoke(Invocation invocation) throws Throwable {
        SettableFuture<Result> future = Futures.createSettableFuture();
        Result result = new Result();
        future.set(result);
        return future;
    }
}
