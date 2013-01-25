package com.mapviewballoons.example.custom;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.baidu.mapapi.MapView;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;
import com.readystatesoftware.mapviewballoons.BalloonOverlayView;

public class CustomItemizedOverlay extends BalloonItemizedOverlay<CustomOverlayItem> {
	
	private Context mContext;
	
	private ArrayList<CustomOverlayItem> mOverlayItems = new ArrayList<CustomOverlayItem>();

	public CustomItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
		this.mContext = mapView.getContext();
	}

	
	public void addOverlay(CustomOverlayItem mOverlayItem){
		mOverlayItems.add(mOverlayItem);
		populate();
	}
	
	@Override
	protected CustomOverlayItem createItem(int arg0) {
		return mOverlayItems.get(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlayItems.size();
	}

	
	@Override
	protected boolean onBalloonTap(int index, CustomOverlayItem item) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	protected BalloonOverlayView<CustomOverlayItem> createBalloonOverlayView() {
		// TODO Auto-generated method stub
		return new CustomBalloonOverlayView<CustomOverlayItem>(mContext, getBalloonBottomOffset());
	}
}
