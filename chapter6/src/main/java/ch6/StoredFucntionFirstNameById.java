package ch6;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class StoredFucntionFirstNameById extends SqlFunction<String> {
    private static final String SQL = "select getFirstNameById(?)";

    public StoredFucntionFirstNameById(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }
}
