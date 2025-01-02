package com.project.wma.Exeption;

import lombok.NonNull;

public class ObjectNotFoundException extends Exception{

    public ObjectNotFoundException(@NonNull String objectType, Object id) {
        super(String.format("object not found: objectType=%s, object=%s", objectType, id));
    }
}
