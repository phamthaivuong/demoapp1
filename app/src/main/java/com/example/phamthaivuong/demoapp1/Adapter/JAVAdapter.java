package com.example.phamthaivuong.demoapp1.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phamthaivuong.demoapp1.Model.JAVModel;
import com.example.phamthaivuong.demoapp1.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class JAVAdapter extends BaseAdapter {
    private List<JAVModel> list;
    Context context;

    public JAVAdapter(Context context,List<JAVModel> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item,null);

            holder = new ViewHolder();
            //Init
            holder.id = view.findViewById(R.id.id);
            holder.name = view.findViewById(R.id.name);
            holder.slug = view.findViewById(R.id.slug);
            holder.simpleDraweeView = (SimpleDraweeView)view.findViewById(R.id.my_iamge);
            holder.root = view.findViewById(R.id.root);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        final JAVModel javModel = list.get(i);
        holder.id.setText(javModel.getcHID());
        holder.name.setText(javModel.getName());
        holder.slug.setText(javModel.getSlug());
        holder.simpleDraweeView.setImageURI( Uri.parse(javModel.getCoverUrl()));
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                OnCickItemJAV(javModel);
                if (onCickItemJAV != null){
                    onCickItemJAV.onClick(javModel);
                }
            }
        });
        return view;
    }
    private class ViewHolder{
        TextView id,name,slug;
        SimpleDraweeView simpleDraweeView;
        View root;
    }


    /*  phương thức abstract onCLick

    //    public abstract void OnCickItemJAV(JAVModel javModel);

    */


    // Phương thức OnClickITem
    private OnCickItemJAV onCickItemJAV;

    public void setOnCickItemJAV(OnCickItemJAV onCickItemJAV) {
        this.onCickItemJAV = onCickItemJAV;
    }

    public interface OnCickItemJAV{
        void onClick(JAVModel javModel);
    }
}
