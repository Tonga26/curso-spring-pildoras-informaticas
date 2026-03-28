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

    // MÉTODO insertarCliente: Implementa la escritura de un nuevo registro.
    @Override
    @Transactional
    public void insertarCliente(Cliente elCliente) {

        // 1. Obtenemos la conexión a la base de datos gestionada por Spring.
        Session miSession = sessionFactory.getCurrentSession();

        // 2. El método .save() toma el objeto Java, lee sus anotaciones (@Entity, @Column)
        // y genera automáticamente la instrucción "INSERT INTO cliente (nombre, apellido...) VALUES (?, ?...)"
        // Como está bajo @Transactional, Spring confirmará (Commit) este guardado al terminar el método.
        miSession.save(elCliente);
    }
}