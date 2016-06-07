package com.example.i_jinliangshan.myrxjavademo;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.i_jinliangshan.myrxjavademo.Observables.NetworkObserable;
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
        NetworkObserable.getInstance()
            .compose(this.<String>bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                data -> mTvResult.setText(data),
                throwable -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show()
            );
    }
}
