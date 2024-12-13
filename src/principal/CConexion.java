package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CConexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contraseña = "Juanjui*peru95";
    String bd = "sistema_servicios";
    String server = "localhost"; 
    String puerto = "3306";

    String cadena ="jdbc:mysql://"+server+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conectar = DriverManager.getConnection(cadena,usuario,contraseña);
            System.out.println("La conexión se ha realizado con éxito");
            //JOptionPane.showMessageDialog(null,"La conexión se ha realizado con éxito");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al conectarse a la base de datos, erro: "+ e.toString());
        }
        return conectar;
    }   
    
}
