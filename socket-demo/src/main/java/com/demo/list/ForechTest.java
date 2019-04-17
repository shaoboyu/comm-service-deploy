package com.demo.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-15
 **/
public class ForechTest {

    public static void main(String[] args) {
        succRemoveListValue();
    }

    public static void errorRemoveListValue(){
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");

        for (String value: stringList) {
            if (value.equals("b")){
                stringList.remove(value);
            }
        }
    }

    public static void succRemoveListValue(){
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");


        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if (next.equals("a")){
                iterator.remove();
            }
        }
    }
}
