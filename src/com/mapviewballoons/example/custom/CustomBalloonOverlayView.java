package com.mapviewballoons.example.custom;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.OverlayItem;
import com.mapviewballoons.R;
import com.readystatesoftware.mapviewballoons.BalloonOverlayView;

public class CustomBalloonOverlayView<Item extends OverlayItem> extends BalloonOverlayView<CustomOverlayItem> {

	private TextView title;
	private TextView snippet;
	private ImageView image;
	
	public CustomBalloonOverlayView(Context context, int balloonBottomOffset) {
		super(context, balloonBottomOffset);
	}
	
	
	/**
	 * 重新绑定新的自定义的View
	 */
	@Override
	protected void setupView(Context context, ViewGroup parent) {
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = mInflater.inflate(R.layout.balloon_overlay_example2, parent);
		
		title = (TextView) view.findViewById(R.id.balloon_item_title);
		snippet = (TextView) view.findViewById(R.id.balloon_item_snippet);
		image = (ImageView) view.findViewById(R.id.balloon_item_image);
	}

	
	/**
	 * 给新的View 绑定数据
	 */
	@Override
	protected void setBalloonData(CustomOverlayItem item, ViewGroup parent) {
		// TODO Auto-generated method stub
		// map our custom item data to fields
		title.setText(item.getTitle());
		snippet.setText(item.getSnippet());
		
		// get remote image from network.
		// bitmap results would normally be cached, but this is good enough for demo purpose.
		image.setImageResource(R.drawable.icon);
		new FetchImageTask() { 
	        protected void onPostExecute(Bitmap result) {
	            if (result != null) {
	            	image.setImageBitmap(result);
	            }
	        }
	    }.execute(item.getImageUrl());
	}
	
	
	/**
	 * 下载图片的异步线程
	 * @author chunjiang.shieh
	 *
	 */
	private class FetchImageTask extends AsyncTask<String, Integer, Bitmap> {
	    @Override
	    protected Bitmap doInBackground(String... arg0) {
	    	Bitmap b = null;
	    	try {
				 b = BitmapFactory.decodeStream((InputStream) new URL(arg0[0]).getContent());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
	        return b;
	    }	
	}
	
}
