package com.yayandroid.simplelistview;

/**
 * @author Yahya BAYRAMOÐLU
 */

import java.util.ArrayList; 

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yayandroid.simplelistview.SimpleListView.RowTypes;

public class ListItem {

	private RowTypes rowType;
	private ArrayList<RowItem> rowItems;
	private int rowLayout; 
	private TextChangedListener editTextListener;

	/**
	 * Interface to dispatch TextWatcher functions to main activity
	 * 
	 * @author y.bayramoglu
	 *
	 */
	public interface TextChangedListener {
		public void afterTextChanged(Editable s);
		public void beforeTextChanged(CharSequence s, int start, int count, int after); 
		public void onTextChanged(CharSequence s, int start, int before, int count);
	}
	
	/**
	 * This constructor is needed to set single type list item 
	 * 
	 * @param rowLayout
	 *            : layoutId to use on inflating row
	 * @param rowItems
	 *            : RowItem list to get and set data to relative positions 
	 */
	public ListItem(int rowLayout, ArrayList<RowItem> rowItems) {
		this.rowLayout = rowLayout;
		this.rowItems = new ArrayList<RowItem>(rowItems);
	}

	/**
	 * This constructor is needed to set multi type list items
	 * 
	 * @param rowType
	 *            : type for recognizing the item
	 * @param rowLayout
	 *            : layoutId to use on inflating row
	 * @param rowItems
	 *            : RowItem list to get and set data to relative positions
	 */
	public ListItem(RowTypes rowType, int rowLayout, ArrayList<RowItem> rowItems) {
		this.rowType = rowType;
		this.rowLayout = rowLayout;
		this.rowItems = new ArrayList<RowItem>(rowItems);
	}

	public View getView(LayoutInflater inflater, View convertView,
			final ViewGroup parent, final int position) {

		if (convertView == null)
			convertView = inflater.inflate(rowLayout, parent, false);

		for (int i = 0; i < rowItems.size(); i++) {
			switch (rowItems.get(i).getViewType()) {
			case TEXTVIEW:
				TextView tv = (TextView) convertView.findViewById(rowItems.get(
						i).getViewId());
				if (tv != null)
					tv.setText(rowItems.get(i).getViewValue());
				break;
			case IMAGEVIEW_STATIC:
				ImageView img = (ImageView) convertView.findViewById(rowItems
						.get(i).getViewId());
				if (img != null)
					img.setImageResource(Integer.parseInt(rowItems.get(i)
							.getViewValue()));
				break;
			case IMAGEVIEW_ASYNC:
				ImageView imgAsync = (ImageView) convertView
						.findViewById(rowItems.get(i).getViewId());
				if (imgAsync != null)
					ImageLoader.getInstance().displayImage(
							rowItems.get(i).getViewValue(), imgAsync);
				break;
			case EDITTEXT:
				final EditText edit = (EditText) convertView
						.findViewById(rowItems.get(i).getViewId());
				if (edit != null) {
					edit.setTag(rowItems.get(i));
					edit.setText(rowItems.get(i).getViewValue());
					edit.addTextChangedListener(new TextWatcher() {

						@Override
						public void afterTextChanged(Editable s) {
							((RowItem) edit.getTag()).setViewValue(s.toString());
							
							if(editTextListener != null)
								editTextListener.afterTextChanged(s);
						}

						@Override
						public void beforeTextChanged(CharSequence arg0,
								int arg1, int arg2, int arg3) {
							if(editTextListener != null)
								editTextListener.beforeTextChanged(arg0, arg1, arg2, arg3);
						}

						@Override
						public void onTextChanged(CharSequence s, int arg1,
								int arg2, int arg3) {
							if(editTextListener != null)
								editTextListener.onTextChanged(s, arg1, arg2, arg3);
						}

					});
				}

				convertView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						long id = ((ListView) parent).getAdapter().getItemId(
								position);
						((ListView) parent).getOnItemClickListener()
								.onItemClick((AdapterView<?>) parent, v,
										position, id);
					}
				});

				break;
			}
		}
		return convertView;

	}
	
	public ArrayList<RowItem> getInnerItems() {
		return rowItems;
	}

	public RowTypes getRowType() {
		return rowType;
	} 

	public int getRowTypeId() {
		if (rowType != null)
			return ((Enum<?>) rowType).ordinal();
		else
			return '\0';
	} 

	public void setOnTextChangedListener(TextChangedListener editTextListener) {
		this.editTextListener = editTextListener;
	}

}
