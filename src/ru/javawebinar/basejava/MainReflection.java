package ru.javawebinar.basejava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {

  public static void main(String[] args) throws IllegalAccessException {
    Resume r = new Resume();
    Field field = r.getClass().getDeclaredFields()[0];
    field.setAccessible(true);
    System.out.println(field.getName());
    System.out.println(field.get(r));
    field.set(r, "new_uuid");
    // TODO : invoke r.toString via reflection
    var arrayMethods = r.getClass().getMethods();
    for (Method method : arrayMethods) {
      if ("toString".equals(method.getName())) {
        try {
          System.out.println(method.invoke(r));
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    }
    System.out.println(r);
  }
}