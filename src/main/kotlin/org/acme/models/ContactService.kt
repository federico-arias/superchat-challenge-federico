package org.acme.models

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@ApplicationScoped
class ContactService {
    @Inject
    lateinit var em: EntityManager

    @Transactional
    fun createContact(name: String, userId: Long?, phone: String?): Contact {
        if (userId == null)
            throw Error("ValidationError")
        val contact = Contact()
        contact.setName(name)
        contact.setUser(userId)
        contact.setPhone(phone)
        em.persist(contact);
        return contact
    }

    fun getContacts(uid: Long): List<Contact> {
        val query = em.createQuery("select new org.acme.models.Contact(c.id, c.name, c.phone, c.uid) from org.acme.models.Contact c where c.uid = :uid", Contact::class.java)
        return query.setParameter("uid", uid)
            .resultList
    }
}