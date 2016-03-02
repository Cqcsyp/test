package com.cqc.androidtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
         
	private ViewPager pager;
    private String tata = "dsaw";
	private List<Integer> list;
	private ImageView image;
	private TestAdapter adapter;
	private ImageView imageView;

	private long firstTime = 0;
	private long secondTime = 0;
	private int count = 0;

	private BroadcastReceiver bigbr;

	private static final int[] IMAGERES = { R.drawable.cat1, R.drawable.cat2, R.drawable.cat3, R.drawable.cat4,
			R.drawable.cat5 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pager = (ViewPager) findViewById(R.id.pager);
		imageView = (ImageView) findViewById(R.id.iv);
		list = new ArrayList<Integer>();

		for (int i : IMAGERES) {
			list.add(i);
		}
		adapter = new TestAdapter(this, list);

		pager.setAdapter(adapter);
		pager.setCurrentItem(Integer.MAX_VALUE / 2);

		bigbr = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				int intExtra = intent.getIntExtra(TestAdapter.EXTRA_RESID_KEY, 0);
				imageView.setImageResource(intExtra);
				imageView.setVisibility(View.VISIBLE);
			}
		};
		IntentFilter intentf1 = new IntentFilter(TestAdapter.BIGIMAGE);
		registerReceiver(bigbr, intentf1);

		// Doublick Listener
		imageView.setOnClickListener(new OnClickListener() {

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

						imageView.setVisibility(View.GONE);

					}

				}

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(bigbr);
	}

}
