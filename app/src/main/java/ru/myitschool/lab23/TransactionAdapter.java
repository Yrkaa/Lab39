package ru.myitschool.lab23;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    ArrayList<String> typesList = new ArrayList<>();
    ArrayList<String> datesList = new ArrayList<>();
    ArrayList<Float> sumList = new ArrayList<>();

    LayoutInflater inflater;

    public void addTransaction(String t, String d, float sum){
        typesList.add(t);
        datesList.add(d);
        sumList.add(sum);
        notifyItemInserted(sumList.size()-1);
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        holder.type.setText(typesList.get(position));
        holder.date.setText(datesList.get(position));
        holder.sum.setText(sumList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return sumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView type, date, sum;

        public ViewHolder(View v){
            super(v);
            type = v.findViewById(R.id.expense_type_text);
            date = v.findViewById(R.id.expense_date_text);
            sum = v.findViewById(R.id.expense_amount_text);
        }
    }

    public TransactionAdapter(Context c){
        this.inflater = LayoutInflater.from(c);
    }
}
