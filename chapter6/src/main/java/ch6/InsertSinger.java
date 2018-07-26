package ch6;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;

import static java.sql.Types.DATE;
import static java.sql.Types.VARCHAR;

public class InsertSinger extends SqlUpdate {
    private static final String SQL_INSERT_SINGER =
            "insert into singer (first_name, last_name, birth_date)" +
                    "values (:first_name, :last_name, :birth_date)";

    public InsertSinger(DataSource ds) {
        super(ds, SQL_INSERT_SINGER);
        declareParameter(new SqlParameter("first_name", VARCHAR));
        declareParameter(new SqlParameter("last_name", VARCHAR));
        declareParameter(new SqlParameter("birth_date", DATE));
        setGeneratedKeysColumnNames("id");
        setReturnGeneratedKeys(true);
    }
}
