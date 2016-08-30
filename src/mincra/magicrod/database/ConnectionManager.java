package mincra.magicrod.database;

import mincra.magicrod.main.Magic;
import mincra.magicrod.util.Util;

import java.sql.*;
import java.util.Properties;

public class ConnectionManager {
	private static String url;
	private final static Properties properties = new Properties();
	private String database,hostname,port,username,password;
	//以下テーブルの情報取得
	private String createuser =  "CREATE TABLE IF NOT EXISTS user ("
			+ "id int(11) unsigned NOT NULL AUTO_INCREMENT,"
		    + "name varchar(64) DEFAULT NULL,"
		    + "uuid char(36) DEFAULT NULL,"
		    + "PRIMARY KEY (id),"
		    + "KEY uuid (uuid)"
		    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
	private static String createjob =  "CREATE TABLE IF NOT EXISTS job ("
		    + "id int(2) unsigned NOT NULL AUTO_INCREMENT,"
		    + "name varchar(64) DEFAULT NULL,"
		    + "PRIMARY KEY (id)"
		    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
	private static String creatematerial =  "CREATE TABLE IF NOT EXISTS material (id int(2) unsigned NOT NULL AUTO_INCREMENT,name varchar(64) NOT NULL DEFAULT '',"
											+"PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
	private static String createmagic_history =  "CREATE TABLE IF NOT EXISTS magic_history ("
			+"user_id int(11) unsigned NOT NULL,"
			+"material_id int(2) unsigned NOT NULL,"
			+"PRIMARY KEY (user_id,material_id),"
			+"KEY material_id (material_id),"
			+"CONSTRAINT magic_history_ibfk_2 FOREIGN KEY (material_id) REFERENCES material (id) ON DELETE CASCADE ON UPDATE CASCADE,"
			+"CONSTRAINT magic_history_ibfk_1 FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE"
			+") ENGINE=InnoDB DEFAULT CHARSET=utf8";
	private static String createmagic_info =  "CREATE TABLE IF NOT EXISTS magic_info ("
		    + "id int(11) unsigned NOT NULL,"
		    + "skill text,"
		    + "magicchest text,"
		    + "giftbox text,"
		    + "job int(2) unsigned,"
		    + "notice tinyint(1) NOT NULL DEFAULT '1',"
		    + "enable tinyint(1) NOT NULL DEFAULT '1',"
		    + "PRIMARY KEY (id),"
		    + "CONSTRAINT magic_info_ibfk_1 FOREIGN KEY (id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,"
		    + "CONSTRAINT magic_info_ibfk_2 FOREIGN KEY (job) REFERENCES job (id) ON DELETE SET NULL ON UPDATE CASCADE"
		    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
	private static String insertjob = "INSERT INTO job (id, name) VALUES (1, '戦士'), (2, 'プリースト'), (3, '魔術師'), (4, 'アーチャー')";
	private static String insertmaterial = "INSERT INTO material (id, name) VALUES"
											+"(1, 'ブリザード'),(2, 'ファイア'),"
											+"(3, '挑発Lv1'),(4, 'サンダー'),"
											+"(5, 'キュア'),(6, '緑水晶'),"
											+"(7, '青水晶'),(8, 'プロテクト'),"
											+"(9, 'バースト'),(10, 'リザレクション'),"
											+"(11, 'ブースト'),(12, 'キュアル'),"
											+"(13, '挑発Lv2'),(14, '蒼水晶'),"
											+"(15, '翠水晶'),(16, 'ホーリー'),"
											+"(17, 'ホーリラ'),(18, 'グラビティ'),"
											+"(19, 'アンチグラビティ'),(20, 'むらびと'),"
											+"(21, 'マジックアロー'),(22, 'ディレクション')"
											+"(23, 'ディバイド'),(24, 'チャージ')";


		public ConnectionManager(){
		database = Magic.config.getString("config.mysql.database");
		hostname = Magic.config.getString("config.mysql.hostname");
		port = Magic.config.getString("config.mysql.port");
		username = Magic.config.getString("config.mysql.username");
		password = Magic.config.getString("config.mysql.password");
		url = "jdbc:mysql://"+hostname+":"+port+"/"+database+"?user="+username+"&password="+password;
		properties.put("characterEncoding", "utf-8");
		checkTable();
	}

	private void checkTable(){
		Connection conn = null;
		Statement stats = null;
		Util.debug("データベースに接続します.");
		try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			stats.executeUpdate(createuser);
			stats.executeUpdate(createjob);
			ResultSet rs = stats.executeQuery("SELECT * FROM job");
			if(!rs.next()){
				stats.executeUpdate(insertjob);
			}
			stats.executeUpdate(creatematerial);
			ResultSet rs2 = stats.executeQuery("SELECT * FROM material");
			if(!rs2.next()){
				stats.executeUpdate(insertmaterial);
			}
			stats.executeUpdate(createmagic_info);
			stats.executeUpdate(createmagic_history);
		} catch (SQLException e) {
			Util.debug("データベースの接続に失敗しました...");
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection conn;

		conn = DriverManager.getConnection(url, properties);
		if (!conn.isValid(1)) {
			conn.close();
			throw new SQLException("新しい接続を確立できませんでした.");
		}
		return conn;
	}
}
