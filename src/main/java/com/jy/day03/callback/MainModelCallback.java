package com.jy.day03.callback;

import com.jy.day03.bean.Girl;

import java.util.List;

public interface MainModelCallback {
    void onSuccess(List<Girl.ResultsBean> list);
    void onFail(String error);
}
