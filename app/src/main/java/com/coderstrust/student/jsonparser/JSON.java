package com.coderstrust.student.jsonparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.coderstrust.student.DataStruct.Login;

public class JSON {
	//public Context c;


    public String parseLogin(String Url,String data,Context con){
        JSONParser jParser = new JSONParser();
        String json = jParser.requestPOST(Url, data);
        String rr="error";
        try {
            if(json.startsWith("Error")||json.equals("{}"))
                return "Internet";
            JSONObject jObj = new JSONObject(json);
            String student_id = "", auth_token = "", errorss="";

            try {
                errorss = jObj.getString("error");
            } catch (Exception e) {
            }

            if(errorss.equals("")) {
                try {
                    student_id = jObj.getString("student_id");
                } catch (Exception e) {
                }
                try {
                    auth_token = jObj.getString("auth_token");
                } catch (Exception e) {
                }
                rr="success _ "+student_id+" _ "+auth_token;
            }
            else
                rr="error";
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rr;
    }


    public String parseSignup(String Url,String data,Context con){
        JSONParser jParser = new JSONParser();
        String json = jParser.requestGET(Url, data);
        String rr="error";
        try {
            if(json.startsWith("Error")||json.equals("{}"))
                return "Internet";
            JSONObject jObj = new JSONObject(json);
            String student_id = "", auth_token = "", errorss="";

            try {
                errorss = jObj.getString("error");
            } catch (Exception e) {
            }

            if(errorss.equals("")) {
                try {
                    student_id = jObj.getString("student_id");
                } catch (Exception e) {
                }
                try {
                    auth_token = jObj.getString("student");
                } catch (Exception e) {
                }
                rr="success _ "+student_id+" _ "+auth_token;
            }
            else
                rr="error";
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rr;
    }
	
	
	
	
//	public  ArrayList<Category> parseCategory(String Url){
//
//        ArrayList<Category> temp = new ArrayList<Category>();
//		JSONParser jParser = new JSONParser();
//		String json = jParser.requestPost(Url,"");
//
//		try {
//			if(json.startsWith("Error"))
//				return null;
//
//			JSONObject jObj = new JSONObject(json);
//
//			JSONArray contacts = jObj.getJSONArray("category");
//			System.out.println("category..........  "+contacts.length());
////		    // looping through All Contacts
//		    for(int i = 0; i < contacts.length(); i++){
//		        JSONObject c = contacts.getJSONObject(i);
//
//		        String id = c.getString("id");
//                String name = c.getString("name");
//                String description = c.getString("description");
//                String color = c.getString("color");
//                String homescreen = c.getString("homescreen");
//                String link = c.getString("url");
//
//		        Category nn = new Category(id,name,description,color,homescreen,link) ;
//                AppData.catListAdditionalReference.add(id);
//		        temp.add(nn) ;
//
//		    }
//		} catch (JSONException e) {
//		    e.printStackTrace();
//		}
//		return temp;
//	}
//
//
//    public  ArrayList<News> parseHomeNews(String Url){
//
//        ArrayList<News> temp = new ArrayList<News>();
//        JSONParser jParser = new JSONParser();
//        String json = jParser.requestPost(Url,"");
//
//        try {
//            if(json.startsWith("Error"))
//                return null;
//
//            JSONObject jObj = new JSONObject(json);
//            JSONArray contacts = jObj.getJSONArray("newslist");
//            for(int i = 0; i < contacts.length(); i++){
//                    JSONArray cont = contacts.getJSONArray(i);
//                    for(int j = 0; j < cont.length(); j++){
//                        System.out.println("URL..........  "+j);
//                        JSONObject c = cont.getJSONObject(j);
//
//                        String id = c.getString("id");
//                        String shoulder = c.getString("shoulder");
//                        String headline = c.getString("headline");
//                        String subhead = c.getString("subhead");
//                        String byline = c.getString("byline");
//                        String news_excerpt = c.getString("news_excerpt");
//                        String news_content = c.getString("news_content");
//                        String categoryid = c.getString("categoryid");
//                        String author = c.getString("author");
//                        String colorcode = c.getString("colorcode");
//                        String relatednewsids = c.getString("relatednewsids");
//                        String newstype = c.getString("newstype");
//                        String publishdatetime = c.getString("publishdatetime");
//                        String updatedatatime = c.getString("updatedatatime");
//                        String link = c.getString("link");
//
//
//                        System.out.println("URL..........  ID  "+link);
//
////                        String name = c.getString("name");
////                        String description = c.getString("description");
////                        String color = c.getString("color");
////                        String homescreen = c.getString("homescreen");
////                        String link = c.getString("link");
////                        Log.e("Title", name) ;
////
////                        News nn = new News(id,name,description) ;
////                        AppData.catListAdditionalReference.add(id);
////                        temp.add(nn) ;
//
//                    }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return temp;
//    }
//
//
//    public ArrayList<News> parseCatNews(String Url,Context con, String cat){
//
//        System.out.println("URL..........  "+Url);
//
//        ArrayList<News> temp = new ArrayList<News>();
//        JSONParser jParser = new JSONParser();
//        String json = jParser.requestPost(Url,"");
//
//        System.out.println("URL..........  "+json);
//
//        try {
//            if(json.startsWith("Error"))
//                return null;
//
//            JSONObject jObj = new JSONObject(json);
//            JSONArray contacts = jObj.getJSONArray("newslist");
//            Vector<ContentValues> cVVector = new Vector<ContentValues>(contacts.length());
//
//            for(int i = 0; i < contacts.length(); i++){
////                    JSONArray cont = contacts.getJSONArray(i);
////                    for(int j = 0; j < cont.length(); j++){
//                System.out.println("URL..........  "+i);
//                JSONObject c = contacts.getJSONObject(i);
//
//                String id="",shoulder="",headline="",subhead="",byline ="",
//                        news_excerpt="",news_content="",categoryid="",author="",colorcode="",
//                        relatednewsids="",newstype="",publishdatetime="",updatedatatime="",
//                        link="",image_array="",gallery_array="",video_array="",audio_array="";
//
//                try{id = c.getString("id");}catch (Exception e){}
//                try{shoulder = c.getString("shoulder");}catch (Exception e){}
//                try{headline = c.getString("headline");}catch (Exception e){}
//                try{subhead = c.getString("subhead");}catch (Exception e){}
//                try{byline = c.getString("byline");}catch (Exception e){}
//                try{news_excerpt = c.getString("news_excerpt");}catch (Exception e){}
//                try{news_content = c.getString("news_content");}catch (Exception e){}
//                try{categoryid = c.getString("categoryid");}catch (Exception e){}
//                try{author = c.getString("author");}catch (Exception e){}
//                try{colorcode = c.getString("colorcode");}catch (Exception e){}
//                try{relatednewsids = c.getString("relatednewsids");}catch (Exception e){}
//                try{newstype = c.getString("newstype");}catch (Exception e){}
//                try{publishdatetime = c.getString("publishdatetime");}catch (Exception e){}
//                try{updatedatatime = c.getString("updatedatatime");}catch (Exception e){}
//                try{image_array = c.getString("images");}catch (Exception e){}
//                try{gallery_array = c.getString("img_gallery");}catch (Exception e){}
//                try{video_array = c.getString("videos");}catch (Exception e){}
//                try{audio_array = c.getString("audios");}catch (Exception e){}
//                try{link = c.getString("url");}catch (Exception e){}
//
//                News nn = new News(id,headline,categoryid);
//
//                nn.setShoulder(shoulder);
//                nn.setSubhead(subhead);
//                nn.setByline(byline);
//                nn.setNews_excerpt(news_excerpt);
//                nn.setNews_content(news_content);
//                nn.setAuthor(author);
//                nn.setColorcode(colorcode);
//                nn.setRelatednewsids(relatednewsids);
//                nn.setNewstype(newstype);
//                nn.setPublishdatetime(publishdatetime);
//                nn.setUpdatedatatime(updatedatatime);
//                nn.setImages(image_array);
//                nn.setImg_gallery(gallery_array);
//                nn.setVideos(video_array);
//                nn.setAudios(audio_array);
//                nn.setUrl(link);
//
//                temp.add(nn);
//
//                Calendar cal = Calendar.getInstance();
//                cal.add(Calendar.DATE, 0);
//                String yesterdayDate = NewsContract.getDbDateString(cal.getTime());
//                ContentValues newsValues = new ContentValues();
//
//                Date date = new Date();
//
//                newsValues.put(NewsContract.NewsEntry.COLUMN_NEWS_ID, id);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_SHOULDER, shoulder);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_TITLE, headline);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_SUBHEAD, subhead);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_BYLINE, byline);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_EXCERPT, news_excerpt);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_CONTENT, news_content);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_CAT_ID, categoryid);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_AUTHOR, author);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_NEWSCOLOR, colorcode);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_RELATED_NEWS, relatednewsids);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_NEWS_TYPES, newstype);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_PUB_TIME, publishdatetime);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_UPDATE_TIME, updatedatatime);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_LINK, link);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_IMG, image_array);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_IMG_GAL, gallery_array);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_VID, video_array);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_AUD, audio_array);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_INSERT_DATE, yesterdayDate);
//
//                newsValues.put(NewsContract.NewsEntry.COLUMN_INSERT_DATE_TIME, String.valueOf(date.getTime()));
//
//                cVVector.add(newsValues);
//
////                String cccc[] =cat.split(",");
////                for(int i4=0;i4<cccc.length;i4++)
////                {
////                    //String nId = cvArray[i].getAsString(NewsEntry.COLUMN_NEWS_ID);
////                    System.out.println(">>>>>> FFFFFFFFFF "+cccc[i4]);
////                    con.getContentResolver().delete(NewsContract.NewsEntry.CONTENT_URI,
////                            NewsContract.NewsEntry.COLUMN_CAT_ID + " <= ?",
////                            new String[] {cccc[i4]});
////                }
//
////                        String name = c.getString("name");
////                        String description = c.getString("description");
////                        String color = c.getString("color");
////                        String homescreen = c.getString("homescreen");
////                        String link = c.getString("link");
////                        Log.e("Title", name) ;
////
////                        News nn = new News(id,name,description) ;
////                        AppData.catListAdditionalReference.add(id);
////                        temp.add(nn) ;
//
////                }
//            }
//
//
//            if (cVVector.size() > 0) {
//                ContentValues[] cvArray = new ContentValues[cVVector.size()];
//                cVVector.toArray(cvArray);
////                for(int i=0;i<cVVector.size();i++)
////                {
////                    String nId = cvArray[i].getAsString(NewsContract.NewsEntry.COLUMN_NEWS_ID);
////                    con.getContentResolver().delete(NewsContract.NewsEntry.CONTENT_URI,
////                            NewsContract.NewsEntry.COLUMN_NEWS_ID + " <= ?",
////                            new String[] {nId});
////                }
//
//                Calendar cal = Calendar.getInstance(); //Get's a calendar object with the current time.
//                cal.add(Calendar.DATE, -1); //Signifies yesterday's date
//                String yesterdayDate = NewsContract.getDbDateString(cal.getTime());
//                con.getContentResolver().delete(NewsContract.NewsEntry.CONTENT_URI,
//                        NewsContract.NewsEntry.COLUMN_INSERT_DATE + " <= ?",
//                        new String[] {yesterdayDate});
//
//                con.getContentResolver().bulkInsert(NewsContract.NewsEntry.CONTENT_URI,
//                        cvArray);
//
//
//
//
//
//
//
////                notifyWeather();
//            }
////            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return temp;
//    }
//
//
//    public ArrayList<News> parseDetailsNews(String Url,Context con){
//
//        System.out.println("URLFUCK..........  "+Url);
//
//        ArrayList<News> temp = new ArrayList<News>();
//        JSONParser jParser = new JSONParser();
//        String json = jParser.requestPost(Url,"");
//        try {
//            if(json.startsWith("Error")||json.equals("{}"))
//                return null;
//
//
//            JSONObject jObj = new JSONObject(json);
//            JSONArray contacts = jObj.getJSONArray("newslist");
//
//            System.out.println("URLFUCK 112..........  "+contacts.length());
//
//            for(int i = 0; i < contacts.length(); i++) {
//                JSONObject c = contacts.getJSONObject(i);
//
//                String id = "", shoulder = "", headline = "", subhead = "", byline = "",
//                        news_excerpt = "", news_content = "", categoryid = "", author = "", colorcode = "",
//                        relatednewsids = "", newstype = "", publishdatetime = "", updatedatatime = "",
//                        link = "", image_array = "", gallery_array = "", video_array = "", audio_array = "";
//
//                try {
//                    id = c.getString("id");
//                } catch (Exception e) {
//                }
//
//                System.out.println("URL..........  " + id);
//
//                try {
//                    shoulder = c.getString("shoulder");
//                } catch (Exception e) {
//                }
//                try {
//                    headline = c.getString("headline");
//                } catch (Exception e) {
//                }
//                try {
//                    subhead = c.getString("subhead");
//                } catch (Exception e) {
//                }
//                try {
//                    byline = c.getString("byline");
//                } catch (Exception e) {
//                }
//                try {
//                    news_excerpt = c.getString("news_excerpt");
//                } catch (Exception e) {
//                }
//                try {
//                    news_content = c.getString("news_content");
//                } catch (Exception e) {
//                }
//                try {
//                    categoryid = c.getString("categoryid");
//                } catch (Exception e) {
//                }
//                try {
//                    author = c.getString("author");
//                } catch (Exception e) {
//                }
//                try {
//                    colorcode = c.getString("colorcode");
//                } catch (Exception e) {
//                }
//                try {
//                    relatednewsids = c.getString("relatednewsids");
//                } catch (Exception e) {
//                }
//                try {
//                    newstype = c.getString("newstype");
//                } catch (Exception e) {
//                }
//                try {
//                    publishdatetime = c.getString("publishdatetime");
//                } catch (Exception e) {
//                }
//                try {
//                    updatedatatime = c.getString("updatedatatime");
//                } catch (Exception e) {
//                }
//                try {
//                    image_array = c.getString("images");
//                } catch (Exception e) {
//                }
//                try {
//                    gallery_array = c.getString("img_gallery");
//                } catch (Exception e) {
//                }
//                try {
//                    video_array = c.getString("videos");
//                } catch (Exception e) {
//                }
//                try {
//                    audio_array = c.getString("audios");
//                } catch (Exception e) {
//                }
//                try {
//                    link = c.getString("url");
//                } catch (Exception e) {
//                }
//
//
//                System.out.println("URLFUCK 11..........  "+json);
//
//                News nn = new News(id, headline, categoryid);
//
//                nn.setShoulder(shoulder);
//                nn.setSubhead(subhead);
//                nn.setByline(byline);
//                nn.setNews_excerpt(news_excerpt);
//                nn.setNews_content(news_content);
//                nn.setAuthor(author);
//                nn.setColorcode(colorcode);
//                nn.setRelatednewsids(relatednewsids);
//                nn.setNewstype(newstype);
//                nn.setPublishdatetime(publishdatetime);
//                nn.setUpdatedatatime(updatedatatime);
//                nn.setImages(image_array);
//                nn.setImg_gallery(gallery_array);
//                nn.setVideos(video_array);
//                nn.setAudios(audio_array);
//                nn.setUrl(link);
//
//                temp.add(nn);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return temp;
//    }
//
//
//
//    public String syncHomeNews (Context con){
//        String cat="";
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        String newsXmlStr = null;
//        try {
//            AppData.sharedPref = con.getSharedPreferences("star_pref", con.MODE_PRIVATE);
//            cat = AppData.sharedPref.getString("home_cat", "");
//
//
//            if(cat.equals("")||cat.equals(null))
//            {
//                cat = "1,2,3,4,5,9463";
//                SharedPreferences.Editor editor = AppData.sharedPref.edit();
//                editor.putString("home_cat", cat);
//                editor.commit();
//            }
//            else if(!cat.contains("9463")){
//                cat = cat+",9463";
//                SharedPreferences.Editor editor = AppData.sharedPref.edit();
//                editor.putString("home_cat", cat);
//                editor.commit();
//            }
//
//            System.out.println("Test Home ..... "+ cat);
//
//            final String FORECAST_BASE_URL = AppUrl.homeUrl+cat+"&count=10&offset=0";
//
//            System.out.println("FFFFFFFFFFFFFFFFFF _JSONNEWS  ..........  "+FORECAST_BASE_URL);
//
//            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon().build();
//            URL url = new URL(builtUri.toString());
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//            InputStream inputStream = urlConnection.getInputStream();
//            StringBuffer buffer = new StringBuffer();
//            if (inputStream == null) {
//                return "";
//            }
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line + "\n");
//            }
//            if (buffer.length() == 0) {
//                return "";
//            }
//            newsXmlStr = buffer.toString();
//        } catch (IOException e) {
//            Log.e("Error", "Error ", e);
//            return "";
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (final IOException e) {
//                    Log.e("Error", "Error closing stream", e);
//                }
//            }
//        }
//
//        return  newsXmlStr;
//
//
//    }
//
//
//
//    public  void parseHomeNewsW(String json, String cat,Context con){
//        JSONParser jParser = new JSONParser();
//
//        System.out.println("FFFFFFFFFFFFFFFFFF _JSONNEWS  ..........  "+json);
//        try {
//            if(json.startsWith("Error"))
//                return;
//
//            JSONObject jObj = new JSONObject(json);
//            JSONArray contacts = jObj.getJSONArray("newslist");
//
//            Vector<ContentValues> cVVector = new Vector<ContentValues>(contacts.length());
//            System.out.println("FFFFFFFFFFFFFFFFFF _JSONNEWSL.........  "+contacts.length());
//
//            for(int i = 0; i < contacts.length(); i++){
//
//
////                JSONArray cont = contacts.getJSONArray(i);
////                for(int j = 0; j < cont.length(); j++){
////                    System.out.println("URL..........  "+j);
////                    JSONObject c = cont.getJSONObject(j);
//                JSONObject c = contacts.getJSONObject(i);
//
//                String id="",shoulder="",headline="",subhead="",byline ="",
//                        news_excerpt="",news_content="",categoryid="",author="",colorcode="",
//                        relatednewsids="",newstype="",publishdatetime="",updatedatatime="",
//                        link="",image_array="",gallery_array="",video_array="",audio_array="";
//
//                try{id = c.getString("id");}catch (Exception e){}
//                try{shoulder = c.getString("shoulder");}catch (Exception e){}
//                try{headline = c.getString("headline");}catch (Exception e){}
//                try{subhead = c.getString("subhead");}catch (Exception e){}
//                try{byline = c.getString("byline");}catch (Exception e){}
//                try{news_excerpt = c.getString("news_excerpt");}catch (Exception e){}
//                try{news_content = c.getString("news_content");}catch (Exception e){}
//                try{categoryid = c.getString("categoryid");}catch (Exception e){}
//                try{author = c.getString("author");}catch (Exception e){}
//                try{colorcode = c.getString("colorcode");}catch (Exception e){}
//                try{relatednewsids = c.getString("relatednewsids");}catch (Exception e){}
//                try{newstype = c.getString("newstype");}catch (Exception e){}
//                try{publishdatetime = c.getString("publishdatetime");}catch (Exception e){}
//                try{updatedatatime = c.getString("updatedatatime");}catch (Exception e){}
//                try{image_array = c.getString("images");}catch (Exception e){}
//                try{gallery_array = c.getString("img_gallery");}catch (Exception e){}
//                try{video_array = c.getString("videos");}catch (Exception e){}
//                try{audio_array = c.getString("audios");}catch (Exception e){}
//                try{link = c.getString("url");}catch (Exception e){}
//
//
//
//
//                Calendar cal = Calendar.getInstance();
//                cal.add(Calendar.DATE, 0);
//                String yesterdayDate = NewsContract.getDbDateString(cal.getTime());
//                ContentValues newsValues = new ContentValues();
//
//                Date date = new Date();
//
//
//
//                newsValues.put(NewsContract.NewsEntry.COLUMN_NEWS_ID, id);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_SHOULDER, shoulder);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_TITLE, headline);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_SUBHEAD, subhead);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_BYLINE, byline);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_EXCERPT, news_excerpt);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_CONTENT, news_content);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_CAT_ID, categoryid);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_AUTHOR, author);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_NEWSCOLOR, colorcode);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_RELATED_NEWS, relatednewsids);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_NEWS_TYPES, newstype);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_PUB_TIME, publishdatetime);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_UPDATE_TIME, updatedatatime);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_LINK, link);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_IMG, image_array);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_IMG_GAL, gallery_array);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_VID, video_array);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_AUD, audio_array);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_INSERT_DATE, yesterdayDate);
//                newsValues.put(NewsContract.NewsEntry.COLUMN_INSERT_DATE_TIME, String.valueOf(date.getTime()));
//
//                cVVector.add(newsValues);
//
////                        String name = c.getString("name");
////                        String description = c.getString("description");
////                        String color = c.getString("color");
////                        String homescreen = c.getString("homescreen");
////                        String link = c.getString("link");
////                        Log.e("Title", name) ;
////
////                        News nn = new News(id,name,description) ;
////                        AppData.catListAdditionalReference.add(id);
////                        temp.add(nn) ;
//
////                }
//            }
//
//
//            if (cVVector.size() > 0) {
//                ContentValues[] cvArray = new ContentValues[cVVector.size()];
//                cVVector.toArray(cvArray);
//
//                String cccc[] =cat.split(",");
//                for(int i=0;i<cccc.length;i++)
//                {
//                    //String nId = cvArray[i].getAsString(NewsEntry.COLUMN_NEWS_ID);
//                    System.out.println(">>>>>> FFFFFFFFFF "+cccc[i]);
//                    con.getContentResolver().delete(NewsContract.NewsEntry.CONTENT_URI,
//                            NewsContract.NewsEntry.COLUMN_CAT_ID + " <= ?",
//                            new String[] {cccc[i]});
//                }
//
////                for(int i=0;i<cVVector.size();i++)
////                {
////                    String nId = cvArray[i].getAsString(NewsEntry.COLUMN_NEWS_ID);
////                    getContext().getContentResolver().delete(NewsEntry.CONTENT_URI,
////                            NewsEntry.COLUMN_NEWS_ID + " <= ?",
////                            new String[] {nId});
////                }
//
//                Calendar cal = Calendar.getInstance(); //Get's a calendar object with the current time.
//                cal.add(Calendar.DATE, -1); //Signifies yesterday's date
//                String yesterdayDate = NewsContract.getDbDateString(cal.getTime());
//                con.getContentResolver().delete(NewsContract.NewsEntry.CONTENT_URI,
//                        NewsContract.NewsEntry.COLUMN_INSERT_DATE + " <= ?",
//                        new String[] {yesterdayDate});
//
//                con.getContentResolver().bulkInsert(NewsContract.NewsEntry.CONTENT_URI,
//                        cvArray);
//
//
//
//
//
//
//
////                notifyWeather();
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//
//
//    public String parseVersion (){
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        String newsXmlStr = null;
//        try {
//
//
//            final String FORECAST_BASE_URL = AppUrl.versionUrl;
//
//            System.out.println("FFFFFFFFFFFFFFFFFF _JSONNEWS  ..........  "+FORECAST_BASE_URL);
//
//            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon().build();
//            URL url = new URL(builtUri.toString());
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//            InputStream inputStream = urlConnection.getInputStream();
//            StringBuffer buffer = new StringBuffer();
//            if (inputStream == null) {
//                return "";
//            }
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line + "\n");
//            }
//            if (buffer.length() == 0) {
//                return "";
//            }
//            newsXmlStr = buffer.toString();
//        } catch (IOException e) {
//            Log.e("Error", "Error ", e);
//            return "";
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (final IOException e) {
//                    Log.e("Error", "Error closing stream", e);
//                }
//            }
//        }
//
//        return  newsXmlStr;
//
//
//    }
//
//
//    public  String parseverSionCode(String json){
//        String nn="";
//
//        System.out.println("FFFFFFFFFFFFFFFFFF _JSONNEWS  ..........  "+json);
//        try {
//            if(json.startsWith("Error"))
//                return "";
//
//            JSONObject jObj = new JSONObject(json);
//            JSONArray contacts = jObj.getJSONArray("dsappversion");
//
//            for(int i = 0; i < contacts.length(); i++){
//
//                JSONObject c = contacts.getJSONObject(i);
//                String id="";
//                try{id = c.getString("version_no");}catch (Exception e){}
//                nn = id;
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return nn;
//    }
}