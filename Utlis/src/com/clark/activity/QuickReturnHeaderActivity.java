package com.clark.activity;

import java.util.ArrayList;
import java.util.List;

import com.clark.commonview.QuickReturnHeaderHelper;
import com.clark.utils.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QuickReturnHeaderActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		QuickReturnHeaderHelper helper = new QuickReturnHeaderHelper(this,
				R.layout.common_view, R.layout.header);
		View view = helper.createView();
		setContentView(view);

		ListView listView = (ListView) findViewById(R.id.lv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, geneItems());
		listView.setAdapter(adapter);
	}

	private List<String> geneItems() {
		List<String> sNewsList=new ArrayList<String>();
		for (int i = 0; i != 40; ++i) {
			sNewsList.add("你好测试用的哦：" + i);
		}
		return sNewsList;
	}

}
