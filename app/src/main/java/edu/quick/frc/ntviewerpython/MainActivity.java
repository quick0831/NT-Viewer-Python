package edu.quick.frc.ntviewerpython;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    nt_utils nt;
    ArrayList<Item> data;
    MyAdapter adapter;
    RecyclerView rv;
    nt_utils.Pwd pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nt = new nt_utils(this);
        pwd = new nt_utils.Pwd(this, nt);
/*
        TextView t = findViewById(R.id.Text);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(v -> {
            t.setText(nt.getTables());
        });*/

        rv = findViewById(R.id.RecyclerView);
        data = new ArrayList<>();
        adapter = new MyAdapter(this, data);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        updateData();
    }

    void updateData(){
        //data.add(new Item("SmartDashboard", "", "SubTable"));
        //data.add(new Item("Entry 1", "123.45", "Number"));
        //data.add(new Item("Entry 2", "Hello There!", "String"));
        data.clear();

        if(!pwd.isRoot())
            data.add(new Item("<= Return", "", pwd.getFullPath()));

        // SubTables
        for(String s:nt.getSubTables(pwd.getTable()))
            data.add(new Item(s, "", pwd.isRoot() ? "Table" : "SubTable"));

        // Entries
        for(String s:nt.getKeys(pwd.getTable())){
            nt_utils.Entry e = nt.getEntry(pwd.getTable(), s);
            data.add(new Item(s, e.getValue(), e.getType()));
        }
        adapter.notifyDataSetChanged();
    }

// ==== Adapter for RecyclerView ===================================================================

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private final Context mContext;
        private final ArrayList<Item> mData;

        public MyAdapter(Context context, ArrayList<Item> data) {
            this.mContext = context;
            this.mData = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            holder.keyText = view.findViewById(R.id.key_text);
            holder.valueText = view.findViewById(R.id.value_text);
            holder.typeText = view.findViewById(R.id.type_text);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Item item = mData.get(position);
            holder.keyText.setText(item.key);
            holder.valueText.setText(item.value);
            holder.typeText.setText(item.type);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView keyText, valueText, typeText;

            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(v -> {
                    String type = (String) typeText.getText();
                    if(type.equals("SubTable") || type.equals("Table")) {
                        Log.i("OnClickSubTable", (String) keyText.getText());
                        pwd.cd((String) keyText.getText());
                        return;
                    }
                    if(type.matches("Boolean|Boolean Array|Number|Number Array|Raw|String|String Array")) {
                        Log.i("OnClickEntry", (String) keyText.getText());
                        return;
                    }
                    Log.i("OnClickElse", type); // Pressed the "Return" button
                    pwd.cdBack();
                });
            }
        }
    }

    public static class Item {
        String key, value, type;

        public Item(String key, String value, String type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

    }
}