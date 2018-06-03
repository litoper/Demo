package com.example.kadh.utils.RxJava.RxApi;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class RxCompose implements FlowableTransformer {


    @Override
    public Publisher apply(Flowable upstream) {
        Flowable flowable = upstream.doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {

            }
        }).doOnTerminate(new Action() {
            @Override
            public void run() throws Exception {

            }
        }).doFinally(new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        return flowable;
    }
}
