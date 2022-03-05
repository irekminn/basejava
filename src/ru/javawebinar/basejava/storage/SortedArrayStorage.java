package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {

  @Override
  protected void fillDeletedElement(int index) {
    int numMoved = size - index - 1;
    if (numMoved > 0) {
      System.arraycopy(storage, index + 1, storage, index, numMoved);
    }
  }

  @Override
  protected void insertElement(Resume r) {
    // http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
    var index = Arrays.binarySearch(storage, 0, size, r);
    var insertIdx = -index - 1;
    System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
    storage[insertIdx] = r;
  }

  @Override
  protected Integer getIndex(String uuid) {
    Resume searchKey = new Resume(uuid);
    var index = Arrays.binarySearch(storage, 0, size, searchKey);
    return index >= 0  ? index : null;
  }
}