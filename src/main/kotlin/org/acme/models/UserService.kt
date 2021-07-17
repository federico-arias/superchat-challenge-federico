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

    fun getUser(uid: Long): User {
       val query = em.createQuery("""
                select new org.acme.models.User(u.id, u.name)
                from org.acme.models.User u
                where u.id = :uid
       """.trimIndent(), User::class.java)
        return query.setParameter("uid", uid).singleResult
    }

    fun getUserByWebhook(webhook: String): User {
        val query = em.createQuery("""
                select new org.acme.models.User(u.id, u.name)
                from org.acme.models.User u
                where u.webhook = :wh
       """.trimIndent(), User::class.java)
        return query.setParameter("wh", webhook).singleResult
    }
}

