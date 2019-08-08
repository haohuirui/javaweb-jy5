package com.hhr.rlg.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class RlgUtil {
    private static final ComboPooledDataSource co = new ComboPooledDataSource();
    public static ComboPooledDataSource getCom(){
        return co;
    }
}
