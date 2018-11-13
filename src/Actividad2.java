import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Actividad2 {


    public static void main(String[] args) {

        try {
            //Cargamos el driver
            Class.forName("org.sqlite.JDBC");
            //PARA CLASE
            //Connection conexion = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\db\\repasoConectores.db");
            //PARA CASA ( no se ha podido crear la bbdd con sqlite)
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\OneDrive2\\OneDrive\\AA_Acceso Datos\\CONECTORS\\sqlite\\examen.db");
            //Preparamoslaconsulta
            Statement sentencia = conexion.createStatement();
            int filas = sentencia.executeUpdate(
                    "CREATE TABLE Profesores (ID INT NOT NULL PRIMARY KEY, Nombre VARCHAR(15),Apellido VARCHAR(15))");

            sentencia = conexion.createStatement();
            filas = sentencia.executeUpdate(
                    "CREATE TABLE Asignaturas (ID INT NOT NULL PRIMARY KEY, Nombre VARCHAR(15),Profesor int NOT NULL," +
                            "FOREIGN KEY (ID) REFERENCES Profesores(ID) )");
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();//PONER SIEMPRE EL PRINTSTACKTRACE!! SIEMPRE PARA MOSTRAR ALGO
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
