package ru.javawebinar.basejava.storage;

import java.util.ArrayList;

public class ListStorageTest extends AbstractStorageTest {


  public ListStorageTest() {
    super(new ListStorage<>(new ArrayList<>()));
  }
}