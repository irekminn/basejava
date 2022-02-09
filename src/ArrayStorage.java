import java.util.Arrays;
import java.util.stream.IntStream;

/*
 * Array based storage for Resumes
 */
public class ArrayStorage {

  private int size = 0;
  private final Resume[] storage = new Resume[10000];


  void clear() {
    if (size == 0) {
      return;
    }
    IntStream.range(0, size)
        .forEach(i -> storage[i] = null);
    size = 0;
  }

  void save(final Resume r) {
    if (size == 0) {
      storage[0] = r;
      size++;
      return;
    }

    var checkDuplicate = Arrays.stream(storage, 0, size)
        .anyMatch(resume -> resume.uuid.equals(r.uuid));

    if (checkDuplicate) {  //uuid duplicate, nothing, return
      return;
    }

    if (size < storage.length) {
      storage[size] = r;
    }
    size++;
  }

  Resume get(final String uuid) {
    if (size == 0) {
      return null;
    }
    return Arrays.stream(storage, 0, size)
        .filter(resume -> resume.uuid.equals(uuid))
        .findFirst().orElse(null);
  }

  void delete(final String uuid) {
    if (size == 0) {
      return;
    }

    if (Arrays.stream(storage, 0, size)  // not found uuid, nothing, return
        .noneMatch(resume -> resume.uuid.equals(uuid))) {
      return;
    }

    var index = (int) Arrays.stream(storage, 0, size)
        .takeWhile(resume -> !resume.uuid.equals(uuid))
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
  Resume[] getAll() {
    if (size == 0) {
      return new Resume[0];
    }
    return Arrays.stream(storage, 0, size).toArray(Resume[]::new);
  }

  int size() {
    return size;
  }
}
