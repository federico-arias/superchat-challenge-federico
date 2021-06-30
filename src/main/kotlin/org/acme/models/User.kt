package org.acme.models

import javax.persistence.*

@Entity
class User {
    @Id
    @SequenceGenerator(name = "usrSeq", sequenceName = "usr_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "usrSeq")
    private var id: Long? = null

    @OneToMany
    private var contacts: List<Contact>? = null

}