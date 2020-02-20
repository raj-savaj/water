package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.rj.watersupply.adapter.CalendarAdapter;
import com.rj.watersupply.modal.CustomCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

public class MyScheduleCalendarActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private AlertDialog internetDialog;
    public boolean isContinueRequest;
    private boolean isFirstTime;
    private boolean isFirstTimeInMonth;
    public boolean isNetDialogShowing = false;
    private int CurrentPosition = -1;
    GridView mCalendar;
    private CalendarAdapter mMaterialCalendarAdapter;
    TextView mMonthName;
    ImageView mNext;
    ImageView mPrevious;
    private RecyclerView rcRequestList;
    public static ArrayList<String> mSavedEventDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule_calendar);
        this.rcRequestList = (RecyclerView) findViewById(R.id.rcRequestList);
        this.rcRequestList.setLayoutManager(new LinearLayoutManager(this));
        mSavedEventDays = new ArrayList<>();
        CustomCalendar.getInitialCalendarInfo();
        this.mPrevious = (ImageView) findViewById(R.id.material_calendar_previous);
        if (this.mPrevious != null) {
            this.mPrevious.setOnClickListener(this);
        }
        this.mMonthName = (TextView) findViewById(R.id.material_calendar_month_name);
        if (this.mMonthName != null) {
            Calendar cal = Calendar.getInstance();
            if (cal != null) {
                this.mMonthName.setText(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + cal.get(Calendar.YEAR));
            }
        }
        this.mNext = (ImageView) findViewById(R.id.material_calendar_next);
        if (this.mNext != null) {
            this.mNext.setOnClickListener(this);
        }
        this.mCalendar = (GridView) findViewById(R.id.material_calendar_gridView);
        this.mMaterialCalendarAdapter = new CalendarAdapter(this);
        if (this.mCalendar != null) {
            this.mCalendar.setOnItemClickListener(this);
            this.mCalendar.setAdapter(this.mMaterialCalendarAdapter);
        }
        this.isFirstTime = true;
        this.isFirstTimeInMonth = false;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.material_calendar_previous /*2131624071*/:
                    CustomCalendar.previousOnClick(this.mPrevious, this.mMonthName, this.mCalendar, this.mMaterialCalendarAdapter);
                    this.CurrentPosition = -1;
                    this.isFirstTimeInMonth = true;
                    return;
                case R.id.material_calendar_next /*2131624073*/:
                    CustomCalendar.nextOnClick(this.mNext, this.mMonthName, this.mCalendar, this.mMaterialCalendarAdapter);
                    this.CurrentPosition = -1;
                    this.isFirstTimeInMonth = true;
                    return;
                default:
                    return;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        switch (parent.getId()) {
            case R.id.material_calendar_gridView /*2131624074*/:
                CustomCalendar.selectCalendarDay(this.mMaterialCalendarAdapter, position);
                showSavedEventsListView(position);
                return;
            default:
                return;
        }
    }
    public void getSavedEventsForCurrentMonth() {
        mSavedEventDays.clear();
    }
    public void showSavedEventsListView(int position) {

    }
}
