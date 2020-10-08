package com.example.healthtourism.ui.comments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthtourism.Model.CommentModel;
import com.example.healthtourism.ViewHolder.CommentViewHolder;

import java.util.List;

public class CommentViewModel extends ViewModel {
    private MutableLiveData<List<CommentModel>>mutableLiveData;

    public CommentViewModel(){
        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<CommentModel>>getMutableLiveData(){
        return mutableLiveData;
    }

    public void setCommenList(List<CommentModel>commenList){
        mutableLiveData.setValue(commenList);
    }

}
