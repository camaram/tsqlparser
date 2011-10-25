package com.relationalcloud.tsqlparser;


import com.relationalcloud.tsqlparser.Parser;
import com.relationalcloud.tsqlparser.loader.ForeignKey;
import com.relationalcloud.tsqlparser.loader.Schema;
import com.relationalcloud.tsqlparser.loader.SchemaTable;
import com.relationalcloud.tsqlparser.statement.insert.Insert;
import com.relationalcloud.tsqlparser.statement.select.PlainSelect;

public class SimpleTestParser {

  /**
   * @param args
   */
  public static void main(String[] args) {

    try {
      
      //String sql = " INSERT INTO tab VALUES(1,1.27106114500000000e+09,'stringas')";
      //String sql = "CREATE TABLE A (a1 int, a2 varchar(255));";
      //String sql = "INSERT INTO `revision` (rev_id,rev_page,rev_text_id,rev_comment,rev_minor_edit,rev_user,rev_user_text,rev_timestamp,rev_deleted,rev_len,rev_parent_id) VALUES (NULL,'2483524',182358929,'','0','0',\"10.1.26.116\",\"1110494025\",'0','20278','182356676')";
    	String sql = "INSERT INTO `tab` VALUES (1,2,3,4);";
    	
      Schema schema = new Schema(null,"tpcc",null,null,null,null);
      SchemaTable t = new SchemaTable();
      t.setTableName("tab");
      t.addColumn("a");
      t.addColumn("b");
      t.addColumn("c");
      t.addColumn("d");
      schema.addTable(t);
    
      SchemaTable t2 = new SchemaTable();
      t2.setTableName("tob");
      t2.addColumn("a");
      t2.addColumn("b");
      t2.addColumn("f");
      schema.addTable(t2);
    
      
      Parser p = new Parser("tpcc",schema, sql);
      // LIST TABLES INVOLVED IN THE QUERY
      System.out.println("TABLES: " + p.getTableStringList());
      System.out.println("COUNT  QUERY: " + p.getCountEquivalent());

  
           
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
