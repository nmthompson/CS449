package com.example.nthompson.umpire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBallButton, mStrikeButton;
    private TextView mBallCount, mStrikeCount, mTotalCount;

    private Integer ballCount = 0;
    private Integer strikeCount = 0;
    private Integer totalCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            totalCount = savedInstanceState.getInt("TOTAL_COUNT");
            onSaveInstanceState(savedInstanceState);
        }
        mBallButton = (Button) findViewById(R.id.ballButtonCounter);
        if (mBallButton != null) {
            mBallButton.setOnClickListener(this);
        }
        mStrikeButton = (Button) findViewById(R.id.strikeButtonCounter);
        if (mStrikeButton != null) {
            mStrikeButton.setOnClickListener(this);
        }
        mBallCount = (TextView) findViewById(R.id.ballCountView);
        mStrikeCount = (TextView) findViewById(R.id.strikeCountView);
        mTotalCount = (TextView) findViewById(R.id.totalCountView);
        mTotalCount.setText(totalCount.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ballButtonCounter:
                ballCount++;
                if (mBallCount != null) {
                    if (ballCount == 4) {
                        ballCount = 0;
                        strikeCount = 0;
                        mStrikeCount.setText(strikeCount.toString());
                        }
                    mBallCount.setText(ballCount.toString());
                }
                break;
            case R.id.strikeButtonCounter:
                strikeCount++;
                if (mStrikeCount != null) {
                    mStrikeCount.setText(strikeCount.toString());
                }
                if (strikeCount == 3) {
                    updateOutCount();
                }
                break;
        }
    }
    //I need to condense a ton of this code, but I'm sleepy. This should give you an idea of how this works though
    //Let me know if you have any questions.
    public void updateOutCount() {
        strikeCount = 0;
        ballCount = 0;
        if (mStrikeCount != null) {
            mStrikeCount.setText(strikeCount.toString());
        }
        if (mBallCount != null) {
            mBallCount.setText(ballCount.toString());
        }
        totalCount++;
        if (mTotalCount != null) {
            mTotalCount.setText(totalCount.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.resetButton) {
            ballCount = 0;
            strikeCount = 0;
            totalCount = 0;
            mBallCount.setText(ballCount.toString());
            mStrikeCount.setText(strikeCount.toString());
            mTotalCount.setText(totalCount.toString());
            return true;
        }
        if (item.getItemId() == R.id.aboutButton) {
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(totalCount != null) {
            outState.putInt("TOTAL_COUNT", totalCount);
        }
        super.onSaveInstanceState(outState);
    }
}
