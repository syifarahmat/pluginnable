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
public class CharArray extends BaseTypeHandler<List<Character>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Character> parameter, JdbcType jdbcType) throws SQLException {
        Array array = ps.getConnection().createArrayOf("char", parameter.toArray());
        ps.setArray(i, array);
    }
    @Override
    public List<Character> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toList(rs.getArray(columnName));
    }
    @Override
    public List<Character> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toList(rs.getArray(columnIndex));
    }
    @Override
    public List<Character> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toList(cs.getArray(columnIndex));
    }
    private List<Character> toList(Array columnArray) throws SQLException {
        if (columnArray == null) return Lists.newArrayList();
        Character[] valueArray = (Character[]) columnArray.getArray();
        return containsOnlyNulls(valueArray) ? Lists.<Character>newArrayList() : Lists.newArrayList(valueArray);
    }
    private boolean containsOnlyNulls(Character[] valueArray) {
        for(Character value : valueArray) {
            if (value != null) {
                return false;
            }
        }
        return true;
    }
}
