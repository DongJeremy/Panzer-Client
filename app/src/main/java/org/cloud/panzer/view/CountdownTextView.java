package org.cloud.panzer.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownTextView extends AppCompatTextView {

    private final int what_count_down_tick = 1;
    private long mSeconds;
    private String first, end;
    private String mStrFormat;
    private TimerTask mTimerTask;
    private Map<Integer, Timer> mTimerMap;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == what_count_down_tick) {
                setText(first);
                if (mSeconds <= 0) {
                    append(String.format(mStrFormat, "00:00:00"));
                } else {
                    append(mStrFormat == null ? second2TimeSecond(mSeconds) : String.format(mStrFormat, second2TimeSecond(mSeconds)));
                }
                append(end);
            }
        }
    };

    public CountdownTextView(Context context) {
        super(context);
    }

    public CountdownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(String format, long seconds, String first, String end) {
        this.first = first;
        this.end = end;
        cancel();
        mTimerMap = new HashMap<>();
        if (!TextUtils.isEmpty(format)) {
            mStrFormat = format;
        }
        mSeconds = seconds; //设置总共的秒数
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (mSeconds > 0) {
                    mSeconds--;
                    mHandler.sendEmptyMessage(what_count_down_tick);
                } else {
                    cancel();
                }
            }
        };
    }

    public void start(int position) {
        if (mTimerMap.get(position) == null) {
            Timer timer = new Timer();
            mTimerMap.put(position, timer);
            mTimerMap.get(position).schedule(mTimerTask, 0, 1000);
        }
    }

    public void cancel() {
        if (mTimerTask != null) {
            mTimerTask.cancel();//注意：这儿必须先停止倒计时再初始化控件，否则有可能时间会乱
            mTimerTask = null;
        }
    }

    /**
     * 与Window解绑自动停止计时
     **/
    @Override
    protected void onDetachedFromWindow() {
        cancel();
        super.onDetachedFromWindow();
    }

    @Override
    public void removeOnLayoutChangeListener(OnLayoutChangeListener listener) {
        super.removeOnLayoutChangeListener(listener);
    }

    @Override
    public void removeOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
        super.removeOnAttachStateChangeListener(listener);
    }

    private String second2TimeSecond(long second) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd HH:mm:ss", Locale.CHINESE);
        String time = simpleDateFormat.format(new Date(second));

        long day = second / 86400;
        long hours = (second % 86400) / 3600;
        long minutes = ((second % 86400) % 3600) / 60;
        long seconds = (((second % 86400) % 3600) % 60);

        String dayString = "";
        String hourString = "";
        String minuteString = "";
        String secondString = "";
        if (day < 10) {
            dayString = "0";
            if (day == 0) {
                dayString += "0";
            } else {
                dayString += String.valueOf(day);
            }
        } else {
            dayString = String.valueOf(day);
        }
        if (hours < 10) {
            hourString = "0";
            if (hours == 0) {
                hourString += "0";
            } else {
                hourString += String.valueOf(hours);
            }
        } else {
            hourString = String.valueOf(hours);
        }
        if (minutes < 10) {
            minuteString = "0";
            if (minutes == 0) {
                minuteString += "0";
            } else {
                minuteString += String.valueOf(minutes);
            }
        } else {
            minuteString = String.valueOf(minutes);
        }
        if (seconds < 10) {
            secondString = "0";
            if (seconds == 0) {
                secondString += "0";
            } else {
                secondString += String.valueOf(seconds);
            }
        } else {
            secondString = String.valueOf(seconds);
        }

        String result = "";

        if (!dayString.equals("00")) {
            result = result + dayString + "天";
        }

        if (!hourString.equals("00")) {
            result = result + hourString + ":";
        }

        if (!minuteString.equals("00")) {
            result = result + minuteString + ":";
        }

        if (!secondString.equals("00")) {
            result = result + secondString;
        }

        return result;
    }
}