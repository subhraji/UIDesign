package e.subhrajit.uidesign;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MyAlarm extends BroadcastReceiver {
    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.alarmaudio);
        mp.start();
        Toast.makeText(context, "Alarm........", Toast.LENGTH_LONG).show();
        simple_Notification();
    }
    private void simple_Notification()
    {
        Context context = GlobalApplication.getAppContext();
        int notificatoinid= 0;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.xipaarlogo)
                .setContentTitle("Task Alert")
                .setContentText("You have a task to complete...!")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

        builder.addAction(android.R.drawable.ic_menu_view,"VIEW",pendingIntent);

        Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(path);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelid = "YOUR_CHANNEL_ID";
            NotificationChannel channel = new NotificationChannel(channelid,"YOUR_CHANNEL_ID", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelid);

        }
        notificationManager.notify(notificatoinid,builder.build());
    }
}