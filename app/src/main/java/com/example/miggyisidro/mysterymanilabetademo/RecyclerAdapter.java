package com.example.miggyisidro.mysterymanilabetademo;

/**
 * Created by MiggyIsidro on 13/03/2018.
 */


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MiggyIsidro on 12/03/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerItemViewHolder> {
    private ArrayList<RecyclerData> myList;
    int mLastPosition = 0;
    private RemoveClickListner mListner;
    public RecyclerAdapter(ArrayList<RecyclerData> myList,RemoveClickListner listner) {
        this.myList = myList;
        mListner=listner;
    }
    public RecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        RecyclerItemViewHolder holder = new RecyclerItemViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerItemViewHolder holder, final int position) {
        Log.d("onBindViewHoler ", myList.size() + "");
        holder.TextViewRoomName.setText(myList.get(position).getRoomName());
        holder.TextViewSchedule.setText(myList.get(position).getSchedule());
        holder.TextViewName.setText(myList.get(position).getName());
        holder.TextViewTransactionID.setText(myList.get(position).getTransactionID());

        mLastPosition =position;
    }
    @Override
    public int getItemCount() {
        return(null != myList?myList.size():0);
    }
    public void notifyData(ArrayList<RecyclerData> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.myList = myList;
        notifyDataSetChanged();
    }
    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView TextViewRoomName;
        private final TextView TextViewSchedule;
        private final TextView TextViewName;
        private final TextView TextViewTransactionID;
        private LinearLayout mainLayout;
        public RecyclerItemViewHolder(final View parent) {
            super(parent);
            TextViewRoomName = (TextView) parent.findViewById(R.id.txtRoomName);
            TextViewSchedule = (TextView) parent.findViewById(R.id.txtSchedule);
            TextViewName = (TextView) parent.findViewById(R.id.txtName);
            TextViewTransactionID = (TextView) parent.findViewById(R.id.txtTransactionID);

            mainLayout = (LinearLayout) parent.findViewById(R.id.mainLayout);
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Position:" + Integer.toString(getPosition()), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
