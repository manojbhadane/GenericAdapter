package com.manojbhadane.genericadapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author manoj.bhadane manojbhadane777@gmail.com
 */
public abstract class GenericFilterAdapter<T, D> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<T> mArrayList;
    private ArrayList<T> mArrayListFilter;

    public GenericFilterAdapter(Context context, ArrayList<T> arrayList) {
        this.mContext = context;
        this.mArrayList = arrayList;
        this.mArrayListFilter = arrayList;
    }

    public abstract int getLayoutResId();

    public abstract void onBindData(T model, int position, D dataBinding);

    public abstract void onItemClick(T model, int position);

    public abstract View getSearchField();

    public abstract ArrayList<T> performFilter(String searchText, ArrayList<T> originalList);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutResId(), parent, false);
        RecyclerView.ViewHolder holder = new ItemViewHolder(dataBinding);

        if (getSearchField() != null) {
            if (getSearchField() instanceof EditText) {
                ((EditText) getSearchField()).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            } else if (getSearchField() instanceof SearchView) {
                ((SearchView) getSearchField()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        getFilter().filter(s);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        getFilter().filter(s);
                        return false;
                    }
                });
            } else if (getSearchField() instanceof androidx.appcompat.widget.SearchView) {
                ((androidx.appcompat.widget.SearchView) getSearchField()).setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        getFilter().filter(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        getFilter().filter(newText);
                        return false;
                    }
                });
            }
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final T model = mArrayListFilter.get(position);

        onBindData(model, position, ((ItemViewHolder) holder).mDataBinding);

        ((ViewDataBinding) ((ItemViewHolder) holder).mDataBinding).getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(model, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayListFilter.size();
    }

    public void addItems(ArrayList<T> arrayList) {
        mArrayList = arrayList;
        mArrayListFilter = arrayList;
        this.notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                ArrayList<T> arrayList = new ArrayList<>();

                try {
                    if (charSequence.toString().isEmpty()) {
                        arrayList = mArrayList;
                    } else {
                        arrayList = performFilter(charSequence.toString().trim(), mArrayList);
                        if (arrayList == null) {
                            arrayList = mArrayList;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mArrayListFilter = (ArrayList<T>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public T getItem(int position) {
        return mArrayListFilter.get(position);
    }

    public Context getContext() {
        return mContext;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        protected D mDataBinding;

        public ItemViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mDataBinding = (D) binding;
        }
    }
}
