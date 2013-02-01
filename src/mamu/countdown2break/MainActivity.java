package mamu.countdown2break;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	int[] millis = {27300000, 30300000, 33900000, 36900000, 40800000, 43800000, 46800000, 49500000, 52200000, 113700000};
	TextView tv1;
	TextView tv2;
	ProgressBar p1;
	
	private long getMillisOfLessonsEndFromLessonNumber(int lessonnumber)
	{
		return getMidnightMillis() + millis[lessonnumber - 1];
	}
		
	private long getMidnightMillis()
	{
		long millis = System.currentTimeMillis();
		long minimalanteil = millis % 86400000;
		return millis - minimalanteil;
	}
	
	private long getNextLessonFinishedMillis()
	{
		int i = 1;
		while (System.currentTimeMillis() > getMillisOfLessonsEndFromLessonNumber(i))
		{
			i++;
		}
		tv1.setText(""+(i % 9));
		return getMillisOfLessonsEndFromLessonNumber(i);
	}
	
	private void setTime(long muf)
	{
		int h, m, s = 0;
		long tmp = muf % 1000;
		muf = muf - tmp;
		muf = muf / 1000;
		s = (int) muf % 60;
		muf = muf - s;
		muf = muf / 60;
		m = (int) muf % 60;
		muf = muf - m;
		muf = muf / 60;
		h = (int) muf % 24;
		tv2.setText(int2String(h)+":"+int2String(m)+":"+int2String(s));
	}
	
	private String int2String(int i)
	{
		if ( i < 10)
		{
			return "0" + i;
		}
		else
		{
			return ""+i;
		}
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		p1 = (ProgressBar) findViewById(R.id.progressBar1);
		p1.setMax(2700000);
//		System.out.println(getMidnightMillis());
//		System.out.println(System.currentTimeMillis());
//		System.out.println(getMillisOfLessonsEndFromLessonNumber(1));
//		System.out.println(getNextLessonFinishedMillis());
		
		 new CountDownTimer(getNextLessonFinishedMillis() - System.currentTimeMillis(), 998) {
			 
		     public void onTick(long millisUntilFinished) {
		         setTime(millisUntilFinished);
//		         if (millisUntilFinished <= 2700000)
//		         {
//		        	 int temp = (int) millisUntilFinished;
//		        	 p1.setProgress(temp);
//		         }
		         
//		         System.out.println("hans");
		     }

		     public void onFinish() {
		         tv2.setText("Happy Pause ;-)");
		     }
		  }.start();
	}
}
