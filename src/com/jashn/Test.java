package com.jashn;

import java.sql.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;

@Path("/test")
public class Test {
	@GET
	@Produces("application/text")
	public String convertCtoF() {

		String message = null;
		try {
			JSONObject job = new JSONObject();
		//	job.append("Test", "Rohit test");

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://mysql4.gear.host:3306/jashndb", "jashndb", "Hq7Xf4!qe!H1");
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM country;");
			while (rs.next())
				
				message = (rs.getInt(1) + "  " + rs.getString(2) + "  " + rs
						.getString(3));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			message = e.getMessage();
		}

		return message;
	}

	@Path("{c}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("c") Double c) {
		Double fahrenheit;
		Double celsius = c;
		fahrenheit = ((celsius * 9) / 5) + 32;

		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n"
				+ fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>"
				+ "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
}