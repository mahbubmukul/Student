package com.coderstrust.student.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderstrust.student.R;


public class FeedAdapter extends BaseAdapter {


	String [] team;
	int [] img;
	Context ctx;

	public FeedAdapter(Context ctx, String[] team, int[] img) {
		this.ctx = ctx;
		this.team = team;
		this.img = img;
	}

	public FeedAdapter(Context ctx) {
		this.ctx = ctx;
	}

	static class ViewHolder{
		TextView venue;
		ImageView team1_image;
		TextView cap;
		TextView mat;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return team.length;
		return 5;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		convertView= LayoutInflater.from(ctx).inflate(R.layout.feed_list, parent, false);
		
//		ViewHolder Holder=new ViewHolder();
//		String[] txt = team[position].split("_");
//		Holder.venue =(TextView) convertView.findViewById(R.id.team1_name);
//		Holder.venue.setText(txt[0]);
//		Holder.cap =(TextView) convertView.findViewById(R.id.cap);
//		Holder.cap.setText("Capacity: "+txt[1]);
//		Holder.mat =(TextView) convertView.findViewById(R.id.mat);
//		Holder.mat.setText("Matches: "+txt[2]);
//		Holder.team1_image= (ImageView) convertView.findViewById(R.id.team1_img);
//		Holder.team1_image.setImageResource(img[position]);
		return convertView;
	}
	
	
	

}
