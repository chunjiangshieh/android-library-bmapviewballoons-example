package com.mapviewballoons.example.simple;

import java.util.List;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.OverlayItem;
import com.mapviewballoons.MainApplication;
import com.mapviewballoons.R;

/**
 * Ĭ������ View�ĵ�ͼ���
 * @author chunjiang.shieh
 *
 */
public class SimpleMap extends MapActivity {
	private static final String TAG = SimpleMap.class.getName();
	private static final String KEY_FOCUSED = "focused_1";
	private static final String KEY_FOCUSED2 = "focused_2";
	
	private MapView mMapView;
	private List<Overlay> mMapOverlays;
	
	private Drawable mDrawable,mDrawable2;
	
	private SimpleItemizedOverlay mSimpleItemizedOverlay,mSimpleItemizedOverlay2;
	
	private GeoPoint point,point2,point3,point4;
	 
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
	 * ��ʼ�����λ�õ�
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
		
		mDrawable = getResources().getDrawable(R.drawable.marker);
		mDrawable2 = getResources().getDrawable(R.drawable.marker2);

		/**
		 * ÿ������ͼ����������
		 */
		mSimpleItemizedOverlay = new SimpleItemizedOverlay(mDrawable, mMapView);
		OverlayItem mOverlayItem = new OverlayItem(point, "Tomorrow Never Dies (1997)", 
				"(M gives Bond his mission in Daimler car)");
		OverlayItem mOverlayItem2 = new OverlayItem(point2, "GoldenEye (1995)", 
				"(Interiors Russian defence ministry council chambers in St Petersburg)");
		mSimpleItemizedOverlay.addOverlay(mOverlayItem);
		mSimpleItemizedOverlay.addOverlay(mOverlayItem2);
		
		mSimpleItemizedOverlay2 = new SimpleItemizedOverlay(mDrawable2, mMapView);
		OverlayItem mOverlayItem3 = new OverlayItem(point3, "Sliding Doors (1998)", null);
		OverlayItem mOverlayItem4 = new OverlayItem(point4, "Mission: Impossible (1996)", 
				"(Ethan & Jim cafe meeting)");
		mSimpleItemizedOverlay2.addOverlay(mOverlayItem3);
		mSimpleItemizedOverlay2.addOverlay(mOverlayItem4);
		
		/**
		 * ���2������ͼ������ͼ�б�
		 */
		mMapOverlays.add(mSimpleItemizedOverlay);
		mMapOverlays.add(mSimpleItemizedOverlay2);
		
	}
	
	/**
	 * �����Activity�ں�̨�������� 
	 *  savedInstanceState �᲻Ϊ�գ���¼֮ǰ��״̬
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
	 * Activity�����̨״̬ʱ���浱ǰActivity��״̬
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
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * �����˵�
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 0, 1, "Remove Overlay");
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() == 0){
			//�Ƴ�ǰ�����ص�����������ͼ
			if(mSimpleItemizedOverlay.getFocus() != null){
				mSimpleItemizedOverlay.hideBalloon();
			}
			//�Ӹ���ͼ�б��Ƴ�����ͼ
			mMapOverlays.remove(mSimpleItemizedOverlay);
			mMapView.invalidate();
		}
		return true;
	}

}
