package com.example.healthtourism.ui.TopDestination;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthtourism.Callbak.ITopCallbackListener;
import com.example.healthtourism.Model.WisataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TopDestinationViewModel extends ViewModel implements ITopCallbackListener {

    private MutableLiveData<List<WisataModel>> wisataModelMutableLiveData;
    private MutableLiveData<String>massageError = new MutableLiveData<>();
    private ITopCallbackListener topCallbackListener;

    public TopDestinationViewModel() {
        topCallbackListener = this;
    }

    public MutableLiveData<List<WisataModel>> getWisataModelMutableLiveData() {
        if (wisataModelMutableLiveData == null){
            wisataModelMutableLiveData = new MutableLiveData<>();
            massageError = new MutableLiveData<>();
            loadTopDestination();
        }
        return wisataModelMutableLiveData;
    }

    private void loadTopDestination() {
        List<WisataModel> wisataList =new ArrayList<>();
        DatabaseReference topDestinationRef = FirebaseDatabase.getInstance().getReference("Wellnes");
        topDestinationRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){
                    WisataModel wisataModel = dataSnapshot.getValue(WisataModel.class);
                    wisataList.add(wisataModel);
                }
                WisataModel wisataModel = new WisataModel();
                    wisataModel.setNama(wisataModel.getNama());
                    wisataModel.setGambar(wisataModel.getGambar());
                    wisataList.add(wisataModel);
                topCallbackListener.onTopDestinationLoadSuccess(wisataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public MutableLiveData<String> getMassageError() {
        return massageError;
    }

    @Override
    public void onTopDestinationLoadSuccess(List<WisataModel> wisataModelList) {
        wisataModelMutableLiveData.setValue(wisataModelList);
    }

    @Override
    public void onTopDestinationLoadFailed(String message) {
        massageError.setValue(message);
    }
}