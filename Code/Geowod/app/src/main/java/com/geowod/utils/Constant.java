package com.geowod.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Praphulla on 11/8/2015.
 */
public class Constant {

    public static void volleySSLSocket() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                    return myTrustedAnchors;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs,
                                               String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs,
                                               String authType) {
                }
            } };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection
                    .setDefaultHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String arg0, SSLSession arg1) {
                            return true;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        Bitmap output = null;
        if (scaleBitmapImage.getWidth() > scaleBitmapImage.getHeight()) {
            output = Bitmap.createBitmap(scaleBitmapImage.getHeight(),
                    scaleBitmapImage.getHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(output);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, scaleBitmapImage.getWidth(),
                    scaleBitmapImage.getHeight());

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            canvas.drawCircle(scaleBitmapImage.getHeight() / 2,
                    scaleBitmapImage.getHeight() / 2,
                    scaleBitmapImage.getHeight() / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(scaleBitmapImage, rect, rect, paint);
            // Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
            // return _bmp;
        } else {
            try {

                output = Bitmap.createBitmap(scaleBitmapImage.getWidth(),
                        scaleBitmapImage.getHeight(), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(output);
                final int color = 0xff424242;
                final Paint paint = new Paint();
                final Rect rect = new Rect(0, 0, scaleBitmapImage.getWidth(),
                        scaleBitmapImage.getHeight());

                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(color);
                // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
                canvas.drawCircle(scaleBitmapImage.getWidth() / 2,
                        scaleBitmapImage.getHeight() / 2,
                        scaleBitmapImage.getWidth() / 2, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(scaleBitmapImage, rect, rect, paint);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return output;
    }





}
