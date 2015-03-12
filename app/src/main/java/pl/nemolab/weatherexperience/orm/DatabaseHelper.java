package pl.nemolab.weatherexperience.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import pl.nemolab.weatherexperience.model.ForecastEntry;
import pl.nemolab.weatherexperience.model.QueryEntry;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DB_NAME = "weather_experience.db";
    public static final int DB_VERS = 1;

    private Context context;
    private Dao<QueryEntry, Integer> queryDao;
    private Dao<ForecastEntry, Integer> forecastDao;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERS);
        try {
            queryDao = getDao(QueryEntry.class);
            forecastDao = getDao(ForecastEntry.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, QueryEntry.class);
            TableUtils.createTable(connectionSource, ForecastEntry.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, QueryEntry.class, true);
            TableUtils.dropTable(connectionSource, ForecastEntry.class, true);
            onCreate(database);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public Dao<QueryEntry, Integer> getQueryDao() {
        return queryDao;
    }

    public Dao<ForecastEntry, Integer> getForecastDao() {
        return forecastDao;
    }
}
