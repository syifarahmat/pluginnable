package id.syifarahmat.librarydesign.shared.mapper.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import id.syifarahmat.librarydesign.shared.domain.constant.Status;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.ARRAY)
public class JsonArray extends BaseTypeHandler<List<? extends Serializable>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<? extends Serializable> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toString(parameter));
    }
    @Override
    public List<? extends Serializable> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toListObject(rs.getString(columnName));
    }
    @Override
    public List<? extends Serializable> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toListObject(rs.getString(columnIndex));
    }
    @Override
    public List<? extends Serializable> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toListObject(cs.getString(columnIndex));
    }
    private String toString(List<? extends Serializable> columnObject) throws SQLException {
        if (columnObject == null) return Status.EMPTY;
        try {
            return new ObjectMapper().writeValueAsString(columnObject);
        } catch (JsonProcessingException e) {
            throw new SQLException(e);
        }
    }
    private List<? extends Serializable> toListObject(String columnJson) throws SQLException {
        if (columnJson == null) return Lists.newArrayList();
        try {
            return new ObjectMapper().readValue(columnJson, new TypeReference<ArrayList<? extends Serializable>>(){});
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}
