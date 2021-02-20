package org.wuqi.invoke.filter;

import org.wuqi.core.Invocation;
import org.wuqi.core.InvocationHandler;
import org.wuqi.core.ListenableFuture;
import org.wuqi.core.Result;

public class FilterNode implements InvocationHandler {

    public static final String LAST_FILTER_KEY = "_last_filter";
    public static final String REACH_TERMINAL_KEY = "_reach_terminal";

    protected FilterNode pre;

    protected FilterNode next;

    private final Filter filter;

    public FilterNode(Filter filter){
        this.filter = filter;
    }

    @Override
    public ListenableFuture<Result> invoke(Invocation invocation) throws Throwable{
        markNode(invocation);
        return filter.invoke(next, invocation);
    }

    public void onResponse(Invocation invocation, Result result){
        filter.onResponse(invocation, result);
        pre.onResponse(invocation, result);
    }

    private void markNode(Invocation invocation){
        invocation.put(LAST_FILTER_KEY, this);
    }

    public final FilterNode fetchMarkedNode(Invocation invocation){
        return (FilterNode)invocation.get(LAST_FILTER_KEY);
    }

    public final void reachTerminal(Invocation invocation){
        invocation.put(REACH_TERMINAL_KEY, Boolean.TRUE);
    }

    public final boolean isReachTerminal(Invocation invocation){
        return Boolean.TRUE.equals(invocation.get(REACH_TERMINAL_KEY));
    }
}
