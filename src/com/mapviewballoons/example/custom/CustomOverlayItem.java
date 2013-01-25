package com.mapviewballoons.example.custom;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.OverlayItem;

public class CustomOverlayItem extends OverlayItem {
	
	
	private String mImageUrl;

	public CustomOverlayItem(GeoPoint point,
			String title, String snippet,String imageUrl) {
		super(point, title, snippet);
		this.mImageUrl = imageUrl;
	}

	public String getImageUrl() {
		return mImageUrl;
	}

	public void setImageUrl(String mImageUrl) {
		this.mImageUrl = mImageUrl;
	}
	
	
	

}
