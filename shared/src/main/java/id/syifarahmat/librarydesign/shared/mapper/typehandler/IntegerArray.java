package id.syifarahmat.librarydesign.shared.mapper.typehandler;

import com.google.common.collect.Lists;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.ARRAY)
public class IntegerArray extends BaseTypeHandler<List<Integer>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
        Array array = ps.getConnection().createArrayOf("integer", parameter.toArray());
        ps.setArray(i, array);
    }
    @Override
    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toList(rs.getArray(columnName));
    }
    @Override
    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toList(rs.getArray(columnIndex));
    }
    @Override
    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toList(cs.getArray(columnIndex));
    }
    private List<Integer> toList(Array columnArray) throws SQLException {
        if (columnArray == null) return Lists.newArrayList();
        Integer[] valueArray = (Integer[]) columnArray.getArray();
        return containsOnlyNulls(valueArray) ? Lists.<Integer>newArrayList() : Lists.newArrayList(valueArray);
    }
    private boolean containsOnlyNulls(Integer[] valueArray) {
        for(Integer value : valueArray) {
            if (value != null) {
                return false;
            }
        }
        return true;
    }
}
