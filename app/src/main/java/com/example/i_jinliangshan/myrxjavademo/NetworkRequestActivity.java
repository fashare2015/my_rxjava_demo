package com.example.i_jinliangshan.myrxjavademo;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.i_jinliangshan.myrxjavademo.Observables.NetworkObservable;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;

public class NetworkRequestActivity extends RxAppCompatActivity {
    @Bind(R.id.tv_result) TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_request);
        setTitle(getResources().getString(R.string.string_network_request));

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_GET) public void onClick() {
        NetworkObservable.getInstance()                 // 获取 Observable 实例
            .compose(this.<String>bindToLifecycle())    // Rxlifecycle, 绑定到 Activity 的生命周期。
            .observeOn(AndroidSchedulers.mainThread())  // 指定观察者的线程————主线程。
            .subscribe(                                 // 订阅事件
                // onNext, 请求成功
                data -> mTvResult.setText(data),
                // onError, 请求失败
                throwable -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show()
            );
    }
}
