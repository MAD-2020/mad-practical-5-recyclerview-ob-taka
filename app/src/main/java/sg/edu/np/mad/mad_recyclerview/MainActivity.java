package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ItemToDo> myToDoList;
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button mBtn;
    private EditText mEditText;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createItemList();
        buildRecyclerView();

        mBtn = findViewById(R.id.myBtn);
        mEditText = findViewById(R.id.myEditText);


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditText.length() != 0){
                    addItem(mEditText.getText().toString());
                    showNewEntry(mRecyclerView, myToDoList);
                }
            }
        });


    }

    public void addItem(String item){
        myToDoList.add(new ItemToDo(item, false));
        mAdapter.notifyDataSetChanged();
    }
    public void removeItem(int position){
        myToDoList.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    public void createItemList(){
        myToDoList = new ArrayList<>();
        myToDoList.add(new ItemToDo("Buy milk", false));
        myToDoList.add(new ItemToDo("Send mail", false));
        myToDoList.add(new ItemToDo("Buy Book", false));
    }
    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.myRecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ItemAdapter(myToDoList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ItemAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(int position) {
                ItemToDo currentitem = myToDoList.get(position);
                alerbox(position, currentitem.getMyToDo() + "?");
                //removeItem(position);
            }
        });
    }

    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }

    private void alerbox(final int position, String item) {
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.dialog);
        TextView tempitem = mDialog.findViewById(R.id.Item_name);
        TextView Yes = mDialog.findViewById(R.id.Yes_btn);
        TextView No = mDialog.findViewById(R.id.No_btn);
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
                mDialog.cancel();
            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.cancel();
            }
        });
        tempitem.setText(item);
        mDialog.show();

    }
}
