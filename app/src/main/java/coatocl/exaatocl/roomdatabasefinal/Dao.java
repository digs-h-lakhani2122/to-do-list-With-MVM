package coatocl.exaatocl.roomdatabasefinal;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao
{

    @Query("SELECT * FROM custommodel")
    List<CustomModel> getAll();

    @Insert
    void insert(CustomModel customModel);

    @Delete
    void delete(CustomModel custommodel);

    @Update
    void update(CustomModel custommodel);
}
