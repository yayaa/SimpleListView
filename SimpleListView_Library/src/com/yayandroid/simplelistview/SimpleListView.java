package com.yayandroid.simplelistview;

/**
 * @author Yahya BAYRAMOÐLU
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View; 
import android.widget.AdapterView;
import android.widget.ListView;

public class SimpleListView extends ListView {
	
	/**
	 * Interface to set a custom type enumeration
	 * 
	 * @author y.bayramoglu
	 *
	 */
	public interface RowTypes {
	}

	/**
	 * Private listener to deliver item info including type
	 */
	private SimpleClickListener onSimpleClickListener;

	/**
	 * Interface to handle onItemClick for different type items
	 * 
	 * @author y.bayramoglu
	 *
	 */
	public interface SimpleClickListener {
		public void onSimpleClick(AdapterView<?> parent, View view,
				int position, long id, RowTypes type);
	} 

	public SimpleListView(Context context) {
		super(context);
		Configure();
	}

	public SimpleListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Configure();
	}

	public SimpleListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		Configure();
	}

	private void Configure() {
		
		setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				if (getOnSimpleClickListener() != null && getAdapter() != null)
					getOnSimpleClickListener().onSimpleClick(
							parent, view, position, id,
							GetTypeForPosition(position));
			}

		}); 
		
	}
	
	/**
	 * Get given item's type as in enumeration
	 * 
	 * @param position
	 * @return SimpleTypes current item's type
	 */
	private RowTypes GetTypeForPosition(int position) {  
		ListItem item = (ListItem) getAdapter().getItem(position);
		return item.getRowType();
	}

	public SimpleClickListener getOnSimpleClickListener() {
		return onSimpleClickListener;
	}

	public void setOnSimpleClickListener(
			SimpleClickListener onItemClickListener) {
		this.onSimpleClickListener = onItemClickListener;
	}

}
