package id.syifarahmat.librarydesign.shared.mapper.typehandler;

import com.google.common.collect.Lists;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.ARRAY)
public class NumericArray extends BaseTypeHandler<List<BigDecimal>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<BigDecimal> parameter, JdbcType jdbcType) throws SQLException {
        Array array = ps.getConnection().createArrayOf("numeric", parameter.toArray());
        ps.setArray(i, array);
    }
    @Override
    public List<BigDecimal> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toList(rs.getArray(columnName));
    }
    @Override
    public List<BigDecimal> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toList(rs.getArray(columnIndex));
    }
    @Override
    public List<BigDecimal> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toList(cs.getArray(columnIndex));
    }
    private List<BigDecimal> toList(Array columnArray) throws SQLException {
        if (columnArray == null) return Lists.newArrayList();
        BigDecimal[] valueArray = (BigDecimal[]) columnArray.getArray();
        return containsOnlyNulls(valueArray) ? Lists.<BigDecimal>newArrayList() : Lists.newArrayList(valueArray);
    }
    private boolean containsOnlyNulls(BigDecimal[] valueArray) {
        for(BigDecimal value : valueArray) {
            if (value != null) {
                return false;
            }
        }
        return true;
    }
}
