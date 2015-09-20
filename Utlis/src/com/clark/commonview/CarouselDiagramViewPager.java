package com.clark.commonview;

import java.util.ArrayList;
import java.util.List;

import com.clark.common.until.BaseTools;
import com.clark.common.until.Utils;
import com.clark.entity.KtvAdvertisementInfo;
import com.clark.utils.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * �Զ���Ŀɹ�����viewpager
 */
@SuppressLint({ "HandlerLeak", "ViewConstructor" })
public class CarouselDiagramViewPager extends ViewPager {

	private Context context;
	// �ɹ���viewpager��ͼƬ����
	// private TextView top_news_title;
	// ���⣬ͼƬ������б�����
	// private List<String> titleList;
	// �ֲ�ͼƬ�����ӵ�ַ�б�
	private List<KtvAdvertisementInfo> carouselDiagramList;
	// ��ʼ��Բ�����ͼ
	private List<View> dotList;
	// �����������
	private RunnableTask runnableTask;
	// �ֲ�ͼ�����������
	private MyPagerAdapter adapter;
	// �ֲ�ͼ�ĵ�ǰͼƬλ��
	private int currentPosition;
	// ��ָ�����ֲ�ͼ��ʱ��λ��
	private int downX;
	private int downY;

	// ���ص���ͼ�еĿؼ�
	private View layout_roll_view;
	private LinearLayout ll_viewpager_container;
	private LinearLayout ll_dots;

