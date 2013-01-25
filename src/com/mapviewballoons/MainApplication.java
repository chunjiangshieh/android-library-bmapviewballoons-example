package com.mapviewballoons;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;


import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MainApplication extends Application {
	
	private static final String TAG = MainApplication.class.getName();
	/**
	 *  授权的KEY
	 *  申请地址：http://dev.baidu.com/wiki/static/imap/key/
	 */	
	private String mStrKey = "CB6E2B6BEF8CDA5FA34192DEA322FCDDF2279008";
	
	
	private static MainApplication mMainApplication;
	/**
	 * 百度地图的管理类
	 */
	private BMapManager mBMapManager;
	
	
	
	@Override
	public void onCreate() {
		mMainApplication = this;
		/**
		 * 初始化百度地图的管理者
		 */
		mBMapManager = new BMapManager(this);
		mBMapManager.init(mStrKey, mMKGeneralListener);
		super.onCreate();
	}
	
	
	@Override
	public void onTerminate() {
		/**
		 * 退出应用的时候释放资源
		 */
		if(mBMapManager != null){
			mBMapManager.destroy();
			mBMapManager = null;
		}
		super.onTerminate();
	}
	
	public static Context getContext(){
		if(mMainApplication == null){
			mMainApplication = new MainApplication();
		}
		return mMainApplication;
	}


	
	public BMapManager getBMapManager() {
		return mBMapManager;
	}


	public void setBMapManager(BMapManager mBMapManager) {
		this.mBMapManager = mBMapManager;
	}

	
	
	/**
	 * 常用的监听事件
	 * 1.处理网络错误
	 * 2.处理授权验证错误
	 */
	private MKGeneralListener mMKGeneralListener = new MKGeneralListener() {
		
		@Override
		public void onGetPermissionState(int arg0) {
			// TODO Auto-generated method stub
			Log.d(TAG, "GetPermissionState: "+arg0);
		}
		
		@Override
		public void onGetNetworkState(int arg0) {
			// TODO Auto-generated method stub
			Log.d(TAG, "GetNetworkState:"+arg0);
			
		}
	};
	

	/**
	 * 启动百度地图管理者
	 * @return
	 */
	public void startBMapManager(){
		if(mBMapManager == null){
			mBMapManager = new BMapManager(this);
			mBMapManager.init(mStrKey, mMKGeneralListener);
		}
		mBMapManager.start();
	}
	
	
}
