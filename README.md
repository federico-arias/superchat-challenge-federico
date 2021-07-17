# Running

To run the app on port 1313:

```bash
 $ ./mvnw package
 $ docker-compose up -d
```

# API

## [POST] /users/{id}/contacts

Create contacts given their personal information (Name, E-Mail, etc) for user of given _id_.

```bash
curl -s -H 'content-type: application/json' \
	--data '{ "name": "Lauri", "phone": "1234", "user": 1 }' \
	localhost:1313/api/v1/contacts
```

## [GET] /users/{id}/contacts

List all contacts for user of given _id_.

```bash
curl -s localhost:1313/api/v1/users/1/contacts
```

## [POST] /users/{id}/messages?contact=contact_id

Adds a new message to the conversation with _contact_ for
user of id 1.

## [GET] /users/{id}/conversations?contact=id

List all messages in the conversation.

## [POST] /webhook/users/{secret-uuuid}/messages

Receive messages from an external service via a webhook

```bash
curl -s -H 'content-type: application/json' \
	--data-raw '{ "message": "Hi ${name}. Did you know Bitcoin was valued at ${bcp} USD today?"}' \
	localhost:1313/api/v1/webhook/users/{secret-uuuid}/messages
```

```java
StringTemplate hello = new StringTemplate("Hello, $name$", DefaultTemplateLexer.class);
hello.setAttribute("name", "World");
System.out.println(hello.toString());
```

# To do

- Input data validation
- Structured API error response
- Authorization
- Consider using XMPP / websockets
- Unit testing
- GraalVM
- CI/CD
- Swagger
