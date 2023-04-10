# Banking Application

### Endpoints

A post request to the account endpoint creates an account and if initialCredit is present a transaction will be made

POST http://localhost:8080/accounts \
Content-Type: application/json \
Request body:
```json
{
  "customerId": "8dd4b430-475f-4303-b01c-28aec517646e",
  "initialCredit": 1.00
}
```

* _The id in the request body is optional._ If passed a deposit will be made into an existing account identified by the
id, otherwise a new account will be created with an initial deposit

A get request to the customers endpoint will return the customer's accounts info

GET http://localhost:8080/customers/8dd4b430-475f-4303-b01c-28aec517646e \
Accept: application/json
Response body:

```json
{
  "id": "8dd4b430-475f-4303-b01c-28aec517646e",
  "accounts": [
    {
      "id": "de8a410f-655a-4bb9-a29a-a78e44dcd158",
      "transactions": [
        {
          "id": "9ebc26f3-9cb0-4b63-8d70-bdc9ac6478c5",
          "transactionType": "DEPOSIT",
          "amount": 1.00,
          "updatedBalance": 1.00,
          "createdOn": "2023-04-07T02:25:23.440434"
        }
      ],
      "balance": 1.00
    }
  ],
  "name": "Maikel",
  "surname": "Maas"
}
```
### How to run it

###### Dockerized version

Just run from the root folder:

```shell
docker compose up
```
or

```shell
docker compose -f docker-compose.yml up
```

