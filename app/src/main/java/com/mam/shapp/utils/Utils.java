package com.mam.shapp.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Point;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.Display;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utils {

    public static int getScreenWidth(Display display) {
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 3;
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static boolean isPhoneValid(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean isNameValid(String name) {
        return name.length() > 2;
    }

    public static boolean isActivationCodeValid(String activationCode) {
        return activationCode.length() > 4;
    }

    public static SpannableString underlineText(String textFull, String textUnderlined, final int color) {
        SpannableString spanText = new SpannableString(textFull);
        int startTextToUnderline;
        int endTextToUnderline;
        startTextToUnderline = textFull.indexOf(textUnderlined);
        if (startTextToUnderline == -1) {
            startTextToUnderline = 0;
            endTextToUnderline = textFull.length();
        } else {
            endTextToUnderline = startTextToUnderline + textUnderlined.length();
        }
        spanText.setSpan(new UnderlineSpan(), startTextToUnderline, endTextToUnderline, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanText;
    }

    public static CharSequence getFormattedText(String text) {
        CharSequence formattedText;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formattedText = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            formattedText = Html.fromHtml(text);
        }
        return formattedText;
    }

    public static String buildGetUrl(String baseUrl, HashMap<String, String> mapValues) {
        Iterator it = mapValues.entrySet().iterator();
        baseUrl = baseUrl + "?";
        while (it.hasNext()) {
            Map.Entry value = (Map.Entry) it.next();
            String pair = value.getKey() + "=" + value.getValue();
            baseUrl = baseUrl + pair;
            if (it.hasNext()) {
                baseUrl = baseUrl + "&";
            }
        }
        return baseUrl.trim();
    }

    public static Map<String, String> getHashMapResource(Context context, int hashMapResId) {
        Map<String, String> map = new HashMap<>();
        try {
            XmlResourceParser parser = context.getResources().getXml(hashMapResId);
            String key = null, value = null;
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("item")) {
                        key = parser.getAttributeValue(null, "name");
                        if (null == key) {
                            parser.close();
                            return null;
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (parser.getName().equals("item")) {
                        map.put(key, value);
                        key = null;
                        value = null;
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    if (null != key) {
                        value = parser.getText();
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }
}
