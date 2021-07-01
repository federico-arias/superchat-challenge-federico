package org.acme.models

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@ApplicationScoped
class UserService {
    @Inject
    lateinit var em: EntityManager

    @Transactional
    open fun addUser(): User {
       var u = User();
        em.persist(u)
        return u
    }
}

