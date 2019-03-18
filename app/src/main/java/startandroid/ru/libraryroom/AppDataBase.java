package startandroid.ru.libraryroom;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Employee.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();
}
