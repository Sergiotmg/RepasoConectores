import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Actividad1_SQLite {


    public static void main(String[] args) {

        try {
            //Cargamos el driver
            Class.forName("org.sqlite.JDBC");
            //PARA PC CLASE
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\db\\repasoConectores.db");
            //PARA PC CASA ( no se ha podido crear la bbdd con sqlite)
            //Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\OneDrive2\\OneDrive\\AA_Acceso Datos\\CONECTORS\\sqlite\\examen.db");

            Statement sentencia = conexion.createStatement();
            int filas = sentencia.executeUpdate(
                    "CREATE TABLE Profesores (ID INT  PRIMARY KEY NOT NULL, Nombre VARCHAR(15),Apellido VARCHAR(15))");

            sentencia = conexion.createStatement();
            filas = sentencia.executeUpdate(
                    "CREATE TABLE Asignaturas (ID INT  PRIMARY KEY NOT NULL, Nombre VARCHAR(15),Profesor int NOT NULL," +
                            "FOREIGN KEY (Profesor) REFERENCES Profesores(ID) )");
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();//PONER SIEMPRE EL PRINTSTACKTRACE!! SIEMPRE PARA MOSTRAR ALGO
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
