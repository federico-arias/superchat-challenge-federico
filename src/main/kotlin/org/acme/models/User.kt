package org.acme.models

import javax.persistence.*

@Entity
@Table(name="user_table")
open class User {
    @Id
    @SequenceGenerator(name = "usrSeq", sequenceName = "usr_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "usrSeq")
    private var id: Long? = null
    private var webhook: String? = null

    private var name: String? = null

    open fun getName() = name
    open fun setName(n: String?) {
        name = n
    }

    open fun getWebhook() = webhook
    open fun setWebhook(n: String?) {
        webhook = webhook
    }

    //private var webhooks: Listof<String>

    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = [CascadeType.MERGE], orphanRemoval = true)
    private val contacts = mutableListOf<Contact>()

    fun addContact(c: Contact) {
        c.setUser(this)
       contacts.add(c)
    }
     */

    open fun setId(i: Long) {
        id = i
    }

    open fun getId() = id


    constructor (i: Long?) {
        id = i
    }

    constructor (i: Long, name: String?) {
        id = i
        setName(name)
    }

    constructor() {

    }
}
