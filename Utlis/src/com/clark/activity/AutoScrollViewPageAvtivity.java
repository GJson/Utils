package com.clark.activity;

import java.util.ArrayList;
import java.util.List;

import com.clark.commonview.CarouselDiagramViewPager;
import com.clark.entity.KtvAdvertisementInfo;
import com.clark.utils.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class AutoScrollViewPageAvtivity extends Activity {

	private LinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audosroll_viewpage);
		ll = (LinearLayout) findViewById(R.id.contentLl);
		List<KtvAdvertisementInfo> KtvAdvertisementInfos = new ArrayList<KtvAdvertisementInfo>();
		KtvAdvertisementInfo KtvAdvertisementInfo1 = new KtvAdvertisementInfo();
		KtvAdvertisementInfo1.pic_id = "0";
		KtvAdvertisementInfo1.pic_url = "http://img10.360buyimg.com/da/jfs/t1132/47/262266971/56195/f7b77f7a/5511146fN665238e3.jpg";
		KtvAdvertisementInfos.add(KtvAdvertisementInfo1);
		KtvAdvertisementInfo KtvAdvertisementInfo2 = new KtvAdvertisementInfo();
		KtvAdvertisementInfo2.pic_id = "1";
		KtvAdvertisementInfo2.pic_url = "http://img14.360buyimg.com/da/jfs/t1048/350/279692724/59500/962a7626/551215daN24fdaf05.jpg";
		KtvAdvertisementInfos.add(KtvAdvertisementInfo2);
		KtvAdvertisementInfo KtvAdvertisementInfo3 = new KtvAdvertisementInfo();
		KtvAdvertisementInfo3.pic_id = "2";
		KtvAdvertisementInfo3.pic_url = "http://img20.360buyimg.com/da/jfs/t706/147/1027449200/55997/58dcf1d8/5511184dN1dfe9b5a.jpg";
		KtvAdvertisementInfos.add(KtvAdvertisementInfo3);
		// KtvAdvertisementInfo KtvAdvertisementInfo4=new
		// KtvAdvertisementInfo();
		// KtvAdvertisementInfo4.id="3";
		// KtvAdvertisementInfo4.pic="http://img10.360buyimg.com/da/jfs/t829/348/344528143/40164/c4f7a1d9/551b629dN960d3b7c.jpg";
		// KtvAdvertisementInfos.add(KtvAdvertisementInfo4);
		// �����ֲ����Զ���View
		CarouselDiagramViewPager viewPager = new CarouselDiagramViewPager(this,
				KtvAdvertisementInfos);
		// ���ֲ��ؼ�ֱ�Ӽ��뵽���Բ�����
		ll.addView(viewPager.getView(), android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);

	}
}
