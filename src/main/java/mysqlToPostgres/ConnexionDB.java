package mysqlToPostgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;


public class ConnexionDB implements Constant {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(ConnexionDB.class);
	
	public ConnexionDB(){}
	
	public static Connection ConnexionMySQL(Properties prop) {
		Connection connecMysql = null;
		 try {

				logger.info("ConnexionMySQL(), Connexion to Mysql Database");
	           
				Class.forName(prop.getProperty("mysqlDriver"));
	
	            connecMysql=DriverManager.getConnection(REGEX_URL_MYSQL.replace(REGEX_HOST, prop.getProperty("mySqlHost"))
	         		   													.replace(REGEX_PORT, prop.getProperty("mySqlPort"))
	         		   													.replace(REGEX_DATABASE,prop.getProperty("MysqlDatabase")),
	         		   													prop.getProperty("mySqlUser"),
	         		   													prop.getProperty("mySqlPassword"));
	            logger.info("Success");
			} catch (Exception e) {
				// the connexion fail but we continue, if the user doesn't need it
				logger.warn("ConnexionMySQL() KO: Impossible de se connecter à la base Mysql, les informations sont eronées ");
			}
		 	return connecMysql;
	}
	
	public static Connection  ConnexionPostgres (Properties prop) throws ClassNotFoundException, SQLException{

		// Connnect to the postgres database.
		Connection connecPostgres = null;
		try {
		
			logger.info("ConnexionPostgres(), Connexion to postgres Database");
	    	Class.forName(prop.getProperty("postgresDriver"));
			connecPostgres = DriverManager.getConnection(REGEX_URL_PGSQL.replace(REGEX_HOST, prop.getProperty("postgresHost"))
					   													.replace(REGEX_PORT, prop.getProperty("postgresPort"))
					   													.replace(REGEX_DATABASE,prop.getProperty("postgresDatabase")),
					   													prop.getProperty("postgresUser"),
																		prop.getProperty("postgresPassword"));
			logger.info("Success");
		} catch (Exception e) {
			// the connexion fail but we continue, if the user doesn't need it
			logger.warn("ConnexionMySQL() KO: Impossible de se connecter à la base Mysql, les informations sont eronées ");
		}
		return connecPostgres;
	}
}
