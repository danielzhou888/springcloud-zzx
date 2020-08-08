package com.zzx.design.pattern.zzxdesignpattern.fly_weight_model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-08-03
 */
public class TeacherFactory {
    private static final Map<Long, Teacher> teacherPool = new HashMap<>();

    public static Teacher getTeacher(Long tNo) {
        if (tNo != null && tNo > 0) {
            if (teacherPool.get(tNo) != null) {
                return teacherPool.get(tNo);
            } else {
                Teacher teacher = new Teacher(tNo);
                teacherPool.put(tNo, teacher);
                return teacher;
            }
        } else {
            throw new RuntimeException("教师编号非法");
        }
    }
}
