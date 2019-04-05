package com.manojbhadane.genericadaptersample;

import android.os.Bundle;

import com.manojbhadane.genericadapter.GenericAdapter;
import com.manojbhadane.genericadaptersample.databinding.ActivityMainBinding;
import com.manojbhadane.genericadaptersample.databinding.ListitemMainBinding;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // List
        ArrayList<PeopleModel> arrayList = new ArrayList<>();
        arrayList.add(new PeopleModel().setName("MS Dhoni").setAddress("Ranchi"));
        arrayList.add(new PeopleModel().setName("Sachin tendulkar").setAddress("Mumbai"));
        arrayList.add(new PeopleModel().setName("Virat kohli").setAddress("Delhi"));
        arrayList.add(new PeopleModel().setName("Rohit Sharma").setAddress("Mumbai"));
        arrayList.add(new PeopleModel().setName("Saurav Ganguli").setAddress("Kolkata"));

        // Layout Manager
        mDataBinding.recylerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        // List Adapter
        mDataBinding.recylerview.setAdapter(new GenericAdapter<PeopleModel, ListitemMainBinding>(this, arrayList) {
            @Override
            public int getLayoutResId() {
                return R.layout.listitem_main;
            }

            @Override
            public void onBindData(PeopleModel model, int position, ListitemMainBinding dataBinding) {
                dataBinding.txtName.setText(model.getName());
                dataBinding.txtAddress.setText(model.getAddress());
            }

            @Override
            public void onItemClick(PeopleModel model, int position) {

            }
        });
    }
}
