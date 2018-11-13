import java.sql.*;
import java.util.Scanner;

public class Actividad4 {

    private  static Connection conexion;
    private Scanner tec=new Scanner(System.in);
    public static int idAsignatura;

    public static void main(String[] args) throws SQLException {
        Actividad4 act4=new Actividad4();

        // Establecemos conexion con la BBDD
        act4.conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost/examen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "sergio", "");
        act4.modAsig();
    }

    private void modAsig() throws SQLException {
        System.out.println("Intro id de la asigntura");
        idAsignatura=tec.nextInt();
        if (!checkAsig(idAsignatura)){
            System.out.println("No existe dicha asignatura");
            modAsig();
        }else {
            tec.nextLine();
            System.out.println("Introduce nuevo nombre");
            String nombreA=tec.nextLine();
            System.out.println("Introduce id del profesor");
            int idProfesor=tec.nextInt();
            // Check profesor
            //Actividad3 a3=new Actividad3();
            if (checkProf(idProfesor)){
                // INSERTAR EN TABLA
                Statement sentencia=conexion.createStatement();
                String sql = "UPDATE asignaturas SET Nombre = '"+nombreA+"',"+"Profesor="+
                        idProfesor+" WHERE ID="+idAsignatura;

                System.out.println("texto sentencia sql :"+sql);
                int filas=sentencia.executeUpdate(sql);
                if(filas==1)
                System.out.println("Asignatura modificada");
                sentencia.close();
                conexion.close();
            } else {
                System.out.println("No existe dicho profesor en la lista");
                modAsig();


            }


        }
    }


    private boolean  checkAsig(int idAsignatura) throws SQLException {
        String sql="SELECT * FROM asignaturas WHERE id = "+idAsignatura;
        ResultSet result;
        //System.out.println(sql);
        System.out.println(" id profesor "+idAsignatura);
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
