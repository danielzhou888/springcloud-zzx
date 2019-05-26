//package com.concurrency.chapter12;
//
//import java.util.List;
//
///**
// * @program: springcloud-zzx
// * @description: 部门业务操作实现类
// * @author: zhouzhixiang
// * @create: 2019-04-17 10:14
// */
//public class DepartmentServiceImpl implements DepartMentService {
//
//    @Autowired
//    private DepartmentMapper departmentMapper;
//
//    @Override
//    public List<Department> getDepartmentList(List<Department> departList, Integer departmentId) {
//        try {
//            List<Department> list = departmentMapper.getDListByParentId(departmentId);
//            if (null != list && list.size()>0) {
//                for (int i = 0; i < list.size(); i++) {
//                    Department department = list.get(i);
//                    departList.add(department);
//                    getDepartmentList(departList, department.getDepartmentid());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return departList;
//}
