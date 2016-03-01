package com.cqc.androidtest;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class TestAdapter extends PagerAdapter {

	public static final String BIGIMAGE = "com.cqc.test.showbigImage";
	public static final String EXTRA_RESID_KEY = "com.cqc.test.imageresourceid";
	public static boolean ISOPENDEBUG = false;
	private Context context;
	private List<Integer> list;

	private boolean isSmall = true;
	private long firstTime = 0;
	private long secondTime = 0;
	private int count = 0;

	private ImageView image;
	private boolean isSmallImage = true;

	public TestAdapter(Context context, List<Integer> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		// TODO Auto-generated method stub

		final int resid = list.get(position % list.size());
		image = new ImageView(context);
//		image.setScaleX(0.5f);
//		image.setScaleY(0.5f);
		image.setImageResource(resid);
		ViewPager pager = (ViewPager) container;
		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (firstTime != 0 && System.currentTimeMillis() - firstTime > 1000) {
					count = 0;
				}
				count++;
				if (count == 1) {
					firstTime = System.currentTimeMillis();
				} else if (count == 2) {
					secondTime = System.currentTimeMillis();

					if (secondTime - firstTime < 1000) {

						if (isSmall) {
							ScaleAnimation sc = new ScaleAnimation(0, 2, 0, 2);
							image.setAnimation(sc);
							sc.start();
						} else {
							ScaleAnimation sc = new ScaleAnimation(0, 1, 0, 1);
							image.setAnimation(sc);
							sc.start();
						}
						isSmall = !isSmall;
						// Intent i = new Intent(BIGIMAGE);
						// i.putExtra(EXTRA_RESID_KEY, resid);
						// context.sendBroadcast(i);
						//
						// printLogs(ISOPENDEBUG, "DoubleClick","Success!!!");
					}

				}

			}
		});
		pager.addView(image);
		return image;

	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		int resid = list.get(position % list.size());
		image = new ImageView(context);
		image.setScaleX(0.5f);
		image.setScaleY(0.5f);
		image.setImageResource(resid);
		ViewPager pager = (ViewPager) container;
		pager.removeView(image);

	}

	private void printLogs(boolean isdebug, String tag, String msg) {
		if (isdebug) {
			Log.d(tag, msg);
		}
	}
}
