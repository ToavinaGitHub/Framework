package objet;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class DbManager extends Object{

    public static Vector<Object> select(Class c,Connection con) throws SQLException {
        if (c == null) return null;
        Vector<Object> databaseResults = new Vector<Object>();
        Statement stat = con.createStatement();
        String query = "SELECT * FROM "+c.getSimpleName();
        ResultSet results = stat.executeQuery(query);
        try {
            while (results.next()) {
                Field[] rowFields = c.getDeclaredFields();
                Object newObj = c.getConstructor().newInstance();

                for (int i = 0; i < rowFields.length; i++) {
                    if (Modifier.isPrivate(rowFields[i].getModifiers())) continue;

                    String capital = rowFields[i].getName().substring(0, 1).toUpperCase() + rowFields[i].getName().substring(1);
                    Method setterMethod = newObj.getClass().getDeclaredMethod("set" + capital, rowFields[i].getType());

                    if (rowFields[i].getType() == int.class) {
                        setterMethod.invoke(newObj, results.getInt(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == double.class) {
                        setterMethod.invoke(newObj, results.getDouble(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == String.class) {
                        setterMethod.invoke(newObj, results.getString(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == Date.class) {
                        setterMethod.invoke(newObj, results.getDate(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == Timestamp.class) {
                        setterMethod.invoke(newObj, results.getTimestamp(rowFields[i].getName()));
                    }
                }
                databaseResults.add(newObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseResults;
    }

    public static Vector<Object> selectWhere(Class c,String attr,Object value,Connection con) throws SQLException {
        if (c == null) return null;
        Vector<Object> databaseResults = new Vector<Object>();
        Statement stat = con.createStatement();
        String query = "SELECT * FROM "+c.getSimpleName()+" ";

        if (attr!=null)
        {
            query+="WHERE "+attr+" = '"+value+"'";
        }
        ResultSet results = stat.executeQuery(query);
        try {
            while (results.next()) {
                Field[] rowFields = c.getDeclaredFields();
                Object newObj = c.getConstructor().newInstance();

                for (int i = 0; i < rowFields.length; i++) {
                    if (Modifier.isPrivate(rowFields[i].getModifiers())) continue;

                    String capital = rowFields[i].getName().substring(0, 1).toUpperCase() + rowFields[i].getName().substring(1);
                    Method setterMethod = newObj.getClass().getDeclaredMethod("set" + capital, rowFields[i].getType());

                    if (rowFields[i].getType() == int.class) {
                        setterMethod.invoke(newObj, results.getInt(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == double.class) {
                        setterMethod.invoke(newObj, results.getDouble(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == String.class) {
                        setterMethod.invoke(newObj, results.getString(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == Date.class) {
                        setterMethod.invoke(newObj, results.getDate(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == Timestamp.class) {
                        setterMethod.invoke(newObj, results.getTimestamp(rowFields[i].getName()));
                    }
                }
                databaseResults.add(newObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseResults;
    }

    public static Object selectBy(Class c,String attr,Object value,Connection con) throws SQLException {
        if (c == null) return null;
        Vector<Object> databaseResults = new Vector<Object>();
        Statement stat = con.createStatement();
        String query = "SELECT * FROM "+c.getSimpleName()+" ";

        if (attr!=null)
        {
            query+="WHERE "+attr+" = '"+value+"'";
        }
        ResultSet results = stat.executeQuery(query);
        try {
            while (results.next()) {
                Field[] rowFields = c.getDeclaredFields();
                Object newObj = c.getConstructor().newInstance();

                for (int i = 0; i < rowFields.length; i++) {
                    if (Modifier.isPrivate(rowFields[i].getModifiers())) continue;

                    String capital = rowFields[i].getName().substring(0, 1).toUpperCase() + rowFields[i].getName().substring(1);
                    Method setterMethod = newObj.getClass().getDeclaredMethod("set" + capital, rowFields[i].getType());

                    if (rowFields[i].getType() == int.class) {
                        setterMethod.invoke(newObj, results.getInt(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == double.class) {
                        setterMethod.invoke(newObj, results.getDouble(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == String.class) {
                        setterMethod.invoke(newObj, results.getString(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == Date.class) {
                        setterMethod.invoke(newObj, results.getDate(rowFields[i].getName()));
                    } else if (rowFields[i].getType() == Timestamp.class) {
                        setterMethod.invoke(newObj, results.getTimestamp(rowFields[i].getName()));
                    }
                }
                databaseResults.add(newObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(databaseResults.size()==0)return null;
        return databaseResults.get(0);
    }
    public static boolean checkMethod(String nom,Method m)
    {
        if(nom.compareTo(m.getName())==0)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public static Method getGetter(Class c,String att)
    {
        Method res = null;
        for(int i=0;i<c.getDeclaredMethods().length;i++)
        {
            if(checkMethod("get"+att, c.getDeclaredMethods()[i]))
            {
                res = c.getDeclaredMethods()[i];
            }
        }
        return res;
    }
    public static String[] getFields(Object obj)
    {
        String[] res = new String[obj.getClass().getDeclaredFields().length];
        for(int i=0;i<res.length;i++)
        {
            res[i] = obj.getClass().getDeclaredFields()[i].getName();
        }
        return res;
    }
    //get carac of object
    public static Object[] getCarac(Object obj) throws Exception
    {
        String[] tabField =getFields(obj);
        Object[] tabObject = new Object[obj.getClass().getDeclaredFields().length];
        Method[] tabMethod = obj.getClass().getDeclaredMethods();
        for(int i=0;i<tabField.length;i++)
        {
            for(int j=0;j<tabMethod.length;j++)
            {

                try {
                    String capital = tabField[i].substring(0, 1).toUpperCase() + tabField[i].substring(1);
                    tabObject[i]=getGetter(obj.getClass(), capital).invoke(obj, null);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("impossible"+tabField[i]);
                    throw new Exception("Impossible d'appeler la method");
                }
            }
        }
        return tabObject;
    }
    ///insert
    public static String getInsertRequest(Object obj,Connection c) throws Exception
    {
        Object[] tabObj =getCarac(obj);

        String table = obj.getClass().getSimpleName().toUpperCase();

        String res ="";
            res = "INSERT INTO "+table+" VALUES (";
            for(int i=0;i<tabObj.length;i++) {
                if (i != tabObj.length - 1) {
                    if (tabObj[i] instanceof Integer || tabObj[i] instanceof Double) {
                        res += tabObj[i] + ",";
                    } else if (tabObj[i] instanceof String) {
                        res += "'" + tabObj[i] + "',";
                    } else if (tabObj[i] == Timestamp.class) {
                        res += "'" + tabObj[i] + "',";
                    }
                } else {
                    if (tabObj[i] instanceof Integer || tabObj[i] instanceof Double) {
                        res += tabObj[i] + ")";
                    } else if (tabObj[i] instanceof String) {
                        res += "'" + tabObj[i] + "')";
                    } else if (tabObj[i] == Timestamp.class) {
                        res += "'" + tabObj[i] + "')";
                    }
                }
            }
        return res;
    }
    //fonction inserer generalis�
    public void Insert(Connection con) throws Exception
    {
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        String className = this.getClass().getSimpleName();
        String request = getInsertRequest(this,con);
        try {
            stmt.executeUpdate(request);
            con.commit();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            con.rollback();
        }finally {

        }
    }
    public static void insert(Class c,Vector<Object> values,Connection con) throws SQLException {
        String query = "INSERT INTO " + c.getSimpleName()+ " VALUES (";
        for (int i = 0; i < values.size(); i++) {
            query +="'"+values.get(i)+"'";
            if (i < values.size() - 1) {
                query += ",";
            }
        }
        query += ")";
        Statement stmt = con.createStatement();
        stmt.executeQuery(query);
    }
    ///////////////////////////////////----DELETE------////////////////////////////////
    public static String getDeleteRequest(Object obj,Connection c) throws Exception
    {
        Object[] tabObj = getCarac(obj);
        String[] tabField = getFields(obj);
        String table = obj.getClass().getSimpleName().toUpperCase();
        String res = "";
        res = "DELETE FROM "+table+" WHERE ";
        for(int i=0;i<tabObj.length;i++)
        {
            if(i!=tabObj.length-1)
            {
                if(tabObj[i] instanceof Integer || tabObj[i] instanceof Double)
                {
                    res+=tabField[i]+"="+tabObj[i]+" and ";
                }
                if(tabObj[i] instanceof String)
                {
                    res+=tabField[i]+"='"+tabObj[i]+"' and ";
                }
            }
            else {
                if(tabObj[i] instanceof Integer || tabObj[i] instanceof Double)
                {
                    res+=tabField[i]+"="+tabObj[i]+"";
                }
                if(tabObj[i] instanceof String)
                {
                    res+=tabField[i]+"='"+tabObj[i]+"'";
                }
            }

        }
        return res;
    }
    //fonction delete generalise
    public void Delete(Connection con) throws Exception
    {
            con.setAutoCommit(false);
            String className = this.getClass().getSimpleName();
            String request = getDeleteRequest(this,con);
            Statement stmt = con.createStatement();
            try {
                //this.historiser("delete", con);
                System.out.println(request);
                stmt.executeUpdate(request);

                con.commit();
            }catch (Exception e) {
                // TODO: handle exception
                con.rollback();
            }finally {
                //con.close();
            }
    }
    public static void delete(Class c,String colonne, Object value,Connection con) throws SQLException {
        String query = "DELETE FROM " + c.getSimpleName()+ " WHERE " + colonne+ " = '"+value+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
    }
    public static boolean CheckAttributes(Class c,String att)
    {
        boolean res = false;
        for(int i=0;i<c.getDeclaredFields().length;i++)
        {
            if(c.getDeclaredFields()[i].getName().compareTo(att)==0)
            {
                res = true;
                break;
            }
        }
        return res;
    }
    public static Class getReturnValue(Class c,String att)
    {
        Class ret = null;
        if(CheckAttributes(c, att))
        {
            for(int i=0;i<c.getDeclaredFields().length;i++)
            {
                if(c.getDeclaredFields()[i].getName().compareTo(att)==0)
                {
                    ret = c.getDeclaredFields()[i].getType();
                }
            }
        }
        return ret;

    }
    //////////////////////////////////:----UPDATE-------////////////////////////////////
    public static String getUpdateRequest(Object obj,String att,Object val,Connection c) throws Exception
    {
        String res ="";
        if(CheckAttributes(obj.getClass(), att))
        {
            if(getReturnValue(obj.getClass(), att)==val.getClass())
            {
                String table = obj.getClass().getSimpleName().toUpperCase();
                Object[] tabObj = getCarac(obj); //les donnees
                String[] tabField =getFields(obj);

                res = "UPDATE "+table+" SET "+att+"='"+val+"' WHERE ";
                for(int i=0;i<tabObj.length;i++)
                {
                    if(i!=tabObj.length-1)
                    {
                        if(tabObj[i] instanceof Integer || tabObj[i] instanceof Double)
                        {
                            res+=tabField[i]+"="+tabObj[i]+" and ";
                        }
                        if(tabObj[i] instanceof String)
                        {
                            res+=tabField[i]+"='"+tabObj[i]+"' and ";
                        }
                    }
                    else {
                        if(tabObj[i] instanceof Integer || tabObj[i] instanceof Double)
                        {
                            res+=tabField[i]+"="+tabObj[i]+"";
                        }
                        if(tabObj[i] instanceof String)
                        {
                            res+=tabField[i]+"='"+tabObj[i]+"'";
                        }
                    }

                }
            }
            else {
                throw new Exception("Valeur de retour non dispo");
            }
        }else {
            throw new Exception("Attribut non dispo");
        }
        return res;
    }
    ///update generalise
    public void update(String att,String valeur,Connection c) throws SQLException, Exception
    {

        try {
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            String requete = getUpdateRequest(this, att, valeur, c);
            System.out.println(requete);
            stmt.executeUpdate(requete);
            //this.historiser("update", c);
            c.commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                c.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
            }
        }finally {

        }

    }
    public static void update(Class c, String colonne, Object valueCol, Vector<String> colonneEdit, Vector<Object> valuesEdit,Connection con) throws SQLException {
        String query = "UPDATE " + c.getSimpleName() + " SET ";
        for (int i = 0; i < colonneEdit.size(); i++) {
            query += colonneEdit.get(i)+ "='"+valuesEdit.get(i)+"'";
            if (i < colonneEdit.size() - 1) {
                query += ",";
            }
        }
        query += " WHERE " + colonne + "='"+valueCol+"'";
        System.out.printf(query);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
    }

    public static Vector<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        Vector<Class> res = new Vector<Class>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        Class[] cls =  classes.toArray(new Class[classes.size()]);
        for (int i = 0; i < cls.length; i++) {
            res.add(cls[i]);
        }
        return res;
    }
    public static Vector<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        Vector<Class> classes = new Vector<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
    public static Vector<Class> getClassByAnnotationByPackage(String packageName, Class an) throws IOException, ClassNotFoundException {
        Vector<Class> pack = getClasses(packageName);
        Vector<Class> tabClass = new Vector<Class>();
        for (int i = 0; i < pack.size(); i++) {
            if(pack.get(i).getAnnotation(an)!=null)
            {
                tabClass.add(pack.get(i));
            }
        }
        return tabClass;
    }


}
