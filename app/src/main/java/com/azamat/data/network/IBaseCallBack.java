package com.azamat.data.network;

public interface IBaseCallBack <T> {

    void onSuccess(T result);

    void onFailure(Exception e);
}
