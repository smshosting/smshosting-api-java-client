<p align="center">
<img src=https://smshosting.s3.eu-west-3.amazonaws.com/cover-progetto.jpg>
</p>

# [Smshosting](https://www.smshosting.it)

[![it](https://img.shields.io/badge/lang-it-red.svg)](/README.md)

## The most complete platform to improve your marketing communications.

This Java library allows you to integrate our services directly into your project, adding the possibility to send SMS, Emails, manage customers and much more.

## Library Functions
You can use this library to:
- Send SMS
- Send Bulk SMS
- Create and manage Contacts
- Create and manage Groups
- Send single emails
- Send email campaigns
- Check the account status and credit
... and much more.

## Getting Started

#### Create an account
- Create your Smshosting account [here](https://cloud.smshosting.it/sms/signupInit.ic).
- Once the account is activated, go to Developers -> API REST, HTTP and SOAP to access your api credentials. 

#### Download the library

- Git clone or download the library and include it on your project.

## Some Examples

Not all parameters are required. Refer to the [Documentation](https://help.smshosting.it/it/sms-rest-api) for more informations.

#### Get the client instance
```java
SmsHostingClient clientSmsh = SmsHostingClient.getInstance("YOUR_API_KEY", "YOUR_SECRET_KEY"));
```

#### Send a single SMS
```java
SendSmsResponse res = clientSmsh.sendSms("mySender", "39YYYXXXXXXX", null, "smsText", null, null, false, null, "AUTO");
```

#### Add a contact
```java
ContactResponse res = clientSmsh.addContact("39YYYXXXXXXX", "ContactName", "ContactLastname", "contact@email.it", null, null, null);
```

#### Get contact list
```java
ContactSearchResponse res = res = clientSmsh.searchContacts(null, null, null, null, null, null, null);
```

#### Search contact by phone number
```java
ContactSearchResponse res = res = clientSmsh.searchContacts(null, "39YYYXXXXXXX", null, null, null, null, null);
```

## Documentation
Documentation for Smshosting API can be found [here](https://help.smshosting.it/it/sms-rest-api)

## Support
If you need any help you can contact us at [assistenza@smshosting.it](mailto:assistenza@smshosting.it) or visit the [support page](https://www.smshosting.it/it/supporto-tecnico-e-commerciale)