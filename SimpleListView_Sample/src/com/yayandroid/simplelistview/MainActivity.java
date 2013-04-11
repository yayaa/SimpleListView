package com.yayandroid.simplelistview;

/**
 * @author Yahya BAYRAMOÐLU
 */

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration; 
import com.yayandroid.simplelistview.SimpleListView.RowTypes;
import com.yayandroid.simplelistview.RowItem.ViewTypes;
import com.yayandroid.simplelistview.SimpleListView.SimpleClickListener;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {

	private SimpleListView list;
	private SimpleListAdapter adapter;

	/**
	 * Enumeration to specify row types:
	 * Just give some types so that you could recognize the item 
	 * 	# Must implement RowTypes!
	 * 
	 * @author y.bayramoglu
	 * 
	 */
	public enum ItemTypes implements RowTypes {
		TYPE1, TYPE2, TYPE3
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		/*
		 * Create an imageLoader object to use on downloading images. If there will
		 * be an asyncImageLoading in your listItem, then imageLoader should be
		 * initialized here so it can be used in adapter by getting instance.
		 */
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
		ImageLoader loader = ImageLoader.getInstance();
		loader.init(config);

		list = (SimpleListView) findViewById(R.id.list);
		CreateAdapter(1);
		
		// set an itemClickListener
		list.setOnSimpleClickListener(new SimpleClickListener() {
			@Override
			public void onSimpleClick(AdapterView<?> parent, View view,
					int position, long id, RowTypes type) {

				if (type != null) {
					Toast.makeText(
							getApplicationContext(),
							"Type: " + type.toString() + ", Position: "
									+ position, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(),
							"Position: " + position, Toast.LENGTH_SHORT).show();
				}

			}
		});
	}
	
	/** Sample method to create adapter and set its necessary methods */
	private void CreateAdapter(int count) {
		
		// create adapter
		if(count == 1) { 
			adapter = new SimpleListAdapter(this, GetListItemsForSingle());
		} else {
			adapter = new SimpleListAdapter(this, GetListItemsForMulti());
		}
		
		// set its typeCount, this is a MUST
		adapter.setRowTypeCount(count);
		
		// and set it to listView
		list.setAdapter(adapter);
		
	}
	
	/** Sample method to show how to arrange items for one type list item */
	public ArrayList<ListItem> GetListItemsForSingle() {
		ArrayList<ListItem> items = new ArrayList<ListItem>();

		for (int i = 0; i < 100; i++) {

			String value = "singleItem " + i; 
			ArrayList<RowItem> innerItems = new ArrayList<RowItem>();

			innerItems.add(new RowItem(ViewTypes.TEXTVIEW, R.id.text1, value));
			innerItems.add(new RowItem(ViewTypes.TEXTVIEW, R.id.text2, value));  

			ListItem item = new ListItem(R.layout.sample_item2, innerItems);
			items.add(item);
			
		}

		return items;
	}
 
	/** Sample method to show how to arrange multi type items */
	public ArrayList<ListItem> GetListItemsForMulti() {
		ArrayList<ListItem> items = new ArrayList<ListItem>(); 

		for (int i = 0; i < 100; i++) {
			
			String value = "multiItem " + i; 
			ItemTypes type;
			int layoutId;
			ArrayList<RowItem> innerItems = new ArrayList<RowItem>();
			
			if (i % 3 == 0) {
				innerItems.add(new RowItem(ViewTypes.TEXTVIEW, R.id.text1, value));
				innerItems.add(new RowItem(ViewTypes.TEXTVIEW, R.id.text2, value));
				type = ItemTypes.TYPE1;
				layoutId = R.layout.sample_item1; 
			} else if (i % 5 == 0) {
				innerItems.add(new RowItem(ViewTypes.TEXTVIEW, R.id.text1, value));
				type = ItemTypes.TYPE2;
				layoutId = R.layout.sample_item2; 
			} else {
				String path = "https://www.google.com.tr/images/srpr/logo4w.png";
				innerItems.add(new RowItem(ViewTypes.IMAGEVIEW_ASYNC, R.id.imageView, path));
				innerItems.add(new RowItem(ViewTypes.TEXTVIEW, R.id.textView, value));
				innerItems.add(new RowItem(ViewTypes.IMAGEVIEW_STATIC, R.id.imageView2, String.valueOf(R.drawable.ic_launcher)));
				innerItems.add(new RowItem(ViewTypes.EDITTEXT, R.id.editText, value));
				type = ItemTypes.TYPE3;
				layoutId = R.layout.sample_item3; 
			}
			
			ListItem item = new ListItem(type, layoutId, innerItems); 
			items.add(item);
			
		}

		return items;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("MultiType");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle().toString().equals("MultiType")) { 
			item.setTitle("Single");
			CreateAdapter(ItemTypes.values().length); 
		} else { 
			item.setTitle("MultiType"); 
			CreateAdapter(1); 
		}
		return true;
	}
	
}
