package com.zzx.sharding1.api;

import java.sql.SQLException;
import java.util.List;

public interface UserApi extends ExampleService {

    List<Long> insert() throws SQLException;

    void delete(final List<Long> userIds) throws SQLException;
}
