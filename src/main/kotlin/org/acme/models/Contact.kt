package org.acme.models

import javax.persistence.*

@NamedQuery(
        name = "get_person_by_name",
        query = "select name from Contact c where name = :name"
)
@Entity
// Hibernate needs this class to be inheritable (open)
open class Contact  {
    @Id
    @SequenceGenerator(name = "contactSeq", sequenceName = "contact_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "contactSeq")
    private var id: Long? = null
    private var name: String? = null
    private var phone: String? = null
    private var uid: Long? = null

    constructor(id: Long, name: String, phone: String, uid: Long)  {
        setUser(uid)
       setPhone(phone)
       setName(name)
       setId(id)
    }

    constructor(id: Long?) {
        setId(id)
    }

    constructor()

    open fun setId(i: Long?) {
        id = i
    }

    open fun getId() = id

    open fun setPhone(p: String?) {
        phone = p
    }
    open fun getPhone() = phone

    open fun getName() = name

    open fun getUser() = uid;

    open fun setUser(id: Long?) {
        uid= id
    }

    open fun setName(n: String) {
        name = n
    }
}

/*
@Entity
public class Gift {
    private Long id;
    private String name;

    @Id
    @SequenceGenerator(name = "giftSeq", sequenceName = "gift_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "giftSeq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
*/