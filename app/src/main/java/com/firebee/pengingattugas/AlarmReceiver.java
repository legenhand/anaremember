package com.firebee.pengingattugas;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import com.firebee.pengingattugas.model.Alarm;
import com.firebee.pengingattugas.model.AlarmMsg;


public class AlarmReceiver extends BroadcastReceiver {
	
//	private static final String TAG = "AlarmReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		long alarmMsgId = intent.getLongExtra(AlarmMsg.COL_ID, -1);
		long alarmId = intent.getLongExtra(AlarmMsg.COL_ALARMID, -1);
		String nama_alarm;
		AlarmMsg alarmMsg = new AlarmMsg(alarmMsgId);
		alarmMsg.setStatus(AlarmMsg.EXPIRED);
		alarmMsg.persist(PengingatTugas.db);
		
		Alarm alarm = new Alarm(alarmId);
		alarm.load(PengingatTugas.db);
		Notification n = new Notification(R.drawable.ic_launcher, alarm.getName(), System.currentTimeMillis());
		PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(), 0);

		n.setLatestEventInfo(context, "Pengingat Tugas", alarm.getName(), pi);
		if (PengingatTugas.isVibrate()) {
			n.defaults |= Notification.DEFAULT_VIBRATE;
		}
		if (alarm.getSound()) {
			n.sound = Uri.parse(PengingatTugas.getRingtone());
//			n.defaults |= Notification.DEFAULT_SOUND;
		}		
		n.flags |= Notification.FLAG_AUTO_CANCEL;

		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify((int)alarmMsgId, n);

		Intent i = new Intent(context, Main2Activity.class);

		Bundle b = new Bundle();
		b.putString("nama_alarm", alarm.getName());
		b.putString("ket_alarm", alarm.getKet());
		i.putExtras(b);
		context.startActivity(i);
	}

}
