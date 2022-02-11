package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;
import java.util.stream.IntStream;

/*
 * Array based storage for Resumes
 */
public class ArrayStorage {

  private int size = 0;
  private final Resume[] storage = new Resume[10000];


  public void clear() {
    if (size == 0) {
      return;
    }
    IntStream.range(0, size).forEach(i -> storage[i] = null);
    size = 0;
  }

  public void save(final Resume r) {
    if (size == 0) {
      storage[0] = r;
      size++;
      return;
    }

    var checkDuplicate = Arrays.stream(storage, 0, size)
        .anyMatch(resume -> resume.getUuid().equals(r.getUuid()));

    if (checkDuplicate) {  //uuid duplicate, nothing, return
      System.out.println("Duplicate resume! Resume uuid: " + r.getUuid() + " already exists");
      return;
    }

    if (size < storage.length) {
      storage[size] = r;
      size++;
    } else {
      System.out.println("Storage is full. Saving a new resume with uuid: "
          + r.getUuid() + " is not possible");
    }
  }

  public Resume get(final String uuid) {
    if (size == 0) {
      return null;
    }

    if (isResume(uuid)) {
      printErrorMessage(uuid);
      return null;
    }

    return Arrays.stream(storage, 0, size)
        .filter(resume -> resume.getUuid().equals(uuid))
        .findFirst().orElse(null);
  }

  public void delete(final String uuid) {
    if (size == 0) {
      return;
    }

    if (isResume(uuid)) {
      printErrorMessage(uuid);
      return;
    }

    var index = (int) Arrays.stream(storage, 0, size)
        .takeWhile(resume -> !resume.getUuid().equals(uuid))
        .count();

    if (storage[index + 1] == null) {
      storage[index] = null;
    } else {
      storage[index] = storage[size - 1];
      storage[size - 1] = null;
    }
    size--;
  }

  /*
   * @return array, contains only Resumes in storage (without null)
   */
  public Resume[] getAll() {
    if (size == 0) {
      return new Resume[0];
    }
    return Arrays.stream(storage, 0, size).toArray(Resume[]::new);
  }

  public int size() {
    return size;
  }

  public void update(Resume r) {
    if (!isResume(r)) {
      printErrorMessage(r);
      return;
    }
    delete(r.getUuid());
    save(r);
  }

  private void printErrorMessage(Resume r) {
    System.out.println("Resume with uuid: " + r.getUuid() + " not found in storage");
  }

  private void printErrorMessage(String uuid) {
    System.out.println("Resume with uuid: " + uuid + " not found in storage");
  }

  private boolean isResume(Resume r) {
    return Arrays.stream(storage, 0, size)
        .anyMatch(resume -> resume.getUuid().equals(r.getUuid()));
  }

  private boolean isResume(String uuid) {
    return Arrays.stream(storage, 0, size)
        .noneMatch(resume -> resume.getUuid().equals(uuid));
  }

}
