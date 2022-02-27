package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

  protected AbstractArrayStorageTest(Storage storage) {
    super(storage);
  }

  @Override
  protected int doSize() {
    return storage.size();
  }

  @Override
  protected void doClear() {
    storage.clear();
  }

  @Override
  protected void doUpdate(Resume newResume) {
    storage.update(newResume);
  }

  @Override
  protected void doGet(String dummy) {
    storage.get(dummy);
  }

  @Override
  protected void doSave(Resume r) {
    storage.save(r);
  }

  @Override
  protected void doDelete(String uuid) {
    storage.delete(uuid);
  }


  @Test(expected = StorageException.class)
  public void saveOverflow() throws Exception {
    for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
     doSave(new Resume());
    }
    Assert.fail("storage not overflow");
  }

}