package sg.edu.rp.c346.task_manager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    int id = 12345;


    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("Data");

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action a = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, data, pi).build();

        NotificationCompat.WearableExtender e = new NotificationCompat.WearableExtender();
        e.addAction(a);

        NotificationCompat.Builder b = new NotificationCompat.Builder(context, "default");
        b.setContentTitle("Task Manager Reminder");
        b.setContentText(data);
        b.setSmallIcon(R.mipmap.ic_launcher);

        b.extend(e);

        Notification notif = b.build();

        nm.notify(id, notif);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel("default", "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            nm.createNotificationChannel(channel);

        }
    }
}
