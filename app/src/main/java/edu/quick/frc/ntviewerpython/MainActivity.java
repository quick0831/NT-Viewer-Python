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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nt = new nt_utils(this);
/*
        TextView t = findViewById(R.id.Text);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(v -> {
            t.setText(nt.getTables());
        });*/

        RecyclerView rv = findViewById(R.id.RecyclerView);

        ArrayList<Item> data = new ArrayList<>();
        data.add(new Item("Marshmallow", "http://i.imgur.com/mVpDmzc.jpg", "Android 66666666666666"));

        MyAdapter adapter = new MyAdapter(this, data);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        data.add(new Item("Lollipop", "http://i.imgur.com/kyVfpYh.png", "Android 55555555555555"));
        adapter.notifyDataSetChanged();
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
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

        static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView keyText, valueText, typeText;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    public class Item {
        String key, value, type;

        public Item(String key, String value, String type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

    }
}