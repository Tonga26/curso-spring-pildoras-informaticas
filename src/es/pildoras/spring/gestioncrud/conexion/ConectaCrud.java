package es.pildoras.spring.gestioncrud.conexion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * =========================================================================
 * SECCIÓN 1: DEFINICIÓN DEL SERVLET Y MAPEO DE RUTAS
 * =========================================================================
 * Un Servlet es el componente de Java encargado de recibir peticiones web,
 * procesarlas en el servidor y devolver una respuesta al navegador.
 */

/**
 * @WebServlet: Anotación que reemplaza la antigua configuración en archivos XML.
 * Define la ruta exacta ("Endpoint"). Le indica a Tomcat que ejecute esta clase
 * únicamente cuando el usuario acceda a la URL terminada en "/ConectaCrud".
 */
@WebServlet("/ConectaCrud")
public class ConectaCrud extends HttpServlet { // Al heredar de HttpServlet, esta clase adquiere capacidades para operar en un entorno web.

    // =========================================================================
    // SECCIÓN 2: ATRIBUTOS DE CLASE Y CONSTRUCTOR
    // =========================================================================

    /**
     * serialVersionUID: Identificador de versión para la serialización.
     * Garantiza que si el servidor necesita guardar el estado de este Servlet en
     * memoria o enviarlo por la red, la clase que se recupere sea exactamente la misma.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto.
     * Invoca al constructor de la clase padre (HttpServlet) con super() para
     * inicializar correctamente el contexto del Servlet en el contenedor web.
     */
    public ConectaCrud() {
        super();
    }

    // =========================================================================
    // SECCIÓN 3: CICLO DE VIDA - INTERCEPCIÓN DE PETICIONES HTTP
    // =========================================================================

    /**
     * doGet(): Método del ciclo de vida del Servlet que se invoca automáticamente
     * cuando el servidor recibe una petición HTTP GET (ej. abrir una URL en el navegador).
     * * @param request  (HttpServletRequest): Objeto que empaqueta toda la información
     * entrante del cliente (parámetros de la URL, cabeceras, IP).
     * @param response (HttpServletResponse): Objeto proporcionado por el servidor
     * para que nosotros construyamos la respuesta que se enviará de vuelta.
     * @throws ServletException Si ocurre un fallo en la lógica interna del Servlet.
     * @throws IOException      Si ocurre un error en los flujos de entrada/salida de datos.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // =====================================================================
        // SECCIÓN 4: PREPARACIÓN DEL FLUJO DE RESPUESTA
        // =====================================================================

        // Configura la cabecera HTTP indicando que devolveremos texto sin formato (no HTML).
        response.setContentType("text/plain");

        // Instancia un objeto PrintWriter asociado a la respuesta.
        // Actúa como un flujo de salida para imprimir texto directamente en el navegador.
        PrintWriter out = response.getWriter();

        out.println("Iniciando prueba de conexion a la Base de Datos...");

        // =====================================================================
        // SECCIÓN 5: CONFIGURACIÓN DE PARÁMETROS JDBC
        // =====================================================================

        // Cadena de conexión (URL JDBC). Indica el protocolo, host, puerto,
        // nombre de la base de datos exacta y desactiva el uso de SSL para desarrollo local.
        String jdbcUrl = "jdbc:mysql://localhost:3306/gestionpedidoscrud?useSSL=false";

        // Usuario administrador por defecto de la base de datos MySQL.
        String usuario = "root";

        // Contraseña del usuario (vacía en instalaciones locales por defecto de XAMPP/WAMP).
        String contra = "";

        // Ruta del paquete de la clase Driver específica para MySQL (versiones 8+).
        String driver = "com.mysql.cj.jdbc.Driver";

        // =====================================================================
        // SECCIÓN 6: APERTURA Y CIERRE DE CONEXIÓN CON CONTROL DE EXCEPCIONES
        // =====================================================================
        try {
            // Class.forName(): Carga dinámicamente la clase del Driver en la memoria
            // de la JVM en tiempo de ejecución para que DriverManager pueda usarla.
            Class.forName(driver);

            // DriverManager.getConnection(): Intenta establecer el socket físico de
            // red con el servidor MySQL usando las credenciales proporcionadas.
            Connection miConexion = DriverManager.getConnection(jdbcUrl, usuario, contra);

            // Si el hilo de ejecución llega aquí, significa que la conexión no arrojó excepciones.
            out.println("¡Conexion exitosa a gestionPedidosCRUD!");

            // close(): Libera el recurso y cierra el socket de red. Fundamental
            // para evitar fugas de memoria (Memory Leaks) en el servidor de base de datos.
            miConexion.close();

        } catch (Exception e) {
            // Bloque de captura para atrapar fallos de red, credenciales inválidas o clases no encontradas.

            // Imprime la traza técnica detallada del error en la consola del IDE.
            e.printStackTrace();

            // Informa al cliente (navegador) de manera simplificada sobre el fallo.
            out.println("ERROR AL CONECTAR: " + e.getMessage());
        }
    }
}