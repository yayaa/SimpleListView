package com.yayandroid.simplelistview;

/**
 * @author Yahya BAYRAMOGLU
 */

public class RowItem {

	/**
	 * Enumeration to demonstrate supported types
	 * 
	 * @author y.bayramoglu
	 *
	 */
	public enum ViewTypes {
		TEXTVIEW, IMAGEVIEW_STATIC, IMAGEVIEW_ASYNC, EDITTEXT
	}

	private ViewTypes viewType;
	private int viewId;
	private String viewValue;
	
	/**
	 * @param viewType
	 * 					: Type of the view to be set
	 * @param viewId
	 * 					: View's id which is defined in layout
	 * @param viewValue
	 * 					: Value to set view
	 */
	public RowItem(ViewTypes viewType, int viewId, String viewValue) {
		this.viewType = viewType;
		this.viewId = viewId;
		this.viewValue = viewValue;
	}

	public ViewTypes getViewType() {
		return viewType;
	}

	public void setViewType(ViewTypes viewType) {
		this.viewType = viewType;
	}

	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

	public String getViewValue() {
		return viewValue;
	}

	public void setViewValue(String viewValue) {
		this.viewValue = viewValue;
	}

}
