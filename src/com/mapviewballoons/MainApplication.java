package com.mapviewballoons;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;


import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MainApplication extends Application {
	
	private static final String TAG = MainApplication.class.getName();
	/**
	 *  ��Ȩ��KEY
	 *  �����ַ��http://dev.baidu.com/wiki/static/imap/key/
	 */	
	private String mStrKey = "CB6E2B6BEF8CDA5FA34192DEA322FCDDF2279008";
	
	
	private static MainApplication mMainApplication;
	/**
	 * �ٶȵ�ͼ�Ĺ�����
	 */
	private BMapManager mBMapManager;
	
	
	
	@Override
	public void onCreate() {
		mMainApplication = this;
		/**
		 * ��ʼ���ٶȵ�ͼ�Ĺ�����
		 */
		mBMapManager = new BMapManager(this);
		mBMapManager.init(mStrKey, mMKGeneralListener);
		super.onCreate();
	}
	
	
	@Override
	public void onTerminate() {
		/**
		 * �˳�Ӧ�õ�ʱ���ͷ���Դ
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
	 * ���õļ����¼�
	 * 1.�����������
	 * 2.������Ȩ��֤����
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
	 * �����ٶȵ�ͼ������
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
