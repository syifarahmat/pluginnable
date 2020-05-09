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
public class VarcharArray extends BaseTypeHandler<List<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        Array array = ps.getConnection().createArrayOf("varchar", parameter.toArray());
        ps.setArray(i, array);
    }
    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toList(rs.getArray(columnName));
    }
    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toList(rs.getArray(columnIndex));
    }
    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toList(cs.getArray(columnIndex));
    }
    private List<String> toList(Array columnArray) throws SQLException {
        if (columnArray == null) return Lists.newArrayList();
        String[] valueArray = (String[]) columnArray.getArray();
        return containsOnlyNulls(valueArray) ? Lists.<String>newArrayList() : Lists.newArrayList(valueArray);
    }
    private boolean containsOnlyNulls(String[] valueArray) {
        for(String value : valueArray) {
            if (value != null) {
                return false;
            }
        }
        return true;
    }
}
