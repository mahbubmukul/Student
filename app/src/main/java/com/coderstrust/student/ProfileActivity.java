package com.coderstrust.student;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coderstrust.student.Adapter.ContactsAdapter;
import com.coderstrust.student.Adapter.FeedAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences prefs = this.getSharedPreferences(
                "CT_Prefs", Context.MODE_PRIVATE);

        String picUrl = prefs.getString("Profile_Pic", null);
        String Name = prefs.getString("Name", null);
        String trustScore = prefs.getString("Trust_Score", null);


//        ListView lv = (ListView) findViewById(R.id.success_list);
//        FeedAdapter ad = new FeedAdapter(this);
//        lv.setAdapter(ad);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(this);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        mCollapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setCollapsingToolbarLayoutTitle(Name);


        CircleImageView profPic = (CircleImageView) findViewById(R.id.menu_img);
        TextView nameTxt =(TextView) findViewById(R.id.menu_name);
        TextView tcTxt =(TextView) findViewById(R.id.trust_scr);

        nameTxt.setText(Name);
        tcTxt.setText(trustScore);

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(R.mipmap.icon)
                .showImageOnFail(R.mipmap.icon)
                .showImageOnLoading(R.mipmap.icon).build();


        imageLoader.displayImage(picUrl, profPic, options);
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        //Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
    }

    private void setCollapsingToolbarLayoutTitle(String title) {
        mCollapsingToolbarLayout.setTitle(title);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.sample_actions, menu);
//        return true;
//    }
}