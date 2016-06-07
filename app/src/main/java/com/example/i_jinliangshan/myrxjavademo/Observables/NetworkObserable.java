package com.example.i_jinliangshan.myrxjavademo.Observables;

import com.example.i_jinliangshan.myrxjavademo.Utils.NetworkHelper;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by jinliangshan on 2016/6/7.
 */
public class NetworkObserable extends Observable<String> {
    public NetworkObserable(OnSubscribe<String> f) {
        super(f);
    }

    public static Observable<String> getInstance(final String url, final String method, final Map<String, Object> params) {
        return Observable.create((OnSubscribe<String>) subscriber -> initObserable(subscriber, url, method, params))
            .subscribeOn(Schedulers.io());
    }

    public static Observable<String> getInstance() {
        return getInstance("", "", null);
    }

    private static void initObserable(Subscriber<? super String> subscriber, String url, String method, Map<String, Object> params) {
        subscribeData(subscriber, NetworkHelper.getData(NetworkHelper.GET));
    }

    private static void subscribeData(Subscriber<? super String> subscriber, String data) {
        if(data == null){
            subscriber.onError(new Throwable("data 为空"));
            subscriber.onCompleted();
        }else{
            subscriber.onNext(data);
            subscriber.onCompleted();
        }
    }
}
