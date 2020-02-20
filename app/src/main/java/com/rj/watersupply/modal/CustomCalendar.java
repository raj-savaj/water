package com.rj.watersupply.modal;

import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.rj.watersupply.adapter.CalendarAdapter;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class CustomCalendar {
    public static int mCurrentDay = -1;
    public static int mCurrentMonth = -1;
    public static int mCurrentYear = -1;
    public static int mFirstDay = -1;
    public static int mMonth = -1;
    public static int mNumDaysInMonth = -1;
    public static int mYear = -1;

    public static void getInitialCalendarInfo() {
        Calendar cal = Calendar.getInstance();
        if (cal != null) {
            Log.d("MONTH_NUMBER", String.valueOf(cal.getActualMaximum(Calendar.DATE)));
            mNumDaysInMonth = cal.getActualMaximum(Calendar.DATE);
            mMonth = cal.get(Calendar.MONTH);
            mYear = cal.get(Calendar.YEAR);
            mCurrentDay = cal.get(Calendar.DATE);
            mCurrentMonth = mMonth;
            mCurrentYear = mYear;
            getFirstDay(mMonth, mYear);
            Log.d("CURRENT_DAY", String.valueOf(mCurrentDay));
            Log.d("CURRENT_MONTH_INFO", String.valueOf(getMonthName(mMonth) + " " + mYear + " has " + mNumDaysInMonth + " days and starts on " + mFirstDay));
        }
    }

    private static void refreshCalendar(TextView monthTextView, GridView calendarGridView, CalendarAdapter materialCalendarAdapter, int month, int year) {
        checkCurrentDay(month, year);
        getNumDayInMonth(month, year);
        getFirstDay(month, year);
        if (monthTextView != null) {
            Log.d("REFRESH_MONTH", String.valueOf(month));
            monthTextView.setText(getMonthName(month) + " " + year);
        }
        if (materialCalendarAdapter != null) {
            if (calendarGridView != null) {
                calendarGridView.setItemChecked(calendarGridView.getCheckedItemPosition(), false);
            }
            materialCalendarAdapter.notifyDataSetChanged();
        }
    }

    private static String getMonthName(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }

    private static void checkCurrentDay(int month, int year) {
        if (month == mCurrentMonth && year == mCurrentYear) {
            mCurrentDay = Calendar.getInstance().get(Calendar.DATE);
        } else {
            mCurrentDay = -1;
        }
    }

    private static void getNumDayInMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        if (cal != null) {
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.YEAR, year);
            mNumDaysInMonth = cal.getActualMaximum(Calendar.DATE);
            Log.d("MONTH_NUMBER", String.valueOf(cal.getActualMaximum(Calendar.DATE)));
        }
    }

    private static void getFirstDay(int month, int year) {
        Calendar cal = Calendar.getInstance();
        if (cal != null) {
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.DATE, 1);
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case 1:
                    Log.d("FIRST_DAY", "Sunday");
                    mFirstDay = 0;
                    return;
                case 2:
                    Log.d("FIRST_DAY", "Monday");
                    mFirstDay = 1;
                    return;
                case 3:
                    Log.d("FIRST_DAY", "Tuesday");
                    mFirstDay = 2;
                    return;
                case 4:
                    Log.d("FIRST_DAY", "Wednesday");
                    mFirstDay = 3;
                    return;
                case 5:
                    Log.d("FIRST_DAY", "Thursday");
                    mFirstDay = 4;
                    return;
                case 6:
                    Log.d("FIRST_DAY", "Friday");
                    mFirstDay = 5;
                    return;
                case 7:
                    Log.d("FIRST_DAY", "Saturday");
                    mFirstDay = 6;
                    return;
                default:
                    return;
            }
        }
    }

    public static void previousOnClick(ImageView previousImageView, TextView monthTextView, GridView calendarGridView, CalendarAdapter materialCalendarAdapter) {
        if (previousImageView != null && mMonth != -1 && mYear != -1) {
            previousMonth(monthTextView, calendarGridView, materialCalendarAdapter);
        }
    }

    public static void nextOnClick(ImageView nextImageView, TextView monthTextView, GridView calendarGridView, CalendarAdapter materialCalendarAdapter) {
        if (nextImageView != null && mMonth != -1 && mYear != -1) {
            nextMonth(monthTextView, calendarGridView, materialCalendarAdapter);
        }
    }

    private static void previousMonth(TextView monthTextView, GridView calendarGridView, CalendarAdapter materialCalendarAdapter) {
        if (mMonth == 0) {
            mMonth = 11;
            mYear--;
        } else {
            mMonth--;
        }
        refreshCalendar(monthTextView, calendarGridView, materialCalendarAdapter, mMonth, mYear);
    }

    private static void nextMonth(TextView monthTextView, GridView calendarGridView, CalendarAdapter materialCalendarAdapter) {
        if (mMonth == 11) {
            mMonth = 0;
            mYear++;
        } else {
            mMonth++;
        }
        refreshCalendar(monthTextView, calendarGridView, materialCalendarAdapter, mMonth, mYear);
    }

    public static void selectCalendarDay(CalendarAdapter materialCalendarAdapter, int position) {
        Log.d("SELECTED_POSITION", String.valueOf(position));
        if (position > 6 + mFirstDay) {
            getSelectedDate(position, mMonth, mYear);
            if (materialCalendarAdapter != null) {
                materialCalendarAdapter.notifyDataSetChanged();
            }
        }
    }

    private static void getSelectedDate(int selectedPosition, int month, int year) {
        int dateNumber = (selectedPosition - 6) - mFirstDay;
        Log.d("DATE_NUMBER", String.valueOf(dateNumber));
        Log.d("SELECTED_DATE", String.valueOf(month + "/" + dateNumber + "/" + year));
    }
}
