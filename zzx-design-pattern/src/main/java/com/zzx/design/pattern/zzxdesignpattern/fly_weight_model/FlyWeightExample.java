package com.zzx.design.pattern.zzxdesignpattern.fly_weight_model;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhouzhixiang
 * @Date 2020-08-03
 */
@Slf4j
public class FlyWeightExample {

    public static void main(String[] args) {
        Teacher teacher1 = TeacherFactory.getTeacher(1L);
        Teacher teacher2 = TeacherFactory.getTeacher(2L);
        Teacher teacher3 = TeacherFactory.getTeacher(3L);
        Teacher teacher4 = TeacherFactory.getTeacher(2L);

        teacher1.handlePaper(1L);
        teacher2.handlePaper(1L);
        log.info("teacher 1 == tercher4 -> " + (teacher1 == teacher4));
        log.info("teacher 2 == tercher4 -> " + (teacher2 == teacher4));
        //test();
    }

    public static void test() {
        int[] origin = new int[]{1, 2, 3, 4};
        int[] target = new int[]{2, 3, 4, 5};
        System.arraycopy(origin, 0, target, 0, origin.length);
        for (int i = 0; i < target.length; i++) {
            System.out.println(target[i]);
        }
    }
}
