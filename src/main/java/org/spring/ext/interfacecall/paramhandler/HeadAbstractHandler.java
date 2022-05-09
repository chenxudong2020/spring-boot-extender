package org.spring.ext.interfacecall.paramhandler;


import org.spring.ext.interfacecall.annotation.Body;
import org.spring.ext.interfacecall.annotation.Head;
import org.spring.ext.interfacecall.annotation.Query;
import org.spring.ext.interfacecall.annotation.Url;
import org.spring.ext.interfacecall.entity.ParameterMeta;
import org.spring.ext.interfacecall.exception.InterfaceCallInitException;

import java.lang.reflect.Parameter;

public class HeadAbstractHandler extends AbstractHandlerChain {

    private AbstractHandlerChain handler;

    @Override
    public void setNext(AbstractHandlerChain handler) {
        this.handler = handler;
    }

    @Override
    public AbstractHandlerChain handler(HandlerRequest request) {
        ParameterMeta parameterMeta = request.getParameterMeta();
        Parameter parameter = request.getParameter();
        Head head = parameter.getAnnotation(Head.class);
        validate(request);
        if (null != head) {
            parameterMeta.head = head;
        }

        return handler;

    }

    @Override
    public void validate(HandlerRequest request) {
        Parameter parameter = request.getParameter();
        String parameterName = parameter.getName();
        Body body = parameter.getAnnotation(Body.class);
        Head head = parameter.getAnnotation(Head.class);
        Query query = parameter.getAnnotation(Query.class);
        Url url = parameter.getAnnotation(Url.class);
        boolean isNull = ( head == null);
        boolean isValidate = (head != null && url == null && body == null && query == null);
        if (!isNull && isValidate) {
            if (!isValidate) {
                throw new InterfaceCallInitException("head不能和其他，注解同一个参数:" + parameterName + "!");
            }

        }
    }
}
