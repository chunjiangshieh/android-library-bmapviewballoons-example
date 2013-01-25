package com.mapviewballoons.example.custom;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.Overlay;
import com.mapviewballoons.MainApplication;
import com.mapviewballoons.R;

/**
 * 自定义气球View的地图风格
 * @author chunjiang.shieh
 *
 */
public class CustomMap extends MapActivity {
	
	private static final String TAG = CustomMap.class.getName();
	
	private static final String KEY_FOCUSED = "focused_1";
	private static final String KEY_FOCUSED2 = "focused_2";
	
	
	private GeoPoint point,point2,point3,point4;
	
	
	private MapView mMapView;
	private List<Overlay> mMapOverlays;
	private Drawable mDrawable;
	private Drawable mDrawable2;
	private CustomItemizedOverlay mCustomItemizedOverlay;
	private CustomItemizedOverlay mCustomItemizedOverlay2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		MainApplication mainApp = (MainApplication) getApplication();
		mainApp.startBMapManager();
		super.initMapActivity(mainApp.getBMapManager());
		initGeoPoint();
		initView();
		initSaveInstantceState(savedInstanceState);
	}
	
	/**
	 * 初始化所有的地理位置点
	 */
	private void initGeoPoint(){
		point = new GeoPoint((int)(39.90923*1E6),(int)(116.357428*1E6));
		point2 = new GeoPoint((int)(39.94923*1E6),(int)(116.397428*1E6));
		point3 = new GeoPoint((int)(39.96923*1E6),(int)(116.437428*1E6));
		point4 = new GeoPoint((int)(39.88923*1E6),(int)(116.464428*1E6));
	}
	
	
	private void initView(){
		mMapView = (MapView) findViewById(R.id.mapview);
		mMapView.setBuiltInZoomControls(true);
		mMapView.setDrawOverlayWhenZooming(true);
		
		
		mMapOverlays = mMapView.getOverlays();
		
		mDrawable = getResources().getDrawable(R.drawable.marker); //红色的圆点
		mDrawable2 = getResources().getDrawable(R.drawable.marker2); //蓝色的圆点

		/**
		 * 每个覆盖图包含两个点
		 */
		mCustomItemizedOverlay = new CustomItemizedOverlay(mDrawable, mMapView);
		CustomOverlayItem mOverlayItem = new CustomOverlayItem(point, "Tomorrow Never Dies (1997)", 
				"(M gives Bond his mission in Daimler car)","http://ia.media-imdb.com/images/M/MV5BMTM1MTk2ODQxNV5BMl5BanBnXkFtZTcwOTY5MDg0NA@@._V1._SX40_CR0,0,40,54_.jpg");
		CustomOverlayItem mOverlayItem2 = new CustomOverlayItem(point2, "GoldenEye (1995)", 
				"(Interiors Russian )","http://ia.media-imdb.com/images/M/MV5BMzk2OTg4MTk1NF5BMl5BanBnXkFtZTcwNjExNTgzNA@@._V1._SX40_CR0,0,40,54_.jpg");
		mCustomItemizedOverlay.addOverlay(mOverlayItem);
		mCustomItemizedOverlay.addOverlay(mOverlayItem2);
		
		mCustomItemizedOverlay2 = new CustomItemizedOverlay(mDrawable2, mMapView);
		CustomOverlayItem mOverlayItem3 = new CustomOverlayItem(point3, "Sliding Doors (1998)", null,"http://ia.media-imdb.com/images/M/MV5BMjAyNjk5Njk0MV5BMl5BanBnXkFtZTcwOTA4MjIyMQ@@._V1._SX40_CR0,0,40,54_.jpg");
		CustomOverlayItem mOverlayItem4 = new CustomOverlayItem(point4, "Mission: Impossible (1996)", 
				"(Ethan & Jim cafe meeting)",null);
		mCustomItemizedOverlay2.addOverlay(mOverlayItem3);
		mCustomItemizedOverlay2.addOverlay(mOverlayItem4);
		
		/**
		 * 添加2个覆盖图到覆盖图列表
		 */
		mMapOverlays.add(mCustomItemizedOverlay);
		mMapOverlays.add(mCustomItemizedOverlay2);
		
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
				mCustomItemizedOverlay.setFocus(mCustomItemizedOverlay.getItem(focusedIndex));
			}
			if(focused2Index != -1){
				mCustomItemizedOverlay2.setFocus(mCustomItemizedOverlay2.getItem(focused2Index));
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
		if(mCustomItemizedOverlay.getFocus() != null)
			outState.putInt(KEY_FOCUSED, mCustomItemizedOverlay.getLastFocusedIndex());
		if(mCustomItemizedOverlay2.getFocus() != null)
			outState.putInt(KEY_FOCUSED2, mCustomItemizedOverlay2.getLastFocusedIndex());
		super.onSaveInstanceState(outState);
	}
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
