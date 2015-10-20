package com.linhphan.simplerecyclerview.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linhphan.simplerecyclerview.R;

import java.util.ArrayList;

/**
 * Created by linhphan on 10/19/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> data;
    private int selectedItem = 6;

    public MyAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final String name = data.get(position);
        holder.txtHeader.setText(name);
        holder.txtFooter.setText("Footer: " + data.get(position));
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                remove(name);
                selectedItem = position;
                notifyDataSetChanged();
            }
        });
        // Set selected state; use a state list drawable to style the view
        holder.parent.setSelected(selectedItem == position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private boolean tryMoveSelection(RecyclerView.LayoutManager lm, int direction) {
        int nextSelectItem = selectedItem + direction;

        // If still within valid bounds, move the selection, notify to redraw, and scroll
        if (nextSelectItem >= 0 && nextSelectItem < getItemCount()) {
            notifyItemChanged(selectedItem);
            selectedItem = nextSelectItem;
            notifyItemChanged(selectedItem);
            lm.scrollToPosition(selectedItem);
            return true;
        }

        return false;
    }

    public void remove(String item){
        int pos = data.indexOf(item);
        data.remove(pos);
        notifyItemRemoved(pos);
    }

    public void add(int position, String item){
        data.add(position, item);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtHeader;
        public TextView txtFooter;
        public RelativeLayout parent;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtHeader = (TextView) itemView.findViewById(R.id.firstLine);
            txtFooter = (TextView) itemView.findViewById(R.id.secondLine);
            parent = (RelativeLayout) itemView;
        }
    }
}
