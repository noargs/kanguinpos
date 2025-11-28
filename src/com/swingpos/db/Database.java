package com.swingpos.db;

import com.swingpos.config.AppProperties;
import com.swingpos.model.Category;

import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Database {
	
   private List<Category> categories;

   private static Connection con = null;

   private String db = AppProperties.getDatabaseName();
   private String user = AppProperties.getDatabaseUser();
   private String pass = AppProperties.getDatabasePassword();
   private String dir = AppProperties.getDatabaseDir();

   private static Database instance = null;

    public Database()
    {
       // categories = new LinkedList<Controller>();

    }

    public void configure(String db, String user, String pass)throws Exception
    {
        this.db = db;
        this.user = user;
        this.pass = pass;

        if(con != null)
        {
            disconnect();
            connect();
        }
    }
    
    public void connect()
    {
        if(con != null) return;

        try{
            //con = DriverManager.getConnection("jdbc:h2:" + "./driver/" + db, user, pass);
            String url = String.format("jdbc:h2:%s%s", dir, db);
           con = DriverManager.getConnection(url, user, pass);
        }catch(Exception e)
        {
            System.out.println(e);
             try {
//                Log log = new Log(AppProperties.BASIC_LOG_FILE);
//                log.logError(e.toString());
            }catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
    }

    public Connection getCon()
    {
        return con;
    }

    public void disconnect()
    {
        if(con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
        con = null;
    }

    public static synchronized Database getInstance()
    {
        if(instance != null) return instance;

        return instance = new Database();
    }

//    public void addCategory(Controller category)
//    {
//        categories.add(category);
//    }
//
//    public void removeCategory(int index)
//    {
//        categories.remove(index);
//    }
//
//    public List<Controller> getCategories()
//    {
//        return Collections.unmodifiableList(categories);
//    }
//
//    public void load() throws Exception
//    {
//        categories.clear();
//
//        String sql = "select id, name, image, r, g, b, position from category order by position";
//        Statement selectStatement = con.createStatement();
//
//        ResultSet results = selectStatement.executeQuery(sql);
//
//        while (results.next())
//        {
//            int id = results.getInt("id");
//            String name = results.getString("name");
//            String image = results.getString("image");
//            int r = results.getInt("r");
//            int g = results.getInt("g");
//            int b = results.getInt("b");
//            int position = results.getInt("position");
//
//            Controller category = new Controller(id, name, image, r, g, b, position);
//            categories.add(category);
//        }
//        results.close();
//        selectStatement.close();
//    }
//
//    public void save() throws SQLException {
//
//        String selectSql = "select count(*) as count from category where id=?";
//        PreparedStatement selectStmt = con.prepareStatement(selectSql);
//
//        String insertSql = "insert into category (id, name, image, r, g, b, position) values (?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement insertStatement = con.prepareStatement(insertSql);
//
////         String updateSql = "update category set name=?, image=?, r=?, g=?, b=?, position=?, where id=?";
////         PreparedStatement updateStatement = con.prepareStatement(updateSql);
//
//        for (Controller category : categories) {
//            int id = category.getId();
//            String name = category.getName();
//            String image = category.getImage();
//            int r = category.getR();
//            int g = category.getG();
//            int b = category.getB();
//            int position = category.getPosition();
//
//            selectStmt.setInt(1, id);
//
//            ResultSet selectResult = selectStmt.executeQuery();
//            selectResult.next();
//
//            int count = selectResult.getInt(1);
//
//            if (count == 0) {
//                int col = 1;
//                insertStatement.setInt(col++, id);
//                insertStatement.setString(col++, name);
//                insertStatement.setString(col++, image);
//                insertStatement.setInt(col++, r);
//                insertStatement.setInt(col++, g);
//                insertStatement.setInt(col++, b);
//                insertStatement.setInt(col++, position);
//
//                insertStatement.executeUpdate();
//            }
////             else {
////                int col = 1;
////                updateStatement.setString(col++, name);
////                updateStatement.setString(col++, image);
////                updateStatement.setInt(col++, r);
////                updateStatement.setInt(col++, g);
////                updateStatement.setInt(col++, b);
////                updateStatement.setInt(col++, position);
////                updateStatement.setInt(col++, id);
////
////                updateStatement.executeUpdate();
////            }
//        }
//
////        updateStatement.close();
//        insertStatement.close();
//        selectStmt.close();
//    }
//
//    public boolean tableExist(String table) throws SQLException
//    {
//        boolean tExists = false;
//
//        try(ResultSet rs = con.getMetaData().getTables(null, null, table, null))
//        {
//            while(rs.next())
//            {
//                String tName = rs.getString("TABLE_NAME");
//                if(tName != null && tName.equals(table))
//                {
//                    tExists = true;
//                    break;
//                }
//            }
//        }
//        return tExists;
//    }
//
//
//    public void loadFromFile(File file) throws IOException
//    {
//        FileInputStream fis = new FileInputStream(file);
//        ObjectInputStream ois = new ObjectInputStream(fis);
//
//        try{
//            Controller[] category = (Controller[]) ois.readObject();
//
//            categories.clear();
//
//            categories.addAll(Arrays.asList(category));
//
//        }catch(ClassNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        ois.close();
//    }
//
//    public void saveToFile(File file) throws IOException
//    {
//        FileOutputStream fos = new FileOutputStream(file);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//        Controller[] category = categories.toArray(new Controller[categories.size()]);
//
//        oos.writeObject(category);
//
//        oos.close();
//    }
//
//
//
//
////    public void showAllCatgeory()
////    {
////        //TextPanel textPanel = new TextPanel();
////        try{
////            String query = "select * from category";
////            Statement st = con.createStatement();
////            ResultSet rs = st.executeQuery(query);
////
////            while (rs.next()){
////                System.out.println (rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
////            }
////        }catch(Exception e)
////        {
////            System.out.println(e);
////        }
////    }
//
//    public void showAllCatgeory()
//    {
//        //TextPanel textPanel = new TextPanel();
//        try{
//            String query = "select * from category";
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(query);
//
//            while (rs.next()){
//                System.out.println (rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getInt(5) + " " + rs.getInt(6) + " " + rs.getInt(7));
//            }
//        }catch(Exception e)
//        {
//            System.out.println(e);
//        }
//    }
//
//    public void addCategoryTable()
//    {
//        try{
//            Statement stmt = con.createStatement();
//            String query = "CREATE TABLE IF NOT EXISTS category "
//                    + "(id INTEGER auto_increment NOT NULL, "
//                    + " name VARCHAR(255), "
//                    + " image VARCHAR(255), "
//                    + " r INTEGER, "
//                    + " g INTEGER, "
//                    + " b INTEGER, "
//                    + " position INTEGER, "
//                    + " PRIMARY KEY ( id ))";
//            stmt.executeUpdate(query);
//            System.out.println("Table created");
//
//        }
//        catch(Exception e)
//        {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    public void populateCategoryTable()
//    {
//        try {
//            Statement stmt = con.createStatement();
//            String sql = "insert into category(name, image, r, g, b, position) values "
//            		+ " ('Kebab', '', 255, 234, 167, 1),  "
//                    + " ('Wrap', '', 255, 204, 204, 2), "
//                    + " ('Chips', '', 255, 153, 153, 3), "
//                    + " ('Burger', '', 255, 153, 51, 4), "
//                    + " ('Pizza', '', 255, 153, 255, 5), "
//                    + " ('Curry', '', 252, 92, 101, 6), "
//                    + " ('Biryani Dishes', '', 85, 239, 196, 7), "
//                    + " ('Southern Fried', '', 153, 153, 255, 8), "
//                    + " ('Naanbread', '', 0, 204, 51, 9), "
//                    + " ('Sundries', '', 43, 203, 186, 10), "
//                    + " ('Starter', '', 255, 102, 51, 11), "
//                    + " ('Dips', '', 205, 220, 57, 12), "
//                    + " ('Drinks', '', 255, 193, 7, 13) ";
//
//            int i  = stmt.executeUpdate(sql);
//            System.out.println(i + " record[s] created");
//        }
//        catch (Exception e)
//        {
//            System.err.println(e.getMessage());
//        }
//    }
}

