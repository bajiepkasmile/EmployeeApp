package com.nodomain.employeeapp.utils.collection;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CollectionUtil {

    public static <E extends Copyable<E>> List<E> copyCollectionDeep(Collection<E> src) {
        if (src == null)
            return null;

        List<E> copiedList = new ArrayList<>();
        for (E e : src)
            copiedList.add(e.copy());

        return copiedList;
    }

    public static <T, D> List<T> mapCollection(Collection<D> src, MapFunction<T, D> mapFunction) {
        if (src == null)
            return null;

        List<T> list = new ArrayList<>();
        for (D dto : src)
            list.add(mapFunction.map(dto));
        return list;
    }
}
