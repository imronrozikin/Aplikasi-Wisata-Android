package com.example.healthtourism.Callbak;

import com.example.healthtourism.Model.CommentModel;

import java.util.List;

public interface ICommentCallbackListener{
    void onCommentLoadSuccess(List<CommentModel>CommentModels);
    void onCommentLoadFailed(String massage);
}
