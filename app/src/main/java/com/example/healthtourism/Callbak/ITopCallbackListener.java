package com.example.healthtourism.Callbak;

import com.example.healthtourism.Model.WisataModel;

import java.util.List;

public interface ITopCallbackListener {
    void onTopDestinationLoadSuccess(List<WisataModel>wisataModelList);
    void onTopDestinationLoadFailed(String message);

}
