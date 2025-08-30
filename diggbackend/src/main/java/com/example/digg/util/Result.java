package com.example.digg.util;

public sealed interface Result<R, S> permits Result.Success, Result.Reject {

    record Success<R, S>(S value) implements Result<R, S> {
    }

    record Reject<R, S>(R value) implements Result<R, S> {
    }

    static <R, S> Result<R, S> success(S value) {
        return new Success<R, S>(value);
    }

    static <R, S> Result<R, S> reject(R value) {
        return new Reject<R, S>(value);
    }
}
