package at.cgsit.train.java;

import at.cgsit.train.java.objects.Buch;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StartMain {

  static void main() {

    Map<Integer, Buch> map = new HashMap<>();

    ArrayDeque<String> queue = new ArrayDeque<>();

    Buch a = new Buch(1, "shinging");
    Buch b = new Buch(2, "Harry Potter 1");
    Buch c = new Buch(3, "sameID");

    map.put(a.getId(), a);
    map.put(b.getId(), b);
    map.put( 1, c);

    // siehe interface dokumentation. HashMap erlaubt null keys und value
    map.put(null, null);

    // classic:
    for (Integer key : map.keySet()) {
      System.out.println(key + " : " + map.get(key));
    }

    // map util method keySet() --
    Set<Integer> mySetofKeysFromMap = map.keySet();
    for (Integer key : mySetofKeysFromMap) {
      System.out.println(key + " : " + map.get(key));
    }

    // lambda expression with functional interface Consumer
    map.keySet().forEach((k) -> {
      System.out.println(k + " : " + map.get(k));
    });

    
    Class<? extends Buch> aClass = a.getClass();
    Field[] declaredFields = aClass.getDeclaredFields();
    Method[] declaredMethods = aClass.getDeclaredMethods();

  }
}
