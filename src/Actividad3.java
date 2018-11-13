import java.sql.*;
import java.util.Scanner;

public class Actividad3 {
    private  static Connection conexion;
    private Scanner tec=new Scanner(System.in);
    public static int idProfesor;
    public static void main(String[] args) throws SQLException {
    Actividad3 act3=new Actividad3();
        // Establecemos conexion con la BBDD
        act3.conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost/examen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "sergio", "");
        act3.insertProfesor();

    }

    private void insertProfesor() throws SQLException {
        System.out.println("Intro id del profesor");
        idProfesor=tec.nextInt();
        if (checkProf(idProfesor)){
            System.out.println("Ya existe dicho id");
            insertProfesor();
        }else {
            tec.nextLine();
            System.out.println("Introduce su nombre");
            String nombre=tec.nextLine();

            System.out.println("Introduce su apellido");
            String apellido=tec.nextLine();
            // INSERTAR EN TABLA
            Statement sentencia=conexion.createStatement();
            String sql = "INSERT INTO PROFESORES VALUES(" +
                    idProfesor+","+
                    "'"+nombre+"',"+
                    "'"+apellido+"')";

            System.out.println("texto sentencia sql :"+sql);
            int filas= sentencia.executeUpdate(sql);
            sentencia.close();

        }
    }

    public boolean checkProf(int idProfesor) throws SQLException {
        String sql="SELECT * FROM profesores WHERE id = "+idProfesor;
        ResultSet result;
        //System.out.println(sql);
        System.out.println(" id profesor "+idProfesor);
        Statement sentencia = conexion.createStatement();
            //System.out.println(" entre!!!");
            result = sentencia.executeQuery(sql);
            //System.out.println("despues result");

        if (result.next()) {
            return true;
        } else {
            return false;
        }
    }

}
