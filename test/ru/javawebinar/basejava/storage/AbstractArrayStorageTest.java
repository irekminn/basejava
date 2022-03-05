package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

  protected AbstractArrayStorageTest(Storage storage) {
    super(storage);
  }

  @Test(expected = StorageException.class)
  public void saveOverflow() throws Exception {
    for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
     doSave(new Resume());
    }
    Assert.fail("storage not overflow");
  }

}