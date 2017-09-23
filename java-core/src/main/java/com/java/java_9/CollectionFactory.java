package com.java.java_9;

import java.util.List;

/**
 * Created by hulb on 17/9/23.
 *
 * 集合工厂设置
 */
public class CollectionFactory {

    public static void main(String[] arg) {

        /**
         * 集合工厂类
         */
        List<String> str = List.of("a","b","c","d");
        str.stream().forEach(System.out::println);

        ProcessHandle.current();

    }
}
