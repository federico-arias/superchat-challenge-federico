# API

## [POST] /users/{id}/contacts

Adds an entry in the list of contacts for user of given _id_.

```bash
curl -s -H 'content-type: application/json' \
	--data '{ "name": "Lauri", "phone": "1234", "user": 1 }' \
	localhost:8080/api/contacts
```

## [GET] /users/{id}/contacts

```bash
curl -s localhost:8080/api/users/1/contacts
```

## [POST] /users/{id}/messages?contact=contact_id

Adds a new message to the conversation with _contact_ for
user of id 1.

## [GET] /users/{id}/conversations?contact=id

Get all messages in the conversation.

## [POST] /webhook/users/{secret-uuuid}/messages?contact=contact_id

# Domain

Aggregate Root: Conversation(sender, receiver, message, inserted_at)

Your service should allow potential users to use these functionalities:

- Create contacts given their personal information (Name, E-Mail, etc)
- List all contacts
- Send a message to a contact
- List all previous conversations
- Receive messages from an external service via a webhook

At Superchat we allow our clients to include placeholders (like the recipient's name) within the messages to save them time.
When they send out a message that contains placeholders, our backend substitutes them with the required value.

In order to make your challenge a bit more challenging ✨, come up with a way to implement this.

Please support at least substituting the following:

- Name of contact
- Current Bitcoin Price in $ (https://api.coindesk.com/v1/bpi/currentprice.json)


```java
StringTemplate hello = new StringTemplate("Hello, $name$", DefaultTemplateLexer.class);
hello.setAttribute("name", "World");
System.out.println(hello.toString());
```

# To do

- Users table/entity
- Swagger
- Input data validation
- Structured API error response
- Authorization
- Consider using XMPP / websockets
- Unit testing
- CI/CD
- GraalVM
- Fixtures
