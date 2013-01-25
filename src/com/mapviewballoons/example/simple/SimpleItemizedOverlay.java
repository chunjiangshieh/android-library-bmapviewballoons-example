package com.mapviewballoons.example.simple;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

public class SimpleItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {

	private Context mContext;
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	
	public SimpleItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
		setBalloonBottomOffset(10);
		mContext = mapView.getContext();
	}
	
	

	public void addOverlay(OverlayItem overlayItem){
		mOverlays.add(overlayItem);
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int arg0) {
		return mOverlays.get(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}

	
	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
//		Toast.makeText(mContext, "Title:"+item.getTitle() +"\nSnippet:"
//				+item.getSnippet(), Toast.LENGTH_LONG).show();
		return true;
	}
}
