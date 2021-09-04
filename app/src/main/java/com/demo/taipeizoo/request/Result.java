package com.demo.taipeizoo.request;

import androidx.annotation.NonNull;

public class Result<T> {

    public T result;

    @NonNull
    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                '}';
    }
}
