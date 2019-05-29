package com.manojbhadane.genericadaptersample.adapter;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manojbhadane.genericadapter.GenericFilterAdapter;
import com.manojbhadane.genericadaptersample.PeopleModel;
import com.manojbhadane.genericadaptersample.R;
import com.manojbhadane.genericadaptersample.databinding.ActivitySearchBinding;
import com.manojbhadane.genericadaptersample.databinding.ListitemMainBinding;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);

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
        mDataBinding.recylerview.setAdapter(new GenericFilterAdapter<PeopleModel, ListitemMainBinding>(this, arrayList) {
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

            @Override
            public View getSearchField() {
                return mDataBinding.edtSearch;
            }

            @Override
            public ArrayList<PeopleModel> performFilter(String searchText, ArrayList<PeopleModel> originalList) {
                ArrayList<PeopleModel> filteredList = new ArrayList<>();
                for (PeopleModel row : originalList) {
                    if (row.getName().toLowerCase().contains(searchText.toLowerCase()) || row.getName().contains(searchText) ||
                            row.getAddress().toLowerCase().contains(searchText.toLowerCase())) {
                        filteredList.add(row);
                    }
                }

                return filteredList;
            }
        });
    }
}
