package com.mapviewballoons.example.tapcontrolled;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.OverlayItem;
import com.mapviewballoons.MainApplication;
import com.mapviewballoons.R;
import com.mapviewballoons.example.simple.SimpleItemizedOverlay;
import com.readystatesoftware.maps.OnSingleTapListener;
import com.readystatesoftware.maps.TapControlledMapView;

/**
 * 自定义的地图视图
 * @author chunjiang.shieh
 *
 */
public class TapControlledMap extends MapActivity {
	
	private static final String TAG = TapControlledMap.class.getName();
	private static final String KEY_FOCUSED = "focused_1";
	private static final String KEY_FOCUSED2 = "focused_2";
	
	private TapControlledMapView mMapView; // use the custom TapControlledMapView
	private List<Overlay> mMapOverlays;
	private Drawable mDrawable;
	private Drawable mDrawable2;
	private SimpleItemizedOverlay mSimpleItemizedOverlay;
	private SimpleItemizedOverlay mSimpleItemizedOverlay2;
	
	private GeoPoint point,point2,point3,point4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_example3);
		MainApplication mainApp = (MainApplication) getApplication();
		mainApp.startBMapManager();
		super.initMapActivity(mainApp.getBMapManager());
		initGeoPoint();
		initView();
		initSaveInstantceState(savedInstanceState);
	}
	
	
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	/**
	 * 初始化多个位置点
	 */
	private void initGeoPoint(){
		point = new GeoPoint((int)(39.90923*1E6),(int)(116.357428*1E6));
		point2 = new GeoPoint((int)(39.94923*1E6),(int)(116.397428*1E6));
		point3 = new GeoPoint((int)(39.96923*1E6),(int)(116.437428*1E6));
		point4 = new GeoPoint((int)(39.88923*1E6),(int)(116.464428*1E6));
	}
	
	private void initView(){
		mMapView = (TapControlledMapView) findViewById(R.id.mapview);
		mMapView.setBuiltInZoomControls(true);
		mMapView.setDrawOverlayWhenZooming(true);
		mMapView.setOnSingleTapListener(new OnSingleTapListener() {		
			@Override
			public boolean onSingleTap(MotionEvent e) {
				mSimpleItemizedOverlay.hideAllBalloons();
				return true;
			}
		});
		
		mMapOverlays = mMapView.getOverlays();
		
		mDrawable = getResources().getDrawable(R.drawable.marker);
		mDrawable2 = getResources().getDrawable(R.drawable.marker2);

		/**
		 * 每个覆盖图包含两个点
		 */
		mSimpleItemizedOverlay = new SimpleItemizedOverlay(mDrawable, mMapView);
		mSimpleItemizedOverlay.setShowClose(false);
		mSimpleItemizedOverlay.setShowDisclosure(true);
		mSimpleItemizedOverlay.setSnapToCenter(false);
		
		OverlayItem mOverlayItem = new OverlayItem(point, "Tomorrow Never Dies (1997)", 
				"(M gives Bond his mission in Daimler car)");
		OverlayItem mOverlayItem2 = new OverlayItem(point2, "GoldenEye (1995)", 
				"(Interiors Russian defence ministry council chambers in St Petersburg)");
		mSimpleItemizedOverlay.addOverlay(mOverlayItem);
		mSimpleItemizedOverlay.addOverlay(mOverlayItem2);
		
		mSimpleItemizedOverlay2 = new SimpleItemizedOverlay(mDrawable2, mMapView);
		mSimpleItemizedOverlay.setShowClose(false);
		mSimpleItemizedOverlay.setShowDisclosure(true);
		mSimpleItemizedOverlay.setSnapToCenter(false);
		
		OverlayItem mOverlayItem3 = new OverlayItem(point3, "Sliding Doors (1998)", null);
		OverlayItem mOverlayItem4 = new OverlayItem(point4, "Mission: Impossible (1996)", 
				"(Ethan & Jim cafe meeting)");
		mSimpleItemizedOverlay2.addOverlay(mOverlayItem3);
		mSimpleItemizedOverlay2.addOverlay(mOverlayItem4);
		
		/**
		 * 添加2个覆盖图到覆盖图列表
		 */
		mMapOverlays.add(mSimpleItemizedOverlay);
		mMapOverlays.add(mSimpleItemizedOverlay2);
		
	}
	
	/**
	 * 如果该Activity在后台被回收了 
	 *  savedInstanceState 会不为空，记录之前的状态
	 * @param savedInstanceState
	 */
	private void initSaveInstantceState(Bundle savedInstanceState){
		if(savedInstanceState == null){
			Log.d(TAG, "savedInstanceState is null");
			MapController mMapController = mMapView.getController();
			mMapController.animateTo(point2);
			mMapController.setZoom(16);
		}else{
			Log.d(TAG, "savedInstanceState is not null");
			int focusedIndex = savedInstanceState.getInt(KEY_FOCUSED,-1);
			int focused2Index = savedInstanceState.getInt(KEY_FOCUSED2,-1);
			if(focusedIndex != -1){
				mSimpleItemizedOverlay.setFocus(mSimpleItemizedOverlay.getItem(focusedIndex));
			}
			if(focused2Index != -1){
				mSimpleItemizedOverlay2.setFocus(mSimpleItemizedOverlay2.getItem(focused2Index));
			}
		}
		
	}
	
	/**
	 * Activity进入后台状态时保存当前Activity的状态
	 * 
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d(TAG, "----->onSaveInstanceState");
		if(mSimpleItemizedOverlay.getFocus() != null)
			outState.putInt(KEY_FOCUSED, mSimpleItemizedOverlay.getLastFocusedIndex());
		if(mSimpleItemizedOverlay2.getFocus() != null)
			outState.putInt(KEY_FOCUSED2, mSimpleItemizedOverlay2.getLastFocusedIndex());
		super.onSaveInstanceState(outState);
	}

}
