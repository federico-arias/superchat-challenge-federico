package org.acme.models

import org.acme.MessageDto
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@ApplicationScoped
class MessageService {
    @Inject
    lateinit var em: EntityManager

    @Transactional
    fun addMessage(dto: MessageDto): Message {
        var msg = Message(dto.message , dto.sender, dto.receiver)
        em.persist(msg)
        println("executed")
        return msg
    }

    fun getConversation(uid0: Long?, uid1: Long?): List<Message> {
        val query = em.createQuery("""
            select 
                new org.acme.models.Message(m.message, m.sender, m.receiver) 
            from 
                org.acme.models.Message m 
             where 
                sender_id = :uid0 and receiver_id = :uid1 
                    or 
                sender_id = :uid1 and receiver_id = :uid0
        """, Message::class.java)
        return query.setParameter("uid0", uid0)
            .setParameter("uid1", uid1)
            .resultList
    }

}