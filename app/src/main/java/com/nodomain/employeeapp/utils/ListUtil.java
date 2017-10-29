package com.nodomain.employeeapp.utils;


import java.util.ArrayList;
import java.util.List;


public class ListUtil {

    public static <E extends Copyable<E>> List<E> copyListDeep(List<E> src) {
        if (src == null)
            return null;

        List<E> copiedList = new ArrayList<>();
        for (E e : src)
            copiedList.add(e.copy());

        return copiedList;
    }
}
