/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Edwin
 */

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/HotelDB";
    private static final String USUARIO = "empresa_app";
    private static final String PASSWORD = "P123456+";
    
    private static HikariDataSource dataSource;
    
    static{
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USUARIO);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(20); //Máximo de conexiones simultáneas
        config.setMinimumIdle(10); //Mínimo de conexiones en espera
        config.setIdleTimeout(300000); //Tiempo máximo inactivo antes de cerrar la conexión
        config.setMaxLifetime(1800000);//Tiempo máximo de vida de una conexión
        config.setConnectionTimeout(10000); //Timeout para obtener conexión 
        
       config.setConnectionTestQuery("SELECT 1");
       config.setAutoCommit(true); 
        config.setValidationTimeout(5000); // Timeout para validaciones de conexión (opcional)
        
        dataSource = new HikariDataSource(config);                
    }
    
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
    
    public static void closePool(){
        if(dataSource != null){
            dataSource.close();
        }
    }  
}
