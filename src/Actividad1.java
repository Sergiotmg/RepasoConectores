import java.sql.*;

public class Actividad1 {

    public static void main(String[] args) {

        try {
            //Cargamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/examen?"
                    + "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode="
                    + "false&serverTimezone=UTC", "sergio", "");
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
            cn.printStackTrace();//PONER SIEMPRE EL PRINTSTACKTRACE!! SIEMPRE PAR AMOSTRAR ALGO
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
