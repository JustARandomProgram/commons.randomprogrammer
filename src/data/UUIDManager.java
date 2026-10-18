package data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UUIDManager<T> {
    private Random random;
    public Map<Long,T> uuids = new HashMap<>();

    public UUIDManager() {
        random = new Random();
    }

    public UUIDManager(long seed) {
        random = new Random(seed);
    }

    public void generateUUID(T object) {
        long randomUUID;
        do {
            randomUUID = Math.abs(random.nextLong());
        } while (uuids.containsKey(randomUUID));
        uuids.put(randomUUID, object);
    }

    public void removeObject(T object) {
        uuids.remove(getUUID(object), object);
    }

    public boolean objectExists(T object) {
        return uuids.containsValue(object);
    }

    public long getUUID(T object) {
        for (Map.Entry<Long,T> pair : uuids.entrySet()) if (pair.getValue().equals(object)) return pair.getKey();
        return -1;
    }

    public T getObject(long uuid) {
        return uuids.get(uuid);
    }
}
