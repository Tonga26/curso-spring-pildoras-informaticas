package es.pildoras.spring.gestioncrud.dao;

import es.pildoras.spring.gestioncrud.entity.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Cliente> getClientes() {

        // 1. Obtener la Session
        Session miSession = sessionFactory.getCurrentSession();

        // 2. Crear la consulta (query)
        Query<Cliente> miQuery = miSession.createQuery("from Cliente", Cliente.class);

        // 3. Ejecutar la query y devolver resultados
        List<Cliente> clientes = miQuery.getResultList();

        return clientes;
    }


}
