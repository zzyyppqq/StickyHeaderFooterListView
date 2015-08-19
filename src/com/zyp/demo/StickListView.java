package com.zyp.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

public class StickListView extends Activity {
	private ListView lv;

	private LinearLayout invis;

	private FrameLayout.LayoutParams layoutParams;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sticky);
		invis = (LinearLayout) findViewById(R.id.invis);
		
		
		layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.BOTTOM;
		invis.setLayoutParams(layoutParams);
		
		
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < 50; i++) {
			list.add("条目"+i);
		}

		lv = (ListView) findViewById(R.id.lv);
		View header = View.inflate(this, R.layout.stick_header, null);//头部内容
		lv.addHeaderView(header);//添加头部
		lv.addHeaderView(View.inflate(this, R.layout.stick_action, null));//ListView条目中的悬浮部分 添加到头部

		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list));
		lv.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				Log.i("@@@", firstVisibleItem+"__"+visibleItemCount+"__"+totalItemCount);
				if (firstVisibleItem >= 1) {
					invis.setVisibility(View.VISIBLE);
					layoutParams.gravity = Gravity.TOP;
					invis.setLayoutParams(layoutParams);
					Log.i("@@@", "top");

				}else if(visibleItemCount <=2){ 
					invis.setVisibility(View.VISIBLE);
					layoutParams.gravity = Gravity.BOTTOM;
					invis.setLayoutParams(layoutParams);
					Log.i("@@@", "bottom");
				}else{
					invis.setVisibility(View.GONE);

				}
			}
		});
	}

}
