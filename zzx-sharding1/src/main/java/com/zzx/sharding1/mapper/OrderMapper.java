package com.zzx.sharding1.mapper;

import com.zzx.sharding1.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends CommonRepository<Order, Long> {


}
