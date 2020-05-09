package id.syifarahmat.librarydesign.shared.mapper.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.syifarahmat.librarydesign.shared.domain.constant.Status;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Object.class)
@MappedJdbcTypes(JdbcType.OTHER)
public class Json<Any> extends BaseTypeHandler<Any> {
    /* *
    * helper
    * to get object from this
    * new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).convertValue([thisresult].get[Properties](), [Object].class).get[Properties]()
    * */
    private Class<Any> clazz;
    public Json(Class<Any> clazz) {
        this.clazz = clazz;
    }
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Any parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, toString(parameter));
    }
    @Override
    public Any getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toObject(rs.getString(columnName));
    }
    @Override
    public Any getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toObject(rs.getString(columnIndex));
    }
    @Override
    public Any getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toObject(cs.getString(columnIndex));
    }
    private String toString(Any columnObject) throws SQLException {
        if (columnObject == null) return Status.EMPTY;
        try {
            return new ObjectMapper().writeValueAsString(columnObject);
        } catch (JsonProcessingException e) {
            throw new SQLException(e);
        }
    }
    private Any toObject(String columnJson) throws SQLException {
        try {
            return new ObjectMapper().readValue(columnJson, clazz);
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}
