import java.sql.*;
import java.util.Scanner;

public class Actividad2 {
    private static Connection conexion;
    private String nombreA;
    private Scanner tec=new Scanner(System.in);
    private int idProfesor;
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Actividad2 act2=new Actividad2();
        //CREAMOS LA CONEXION
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion= DriverManager.getConnection("jdbc:mysql://localhost/examen?useUnicode=" +
                        "true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "sergio", "");
        act2.buscaAsig();
    }

    private  void buscaAsig() throws SQLException {
        System.out.println("Introduce nombre asignatura :");
        nombreA=tec.nextLine();

        if (checkAsig(nombreA)){
            String sql="select * from profesores where profesores.id="+idProfesor;
            Statement sentencia=conexion.createStatement();
            ResultSet result=sentencia.executeQuery(sql);
            while (result.next()) {
                System.out.println("ID profesor: " + result.getInt("id") +"\n"+"* Nombre profesor: "+
                        result.getString("Nombre") + "\n" + "* Apellido profesor: " +
                        result.getString("Apellido") + "\n");
            }

        }else{
            System.out.println("No existe  \n");
            buscaAsig();
        }
    }

    private boolean  checkAsig(String nombreA) throws SQLException {
        String sql="SELECT * FROM asignaturas WHERE Nombre = '"+nombreA+"'";
        Statement sentencia = conexion.createStatement();
            ResultSet result = sentencia.executeQuery(sql);
            if (result.next()) {
                idProfesor = result.getInt("Profesor");
                System.out.println("El id del profesor de la asignatura es "+idProfesor);
                sentencia.close();
                result.close();
                return true;
            } else {
                return false;
            }

    }

}
