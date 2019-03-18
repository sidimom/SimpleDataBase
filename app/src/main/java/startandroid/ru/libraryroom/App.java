package startandroid.ru.libraryroom;

import android.app.Application;
import android.arch.persistence.room.Room;

/**
 * Created by Администратор on 25.02.2019.
 */

public class App extends Application {

    public static App instance;

    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDataBase.class, "database").allowMainThreadQueries().build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }
}
