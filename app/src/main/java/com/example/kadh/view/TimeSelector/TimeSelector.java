package com.example.kadh.view.TimeSelector;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadh.R;
import com.example.kadh.view.TimeSelector.Utils.DateUtils;
import com.example.kadh.view.TimeSelector.Utils.ScreenUtil;
import com.example.kadh.view.TimeSelector.Utils.TextUtil;
import com.example.kadh.view.TimeSelector.view.PickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liuli on 2015/11/27.
 * <p>
 * Modify by nicai on 2017/9/2
 */
public class TimeSelector {
    public interface ResultHandler {
        void handle(String time, Calendar calender);

    }

    public enum SCROLLTYPE {
        YEAR(1),
        MONTH(2),
        DAY(4),
        HOUR(8),
        MINUTE(16);

        SCROLLTYPE(int value) {
            this.value = value;
        }

        public int value;

    }

    public enum MODE {

        YMD(1),
        YMDHM(2);

        private MODE(int value) {
            this.value = value;
        }

        public int value;

    }

    private int scrollUnits = SCROLLTYPE.YEAR.value + SCROLLTYPE.MONTH.value + SCROLLTYPE.DAY.value + SCROLLTYPE.HOUR.value + SCROLLTYPE.MINUTE.value;
    private ResultHandler handler;
    private Context context;
    private final String FORMAT_STR = "yyyy-MM-dd HH:mm";

    private Dialog seletorDialog;
    private PickerView year_pv;
    private PickerView month_pv;
    private PickerView day_pv;
    private PickerView hour_pv;
    private PickerView minute_pv;

    private final int MAXMINUTE = 59;
    private int MAXHOUR = 23;
    private final int MINMINUTE = 0;
    private int MINHOUR = 0;
    private final int MAXMONTH = 12;

    private int curYear;
    private int curMonth;
    private int curDay;
    private int curHour;
    private int curMininute;
    private String tvDate;

    private ArrayList<String> year, month, day, hour, minute;
    private int startYear, startMonth, startDay, startHour, startMininute, endYear, endMonth, endDay, endHour, endMininute, minute_workStart, minute_workEnd, hour_workStart, hour_workEnd;
    private boolean spanYear, spanMon, spanDay, spanHour, spanMin;
    private Calendar selectedCalender = Calendar.getInstance();
    private final long ANIMATORDELAY = 200L;
    private final long CHANGEDELAY = 90L;
    private String workStart_str;
    private String workEnd_str;
    private Calendar startCalendar;
    private Calendar curCalendar;
    private Calendar endCalendar;
    private TextView tv_cancle;
    private TextView tv_select;
    private TextView hour_text;
    private TextView minute_text;

    /**
     * @param context
     * @param resultHandler 选取时间后回调
     * @param startDate     format："yyyy-MM-dd HH:mm"
     * @param endDate
     */
    public TimeSelector(Context context, ResultHandler resultHandler, String startDate, String endDate) {
        this.context = context;
        this.handler = resultHandler;
        curCalendar = Calendar.getInstance();
        startCalendar = Calendar.getInstance();
        endCalendar = Calendar.getInstance();
        startCalendar.setTime(DateUtils.parse(startDate, FORMAT_STR));
        endCalendar.setTime(DateUtils.parse(endDate, FORMAT_STR));
        initDialog();
        initView();
    }

    /**
     * @param context
     * @param startDate
     * @param endDate
     * @param workStartTime 工作日起始时间 如：朝九晚五  format："09:00"
     * @param workEndTime   工作日结束时间  format："17:00"
     */
    public TimeSelector(Context context, ResultHandler resultHandler, String startDate, String endDate, String workStartTime, String workEndTime) {
        /*this(context, resultHandler, startDate, endDate);*/
        this.workStart_str = workStartTime;
        this.workEnd_str = workEndTime;
    }


