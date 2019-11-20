package com.jy.day03.api;

import com.jy.day03.bean.Girl;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String girlUrl = "http://gank.io/api/data/";

    @GET("%E7%A6%8F%E5%88%A9/20/1")
    Observable<Girl> getData();
}
