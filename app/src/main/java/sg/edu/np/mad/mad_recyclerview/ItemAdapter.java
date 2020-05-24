package sg.edu.np.mad.mad_recyclerview;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private ArrayList<ItemToDo> mToDoList;
    private OnitemClickListener mListener;
    private Context context;

    public interface OnitemClickListener{
        void onItemClick(int position);
    }

    public  void setOnItemClickListener(OnitemClickListener listener){
        mListener = listener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public CheckBox mCheckBox;
        public TextView mTextView;
        public Dialog mDialog;

        public ItemViewHolder(@NonNull View itemView, final OnitemClickListener listener) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.myCheckBox);
            mTextView = itemView.findViewById(R.id.myTextView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);


                        }
                    }
                }
            });
        }
    }

    public ItemAdapter(ArrayList<ItemToDo> todolist) {
        mToDoList = todolist;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ItemViewHolder item = new ItemViewHolder(v, mListener);

        return item;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemToDo currentitem = mToDoList.get(position);
        holder.mTextView.setText(currentitem.getMyToDo());
        holder.mCheckBox.setActivated(currentitem.isMycheckbox());

    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }
}
