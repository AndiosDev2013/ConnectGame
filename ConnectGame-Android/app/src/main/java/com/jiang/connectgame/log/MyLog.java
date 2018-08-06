package com.jiang.connectgame.log;

import android.util.Log;

public class MyLog
{
  static String TAG_ERROR = "TAG_ERROR";
  static String TAG_INFO;
  public static boolean islog = false;

  static
  {
    TAG_INFO = "TAG_INFO";
  }

  public static void LogError(String paramString)
  {
    if (islog)
      Log.e(TAG_ERROR, "##### Connection Game #####" + paramString);
  }

  public static void LogInfo(String paramString)
  {
    if (islog)
      Log.i(TAG_INFO, "##### Connection Game #####" + paramString);
  }
}

