package com.faceserp.common.model.ext;

import oracle.jbo.server.ViewDefImpl;
import oracle.jbo.server.ViewObjectImpl;

public class BaseViewImpl extends ViewObjectImpl {
    
    public BaseViewImpl(String string, ViewDefImpl viewDefImpl) {
        super(string, viewDefImpl);
    }

    public BaseViewImpl() {
        super();
    }
}
