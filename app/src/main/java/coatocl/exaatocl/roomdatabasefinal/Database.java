package coatocl.exaatocl.roomdatabasefinal;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {CustomModel.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract Dao dao();
}
