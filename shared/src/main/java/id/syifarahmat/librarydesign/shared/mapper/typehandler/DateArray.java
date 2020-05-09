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
public class DateArray extends BaseTypeHandler<List<Date>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Date> parameter, JdbcType jdbcType) throws SQLException {
        Array array = ps.getConnection().createArrayOf("date", parameter.toArray());
        ps.setArray(i, array);
    }
    @Override
    public List<Date> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toList(rs.getArray(columnName));
    }
    @Override
    public List<Date> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toList(rs.getArray(columnIndex));
    }
    @Override
    public List<Date> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toList(cs.getArray(columnIndex));
    }
    private List<Date> toList(Array columnArray) throws SQLException {
        if (columnArray == null) return Lists.newArrayList();
        Date[] valueArray = (Date[]) columnArray.getArray();
        return containsOnlyNulls(valueArray) ? Lists.newArrayList() : Lists.newArrayList(valueArray);
    }
    private boolean containsOnlyNulls(Date[] valueArray) {
        for(Date s : valueArray) {
            if (s != null) {
                return false;
            }
        }
        return true;
    }
}
