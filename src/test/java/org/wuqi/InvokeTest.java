package org.wuqi;

import org.junit.Test;
import org.wuqi.core.Invocation;
import org.wuqi.core.InvocationHandler;
import org.wuqi.core.ListenableFuture;
import org.wuqi.core.Result;
import org.wuqi.invoke.filter.FilterBuilder;

public class InvokeTest {

    @Test
    public void invoke_chain(){
        InvocationHandler handler = FilterBuilder.buildInvokerChain(new UnitTestInvocationHandler());
        try{
            ListenableFuture<Result> future = handler.invoke(new Invocation());
            System.out.println(future.get());
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
