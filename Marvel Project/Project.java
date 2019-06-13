import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

import java.net.InetSocketAddress;
import java.util.Map;
import java.io.IOException;

import java.util.Map;
import java.sql.*;
//javac -cp sqlite-jdbc-3.23.1.jar; Project.java
public class Project {

    public static void main(String[] args) throws IOException {
        int port = 8500; 
		Database   db =  new  Database("jdbc:sqlite:EranKarmenEduardo.db" );		

        HttpServer server = HttpServer.create(new InetSocketAddress(port),0);

		//Server Webpage
		
		String htmlFile = Input.readFile("exampleJSON.html");
		server.createContext("/Sever", new RouteHandler(htmlFile));
		
		String sql = "SELECT * FROM MARVELCHARCTERS";		
		server.createContext("/marvelcharacters", new RouteHandler(db,sql));

        String sql2 = "SELECT * FROM MOVIES";		
		server.createContext("/movies", new RouteHandler(db,sql2));
		
        server.start(); 
		
        System.out.println("Server is listening on port " + port );
    }    
}


