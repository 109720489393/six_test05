package com.example.windows_zxy.six_test05.Api;

import com.example.windows_zxy.six_test05.bean.ShopBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitAPi {
    @GET
    Call<ShopBean> beanCall(@Url String url, @QueryMap Map<String, String> map);
}
