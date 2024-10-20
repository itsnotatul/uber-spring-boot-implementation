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
"id": 2,
"latitude": 12.975,
"longitude": 77.59,
"available": true,
"taxiType": "go"
},
{
"id": 4,
"latitude": 12.956,
"longitude": 77.6,
"available": true,
"taxiType": "go"
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