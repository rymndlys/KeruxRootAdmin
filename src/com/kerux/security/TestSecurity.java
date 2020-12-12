package com.kerux.security;

public class TestSecurity {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Security.encrypt("jdbc:mysql://10.70.3.1/isproj2"));
		System.out.println(Security.encrypt("keruxpass"));
		System.out.println(Security.encrypt("root"));
		System.out.println("---------");
		System.out.println(Security.encrypt("isprojAdmin"));
		System.out.println(Security.encrypt("root"));
		System.out.println(Security.decrypt("VnSpNX+SUUvuMywwQkEnl1Krc3dBZyVahFvF0l4x/Y8="));
		System.out.println(Security.decrypt("c4jHYH0nX4p1jCyNfEqv0w=="));
		System.out.println(Security.decrypt("xKAYkh9JN58Iv4/30k3tq8WuMg5bg6dgulYBh8pqOk4="));
		System.out.println(Security.decrypt("U1LDNN09Bdy1JQo6A5pzLA=="));
		System.out.println(Security.encrypt("jdbc:mysql://192.168.64.2/keruxdb2"));
		System.out.println(Security.encrypt("KeruxAdmin"));
		System.out.println(Security.encrypt("keruxAdmin"));
		System.out.println(Security.encrypt("rootAdmin"));
		System.out.println(Security.encrypt("admin"));
		System.out.println("---------");
		System.out.println(Security.encrypt("jdbc:mysql://10.70.0.17/keruxtest"));
		System.out.println(Security.encrypt("jdbc:mysql://10.70.0.17/keruxdb"));
		System.out.println(Security.encrypt("root"));
		System.out.println(Security.encrypt(""));
		System.out.println("---------");
		System.out.println(Security.encrypt("jdbc:mysql://10.70.0.114/isproj"));
		System.out.println(Security.encrypt("jdbc:mysql://localhost:8000/isproj2_sympl"));
		System.out.println(Security.encrypt("jdbc:mysql://127.0.0.1:3306/isproj2_sympl"));
		System.out.println(Security.encrypt("isproj2_sympl"));
		System.out.println(Security.encrypt("mJ9r)Gv^Q>[=>%W#"));
		System.out.println("===============");
		System.out.println(Security.encrypt("rootAdminRoy"));
		System.out.println(Security.encrypt("rootAdmin123"));
		System.out.println(Security.encrypt("rctyabut@gmail.com"));
		System.out.println("===============");
		System.out.println(Security.encrypt("jdbc:mysql://10.70.0.17/kerux-db-final"));
		System.out.println(Security.encrypt("KeruxRoot"));
		System.out.println(Security.encrypt("5QDw1b4ZBXTUjSpR"));

		System.out.println("===============");
		String jdbcUrl ="zp/kWu5b+tDu/jLr/ghDf2veMxqv/JQmMwuTl0iQsMSemYdqNwSEu1ta4QN1N/8M";
		String dbUserName = "Ehjf9dlWTbKya8EjojsH1A==";
		String dbPassword = "c67l0Ac10jF3rdfZru2uhe9fW0wb6YVnsEYgogo9w/w=";
		System.out.println(Security.decrypt(jdbcUrl));
		System.out.println(Security.decrypt(dbUserName));
		System.out.println(Security.decrypt(dbPassword));
		
	}
	
	


}
