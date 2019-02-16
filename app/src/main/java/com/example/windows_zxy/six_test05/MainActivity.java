package com.example.windows_zxy.six_test05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.windows_zxy.six_test05.Api.API;
import com.example.windows_zxy.six_test05.Api.RetrofitAPi;
import com.example.windows_zxy.six_test05.adapter.MyAdapter;
import com.example.windows_zxy.six_test05.bean.ShopBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.but1)
    Button but1;
    private RetrofitAPi retrofitAPi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.API_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitAPi = retrofit.create(RetrofitAPi.class);


    }

    @OnClick(R.id.but1)
    public void onViewClicked() {
        Map<String,String> map = new HashMap<>();
        map.put("page", "1");
        map.put("count", "5");
        String keyword = editText.getText().toString();
        if(keyword.isEmpty()){
            Toast.makeText(this,"关键词不能为空",Toast.LENGTH_SHORT).show();
        }else{
            map.put("keyword",keyword);
            Call<ShopBean> beanCall = retrofitAPi.beanCall(API.API_SHOP, map);
            beanCall.enqueue(new Callback<ShopBean>() {
                @Override
                public void onResponse(Call<ShopBean> call, Response<ShopBean> response) {
                    ShopBean body = response.body();
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                    recycler.setLayoutManager(gridLayoutManager);
                    MyAdapter adapter = new MyAdapter(MainActivity.this, body);
                    recycler.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<ShopBean> call, Throwable t) {

                }
            });
        }
    }
}
