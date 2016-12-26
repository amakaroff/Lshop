package com.makarov.lshop.base.creator.api;

import java.util.List;

public interface Creator<T, E> {

    T create(List<E> list);
}
