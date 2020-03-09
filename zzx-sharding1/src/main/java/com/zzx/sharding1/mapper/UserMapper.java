package com.zzx.sharding1.mapper;

import com.zzx.sharding1.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends CommonRepository<User, Long> {


}
