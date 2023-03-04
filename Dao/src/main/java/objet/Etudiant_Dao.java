package objet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

public class Etudiant_Dao extends DbManager{
    public Etudiant_Dao() {
    }

    public static void create(Etudiant e, Connection c) throws Exception {
        e.Insert(c);
    }
    public static Vector<Etudiant> selectAll(Connection c) throws SQLException {
        Vector<Object> all = Etudiant_Dao.select(Etudiant.class,c);
        Vector<Etudiant> et = new Vector<Etudiant>();
        for (int i = 0; i < all.size(); i++) {
            et.add((Etudiant) all.get(i));
        }
        return et;
    }
    public static Etudiant selectById(int id,Connection c) throws SQLException {
        Etudiant e = (Etudiant) Etudiant_Dao.selectBy(Etudiant.class,"idEtudiant",id,c);
        return e;
    }
}
