/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamics.jdbc;

import com.appdynamics.inventory.QueryExecutor;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 * @author Owner
 */
public class MCallableStatement implements CallableStatement {

    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean wasNull() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getString(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean getBoolean(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte getByte(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public short getShort(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getInt(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getLong(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float getFloat(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getDouble(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte[] getBytes(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getDate(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Time getTime(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getObject(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Ref getRef(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Blob getBlob(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Clob getClob(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Array getArray(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public URL getURL(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setURL(String parameterName, URL val) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNull(String parameterName, int sqlType) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBoolean(String parameterName, boolean x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setByte(String parameterName, byte x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setShort(String parameterName, short x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setInt(String parameterName, int x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLong(String parameterName, long x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setFloat(String parameterName, float x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDouble(String parameterName, double x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setString(String parameterName, String x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBytes(String parameterName, byte[] x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDate(String parameterName, Date x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTime(String parameterName, Time x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setObject(String parameterName, Object x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getString(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean getBoolean(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte getByte(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public short getShort(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getInt(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getLong(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float getFloat(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getDouble(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte[] getBytes(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getDate(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Time getTime(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Timestamp getTimestamp(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getObject(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Ref getRef(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Blob getBlob(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Clob getClob(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Array getArray(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public URL getURL(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RowId getRowId(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RowId getRowId(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setRowId(String parameterName, RowId x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNString(String parameterName, String value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNClob(String parameterName, NClob value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NClob getNClob(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NClob getNClob(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SQLXML getSQLXML(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNString(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNString(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reader getNCharacterStream(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reader getCharacterStream(String parameterName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBlob(String parameterName, Blob x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClob(String parameterName, Clob x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClob(String parameterName, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNClob(String parameterName, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet executeQuery() throws SQLException {
        try {
            Thread.sleep(QueryExecutor.SLEEP_TIME);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return new MResultSet();
    }

    public int executeUpdate() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setByte(int parameterIndex, byte x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setShort(int parameterIndex, short x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setInt(int parameterIndex, int x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLong(int parameterIndex, long x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setFloat(int parameterIndex, float x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDouble(int parameterIndex, double x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setString(int parameterIndex, String x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDate(int parameterIndex, Date x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTime(int parameterIndex, Time x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearParameters() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setObject(int parameterIndex, Object x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addBatch() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setRef(int parameterIndex, Ref x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClob(int parameterIndex, Clob x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setArray(int parameterIndex, Array x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setURL(int parameterIndex, URL x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNString(int parameterIndex, String value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int executeUpdate(String sql) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void close() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxFieldSize() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMaxFieldSize(int max) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxRows() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMaxRows(int max) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setEscapeProcessing(boolean enable) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getQueryTimeout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setQueryTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void cancel() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SQLWarning getWarnings() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearWarnings() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCursorName(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute(String sql) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getResultSet() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getUpdateCount() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean getMoreResults() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setFetchDirection(int direction) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getFetchDirection() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setFetchSize(int rows) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getFetchSize() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getResultSetConcurrency() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getResultSetType() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addBatch(String sql) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearBatch() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int[] executeBatch() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Connection getConnection() throws SQLException {
        return new MConnection();
    }

    public boolean getMoreResults(int current) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getResultSetHoldability() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isClosed() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPoolable(boolean poolable) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isPoolable() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void closeOnCompletion() throws SQLException {
    }


    public boolean isCloseOnCompletion() throws SQLException {
        return false;
    }


    public <T> T getObject(int parameterIndex, Class<T> type)
            throws SQLException {
        return null;
    }


    public <T> T getObject(String parameterName, Class<T> type)
            throws SQLException {
        return null;
    }

}
