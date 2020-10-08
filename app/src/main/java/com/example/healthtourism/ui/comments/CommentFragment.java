package com.example.healthtourism.ui.comments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthtourism.Adapter.CommentAdapter;
import com.example.healthtourism.Callbak.ICommentCallbackListener;
import com.example.healthtourism.Common.Common;
import com.example.healthtourism.Common.CommonWisata;
import com.example.healthtourism.Model.CommentModel;
import com.example.healthtourism.Model.WisataModel;
import com.example.healthtourism.R;
import com.example.healthtourism.ViewHolder.WisataViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CommentFragment extends BottomSheetDialogFragment implements ICommentCallbackListener {

    private CommentViewModel commentViewModel;
    private Unbinder unbinder;
    FirebaseRecyclerAdapter<WisataModel, WisataViewHolder> adapter;

    @BindView(R.id.recycler_comment)
    RecyclerView recycler_comment;
    AlertDialog dialog;
    ICommentCallbackListener listener;

    public CommentFragment(){
        listener = this;
    }

    public static CommentFragment instance;

    private static CommentFragment getInstance(){
        if (instance == null){
            instance = new CommentFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fargment_comment,container ,false);
        unbinder = ButterKnife.bind(this,view);
        initView();
        loadCommentFirebase();
        commentViewModel.getMutableLiveData().observe(this, commentModels -> {
            CommentAdapter adapter = new CommentAdapter(getContext(), commentModels);
            recycler_comment.setAdapter(adapter);
        });
        return view;
    }

    private void loadCommentFirebase() {
        dialog.show();
        List<CommentModel> commentModels = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Wellnes")
                .child(CommonWisata.ambilWisata.getKey().toString()).child("Comment").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot commentSnapshot:dataSnapshot.getChildren()){
                    CommentModel commentModel = commentSnapshot.getValue(CommentModel.class);
                    commentModels.add(commentModel);
                }
                listener.onCommentLoadSuccess(commentModels);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initView() {
        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        dialog.setMessage("Loading");

        recycler_comment.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, true);
        recycler_comment.setLayoutManager(layoutManager);
        recycler_comment.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
    }

    @Override
    public void onCommentLoadSuccess(List<CommentModel> CommentModels) {
        commentViewModel.setCommenList(CommentModels);
    }

    @Override
    public void onCommentLoadFailed(String massage) {
        Toast.makeText(getContext(), massage, Toast.LENGTH_SHORT).show();
    }
}
