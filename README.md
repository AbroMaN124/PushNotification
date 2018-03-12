# PushNotification Service #

RESTful Spring boot service that allows Pushbullet users register and send Pushbullet notifications to registered users, as well as retrieve all registered users on the service.

# Setup & run #

1. Ensure you have Java 1.8+ installed
2. Checkout project and import as a maven project in your IDE (e.g. Eclipse)
3. Sign up to Pushbullet.com if required, ensure you have an access token (can be found under Settings -> Account on the Pushbullet website)
4. Install Pushbullet onto a device (browser or phone)
5. Run Application.java, this will run the application onto http://localhost:8080 (make sure the port is free!)
6. Use a tool like Curl or Postman to send HTTP messages to the service

# API #

**POST** http://{hostname}:{port}/api/user

Register a new user.

#### Request Body
Request Content Type: application/json

    {
        "username": "bbcUser1",
        "accessToken": "anAccessToken"
    }

#### Response Body
Response Content Type: application/json

    {
        "username": "bbcUser1",
        "accessToken": "anAccessToken",
        "creationTime": “2011-02-01T10:30:20”,
        "numOfNotificationsPushed": 0
    }

#### Response Status

201: User registered successfully
400: Bad request, such as missing username or access token in request body
409: User with given username already registered

------
**GET** http://{hostname}:{port}/api/user

Get all registered users.

#### Response Body
Response Content Type: application/json

    [
      {
          "username": "bbcUser1",
          "accessToken": "anAccessToken",
          "creationTime": “2011-02-01T10:30:20”,
          "numOfNotificationsPushed": 0
      },
      ...
    ]
      

#### Response Status

200: The request has succeeded


------
**POST** http://{hostname}:{port}/api/push/{username}

Get all registered users.

#### Response Body
Response Content Type: application/json

Should match the API defined on https://docs.pushbullet.com/#create-push

e.g.

    {
        "type": "note",
        "title": "the title",
        "body": "context of body"
    }
      

#### Response Status

200: The request has succeeded
400: Bad request, check message for more information (if any)
404: User with username {username} not registered
