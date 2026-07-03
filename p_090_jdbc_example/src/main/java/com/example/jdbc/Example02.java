package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Example02 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:8889/corso_java";
		String user = "scott";
		String password = "tiger";
		String query = """
					select
						ri.cod_rip,
						ri.den_rip,
						re.cod_reg,
						re.den_reg,
						pr.cod_uts,
						pr.den_uts
					from
						ripartizioni ri
					inner join regioni re
					on
						re.cod_rip = ri.cod_rip
					inner join province pr
					on
						pr.cod_reg = re.cod_reg
				""";

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			
			System.out.println("Connessione effettuata");

			PreparedStatement st = connection.prepareStatement(query);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				System.out.print("rip=" + rs.getString(2));
				System.out.print("reg=" + rs.getString("den_reg"));
				System.out.print("pro=" + rs.getInt("cod_uts"));
				System.out.println("/" + rs.getString("den_uts"));
			}
			rs.close();
			st.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Sono nel blocco catch" + e.getMessage());
			throw new RuntimeException("Nuova eccezione", e);
		}
		System.out.println("fuori dalla try");
	}

}
