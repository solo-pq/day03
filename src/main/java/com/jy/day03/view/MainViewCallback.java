package com.jy.day03.view;


import com.jy.day03.bean.Girl;

import java.util.List;

public interface MainViewCallback {
    void onSuccess(List<Girl.ResultsBean> list);
    void onFail(String error);
}
