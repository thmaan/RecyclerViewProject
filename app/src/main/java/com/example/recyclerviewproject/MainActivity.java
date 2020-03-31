package com.example.recyclerviewproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.RecursiveAction;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonInsert;
    private Button buttonRemove;
    private Button buttonEdit;
    private EditText editTextInsert;
    private EditText editTextRemove;
    private EditText editTextEdit;
    private EditText title;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createExampleList();
        buildRecyclerView();
        setButtons();
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {

                int position_dragged = dragged.getAdapterPosition();
                int position_target = target.getAdapterPosition();

                Collections.swap(mExampleList,position_dragged,position_target);
                mAdapter.notifyItemMoved(position_dragged,position_target);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                removeItem(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(i);
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }
    public void insertItem(int position){
        mExampleList.add(position, new ExampleItem(R.drawable.ic_attach_money,"New Item at Position" + position,"Description"));
        mAdapter.notifyItemInserted(position);
    }
    public void removeItem(int position){
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
    public void changeItem(int position, String text, String text1){
        mExampleList.get(position).changeText1(text);
        mExampleList.get(position).changeText2(text1);
        mAdapter.notifyItemChanged(position);
    }
    public void createExampleList(){
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 1","Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audiotrack, "Line 3","Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_attach_money, "Line 5","Line 6"));
    }
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
    public void setButtons(){
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonRemove = findViewById(R.id.buttonRemove);
        buttonEdit = findViewById(R.id.buttonEdit);
        editTextInsert = findViewById(R.id.editTextInsert);
        editTextRemove = findViewById(R.id.editTextRemove);
        editTextEdit = findViewById(R.id.editTextEdit);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextEdit.getText().toString());
                changeItem(position, title.getText().toString(),description.getText().toString());
            }
        });
    }
}
