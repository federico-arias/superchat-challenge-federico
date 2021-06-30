package org.acme.rest.client

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/live")
@RegisterRestClient(configKey="bitcoin-api")
interface BitcoinService {
    @GET
    fun getCurrentPrice(@QueryParam("access_key") key: String): Bitcoin
}