    public boolean isShowing() {
        if (seletorDialog != null) {
            return seletorDialog.isShowing();
        } else {
            return true;
        }
    }

    public void show() {
        if (startCalendar.getTime().getTime() >= endCalendar.getTime().getTime()) {
            Toast.makeText(context, "起始时间应小于结束时间", Toast.LENGTH_LONG).show();
            return;
        }
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(curDate);
        this.tvDate = format;
        curCalendar.setTime(DateUtils.parse(tvDate, FORMAT_STR));

        if (!excuteWorkTime()) return;
        initParameter();
        initTimer();
        addListener();

        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.handle(DateUtils.format(selectedCalender.getTime(), FORMAT_STR), selectedCalender);
                seletorDialog.dismiss();
            }
        });
        seletorDialog.show();
    }

    public void show(final String tvDate) {
        if (startCalendar.getTime().getTime() >= endCalendar.getTime().getTime()) {
            Toast.makeText(context, "起始时间应小于结束时间", Toast.LENGTH_LONG).show();
            return;
        }
        this.tvDate = tvDate;
        curCalendar.setTime(DateUtils.parse(tvDate, FORMAT_STR));
        if (!excuteWorkTime()) return;
        initParameter();
        initTimer();
        addListener();
        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.handle(DateUtils.format(selectedCalender.getTime(), FORMAT_STR), selectedCalender);
                seletorDialog.dismiss();
            }
        });
        seletorDialog.show();


    }

    private void initDialog() {
        if (seletorDialog == null) {
            seletorDialog = new Dialog(context, R.style.time_dialog);
            seletorDialog.setCancelable(false);
            seletorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            seletorDialog.setContentView(R.layout.dialog_selector);
            seletorDialog.setCanceledOnTouchOutside(true);
            Window window = seletorDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = window.getAttributes();
            int width = ScreenUtil.getInstance(context).getScreenWidth();
            lp.width = width;
            window.setAttributes(lp);
        }
    }

    private void initView() {
        year_pv = (PickerView) seletorDialog.findViewById(R.id.year_pv);
        month_pv = (PickerView) seletorDialog.findViewById(R.id.month_pv);
        day_pv = (PickerView) seletorDialog.findViewById(R.id.day_pv);
        hour_pv = (PickerView) seletorDialog.findViewById(R.id.hour_pv);
        minute_pv = (PickerView) seletorDialog.findViewById(R.id.minute_pv);
        tv_cancle = (TextView) seletorDialog.findViewById(R.id.tv_cancle);
        tv_select = (TextView) seletorDialog.findViewById(R.id.tv_select);
        hour_text = (TextView) seletorDialog.findViewById(R.id.hour_text);
        minute_text = (TextView) seletorDialog.findViewById(R.id.minute_text);

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seletorDialog.dismiss();
            }
        });

    }

    private void initParameter() {
        curYear = curCalendar.get(Calendar.YEAR);
        curMonth = curCalendar.get(Calendar.MONTH) + 1;
        curDay = curCalendar.get(Calendar.DAY_OF_MONTH);
        curHour = curCalendar.get(Calendar.HOUR_OF_DAY);
        curMininute = curCalendar.get(Calendar.MINUTE);

        startYear = startCalendar.get(Calendar.YEAR);
        startMonth = startCalendar.get(Calendar.MONTH) + 1;
        startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
        startHour = startCalendar.get(Calendar.HOUR_OF_DAY);
        startMininute = startCalendar.get(Calendar.MINUTE);
        endYear = endCalendar.get(Calendar.YEAR);
        endMonth = endCalendar.get(Calendar.MONTH) + 1;
        endDay = endCalendar.get(Calendar.DAY_OF_MONTH);
        endHour = endCalendar.get(Calendar.HOUR_OF_DAY);
        endMininute = endCalendar.get(Calendar.MINUTE);
        spanYear = startYear != endYear;
        spanMon = (!spanYear) && (startMonth != endMonth);
        spanDay = (!spanMon) && (startDay != endDay);
        spanHour = (!spanDay) && (startHour != endHour);
        spanMin = (!spanHour) && (startMininute != endMininute);
        selectedCalender.setTime(curCalendar.getTime());
    }

    private void initTimer() {
        initArrayList();

        if (spanYear) {
            for (int i = startYear; i <= endYear; i++) {
                year.add(String.valueOf(i));
            }
            for (int i = startMonth; i <= MAXMONTH; i++) {
                month.add(fomatTimeUnit(i));
            }
            for (int i = startDay; i <= DateUtils.getMaxDate(curYear, curMonth - 1); i++) {
                day.add(fomatTimeUnit(i));
            }
            for (int i = startHour; i <= MAXHOUR; i++) {
                hour.add(fomatTimeUnit(i));
            }
            for (int i = startMininute; i <= MAXMINUTE; i++) {
                minute.add(fomatTimeUnit(i));
            }
        } else if (spanMon) {
            year.add(String.valueOf(startYear));
            for (int i = startMonth; i <= endMonth; i++) {
                month.add(fomatTimeUnit(i));
            }
            for (int i = startDay; i <= DateUtils.getMaxDate(curYear, curMonth - 1); i++) {
                day.add(fomatTimeUnit(i));
            }
            for (int i = startHour; i <= MAXHOUR; i++) {
                hour.add(fomatTimeUnit(i));
            }
            for (int i = startMininute; i <= MAXMINUTE; i++) {
                minute.add(fomatTimeUnit(i));
            }
        } else if (spanDay) {
            year.add(String.valueOf(startYear));
            month.add(fomatTimeUnit(startMonth));
            for (int i = startDay; i <= endDay; i++) {
                day.add(fomatTimeUnit(i));
            }
            for (int i = startHour; i <= MAXHOUR; i++) {
                hour.add(fomatTimeUnit(i));
            }
            for (int i = startMininute; i <= MAXMINUTE; i++) {
                minute.add(fomatTimeUnit(i));
            }

        } else if (spanHour) {
            year.add(String.valueOf(startYear));
            month.add(fomatTimeUnit(startMonth));
            day.add(fomatTimeUnit(startDay));
            for (int i = startHour; i <= endHour; i++) {
                hour.add(fomatTimeUnit(i));
            }
            for (int i = startMininute; i <= MAXMINUTE; i++) {
                minute.add(fomatTimeUnit(i));
            }

        } else if (spanMin) {
            year.add(String.valueOf(startYear));
            month.add(fomatTimeUnit(startMonth));
            day.add(fomatTimeUnit(startDay));
            hour.add(fomatTimeUnit(startHour));
            for (int i = startMininute; i <= endMininute; i++) {
                minute.add(fomatTimeUnit(i));
            }
        }

        loadComponent();

    }

    private boolean excuteWorkTime() {
        boolean res = true;
        if (!TextUtil.isEmpty(workStart_str) && !TextUtil.isEmpty(workEnd_str)) {
            String[] start = workStart_str.split(":");
            String[] end = workEnd_str.split(":");
            hour_workStart = Integer.parseInt(start[0]);
            minute_workStart = Integer.parseInt(start[1]);
            hour_workEnd = Integer.parseInt(end[0]);
            minute_workEnd = Integer.parseInt(end[1]);
            Calendar workStartCalendar = Calendar.getInstance();
            Calendar workEndCalendar = Calendar.getInstance();
            workStartCalendar.setTime(startCalendar.getTime());
            workEndCalendar.setTime(endCalendar.getTime());
            workStartCalendar.set(Calendar.HOUR_OF_DAY, hour_workStart);
            workStartCalendar.set(Calendar.MINUTE, minute_workStart);
            workEndCalendar.set(Calendar.HOUR_OF_DAY, hour_workEnd);
            workEndCalendar.set(Calendar.MINUTE, minute_workEnd);


            Calendar startTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            Calendar startWorkTime = Calendar.getInstance();
            Calendar endWorkTime = Calendar.getInstance();

            startTime.set(Calendar.HOUR_OF_DAY, startCalendar.get(Calendar.HOUR_OF_DAY));
            startTime.set(Calendar.MINUTE, startCalendar.get(Calendar.MINUTE));
            endTime.set(Calendar.HOUR_OF_DAY, endCalendar.get(Calendar.HOUR_OF_DAY));
            endTime.set(Calendar.MINUTE, endCalendar.get(Calendar.MINUTE));

            startWorkTime.set(Calendar.HOUR_OF_DAY, workStartCalendar.get(Calendar.HOUR_OF_DAY));
            startWorkTime.set(Calendar.MINUTE, workStartCalendar.get(Calendar.MINUTE));
            endWorkTime.set(Calendar.HOUR_OF_DAY, workEndCalendar.get(Calendar.HOUR_OF_DAY));
            endWorkTime.set(Calendar.MINUTE, workEndCalendar.get(Calendar.MINUTE));


            if (startTime.getTime().getTime() == endTime.getTime().getTime() || (startWorkTime.getTime().getTime() < startTime.getTime().getTime() && endWorkTime.getTime().getTime() < startTime.getTime().getTime())) {
                Toast.makeText(context, "时间参数错误", Toast.LENGTH_LONG).show();
                return false;
            }
            startCalendar.setTime(startCalendar.getTime().getTime() < workStartCalendar.getTime().getTime() ? workStartCalendar.getTime() : startCalendar.getTime());
            endCalendar.setTime(endCalendar.getTime().getTime() > workEndCalendar.getTime().getTime() ? workEndCalendar.getTime() : endCalendar.getTime());
            MINHOUR = workStartCalendar.get(Calendar.HOUR_OF_DAY);
            MAXHOUR = workEndCalendar.get(Calendar.HOUR_OF_DAY);

        }
        return res;


    }

    private String fomatTimeUnit(int unit) {
        return unit < 10 ? "0" + String.valueOf(unit) : String.valueOf(unit);
    }

    private void initArrayList() {
        if (year == null) year = new ArrayList<>();
        if (month == null) month = new ArrayList<>();
        if (day == null) day = new ArrayList<>();
        if (hour == null) hour = new ArrayList<>();
        if (minute == null) minute = new ArrayList<>();
        year.clear();
        month.clear();
        day.clear();
        hour.clear();
        minute.clear();
    }


    private void addListener() {
        year_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                curYear = Integer.parseInt(text);
                selectedCalender.set(Calendar.YEAR, curYear);
                dayChangeByYearOrMonth();
                excuteAnimator(ANIMATORDELAY, year_pv);
            }
        });
        month_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                curMonth = Integer.parseInt(text);
                selectedCalender.set(Calendar.MONTH, curMonth - 1);
                dayChangeByYearOrMonth();
                excuteAnimator(ANIMATORDELAY, month_pv);
            }
        });
        day_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                curDay = Integer.parseInt(text);
                selectedCalender.set(Calendar.DATE, curDay);
                excuteAnimator(ANIMATORDELAY, day_pv);
            }
        });
        hour_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender.set(Calendar.HOUR_OF_DAY, Integer.parseInt(text));
                excuteAnimator(ANIMATORDELAY, hour_pv);
            }
        });
        minute_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender.set(Calendar.MINUTE, Integer.parseInt(text));
                excuteAnimator(ANIMATORDELAY, minute_pv);
            }
        });

    }


    private void dayChangeByYearOrMonth() {
        day.clear();
        for (int i = 1; i <= DateUtils.getMaxDate(curYear, curMonth - 1); i++) {
            day.add(fomatTimeUnit(i));
        }
        day_pv.setData(day);
        if (curDay > Integer.parseInt(day.get(day.size() - 1))) {
            curDay = day.size();
            selectedCalender.set(Calendar.DATE, curDay);
        }
        day_pv.setSelected(curDay - 1);
    }


    private void setCurDate() {
        year_pv.setSelected(curYear - startYear);
        month_pv.setSelected(curMonth - 1);
        day_pv.setSelected(curDay - 1);
        hour_pv.setSelected(curHour);
        if (curMininute == 0) {
            minute_pv.setSelected(0);
        } else {
            minute_pv.setSelected(curMininute);
        }
    }

    public void loadComponent() {
        year_pv.setData(year);
        month_pv.setData(month);
        day_pv.setData(day);
        hour_pv.setData(hour);
        minute_pv.setData(minute);
        setCurDate();
        excuteScroll();
    }

    private void excuteScroll() {
        year_pv.setCanScroll(year.size() > 1 && (scrollUnits & SCROLLTYPE.YEAR.value) == SCROLLTYPE.YEAR.value);
        month_pv.setCanScroll(month.size() > 1 && (scrollUnits & SCROLLTYPE.MONTH.value) == SCROLLTYPE.MONTH.value);
        day_pv.setCanScroll(day.size() > 1 && (scrollUnits & SCROLLTYPE.DAY.value) == SCROLLTYPE.DAY.value);
        hour_pv.setCanScroll(hour.size() > 1 && (scrollUnits & SCROLLTYPE.HOUR.value) == SCROLLTYPE.HOUR.value);
        minute_pv.setCanScroll(minute.size() > 1 && (scrollUnits & SCROLLTYPE.MINUTE.value) == SCROLLTYPE.MINUTE.value);
    }

    private void monthChange() {
        month.clear();
        int selectedYear = selectedCalender.get(Calendar.YEAR);
        if (selectedYear == startYear) {
            for (int i = startMonth; i <= MAXMONTH; i++) {
                month.add(fomatTimeUnit(i));
            }
        } else if (selectedYear == endYear) {
            for (int i = 1; i <= endMonth; i++) {
                month.add(fomatTimeUnit(i));
            }
        } else {
            for (int i = 1; i <= MAXMONTH; i++) {
                month.add(fomatTimeUnit(i));
            }
        }
        selectedCalender.set(Calendar.MONTH, Integer.parseInt(month.get(0)) - 1);
        month_pv.setData(month);
        month_pv.setSelected(0);
        excuteAnimator(ANIMATORDELAY, month_pv);
        month_pv.postDelayed(new Runnable() {
            @Override
            public void run() {
                dayChange();
            }
        }, CHANGEDELAY);

    }

    private void dayChange() {
        day.clear();
        int selectedYear = selectedCalender.get(Calendar.YEAR);
        int selectedMonth = selectedCalender.get(Calendar.MONTH) + 1;
        if (selectedYear == startYear && selectedMonth == startMonth) {
            for (int i = startDay; i <= selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                day.add(fomatTimeUnit(i));
            }
        } else if (selectedYear == endYear && selectedMonth == endMonth) {
            for (int i = 1; i <= endDay; i++) {
                day.add(fomatTimeUnit(i));
            }
        } else {
            for (int i = 1; i <= selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                day.add(fomatTimeUnit(i));
            }
        }
        selectedCalender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day.get(0)));
        day_pv.setData(day);
        day_pv.setSelected(0);
        excuteAnimator(ANIMATORDELAY, day_pv);
        day_pv.postDelayed(new Runnable() {
            @Override
            public void run() {
                hourChange();
            }
        }, CHANGEDELAY);
    }

    private void hourChange() {
        hour.clear();
        int selectedYear = selectedCalender.get(Calendar.YEAR);
        int selectedMonth = selectedCalender.get(Calendar.MONTH) + 1;
        int selectedDay = selectedCalender.get(Calendar.DAY_OF_MONTH);

        if (selectedYear == startYear && selectedMonth == startMonth && selectedDay == startDay) {
            for (int i = startHour; i <= MAXHOUR; i++) {
                hour.add(fomatTimeUnit(i));
            }
        } else if (selectedYear == endYear && selectedMonth == endMonth && selectedDay == endDay) {
            for (int i = MINHOUR; i <= endHour; i++) {
                hour.add(fomatTimeUnit(i));
            }
        } else {

            for (int i = MINHOUR; i <= MAXHOUR; i++) {
                hour.add(fomatTimeUnit(i));
            }

        }
        selectedCalender.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour.get(0)));
        hour_pv.setData(hour);
        hour_pv.setSelected(0);
        excuteAnimator(ANIMATORDELAY, hour_pv);
        hour_pv.postDelayed(new Runnable() {
            @Override
            public void run() {
                minuteChange();
            }
        }, CHANGEDELAY);

    }

    private void minuteChange() {
        minute.clear();
        int selectedYear = selectedCalender.get(Calendar.YEAR);
        int selectedMonth = selectedCalender.get(Calendar.MONTH) + 1;
        int selectedDay = selectedCalender.get(Calendar.DAY_OF_MONTH);
        int selectedHour = selectedCalender.get(Calendar.HOUR_OF_DAY);

        if (selectedYear == startYear && selectedMonth == startMonth && selectedDay == startDay && selectedHour == startHour) {
            for (int i = startMininute; i <= MAXMINUTE; i++) {
                minute.add(fomatTimeUnit(i));
            }
        } else if (selectedYear == endYear && selectedMonth == endMonth && selectedDay == endDay && selectedHour == endHour) {
            for (int i = MINMINUTE; i <= endMininute; i++) {
                minute.add(fomatTimeUnit(i));
            }
        } else if (selectedHour == hour_workStart) {
            for (int i = minute_workStart; i <= MAXMINUTE; i++) {
                minute.add(fomatTimeUnit(i));
            }
        } else if (selectedHour == hour_workEnd) {
            for (int i = MINMINUTE; i <= minute_workEnd; i++) {
                minute.add(fomatTimeUnit(i));
            }
        } else {
            for (int i = MINMINUTE; i <= MAXMINUTE; i++) {
                minute.add(fomatTimeUnit(i));
            }
        }
        selectedCalender.set(Calendar.MINUTE, Integer.parseInt(minute.get(0)));
        minute_pv.setData(minute);
        minute_pv.setSelected(0);
        excuteAnimator(ANIMATORDELAY, minute_pv);
        excuteScroll();


    }

    private void excuteAnimator(long ANIMATORDELAY, View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                1.3f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                1.3f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(ANIMATORDELAY).start();
    }

    /**
     * 设置选取时间文本 默认"选择"
     */
    public void setNextBtTip(String str) {
        tv_select.setText(str);
    }

    public int setScrollUnit(SCROLLTYPE... scrolltypes) {
        if (scrolltypes == null || scrolltypes.length == 0)
            scrollUnits = SCROLLTYPE.HOUR.value + SCROLLTYPE.MINUTE.value;
        for (SCROLLTYPE scrolltype : scrolltypes) {
            scrollUnits ^= scrolltype.value;
        }
        return scrollUnits;
    }

    public void setMode(MODE mode) {
        switch (mode.value) {
            case 1:
                setScrollUnit(SCROLLTYPE.HOUR, SCROLLTYPE.MINUTE);
                hour_pv.setVisibility(View.GONE);
                minute_pv.setVisibility(View.GONE);
                hour_text.setVisibility(View.GONE);
                minute_text.setVisibility(View.GONE);
                break;
            case 2:
                setScrollUnit();
                hour_pv.setVisibility(View.VISIBLE);
                minute_pv.setVisibility(View.VISIBLE);
                hour_text.setVisibility(View.VISIBLE);
                minute_text.setVisibility(View.VISIBLE);
                break;

        }
    }
}
