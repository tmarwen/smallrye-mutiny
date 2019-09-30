package io.smallrye.reactive.unimulti.adapt.converters;

import io.reactivex.Single;
import io.smallrye.reactive.unimulti.Uni;

import java.util.function.Function;

public class ToSingleWithDefault<T> implements Function<Uni<T>, Single<T>> {

    private T defaultValue;

    public ToSingleWithDefault(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Single<T> apply(Uni<T> uni) {
        return Single.fromPublisher(uni.onItem().ifNull().continueWith(defaultValue).adapt().toPublisher());
    }
}