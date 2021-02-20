package org.wuqi.invoke.filter;

import org.wuqi.core.Invocation;
import org.wuqi.core.InvocationHandler;
import org.wuqi.core.ListenableFuture;
import org.wuqi.core.Result;

public interface Filter {

    ListenableFuture<Result> invoke(InvocationHandler nextHandler, Invocation invocation) throws Throwable;

    void onResponse(Invocation invocation, Result result);
}
