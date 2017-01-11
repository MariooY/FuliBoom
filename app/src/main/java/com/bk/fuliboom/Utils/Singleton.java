package com.bk.fuliboom.Utils;

/**
 * Created by Bk on 2016/9/24.
 */

public class Singleton {
    private static volatile Singleton instance;
    private Singleton(){}



    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return  instance;
    }

//    public static Singleton getInstance(){
//        if (instance == null){
//            instance = new Singleton();
//        }
//        return instance;
//    }
//    private static final Singleton instance = new Singleton();
//    public static Singleton getInstance(){
//        return instance;
//    }
//
//    private static class  SingletonHolder{
//        public static final Singleton INSTANCE = new Singleton();
//    }
//
//    public  static Singleton getInstance(){
//        return SingletonHolder.INSTANCE;
//    }

}
