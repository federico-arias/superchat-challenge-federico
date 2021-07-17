package org.acme

import org.acme.models.*
import org.acme.rest.client.BitcoinService
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.resteasy.annotations.jaxrs.PathParam
import org.jboss.resteasy.annotations.jaxrs.QueryParam
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.ConstraintViolation
import javax.validation.Valid
import javax.validation.Validator
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

class ContactDto constructor(@NotNull @NotEmpty var name: String? = null, var phone: String? = null, var user: Long? = null)
class MessageDto constructor(var message: String? = null, var sender: Long? = null, var receiver: Long? = null)

@Path("/api/v1")
class GreetingResource {
    @Inject
    @field: RestClient
    internal lateinit var bitcoin: BitcoinService

    @Inject
    internal lateinit var contactService: ContactService

    @Inject
    internal lateinit var messageRepository: MessageService

    @Inject
    internal lateinit var userRepository: UserService

    @Inject
    internal lateinit var validator: Validator

    @POST
    @Path("users/{uid}/messages")
    @Transactional
    fun addMessage(message: MessageDto, @PathParam uid: Long): MessageDto {
        if (message.message !is String)
            throw BadRequestException("message missing")
        val user = userRepository.getUser(uid)
        // todo: cache this until end of day
        // todo: this could be dry-er
        val rate = bitcoin.getCurrentPrice("333e57d98c9cb8cc3ca6872903bf3327").rates?.BTC
        message.receiver = uid
        message.message = message.message?.replace("\$rate\$", rate.toString())
        message.message = message.message?.replace("\$rate\$", rate.toString())
        message.message = message.message?.replace("\$name\$", user.getName() ?: "")
        messageRepository.addMessage(message)
        return message
    }
    @POST
    @Path("webhook/users/{webhook}/messages")
    @Transactional
    fun addMessage(message: MessageDto, @PathParam webhook: String): MessageDto {
        if (message.message !is String)
            throw BadRequestException("message missing")
        val user = userRepository.getUserByWebhook(webhook)
        // todo: cache this until end of day
        // todo: this could be dry-er
        val rate = bitcoin.getCurrentPrice("333e57d98c9cb8cc3ca6872903bf3327").rates?.BTC
        message.receiver = user.getId()
        message.message = message.message?.replace("\$rate\$", rate.toString())
        message.message = message.message?.replace("\$rate\$", rate.toString())
        message.message = message.message?.replace("\$name\$", user.getName() ?: "")
        messageRepository.addMessage(message)
        return message
    }

    @GET
    @Path("users/{uid}/messages")
    fun getMessages(@PathParam uid: Long?, @QueryParam("user") userId: Long?): List<Message>{
        return messageRepository.getConversation(uid, userId)
    }

    @GET
    @Path("users/{uid}/contacts")
    //@Produces(MediaType.APPLICATION_JSON)
    fun getContacts(@PathParam uid: Long) : List<Contact> {
        //try {
            return contactService.getContacts(uid)
        //} catch(e: Exception) {
            //throw NotFoundException("no user ")
        //}
    }

    @GET
    @Path("test")
    fun testEndpoint(@QueryParam("message") message: String): String {
        val rate = bitcoin.getCurrentPrice("333e57d98c9cb8cc3ca6872903bf3327").rates?.BTC
        if (rate !is Float) {
            throw NotFoundException("bitcoin service error")
        }
        if (message !is String){
            throw BadRequestException("message missing")
        }
        return message.replace("\$price", rate.toString())
    }

    @POST
    @Path("users/{uid}/contacts")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addContact(@Valid contact: ContactDto, @PathParam uid: Long): Any {
        val violations = validator.validate(contact)
        if (violations.isEmpty()) {
            return contactService.createContact(contact.name ?: "", uid, contact.phone)
        }
        return HttpError<ContactDto>(violations)
    }
}

class HttpError<T> {
    private var message: String? = null

    constructor(violations: Set<ConstraintViolation<T>>)  {
        //message = violations.stream()
        //.map(cv -> cv.getMessage())
        //.collect(Collectors.joining(", "));
    }

    fun getMessage(): String? {
        return message;
    }
}
