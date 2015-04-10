SimpleListView
==============

This library will be usefull if only you do not have to do some special things on your ListView but just show some data. Because SimpleListView has its adapter and items, you just need to give it to source ids and the data which it needs to show. 

There is a type enumeration to inform ListView that which row has which type of widget.

Supported Components: TextView, EditText, ImageView (Static & Async)

```java
public enum ViewTypes {
    TEXTVIEW, IMAGEVIEW_STATIC, IMAGEVIEW_ASYNC, EDITTEXT
}
```

SimpleListAdapter expects ```ListItem``` so you need to put your data into a ListItem object and then create an array with those objects, finally give it to adapter. 
ListItem has two constructor:

1- To display one type items in ListView
```java 
public ListItem(int rowLayout, ArrayList<RowItem> rowItems) 
```

2- To display multi type items in ListView with different layouts and data
```java 
public ListItem(RowTypes rowType, int rowLayout, ArrayList<RowItem> rowItems) 
```

And ListItem requires an array consist of ```RowItem``` objects. RowItem is just to keep data together. You simply tell adapter which ViewType, which widgetId it has and which data it needs to fill it.

```java 
public RowItem(ViewTypes viewType, int viewId, String viewValue)
```

Used Library
==============

[Universal ImageLoader][UniversalImageLoaderUrl] has used to load async image.
[UniversalImageLoaderUrl]: https://github.com/nostra13/Android-Universal-Image-Loader