	// viewpager��һ��ҳ��(��Ŀ)������ļ�����
	private OnRollViewPagerItemClickListener itemClickListener;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			// ���õ�ǰҳͼƬ��currentPosition��ֵ�ĸ�����run()������
			CarouselDiagramViewPager.this.setCurrentItem(currentPosition);
			// ����һֱȥ����
			startRoll();
		};
	};

	public CarouselDiagramViewPager(Context context,
			List<KtvAdvertisementInfo> carourseDiagramList) {
		super(context);
		// ��ʼ�����
		this.context = context;
		this.carouselDiagramList = carourseDiagramList;
		initData();
	}

	public CarouselDiagramViewPager(Context context,
			List<KtvAdvertisementInfo> carourseDiagramList,
			OnRollViewPagerItemClickListener itemClickListener) {
		super(context);
		// ��ʼ�����
		this.context = context;
		this.carouselDiagramList = carourseDiagramList;
		// ��ʼ��ͼƬ����������
		this.itemClickListener = itemClickListener;
		initData();
	}

	private void initData() {
		// ��ʼ���������������
		runnableTask = new RunnableTask();
		// ����ͼƬ�仯�ļ����¼�
		this.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// ������imageUrlListΪ�ջ��ߴ�СΪ0�����ٽ��к������
				if (carouselDiagramList == null
						|| carouselDiagramList.size() == 0) {
					return;
				}
				// ��ʹ����ָ����viewpagerʱ��ͣ�����ĸ�λ�þͽ�currentPosition����Ϊ�ĸ�λ�ã������ɿ��ֺ�Ϳ����Ե�ǰλ�ÿ�ʼ�����ˡ�
				Log.i("info", "onPageSelected===>" + position);
				currentPosition = position;
				// ��ݵ�ǰͼƬ���±���ͱ�־
				// top_news_title.setText(titleList.get(position));
				// ��positon��ֵ�Ӷ������ֵ�м��ĳ��ֵת��Ϊ0-carouselDiagramList.size()֮���ĳ��ֵ����������ҵ���Ӧ����Դ
				position = position % carouselDiagramList.size();
				for (int i = 0; i < carouselDiagramList.size(); i++) {// ����Բ��ı���Ϊѡ��״̬
					if (position == i) {
						CarouselDiagramViewPager.this.dotList.get(i)
								.setBackgroundResource(R.drawable.dot_focus);
					} else {
						CarouselDiagramViewPager.this.dotList.get(i)
								.setBackgroundResource(R.drawable.dot_normal);
					}
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

	}

	// public void initTitle(List<String> titleList, TextView top_news_title) {
	// this.titleList = titleList;
	// this.top_news_title = top_news_title;
	// //���õ�һ�ν���ҳ��ʱ��ͼƬ������Ϣ
	// if(titleList != null && titleList.size() > 0 && top_news_title != null){
	// top_news_title.setText(titleList.get(0));
	// }
	// }

	/**
	 * ��ʼ���������²���Բ����ͼ
	 * 
	 * @param ll_dots
	 *            ��Ҫ����Բ������Բ���
	 */
	public void initDot(LinearLayout ll_dots) {
		// ������������ͼ����
		ll_dots.removeAllViews();
		dotList = new ArrayList<View>();
		// �����еĵ���뵽�����ļ�������
		for (int i = 0; i < carouselDiagramList.size(); i++) {
			View view = new View(context);
			if (i == 0) {
				view.setBackgroundResource(R.drawable.dot_focus);
			} else {
				view.setBackgroundResource(R.drawable.dot_normal);
			}
			// ����dot�Ĵ�С�ͼ��
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					Utils.dip2px(context, 6), Utils.dip2px(context, 6));
			// ���һ�����Ҳ಻���4������
			if (i == (carouselDiagramList.size() - 1)) {
				params.setMargins(Utils.dip2px(context, 4), 0, 0, 0);
			} else {
				params.setMargins(Utils.dip2px(context, 4), 0,
						Utils.dip2px(context, 4), 0);
			}
			view.setLayoutParams(params);
			// ��dot�������Բ��ֶ�����
			ll_dots.addView(view);
			// ���������뵽�����У���ͼƬ�ı�ʱ������ͨ�����б�ı���ָʾ״̬
			dotList.add(view);
		}
	}

	/**
	 * ��ʼ�ֲ�
	 * 
	 */
	public void startRoll() {
		// ��������run���������µ�ǰҳ��������handler������Ϣ�����õ�ǰ��ǰͼƬ�������ֲ�ͼƬ
		handler.postDelayed(runnableTask, 4000);
	}

	/**
	 * ֹͣ�ֲ� ������Activity��onPause()�����е��ô˷���,ֹͣ�ֲ�,��Activity�ָ���ʱ�������onResume()�����е���
	 * ��ʼ�ֲ��ķ���startRoll();
	 */
	public void stopRoll() {
		// �Ƴ�ǰhandler������ά��������
		if (this != null) {
			if (handler != null) {
				handler.removeCallbacksAndMessages(null);
			}
		}
	}

	class RunnableTask implements Runnable {

		@Override
		public void run() {
			// ����ǰͼƬ����λ��ֵת��Ϊ��һҳ������ֵ
			currentPosition++;
			// ��handler����һ������Ϣ
			handler.obtainMessage().sendToTarget();
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// �������view��Ҫ���ظ�viewpager�Ļ����¼�
			this.getParent().requestDisallowInterceptTouchEvent(true);
			// ��¼��ǰ���µĵ����
			downX = (int) ev.getX();
			downY = (int) ev.getY();
			// �����ֱ����µ�ʱ��ֹͣ�ֲ�
			stopRoll();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) ev.getX();
			int moveY = (int) ev.getY();
			if (Math.abs(moveX - downX) > Math.abs(moveY - downY)) {
				// ˮƽ����λ�ƴ�����ֱ����λ�ƣ�����view��Ҫ���ظ�viewpager�����һ�������
				this.getParent().requestDisallowInterceptTouchEvent(true);
			} else {
				// ��ֱ����λ�ƴ���ˮƽ����λ�ƣ�����view���ظ�viewpager�����һ�������
				this.getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;
		case MotionEvent.ACTION_UP:
			// �����ֱ��ɿ���ʱ��ʼ�ֲ�
			startRoll();
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	// �ӽ������Ƴ�ȥ����õķ���
	@Override
	protected void onDetachedFromWindow() {
		// ֹͣ�ֲ�
		stopRoll();
		super.onDetachedFromWindow();
	}

	// �ֲ�ͼ�����������
	class MyPagerAdapter extends PagerAdapter {
		// TODO
		@Override
		public int getCount() {
			return carouselDiagramList.size() * 2000;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// ������imageUrlListΪ�ջ��ߴ�СΪ0�����ٽ��к������
			if (carouselDiagramList == null || carouselDiagramList.size() == 0) {
				return null;
			}
			// ��positon��ֵ��21���м��ĳ��ֵת��Ϊ0-imageUrlList.size()֮���ĳ��ֵ����������ҵ���Ӧ����Դ
			int newPosition = position % carouselDiagramList.size();
			Log.i("info",
					"instantiateItem  ��======> ��Ӧ�������:" + newPosition
							+ ",position=" + position + ",currentPosition="
							+ currentPosition + "***ʵ���ͼƬλ��**"
							+ carouselDiagramList.get(newPosition).pic_id);
			// �õ���ǰ�ֲ�ͼ����
			KtvAdvertisementInfo carouselDiagram = carouselDiagramList
					.get(newPosition);
			// ���ֲ�ͼ�ĵ���ͼ�Ĳ����ļ�ת��Ϊview����
			View view = View.inflate(context, R.layout.item_poster, null);
			// �󶨿ؼ�
			ImageView imageView = (ImageView) view.findViewById(R.id.iv);
			// ʹ��BitmapUtilͨ��ͼƬurl���س�ͼƬ�����ý�imageView��
			ImageLoader.getInstance().displayImage(carouselDiagram.pic_url,
					imageView);
			// ����ͼƬ����viewpager��
			container.addView(view);
			// ���ø�ͼƬ�ĵ�������¼�
			view.setOnTouchListener(new OnTouchListener() {
				// TODO
				private int downX;
				private long downTime;

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						// ��������ͼƬ��ʱ��ȡ��handler�������񣬼�ֹͣ�ֲ�ͼ���Զ�����
						downX = (int) event.getX();
						downTime = System.currentTimeMillis();
						Log.i("info",
								"onTouch*******************************ACTION_DOWN");
						break;
					case MotionEvent.ACTION_UP:
						// ������뿪ͼƬʱ���ж��Ǵ����������ǵ���¼�
						int upX = (int) event.getX();
						long upTime = System.currentTimeMillis();
						// ����º��뿪��һ��λ�ã��м��С��500ms,��Ϊ����¼�
						if (downX == upX && upTime - downTime < 500) {
							// ȷ��Ϊ����¼���Ҫ��ת������ҳ��
							// Toast.makeText(context,
							// "targetUrlList.get(position) = "+targetUrlList.get(newPosition),
							// Toast.LENGTH_LONG).show();
							// Toast.makeText(context,
							// "url="+carourselDiagram.url,
							// Toast.LENGTH_LONG).show();
							if (itemClickListener != null) {
								// itemClickListener.click(carouselDiagram);
							}
						}
						Log.i("info",
								"onTouch********************************ACTION_UP");
						break;
					case MotionEvent.ACTION_CANCEL:
						// viewpager���ڲ�Ƕ�׵�view���¼���������
						// 1�������¼��������ڲ�view��
						// 2,������������룬���ٶ�δ�ﵽһ��ֵʱ����Ӧ�����¼�������view����
						// 3���������ﵽһ�����룬���Ҽ��ٶȴﵽһ��ֵ���ڲ���view����cancel�¼�(��ᴥ��view��up�¼�)��Ȼ�󽫻������¼�������ؼ�(ViewPager)
						// ������뿪ͼƬʱ����������ֲ�ͼ
						// startRoll();
						Log.i("info",
								"onTouch*********************************ACTION_CANCEL");
						break;
					}
					// ����true��ʾ��ǰ�ؼ���Ӧ���¼������ڽ����¼��ַ�
					return true;
				}
			});
			return view;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

	// �õ��ֲ�ͼ����ͼ����
	public View getView() {
		// ������imageUrlListΪ�ջ��ߴ�СΪ0���򷵻�һ���յ�view
		if (carouselDiagramList == null || carouselDiagramList.size() == 0) {
			return new View(context);
		}
		// ���ֲ�ͼ�Ĳ����ļ�ת��ΪView����
		layout_roll_view = View.inflate(context,
				R.layout.layout_roll_viewpager, null);
		ll_viewpager_container = (LinearLayout) layout_roll_view
				.findViewById(R.id.ll_viewpager_container);
		ll_dots = (LinearLayout) layout_roll_view.findViewById(R.id.ll_dots);
		// ��ʼ���ɹ�����viewpager�������Ϣ(ͼƬ�����⣬��־dot)
		initDot(ll_dots);

		// ��������ͼ�����ֲ�ͼƬ��ӵ�ͷ�������ļ���
		ll_viewpager_container.removeAllViews();
		ll_viewpager_container.addView(this);

		// ��ʼ�����ֲ�ͼ
		if (adapter == null) {
			adapter = new MyPagerAdapter();
			this.setAdapter(adapter);
		} else {
			// �������������������getCount--->instantiateItem
			adapter.notifyDataSetChanged();
		}

		// ��ҳ�������֮���ֵ��ʼ�����Խ�������ѭ������������Ҫȷ����һ�ο�ʼ������imageList.size()�ı���ֻ�а�ȡģ���ֵ��ȥ����
		currentPosition = carouselDiagramList.size() * 1000;
		Log.i("info", "getView==��ǰ���==>" + currentPosition);
		this.setCurrentItem(currentPosition);
		startRoll();
		return layout_roll_view;
	}

	// ����ֲ�ͼ��ռ��Ļ��Ȩ�صõ���Ӧ����ͼ����
	public View getViewByWeight(int weight, int totalWeight) {
		// ������imageUrlListΪ�ջ��ߴ�СΪ0���򷵻�һ���յ�view
		if (carouselDiagramList == null || carouselDiagramList.size() == 0) {
			return new View(context);
		}
		// ���ֲ�ͼ�Ĳ����ļ�ת��ΪView����
		layout_roll_view = View.inflate(context,
				R.layout.layout_roll_viewpager, null);
		ll_viewpager_container = (LinearLayout) layout_roll_view
				.findViewById(R.id.ll_viewpager_container);

		// ���Ȩ�������ֲ�ͼ�ĸ߶�
		int screenHeight = BaseTools.getWindowsHeight((Activity) context);
		android.widget.RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) ll_viewpager_container
				.getLayoutParams();
		layoutParams.height = screenHeight * weight / totalWeight;
		ll_viewpager_container.setLayoutParams(layoutParams);

		ll_dots = (LinearLayout) layout_roll_view.findViewById(R.id.ll_dots);
		// ��ʼ���ɹ�����viewpager�������Ϣ(ͼƬ�����⣬��־dot)
		initDot(ll_dots);

		// ��������ͼ�����ֲ�ͼƬ��ӵ�ͷ�������ļ���
		ll_viewpager_container.removeAllViews();
		ll_viewpager_container.addView(this);

		// ��ʼ�����ֲ�ͼ
		startRoll();
		// ���Խ�������ѭ������������Ҫȷ����һ�ο�ʼ������carouselDiagramList.size()�ı���
		currentPosition = carouselDiagramList.size() * 1000;
		this.setCurrentItem(currentPosition);

		return layout_roll_view;
	}

	// ֮���Զ���һ���ӿڣ�����Ϊ����ʹ�ø�RollViewPager����࣬��new����ʱ������ʵ�ָýӿڲ���д�ڲ���click����
	public interface OnRollViewPagerItemClickListener {
		// ����ʵ�ֽӿ��з�������Ӧ�������ҵ���߼�
		void click(KtvAdvertisementInfo carouselDiagram);
	}

}
