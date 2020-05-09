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
public class TimestampArray extends BaseTypeHandler<List<Timestamp>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Timestamp> parameter, JdbcType jdbcType) throws SQLException {
        Array array = ps.getConnection().createArrayOf("timestamp", parameter.toArray());
        ps.setArray(i, array);
    }
    @Override
    public List<Timestamp> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toList(rs.getArray(columnName));
    }
    @Override
    public List<Timestamp> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toList(rs.getArray(columnIndex));
    }
    @Override
    public List<Timestamp> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toList(cs.getArray(columnIndex));
    }
    private List<Timestamp> toList(Array columnArray) throws SQLException {
        if (columnArray == null) return Lists.newArrayList();
        Timestamp[] valueArray = (Timestamp[]) columnArray.getArray();
        return containsOnlyNulls(valueArray) ? Lists.<Timestamp>newArrayList() : Lists.newArrayList(valueArray);
    }
    private boolean containsOnlyNulls(Timestamp[] valueArray) {
        for(Timestamp s : valueArray) {
            if (s != null) {
                return false;
            }
        }
        return true;
    }
}
