package com.example.tracnghiemv1.Slide;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.ActivityChooserView;

import com.example.tracnghiemv1.R;
import com.example.tracnghiemv1.question.Question;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CheckAnsertAdapter extends BaseAdapter {
    ArrayList  lsData;
    LayoutInflater layoutInflater;

    public CheckAnsertAdapter(ArrayList lsData, Context context) {
        this.lsData = lsData;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Log.d("position",lsData.size()+"");
        return lsData.size();
    }

    @Override
    public Object getItem(int i) {
        Log.d("position",lsData.size()+"");
        return lsData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private static class  ViewHolder{
        TextView txtCau,txtDapAn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Question data = (Question) getItem(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_gridview_dialog,null);

            holder.txtCau = (TextView) convertView.findViewById(R.id.txtCau);
            holder.txtDapAn = (TextView)convertView.findViewById(R.id.txtDapAn);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        int i = position+1;
        holder.txtCau.setText("Câu "+i+" :");

        holder.txtDapAn.setText(data.getAnsert());

        return convertView;
    }
}
