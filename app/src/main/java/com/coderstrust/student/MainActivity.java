package com.coderstrust.student;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    PagerAdapter adapter;
    String[] big_text;
    String[] small_text;
    int[] img;

    LinearLayout ww;
    LinearLayout blk;

    Button bb;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        img = new int[] { R.mipmap.home_back_1,R.mipmap.home_back_2,R.mipmap.home_back_3,R.mipmap.home_back_5};

        big_text = new String[] { "THE WORLD'S BIGGEST FREELANCER COMMUNITY", "ANYTIME","ANYWHERE", "JOIN THE MOVEMENT"};

        small_text = new String[] { "1,354,040,000", "1,210,193,422",
                "315,761,000", "237,641,326", "193,946,886" };
        viewPager = (ViewPager) findViewById(R.id.verificationFragmentPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        // Load adapter
        adapter = new ViewPagerAdapter(MainActivity.this, big_text, small_text, img);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);


        bb = (Button) findViewById(R.id.start);
        login = (Button) findViewById(R.id.login);
        ww = (LinearLayout) findViewById(R.id.rr);
        blk = (LinearLayout) findViewById(R.id.black);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                expand(bb);

                startActivity(new Intent(getApplicationContext(), SignUpStartActivity.class));

//                login.setVisibility(View.GONE);
//                ResizeWidthAnimation anim = new ResizeWidthAnimation(bb, 1080);
//                anim.setDuration(200);
//                bb.startAnimation(anim);
//
//                anim.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        revealEditText(ww);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                expand(login);

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

//
//                bb.setVisibility(View.GONE);
//                ResizeWidthAnimation anim = new ResizeWidthAnimation(login, 1080);
//                anim.setDuration(200);
//                login.startAnimation(anim);
//                anim.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        revealEditText(blk);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });

            }
        });

    }


//    public void expand(final View v) {
//
//        Animation a = new Animation()
//        {
//            @Override
//            protected void applyTransformation(float interpolatedTime, Transformation t) {
//                if (v.getId() == R.id.start)
//                    login.setVisibility(View.GONE);
//                else
//                    bb.setVisibility(View.GONE);
//
//                v.getLayoutParams().width = interpolatedTime ==1
//                        ? ViewGroup.LayoutParams.MATCH_PARENT
//                        : ViewGroup.LayoutParams.WRAP_CONTENT;
//                v.requestLayout();
//            }
//
//            @Override
//            public boolean willChangeBounds() {
//                return true;
//            }
//        };
//
//        // 1dp/ms
//        a.setDuration(50);
//        a.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                if (v.getId() == R.id.start)
//                    revealEditText(ww);
//                else
//                    revealEditText(blk);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        v.startAnimation(a);
//    }

    public void revealEditText(final LinearLayout view) {
        int cx = view.getWidth()/2;
        int cy =view.getHeight()-120;
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        System.out.println(cx + "     " + cy + "   " + finalRadius);
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        view.setVisibility(View.VISIBLE);
        anim.setDuration(400);
        anim.start();

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (view.getId() == R.id.black)
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                else
                    startActivity(new Intent(getApplicationContext(), SignupActivity.class));


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    public class ResizeWidthAnimation extends Animation
    {
        private int mWidth;
        private int mStartWidth;
        private View mView;

        public ResizeWidthAnimation(View view, int width)
        {
            mView = view;
            mWidth = width;
            mStartWidth = view.getWidth();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t)
        {
            int newWidth = mStartWidth + (int) ((mWidth - mStartWidth) * interpolatedTime);

            mView.getLayoutParams().width = newWidth;
            mView.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight)
        {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds()
        {
            return true;
        }
    }


    private void sendRequest(){

        StringRequest stringRequest = new StringRequest("test.coderstrust.com/api/students/9064/personal",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }



//    String url = "test.coderstrust.com/api/students/9064/personal";
//    StringRequest postRequest = new StringRequest(Request.Method.GET, url,
//            new Response.Listener<String>()
//            {
//                @Override
//                public void onResponse(String response) {
//                    // response
//                    Log.d("Response", response);
//                }
//            },
//            new Response.ErrorListener()
//            {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // TODO Auto-generated method stub
//                    Log.d("ERROR","error => "+error.toString());
//                }
//            }
//    ) {
//        @Override
//        public Map<String, String> getHeaders() throws AuthFailureError {
//            Map<String, String>  params = new HashMap<>();
//            params.put("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdHVkZW50X2lkIjo5MDY0LCJleHAiOjE0NTQ2NjIwMDJ9.61MoNiJZQPoQGAb5z8RqRMlkj_HElTzizSsAHsvUBuc");
//            return params;
//        }
//    };
//    queue.add(postRequest);

}


