INSERT INTO taxi (latitude, longitude, available, taxi_type) VALUES 

(12.9716, 77.5946, TRUE, 'sedan'),

(12.9750, 77.5900, TRUE, 'go'),

(12.9630, 77.5800, FALSE, 'xl'),

(12.9560, 77.6000, TRUE, 'go'),

(12.9700, 77.6100, TRUE, 'sedan'),

(12.9550, 77.5500, TRUE, 'xl'),

(12.9000, 77.5000, FALSE, 'go'),

(12.9500, 77.5800, TRUE, 'sedan'),

(12.9100, 77.6000, TRUE, 'xl'),

(12.9400, 77.5700, TRUE, 'go');


//USER Table:

INSERT INTO app_user (name) VALUES ('John Doe');

INSERT INTO app_user (name) VALUES ('Jane Smith');

INSERT INTO app_user (name) VALUES ('Michael Johnson');

INSERT INTO app_user (name) VALUES ('Emily Davis');

INSERT INTO app_user (name) VALUES ('Robert Brown');

INSERT INTO app_user (name) VALUES ('Sophia Wilson');

INSERT INTO app_user (name) VALUES ('Liam Taylor');

INSERT INTO app_user (name) VALUES ('Olivia Martin');

INSERT INTO app_user (name) VALUES ('Noah Thompson');

INSERT INTO app_user (name) VALUES ('Emma White');


#FR #1

 GET http://localhost:8080/taxis/available?latitude=12.9716&longitude=77.5946
 
O/P:

[
{
"id": 1,
"latitude": 12.9716,
"longitude": 77.5946,
"available": true,
"taxiType": "sedan"
},
{
"id": 5,
"latitude": 12.97,
"longitude": 77.61,
"available": true,
"taxiType": "sedan"
},
{
"id": 6,
"latitude": 12.955,
"longitude": 77.55,
"available": true,
"taxiType": "xl"
},
{
"id": 8,
"latitude": 12.95,
"longitude": 77.58,
"available": true,
"taxiType": "sedan"
},
{
"id": 9,
"latitude": 12.91,
"longitude": 77.6,
"available": true,
"taxiType": "xl"
},
{
"id": 10,
"latitude": 12.94,
"longitude": 77.57,
"available": true,
"taxiType": "go"
}
]


#FR #2:

POST: http://localhost:8080/taxis/book

body:

{
"userId":2,    
"taxiId": 5,
"distance": 10,
"bookingTime": "2024-10-23T01:30:00",
"taxiType": "sedan"
}

Taxi booked successfully. Booking ID: 2. Status: PENDING. Please proceed to payment.

{
"userId":2,   
"taxiId": 14,
"distance": 10,
"bookingTime": "2024-10-20T01:30:00",
"taxiType": "sedan"
}

Taxi with ID 14 not found.

{
"userId":2,   
"taxiId": 4,
"distance": 10,
"bookingTime": "2024-10-20T01:30:00",
"taxiType": "sedan"
}

Taxi with ID 4 is not available.


#FR #3: GET: http://localhost:8080/user/booking?userId=2

O/p:

[
{
"id": 2,
"taxi": {
"id": 5,
"latitude": 12.97,
"longitude": 77.61,
"available": false,
"taxiType": "sedan"
},
"taxiType": "sedan",
"bookingTime": "2024-10-23T01:30:00",
"distance": 10.0,
"bookingStatus": "PENDING"
}
]

#FR #4: POST: http://localhost:8080/payment

{
"bookingId":23
}

O/P:

Booking with ID 23 not found.

{
"bookingId":2
}

Payment successful. Booking ID: 2. Status: SUCCESS