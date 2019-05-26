package com.concurrency.chapter12;

        import java.util.List;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-17 10:16
 */
public interface DepartMentService {

    List<Department> getDepartmentList(List<Department> departList, Integer departmentId);
}
