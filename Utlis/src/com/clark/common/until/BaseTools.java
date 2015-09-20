package com.clark.common.until;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
public class BaseTools {
	
	public final static int getWindowsWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	
	public final static int getWindowsHeight(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
	
	
	/**
	 * @param list	��ǰ����
	 * @param pageSize	ÿҳ��ʾ�������
	 * @return	��һҳ������ֵ
	 * @author xuhui.han
	 */
	public static <T> int getRecordNextPageIndex(List<T> list, int pageSize){
		if(list == null || list.size() == 0){
			return 1;
		}else{
			return (list.size()-1)/pageSize + 2;
		}
	}
	
	/**
	 * ���������
	 */
	@SuppressWarnings("static-access")
	public static void hideSoftInput(Activity activity){
		InputMethodManager imm = ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE));
		imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
