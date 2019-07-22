package com.ziv.demo.ntptimeservice;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ziv.demo.ntptimeservice.services.SntpClient;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] ntpServerPool = {"ntp1.aliyun.com", "ntp2.aliyun.com", "ntp3.aliyun.com", "ntp4.aliyun.com", "ntp5.aliyun.com", "ntp6.aliyun.com", "ntp7.aliyun.com",
                "cn.pool.ntp.org", "cn.ntp.org.cn", "sg.pool.ntp.org", "tw.pool.ntp.org", "jp.pool.ntp.org", "hk.pool.ntp.org", "th.pool.ntp.org",
                "time.windows.com", "time.nist.gov", "time.apple.com", "time.asia.apple.com",
                "dns1.synet.edu.cn", "news.neu.edu.cn", "dns.sjtu.edu.cn", "dns2.synet.edu.cn", "ntp.glnet.edu.cn", "s2g.time.edu.cn",
                "ntp-sz.chl.la", "ntp.gwadar.cn", "3.asia.pool.ntp.org"};

        new Thread(){
            @Override
            public void run() {
                SntpClient sntpClient = new SntpClient();

                if (sntpClient.requestTime("cn.pool.ntp.org", 30000)) {
                    long now = sntpClient.getNtpTime() + SystemClock.elapsedRealtime() - sntpClient.getNtpTimeReference();
                    Date current = new Date(now);
                    Log.d(TAG, current.toString());
                }
            }
        }.start();
    }
}
