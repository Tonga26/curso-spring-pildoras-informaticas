package es.pildoras.spring.gestioncrud.dao;

import es.pildoras.spring.gestioncrud.entity.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// ANOTACIÓN @Repository: Indica a Spring que esta clase es el DAO.
// Permite que Spring la instancie y también traduce automáticamente las excepciones SQL complejas.
@Repository
public class ClienteDAOImpl implements ClienteDAO {

    // ATRIBUTO sessionFactory: El motor de Hibernate inyectado por Spring (configurado en el XML).
    @Autowired
    private SessionFactory sessionFactory;

    // MÉTODO getClientes: Implementa la lectura general.
    // ANOTACIÓN @Transactional: Delega a Spring el control de abrir la sesión, iniciar transacción y hacer commit/rollback.
    @Override
    @Transactional
    public List<Cliente> getClientes() {

        // 1. Pedimos a la fábrica de sesiones la conexión actual a la base de datos.
        Session miSession = sessionFactory.getCurrentSession();

        // 2. Creamos la consulta usando HQL (Hibernate Query Language).
        // Nota: "from Cliente" se refiere a la CLASE JAVA, no a la tabla SQL.
        Query<Cliente> miQuery = miSession.createQuery("from Cliente", Cliente.class);

        // 3. Ejecutamos la consulta y almacenamos el resultado en la lista.
        List<Cliente> clientes = miQuery.getResultList();

        return clientes;
    }

    // MÉTODO guardarCliente: Implementa la escritura (Upsert).
    @Override
    @Transactional
    public void guardarCliente(Cliente elCliente) {

        // 1. Obtenemos la conexión a la base de datos gestionada por Spring.
        Session miSession = sessionFactory.getCurrentSession();

        // 2. El método .saveOrUpdate() evalúa el ID del objeto que recibe.
        // Si el ID es 0 o nulo -> Genera un INSERT (Crear).
        // Si el ID existe (ej. 7) -> Genera un UPDATE (Modificar).
        // Como está bajo @Transactional, Spring confirmará (Commit) este guardado automáticamente.
        miSession.saveOrUpdate(elCliente);
    }

    // MÉTODO getClienteById: Recupera un único registro basado en su ID.
    @Override
    @Transactional
    public Cliente getClienteById(int id) {

        // 1. Obtenemos la conexión actual a la base de datos.
        Session miSession = sessionFactory.getCurrentSession();

        // 2. Usamos el método .get() de Hibernate.
        // Le pasamos la clase que queremos mapear (Cliente.class) y la clave primaria (id).
        return miSession.get(Cliente.class, id);
    }

    // MÉTODO eliminarCliente: Implementa la destrucción del registro usando el camino del Objeto.
    // ANOTACIÓN @Transactional: ¡Crucial! Requerido para cualquier operación que modifique la BD.
    @Override
    @Transactional
    public void eliminarCliente(int id) {

        // 1. Obtenemos la conexión actual a la base de datos.
        Session miSession = sessionFactory.getCurrentSession();

        // 2. Buscamos el objeto en la base de datos para traerlo a la memoria.
        Cliente clienteABorrar = miSession.get(Cliente.class, id);

        // 3. Utilizamos el método .delete() de Hibernate para destruir el objeto.
        // Al finalizar el método, Spring hace Commit y genera el: "DELETE FROM cliente WHERE id = ?"
        miSession.delete(clienteABorrar);
    }
}