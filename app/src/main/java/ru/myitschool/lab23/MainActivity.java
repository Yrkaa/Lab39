package ru.myitschool.lab23;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    RecyclerView list;
    TextView balance;
    Button newOperation;

    TransactionAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.ef_expenses_rv);
        balance = findViewById(R.id.ef_current_balance_text);
        newOperation = findViewById(R.id.add_fab);

        adapter = new TransactionAdapter(this);
        list.setAdapter(adapter);

        newOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
    }

    private void createDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Новая операция");
        dialog.setContentView(R.layout.dialog_layout);
        dialog.show();

        Spinner type = dialog.findViewById(R.id.type_spinner);
        EditText sum = dialog.findViewById(R.id.expense_amount_edit_text);
        Button add = dialog.findViewById(R.id.add_button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Float f = Float.parseFloat(balance.getText().toString());
                if(type.getSelectedItem().toString().equals("Income"))
                    f+=Float.parseFloat(sum.getText().toString());
                else
                    f-=Float.parseFloat(sum.getText().toString());
                balance.setText(f.toString());
                LocalDate date = LocalDate.now();
                adapter.addTransaction(type.getSelectedItem().toString(), date.getDayOfMonth()+"."+date.getMonthValue()+"."+date.getYear(), Float.parseFloat(sum.getText().toString()));
                dialog.dismiss();
            }
        });
    }
}
