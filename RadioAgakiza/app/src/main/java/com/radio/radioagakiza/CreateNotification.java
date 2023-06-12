package com.radio.radioagakiza;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class CreateNotification {
    public   static final String channel_id="channel1";
    private static final String actionplay="play";
    public  static Notification notification;
    public static void createNotification(Context context, Umwidondoro umwidondoro, int playbutton, int pos, int size) {


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat=new MediaSessionCompat(context,"tag");
            Bitmap icon= BitmapFactory.decodeResource(context.getResources(),umwidondoro.getThumbnail());

            notification=new NotificationCompat.Builder(context,channel_id)
                    .setSmallIcon(R.drawable.logoradioagakiza)
                    .setContentTitle(umwidondoro.getName())
                    .setContentText(umwidondoro.getSloga())
                    .setLargeIcon(icon)
                    .setShowWhen(false)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();

            notificationManagerCompat.notify(1,notification);


        }
    }
}
