package com.rj.watersupply.Adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rj.watersupply.Customer;
import com.rj.watersupply.R;
import com.rj.watersupply.modal.CustomerData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> implements Filterable {
    List<CustomerData> customerDataList;
    List<CustomerData> tempCustomerDataList;
    public CustomerAdapter(List<CustomerData> customerDataList) {
        this.customerDataList = customerDataList;
        tempCustomerDataList= customerDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_customer, parent, false);
        return new CustomerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerData c=customerDataList.get(position);
        holder.txtName.setText(c.getName());
        GradientDrawable sd = (GradientDrawable) holder.txtLogo.getBackground().mutate();
        sd.setColor(GetRandomColor(String.valueOf(c.getName().charAt(0)).toUpperCase()));
        sd.invalidateSelf();

        holder.txtLogo.setText(String.valueOf(c.getName().charAt(0)).toUpperCase());
        holder.txtMno.setText(c.getPhone());
    }

    @Override
    public int getItemCount() {
        if(customerDataList!=null){
            return customerDataList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                Log.e("Search",charSequence.toString()+"=");
                String charString = charSequence.toString();
                List<CustomerData> filteredList = new ArrayList<>();
                if (charString.isEmpty()) {
                    filteredList=tempCustomerDataList;
                }else {
                    for (CustomerData row : tempCustomerDataList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                customerDataList = (List<CustomerData>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLogo,txtName,txtMno;
        View v,line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            txtLogo = v.findViewById(R.id.txtLogo);
            txtName=v.findViewById(R.id.txtName);
            txtMno=v.findViewById(R.id.txtMno);
            line=v.findViewById(R.id.line);
        }
    }

    public int GetRandomColor(String ch){
        switch (ch) {
            case "A": case "B": case "C": case "D": case "E":
                return 0xff58B4AE;
            case "F": case "G": case "H": case "I": case "J":
                return 0xFFFFE277;
            case "K": case "L": case "M": case "N": case "O":
                return 0xFF162447;
            case "P": case "Q": case "R": case "S": case "T":
                return 0xFFFF5200;
            case "U": case "V": case "W": case "X": case "Y": case "Z":
                return 0xFF06623B;
        }
        return 0xFF06623B;
    }
}
