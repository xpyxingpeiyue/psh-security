package com.psh.web.controller;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by peiyue.xing on 2019/9/27 16:04
 *
 * @version:
 */
public class BitTest {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        HashSet<String> hashSet = new HashSet<>();
        for(int i = 0 ; i<50000;i++){
            String random = RandomUtil.randomString(6);
            hashSet.add(random);
            arrayList.add(random);
        }
        System.out.println(arrayList.size());
        System.out.println(hashSet.size());
    }
}
