package kr.ac.hifly.attention.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hifly.ac.kr.attention.R;
import kr.ac.hifly.attention.adapter_item.Main_Configuration_RecyclerView_Item;

/**
 * Created by hscom-018 on 2017-10-21.
 */

public class Main_Configuration_RecyclerView_Adapter extends RecyclerView.Adapter<Main_Configuration_RecyclerView_Adapter.ViewHolder>  {
    private Context context;
    private List<Main_Configuration_RecyclerView_Item> forth_recyclerView_items;

    public Main_Configuration_RecyclerView_Adapter(Context context, List<Main_Configuration_RecyclerView_Item> forth_recyclerView_items) {
        this.context = context;
        this.forth_recyclerView_items = forth_recyclerView_items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_configuration_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(forth_recyclerView_items.get(position).getName());
        holder.imageView.setImageResource(forth_recyclerView_items.get(position).getDraw());
    }

    @Override
    public int getItemCount() {
        return forth_recyclerView_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public LinearLayout linearLayout;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fourth_RecyclerView_Item_Image);
            name = (TextView) itemView.findViewById(R.id.fourth_RecyclerView_Item_Name);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.fourth_RecyclerView_item_setClick);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fourth_RecyclerView_item_setClick:
                    int position = getAdapterPosition();    //viewHolder 안에 onclick 을 달아줘야 각 position 에 접근가능
                    Intent intent = null;
/*                    switch (position){
                        case 0:
                            intent = new Intent(view.getContext(), Fourth_Recycler_Item_Speaker_Dialog.class);
                            break;
                        case 1:
                            intent = new Intent(view.getContext(), Fourth_Recycler_Item_Tuty_Dialog.class);
                            break;
                        case 3:
                            intent = new Intent(view.getContext(), Fourth_Recycler_Item_I_Dialog.class);
                            break;
                        case 4:
                            intent = new Intent(view.getContext(), Fourth_Recycler_Item_QNA_Dialog.class);
                            break;
                        case 5:
                            intent = new Intent(view.getContext(), Fourth_Recycler_Item_Developer_Dialog.class);
                            break;
                    }*/
                    intent.putExtra("name",forth_recyclerView_items.get(position).getName());
                    view.getContext().startActivity(intent);
                    break;
            }
        }
    }
}
