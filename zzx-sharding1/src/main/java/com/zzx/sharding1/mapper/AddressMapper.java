package com.zzx.sharding1.mapper;

import com.zzx.sharding1.entity.Address;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper extends CommonRepository<Address, Long> {


}
