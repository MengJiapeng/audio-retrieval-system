package mjp.mp3searchengine.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 权限{@link SimpleGrantedAuthority}的类型处理器
 *
 * @author SoWhat
 * @date 2018/07/03
 */
public class SimpleGrantedAuthorityTypeHandler extends BaseTypeHandler<SimpleGrantedAuthority> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SimpleGrantedAuthority parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    // --- no usage below

    @Override
    public SimpleGrantedAuthority getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        if (s == null || s.isEmpty()) {
            return null;
        }
        return new SimpleGrantedAuthority(s);
    }

    @Override
    public SimpleGrantedAuthority getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        if (s == null || s.isEmpty()) {
            return null;
        }
        return new SimpleGrantedAuthority(s);
    }

    @Override
    public SimpleGrantedAuthority getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        if (s == null || s.isEmpty()) {
            return null;
        }
        return new SimpleGrantedAuthority(s);
    }
}
