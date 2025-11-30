package com.ndlcommerce.adapters.persistence.customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class JpaCustomerRepositoryImpl implements CustomCustomerRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<CustomerDataMapper> search(
      String login, String name, String contact, Boolean active, Integer page, Integer size) {

    String jpql =
        """
            SELECT c
            FROM CustomerDataMapper c
            JOIN c.user u
            WHERE LOWER(u.login)  LIKE LOWER(COALESCE(:login,  u.login))
            AND   LOWER(c.name)   LIKE LOWER(COALESCE(:name,   c.name))
            AND   LOWER(c.contact)LIKE LOWER(COALESCE(:contact,c.contact))
            AND   (:active IS NULL OR c.active = :active)
        """;

    TypedQuery<CustomerDataMapper> query =
        entityManager.createQuery(jpql, CustomerDataMapper.class);

    query.setParameter("login", login == null || login.isBlank() ? null : "%" + login + "%");
    query.setParameter("name", name == null || name.isBlank() ? null : "%" + name + "%");
    query.setParameter(
        "contact", contact == null || contact.isBlank() ? null : "%" + contact + "%");
    query.setParameter("active", active);
    //        TODO: validar paginação
    query.setFirstResult(page * size);
    query.setMaxResults(size);

    return query.getResultList();
  }
}
