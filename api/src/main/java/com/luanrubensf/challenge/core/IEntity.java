package com.luanrubensf.challenge.core;

import java.io.Serializable;

public interface IEntity<T> extends Serializable {
    T getId();
}
