package com.azamat.data.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.azamat.R;
import com.azamat.ui.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MyFSMService extends FirebaseMessagingService {

    private String title;
    private String message;
    private Bitmap image;
    private String type;
    private int notificationId;

    private Map<String, String> data;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        onPostExecute(remoteMessage);
    }

    private void onPostExecute(RemoteMessage remoteMessage){
    data = remoteMessage.getData();

    if (remoteMessage.getNotification().getTitle() != null){
        title = remoteMessage.getNotification().getTitle();
    }

    if (remoteMessage.getNotification().getBody() != null){
        message = remoteMessage.getNotification().getBody();
    }

    if (remoteMessage.getNotification().getImageUrl() != null){
        image = getBitmapfromUrl(remoteMessage.getNotification().getImageUrl().toString());
    }

    if (remoteMessage.getData().containsKey("id") && remoteMessage.getData().get("id") != null){
        notificationId = Integer.valueOf(data.get("id"));

        Intent intent = new Intent(this, MainActivity.class);
        openNotifications(intent);
    }

    }

    private void openNotifications(Intent intent) {

        String chanelId = "Default chanel";

        PendingIntent pendingIntent = PendingIntent.getActivity(this, notificationId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(
                this, chanelId)
                .setSmallIcon(R.drawable.ic_brain)
                .setStyle( new NotificationCompat.BigPictureStyle()
                        .bigPicture(image)
                        .bigLargeIcon(null))
                .setLargeIcon(image)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        Notification notification = notBuilder.build();

        notification.tickerText = title;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(chanelId,
                    "Default chanel", notificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(notificationId,notification);
    }

    public Bitmap getBitmapfromUrl (String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e){
            Log.d("bitmap", "error" + e);

            return null;
        }
    }
}
