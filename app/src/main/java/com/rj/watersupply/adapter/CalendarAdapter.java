package com.rj.watersupply.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.rj.watersupply.MyScheduleCalendarActivity;
import com.rj.watersupply.R;
import com.rj.watersupply.modal.CustomCalendar;

import android.text.format.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarAdapter extends BaseAdapter {
    private static ViewHolder mHolder;
    private Context mContext;
    int mGridViewIndexOffset = 1;
    int mWeekDayNames = 7;

    private static class ViewHolder {
        ImageView mSavedEventImageView;
        ImageView mSelectedDayImageView;
        TextView mTextView;

        private ViewHolder() {
        }
    }
    public CalendarAdapter(Context context) {
        this.mContext = context;
    }
    public int getCount() {
        if (CustomCalendar.mFirstDay == -1 || CustomCalendar.mNumDaysInMonth == -1) {
            return this.mWeekDayNames;
        }
        return this.mWeekDayNames + CustomCalendar.mFirstDay + CustomCalendar.mNumDaysInMonth;
    }
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.adapter_calendar, parent, false);
            mHolder = new ViewHolder();
            if (convertView != null) {
                mHolder.mSelectedDayImageView = (ImageView) convertView.findViewById(R.id.material_calendar_selected_day);
                mHolder.mTextView = (TextView) convertView.findViewById(R.id.material_calendar_day);
                mHolder.mSavedEventImageView = (ImageView) convertView.findViewById(R.id.saved_event_imageView);
                convertView.setTag(mHolder);
            }
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        if (mHolder.mSelectedDayImageView != null) {
            GridView gridView = (GridView) parent;
            if (position < this.mWeekDayNames + CustomCalendar.mFirstDay || !gridView.isItemChecked(position)) {
                mHolder.mSelectedDayImageView.setVisibility(View.INVISIBLE);
            } else {
                mHolder.mSelectedDayImageView.setVisibility(View.VISIBLE);
            }
        }
        if (mHolder.mTextView != null) {
            setCalendarDay(position);
        }
        if (mHolder.mSavedEventImageView != null) {
            setSavedEvent(position);
        }
        return convertView;
    }

    private void setCalendarDay(int position) {
        if (position <= (this.mWeekDayNames - this.mGridViewIndexOffset) + CustomCalendar.mFirstDay) {
            mHolder.mTextView.setTextColor(this.mContext.getResources().getColor(R.color.calendar_day_text_color));
        } else {
            mHolder.mTextView.setTextColor(this.mContext.getResources().getColor(R.color.calendar_number_text_color));
        }
        switch (position) {
            case 0:
                mHolder.mTextView.setText(this.mContext.getResources().getString(R.string.sunday));
                mHolder.mTextView.setTypeface(Typeface.DEFAULT);
                return;
            case 1:
                mHolder.mTextView.setText(this.mContext.getResources().getString(R.string.monday));
                mHolder.mTextView.setTypeface(Typeface.DEFAULT);
                return;
            case 2:
                mHolder.mTextView.setText(this.mContext.getResources().getString(R.string.tuesday));
                mHolder.mTextView.setTypeface(Typeface.DEFAULT);
                return;
            case 3:
                mHolder.mTextView.setText(this.mContext.getResources().getString(R.string.wednesday));
                mHolder.mTextView.setTypeface(Typeface.DEFAULT);
                return;
            case 4:
                mHolder.mTextView.setText(this.mContext.getResources().getString(R.string.thursday));
                mHolder.mTextView.setTypeface(Typeface.DEFAULT);
                return;
            case 5:
                mHolder.mTextView.setText(this.mContext.getResources().getString(R.string.friday));
                mHolder.mTextView.setTypeface(Typeface.DEFAULT);
                return;
            case 6:
                mHolder.mTextView.setText(this.mContext.getResources().getString(R.string.saturday));
                mHolder.mTextView.setTypeface(Typeface.DEFAULT);
                return;
            default:
                if (position < this.mWeekDayNames + CustomCalendar.mFirstDay) {
                    mHolder.mTextView.setText("");
                    mHolder.mTextView.setTypeface(Typeface.DEFAULT);
                    return;
                }
                mHolder.mTextView.setText(String.valueOf((position - (this.mWeekDayNames - this.mGridViewIndexOffset)) - CustomCalendar.mFirstDay));
                mHolder.mTextView.setTypeface(Typeface.DEFAULT_BOLD);
                if (CustomCalendar.mCurrentDay == -1) {
                    mHolder.mTextView.setTextColor(this.mContext.getResources().getColor(R.color.calendar_number_text_color));
                    return;
                } else if (position == (this.mWeekDayNames - this.mGridViewIndexOffset) + CustomCalendar.mFirstDay + CustomCalendar.mCurrentDay) {
                    mHolder.mTextView.setTextColor(this.mContext.getResources().getColor(R.color.calendar_current_number_text_color));
                    return;
                } else {
                    mHolder.mTextView.setTextColor(this.mContext.getResources().getColor(R.color.calendar_number_text_color));
                    return;
                }
        }
    }

    private void setSavedEvent(int position) {
        mHolder.mSavedEventImageView.setVisibility(View.INVISIBLE);
        if (CustomCalendar.mFirstDay != -1 && MyScheduleCalendarActivity.mSavedEventDays != null && MyScheduleCalendarActivity.mSavedEventDays.size() > 0) {
            int startingPosition = (this.mWeekDayNames - this.mGridViewIndexOffset) + CustomCalendar.mFirstDay;
            if (position > startingPosition) {
                for (int i = 0; i < MyScheduleCalendarActivity.mSavedEventDays.size(); i++) {
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse((String) MyScheduleCalendarActivity.mSavedEventDays.get(i));
                        int day = Integer.parseInt(String.valueOf(DateFormat.format("dd", date)));
                        int month = Integer.parseInt(String.valueOf(DateFormat.format("MM", date)));
                        int year = Integer.parseInt(String.valueOf(DateFormat.format("yyyy", date)));
                        if (position == startingPosition + day && CustomCalendar.mMonth == month - 1 && CustomCalendar.mYear == year) {
                            mHolder.mSavedEventImageView.setVisibility(View.VISIBLE);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
