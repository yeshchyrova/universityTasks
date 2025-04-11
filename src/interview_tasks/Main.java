package interview_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Iterator;
import java.util.HashMap;

//Bob:Rock,Blues,Jazz
//Alice:Rock,Jazz,Blues
//John:Jazz,Blues,Rock

public class Main {

  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;

    LinkedHashMap<String, String[]> map = new LinkedHashMap<>();
    LinkedHashMap<String, Integer[]> ratingMap = new LinkedHashMap<>();

    int listLength = 0;

    while ((line = in.readLine()) != null) {
      String[] s = line.split(":");
      String[] preferences = s[1].split(",");
      map.put(s[0], preferences);
      if (listLength == 0) listLength = preferences.length;
    }


    Map.Entry<String, String[]> activeUser = map.entrySet().iterator().next();
    String[] activeUserPreferences = activeUser.getValue();

    HashMap<String, Integer> rating = new HashMap<>();
    /*
    RATING
    Rock - 1
    Blues - 2
    Jazz - 3
     */

    for (int i = 0; i < activeUserPreferences.length; i++) {
      rating.put(activeUserPreferences[i], i + 1);
    }

    Iterator<Map.Entry<String, String[]>> iterator = map.entrySet().iterator();
    if (iterator.hasNext()) iterator.next();

    int m = 0;
    Integer[] activeUserValues = new Integer[activeUserPreferences.length];
    for (String ignored : activeUser.getValue()) {
      activeUserValues[m] = m+1;
      m++;
    }
    ratingMap.put(activeUser.getKey(), activeUserValues);

    while (iterator.hasNext()) {
      Map.Entry<String, String[]> user = iterator.next();
      Integer[] values = new Integer[activeUserPreferences.length];
      int t = 0;
      for (String el : user.getValue()) {
        values[t] = rating.get(el);
        t++;
      }
      ratingMap.put(user.getKey(), values);
    }


    for (String key : map.keySet()) {
      System.out.println(key + " - " + Arrays.toString(map.get(key)));
    }
  }
}

