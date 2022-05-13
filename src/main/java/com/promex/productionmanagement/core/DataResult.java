package com.promex.productionmanagement.core;

import java.util.Collection;

public class DataResult<T> extends Result {

    private final T data;

    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }

    public DataResult(T data, boolean success, String message) {
        super(success, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
