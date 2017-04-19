package com.java.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hulb on 17/4/19.
 *
 * -Xmx500M -Xms500M -Xmn200M -XX:+UseConcMarkSweepGC
 * -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=90
 *
 */
public class ArrayListGc {

    public static void   main(String[] args){
        allocateMemory2();
        try {
            Thread.sleep(100000);
        }catch (Exception e){

        }
    }



    public static  void allocateMemory(){
        List<byte[]> list = new ArrayList<byte[]>();
        int size = 1024*1024*1024;
        int len = size/(20*1024);
        for(int i = 0;i < len; i++){
            try{
                byte[] bytes = new byte[20*1024];
                list.add(bytes);
            }catch (java.lang.OutOfMemoryError e){

            }
        }
    }

    public static  void allocateMemory2(){
        List<byte[]> list = new ArrayList<byte[]>();
        int size = 1024*1024*1024*2;
        int len = size/(20*1024);
        for(int i = 0;i < 10000; i++){
            try{
                try {
                    Thread.sleep(50);
                }catch (Exception e){

                }
                byte[] bytes = new byte[1024*1024*2];
                list.add(bytes);
            }catch (java.lang.OutOfMemoryError e){

            }
        }
    }

}
