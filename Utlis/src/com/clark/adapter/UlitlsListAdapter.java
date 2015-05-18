package com.clark.adapter;

import java.util.ArrayList;
import java.util.List;

import com.clark.utils.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UlitlsListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<String> mListStrs = new ArrayList<String>();
	private Context mContext;

	public UlitlsListAdapter(Context context, List<String> str) {
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
		mListStrs = str;
	}

	@Override
	public int getCount() {
		return mListStrs.size();
	}

	@Override
	public Object getItem(int position) {
		return mListStrs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup arg2) {
		ViewHolder viewHolder;
		if (convertview == null) {
			convertview = mInflater.inflate(R.layout.list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.mTxt = (TextView) convertview
					.findViewById(R.id.tv_content);
			convertview.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertview.getTag();
		}
		viewHolder.mTxt.setText(mListStrs.get(position));
		return convertview;
	}

	class ViewHolder {
		TextView mTxt;
	}
}
