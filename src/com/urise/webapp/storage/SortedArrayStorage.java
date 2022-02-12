package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

  @Override
  protected void delElement(int index) {
    var countMoved = size - index - 1;
    if (countMoved > 0) {
      System.arraycopy(storage, index + 1, storage, index, countMoved);
    }

  }

  @Override
  protected int getIndex(String uuid) {
    Resume searchKey = new Resume();
    searchKey.setUuid(uuid);
    return Arrays.binarySearch(storage, 0, size, searchKey);
  }


  @Override
  protected void addElement(Resume r, int index) {
    var insetIndex = -index - 1;
    System.arraycopy(storage, insetIndex, storage, insetIndex + 1, size - insetIndex);
    storage[insetIndex] = r;
  }
}
