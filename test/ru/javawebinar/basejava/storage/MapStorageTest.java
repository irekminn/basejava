package ru.javawebinar.basejava.storage;

import java.util.LinkedHashMap;

public class MapStorageTest extends AbstractStorageTest{

  public MapStorageTest() {
    super(new MapStorage(new LinkedHashMap<>()));
  }
}