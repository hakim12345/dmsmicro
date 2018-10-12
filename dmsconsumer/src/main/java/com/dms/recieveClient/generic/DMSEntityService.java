package com.dms.recieveClient.generic;

import java.io.Serializable;

public interface DMSEntityService <K extends Serializable & Comparable<K>, E extends DMSEntity<K, ?>>
        extends TransactionalAspectAwareService {

    E getById(K id);
}
