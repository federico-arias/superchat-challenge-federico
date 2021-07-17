package org.acme.models

import javax.persistence.*
import javax.persistence.CascadeType.*

@Entity
open class Message {
    @Id
    @SequenceGenerator(name = "msgSeq", sequenceName = "msg_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "msgSeq")
    private var id: Long? = null

    private var message: String? = null

    @ManyToOne(optional = true, cascade = [MERGE], fetch = FetchType.LAZY)
    private var sender: User? = null

    @ManyToOne(optional = true, cascade = [MERGE], fetch = FetchType.LAZY)
    private var receiver: User? = null

    //private var insertedAt: Date? = null

    constructor(m: String?, from: Long?, to: Long?) {
        setSender(User(from))
        setReceiver(User(to))
        setMessage(m)
    }

    constructor(m: String?, from: User, to: User) {
        setSender(from)
        setReceiver(to)
        setMessage(m)
    }

    constructor()

    open fun setMessage(m: String?) {
        message = m
    }

    open fun getMessage(m: String?) = message

    open fun setId(i: Long?) {
        id = i
    }

    open fun getId() = id

    open fun setSender(c: User) {
        sender = c
    }

    open fun getSender() = sender

    open fun setReceiver(c: User) {
        receiver = c
    }

    open fun getReceiver() = receiver

}