package Java.Super;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepositoryBasics {

    //Model getById(int id) throws SQLException;
    ArrayList<Model> getAll() throws SQLException;
    //void add(Model item) throws SQLException;
    //void delete(int id) throws SQLException;

}
