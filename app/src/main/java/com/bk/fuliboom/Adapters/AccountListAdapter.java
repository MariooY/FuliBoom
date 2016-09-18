package com.bk.fuliboom.Adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.bk.fuliboom.R;
import com.bk.fuliboom.Repository.Beans.AccountInfo;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Bk on 2016/9/6.
 */

public class AccountListAdapter extends BaseAdapter{
    private List<AccountInfo> data;
    private Context mContext;
    private ClipboardManager clipboard;
    private ClipData clip;

    public AccountListAdapter(Context context,List<AccountInfo> data) {
        this.data = data;
        mContext = context;
        clipboard = (ClipboardManager) mContext.getSystemService(CLIPBOARD_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_account_list,parent,false);
            holder.accText = (TextView) convertView.findViewById(R.id.item_account);
            holder.pswText = (TextView)convertView.findViewById(R.id.item_psw);
            holder.accBtn = (Button) convertView.findViewById(R.id.copy_acc_item);
            holder.pswBtn = (Button)convertView.findViewById(R.id.copy_psw_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.accBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clip = ClipData.newPlainText("account", data.get(position).getmAccount());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mContext,"已复制账号到粘贴板",Toast.LENGTH_SHORT).show();
            }
        });
        holder.accText.setText(data.get(position).getmAccount());
        holder.pswText.setText(data.get(position).getmPassword());
        holder.pswBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clip = ClipData.newPlainText("password", data.get(position).getmPassword());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mContext,"已复制密码到粘贴板",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }



    public static class ViewHolder{
        public TextView accText;
        public TextView pswText;
        public Button accBtn;
        public Button pswBtn;

    }

}
