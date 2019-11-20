package com.jy.day03.presenter;

import com.jy.day03.bean.Girl;
import com.jy.day03.callback.MainModelCallback;
import com.jy.day03.model.ImpMainModel;
import com.jy.day03.view.MainViewCallback;

import java.util.List;

public class ImpMainPresenter implements MainPresenter, MainModelCallback {

    private MainViewCallback mainViewCallback;
    private ImpMainModel impMainModel;

    public ImpMainPresenter(MainViewCallback mainViewCallback) {
        this.mainViewCallback = mainViewCallback;
        this.impMainModel = new ImpMainModel();
    }

    @Override
    public void getData() {
        if (impMainModel != null){
            impMainModel.getData(this);
        }
    }

    @Override
    public void onSuccess(List<Girl.ResultsBean> list) {
        mainViewCallback.onSuccess(list);
    }

    @Override
    public void onFail(String error) {
        mainViewCallback.onFail(error);
    }
}
