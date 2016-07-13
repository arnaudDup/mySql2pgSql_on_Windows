package Transform.MY;

import java.sql.Connection;
import java.sql.Statement;

public class CSVLoader {
	
	private String SQL_COPY = "COPY ${schema}.${table} FROM '${filename}' CSV ENCODING '${encoding_name}' DELIMITER '${separator}' QUOTE '${quote}';";	
	private static final String TABLE_REGEX = "${table}";
	private static final String Filename_REGEX = "${filename}";
	private static final String ENCODING_NAME = "${encoding_name}";
	private static final String SEPARATOR = "${separator}";
	private static final String SCHEMA = "${schema}";
	private static final String QUOTE = "${quote}";

	private Connection connection;

	public CSVLoader (Connection aConnection,String aSchema ,String anEncoding_Name, String aSeparator, String aQuote) {
		this.connection = aConnection;
		// build the query.
		SQL_COPY = SQL_COPY.replace(ENCODING_NAME, anEncoding_Name); 
		SQL_COPY = SQL_COPY.replace(SEPARATOR, aSeparator);
		SQL_COPY = SQL_COPY.replace(SCHEMA, aSchema);
		SQL_COPY = SQL_COPY.replace(QUOTE, aQuote);
	}
	
	
	public boolean loadCSV(String csvFile, String tableName) throws Exception {
		boolean isCorrect = true; 
		String query = SQL_COPY;
		
		// build the specific request.
		query = query.replace(TABLE_REGEX, tableName); 
		query = query.replace(Filename_REGEX, csvFile); 

		Statement stat =  connection.createStatement();
		System.out.println(query);
		stat.execute(query);
		return isCorrect; 
	}
}
	