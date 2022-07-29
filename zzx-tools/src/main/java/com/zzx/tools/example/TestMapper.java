//package com.zzx.tools.example;
//
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//public interface TestMapper {
//
//	List<TestEntity> queryByIds(@Param("ids") String ids);
//
//    int countByExample(TestExample example);
//
//    int deleteByExample(TestExample example);
//
//    int deleteByPrimaryKey(Short id);
//
//    int insert(TestEntity record);
//
//    int insertSelective(TestEntity record);
//
//    List<TestEntity> selectByExample(TestExample example);
//
//    TestEntity selectByPrimaryKey(Short id);
//
//    int updateByExampleSelective(@Param("record") TestEntity record, @Param("example") TestExample example);
//
//    int updateByExample(@Param("record") TestEntity record, @Param("example") TestExample example);
//
//    int updateByPrimaryKeySelective(TestEntity record);
//
//    int updateByPrimaryKey(TestEntity record);
//}