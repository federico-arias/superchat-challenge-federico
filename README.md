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

## [POST] /users/{id}/messages

Adds a new message to the conversation with other user. 

```bash
curl -v -X POST localhost:1313/api/v1/users/1/messages \
  -H 'content-type: application/json' \
  --data-raw '{"message": "Hi, $name$ this is the rate of Bitcoin: $rate$" USD, "sender": 2}'
```

## [GET] /users/{id}/conversations?user=id

List all messages in the conversation.

```bash
curl -v -X GET -G -d 'user=2' 'localhost:1313/api/v1/users/1/messages'
```


## [POST] /webhook/users/{userwebhook}/messages

Receive messages from an external service via a webhook

```bash
curl -v -H 'content-type: application/json' \
  --data-raw '{"message": "Hi, $name$ this is the rate of Bitcoin: $rate$ USD", "sender": 2}' \
	localhost:1313/api/v1/webhook/users/12345abc/messages
```

# To do

- Input data validation
- Structured API error response
- Error handling
- Authorization
- Unit testing
  
