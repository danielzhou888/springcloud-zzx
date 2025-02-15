/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zzx.shardingsphere.spi.database;

import com.spi.database.BranchDatabaseType;
import com.spi.database.DataSourceMetaData;
import com.spi.database.DatabaseType;
import com.zzx.shardingsphere.database.DatabaseTypes;
import com.zzx.shardingsphere.metadata.datasource.dialect.MariaDBDataSourceMetaData;

import java.util.Collection;
import java.util.Collections;

/**
 * Database type of Mariadb.
 *
 * @author yanqiufang
 */
public final class MariaDBDatabaseType implements BranchDatabaseType {
    
    @Override
    public String getName() {
        return "MariaDB";
    }

    @Override
    public Collection<String> getJdbcUrlPrefixAlias() {
        return Collections.singletonList("jdbc:mariadb:");
    }
    
    @Override
    public DataSourceMetaData getDataSourceMetaData(final String url) {
        return new MariaDBDataSourceMetaData(url);
    }

    @Override
    public DatabaseType getTrunkDatabaseType() {
        return DatabaseTypes.getActualDatabaseType("MySQL");
    }

}
