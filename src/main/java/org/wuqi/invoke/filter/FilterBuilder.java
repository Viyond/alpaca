package org.wuqi.invoke.filter;

import java.util.List;

import org.wuqi.core.Invocation;
import org.wuqi.core.InvocationHandler;
import org.wuqi.core.ListenableFuture;
import org.wuqi.core.Result;
import org.wuqi.spi.SpiContainer;

public class FilterBuilder {

    public static InvocationHandler buildInvokerChain(InvocationHandler nextHandler){
        List<Filter> filters = SpiContainer.getInstances(Filter.class, new String[]{}, true);
        if (filters == null || filters.isEmpty()){
            return nextHandler;
        }

        FilterNode head = new HeadNode();
        FilterNode tail = new TailNode(nextHandler);
        head.next = tail;
        tail.pre = head;

        for (Filter filter : filters){
            FilterNode current = new FilterNode(filter);
            tail.pre.next = current;
            current.pre = tail.pre;
            current.next = tail;
            tail.pre = current;
        }

        return head;
    }

    private static class HeadNode extends FilterNode{
        private HeadNode(){
            super(null);
        }

        @Override
        public ListenableFuture<Result> invoke(Invocation invocation) throws Throwable {
            ListenableFuture<Result> future = next.invoke(invocation);
            return future;
        }

        @Override
        public void onResponse(Invocation invocation, Result result) {
            if (!isReachTerminal(invocation)){
                System.err.println("###中途执行失败!!");
            }
        }
    }

    private static class TailNode extends FilterNode{
        private InvocationHandler invocationHandler;

        private TailNode(InvocationHandler invocationHandler){
            super(null);
            this.invocationHandler = invocationHandler;
        }

        @Override
        public ListenableFuture<Result> invoke(Invocation invocation) throws Throwable {
            reachTerminal(invocation);
            return this.invocationHandler.invoke(invocation);
        }

        @Override
        public void onResponse(Invocation invocation, Result result) {
            pre.onResponse(invocation, result);
        }
    }
}
