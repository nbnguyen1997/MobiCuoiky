package com.example.tracnghiemv1.Slide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.tracnghiemv1.R;
import com.example.tracnghiemv1.question.Question;
import com.example.tracnghiemv1.question.QuestionController;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ScreenSlide extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 15;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;
    QuestionController controller;
    ArrayList<Question> arr_quest;
    public static  final  String ARR_QUEST = "arr_quest";

    private TextView txtKiemTra,txtTimer,txtScore;
    CounterClass timer;

    String subject;
    int num_exam;
    int total_timer;

    public int checkAns=0;
    //CSDL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        //nhận intent
        final Intent intent = getIntent();
        subject = intent.getStringExtra("subject");
        num_exam = intent.getIntExtra("num_exam",0);



        controller= new QuestionController(this);
        arr_quest = new ArrayList<Question>();
        arr_quest = controller.getQuestion(num_exam,subject);

        txtKiemTra = (TextView) findViewById(R.id.tvKiemTra);
        total_timer =10;
        timer = new CounterClass(total_timer*60*1000,1000);
        txtTimer = (TextView) findViewById(R.id.tvTimer);

        txtTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////
            }
        });
        txtScore = (TextView) findViewById(R.id.tvScore);
        txtScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentObject = new Intent(getApplicationContext(),TestDoneActivity.class);
                intentObject.putExtra(ARR_QUEST,arr_quest);
                startActivity(intentObject);
            }
        });
        txtKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    CheckAnsert();
            }
        });
        timer.start();

    }
    public class CounterClass extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            txtTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            txtTimer.setText("00:00");  //SetText cho textview hiện thị thời gian.
        }
    }

    public void CheckAnsert(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_ansert_dialog);
        dialog.setTitle("Danh Sách Câu Trả Lời");

        CheckAnsertAdapter ansertAdapter = new CheckAnsertAdapter(arr_quest,this);
        GridView gridView = (GridView) dialog.findViewById(R.id.gvLsQuestion);
        gridView.setAdapter(ansertAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPager.setCurrentItem(i);
                dialog.dismiss();
            }
        });

        Button btnCancel , btnFinish;
        btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        btnFinish = (Button) dialog.findViewById(R.id.btnFinish);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                CheckKiemTra();
                dialog.dismiss();
            }
        });
            dialog.show();

    }
    private void CheckKiemTra(){
//        if (mPager.getCurrentItem() >= 5)//mốt sửa sau
//            mPager.setCurrentItem(mPager.getCurrentItem()-2);
//        if (mPager.getCurrentItem() <= 5)
//            mPager.setCurrentItem(mPager.getCurrentItem() - 2);





        checkAns = 1;
        txtKiemTra.setVisibility(View.GONE);
        txtScore.setVisibility(View.VISIBLE);
    }

    public ArrayList<Question> getData(){
        return arr_quest;
    }



    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {

            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position,checkAns);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}

