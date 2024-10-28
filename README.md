# Initial Psql Scripts to fill up the DB

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

# Documenting Results

   # FR #1: Get all available taxis for this pair of longitude and latitude
      
       GET http://localhost:8080/api/v1/taxi/available?latitude=12.9716&longitude=77.5946
       
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
    },
    {
        "id": 4,
        "latitude": 12.956,
        "longitude": 77.6,
        "available": true,
        "taxiType": "go"
    },
    {
        "id": 2,
        "latitude": 12.975,
        "longitude": 77.59,
        "available": true,
        "taxiType": "go"
    },
    {
        "id": 5,
        "latitude": 12.97,
        "longitude": 77.61,
        "available": true,
        "taxiType": "sedan"
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



# Problems Faced
1. # Why Does Infinite Recursion Happen?
         Infinite recursion can happen when two or more entities have bidirectional relationships, meaning each entity holds
         a reference to the other.

         In your example:
         
         The Booking entity has a User reference.
         The User entity has a list of Booking references (because a user can have multiple bookings).
         If you try to serialize the Booking entity (e.g., to return it as JSON in a REST API response), 
         Jackson (the default JSON serializer in Spring Boot) will attempt to serialize the User object inside Booking. 
         Then, because User has a reference back to Booking, Jackson will again try to serialize Booking, which again 
         references User, and this loop continues infinitely.
         
         Solution
         To avoid this, we use @JsonIgnore or @ToString.Exclude annotations to break the loop. 
         @JsonIgnore on the User field in Booking prevents Jackson from serializing User when it’s in Booking. 
         Similarly, @ToString.Exclude prevents User's toString from including the Booking reference, breaking 
         the loop during string operations.

2. # Why Do Some Entities Need to Implement Serializable?
         Caching Solutions (e.g., Redis):
      
         Many caching solutions like Redis use serialization to store objects. Since Redis stores data in a byte format,
         entity classes that are cached typically need to be serializable so they can be easily stored and retrieved
         without loss of structure or state.

   # When is it Necessary?
         Entities don’t always need to implement Serializable. It’s typically required when:
      
         The entity will be cached or stored in external storage as a byte stream.
         You’re using an environment or framework (like Redis caching) that explicitly requires serialization.
         For regular database persistence with JPA/Hibernate without caching or distributed requirements, Serializable is
         generally not necessary.


3. # @ToString.Exclude 
         If present, do not include this field in the generated toString.


4. # @JsonIgnore
         When serializing your object into Json, the fields that are tagged with @JsonIgnore will not be included in the 
         serialized Json object. This attribute is read by the Json serialize using reflection.

5. # how to return JSON as return from endpoints?
         In Spring Boot, when you return a Java object from a controller method, the framework automatically converts it to JSON format (or another format) based on the content negotiation settings and the libraries included in your project. Here's how this process works:

   # Jackson Library
         Spring Boot uses the Jackson library for JSON serialization and deserialization by default. When you return an 
         object from a controller, Jackson converts that object into a JSON response. This is handled by the 
         MappingJackson2HttpMessageConverter that Spring Boot provides.

6. # When to Use DTOs
         Using DTOs is not mandatory, but it is recommended in certain scenarios:
         
         Data Encapsulation: If your Taxi entity has many fields and you want to expose only a subset to the client, 
                              using a DTO allows you to control what data is returned.
            
         Security: DTOs help prevent accidental exposure of sensitive fields.
            
         Decoupling: Changes in your entity class won't necessarily break the API contract if you use DTOs, 
                     allowing for better versioning of your API.
            
         Custom Logic: If you need to format or combine data in a specific way before sending it to the client, 
                  DTOs can help manage that.

7. # intial steps to run:
      # psql queries 
         select * from app_user;
         select * from booking;
         select * from taxi;
      
      
         delete from booking;
         
         update taxi set available = true where id = 4;
         update taxi set available = true where id = 2;
         update taxi set available = true where id = 5;
      # cache flush
         ➜  ~ docker ps
         ➜  ~ docker exec -it 08cf90cae3ec redis-cli  # add <redis container id> 
            127.0.0.1:6379> FLUSHALL
            OK
            127.0.0.1:6379> 

8. # Differences Between JPQL and Native SQL Queries:
         JPQL (Java Persistence Query Language):
         
         JPQL is an object-oriented query language, which is used to query entities based on their object model rather than 
         the underlying database tables.
         It operates on the entity level, allowing you to reference entity fields and relationships directly.
         In our query, we wrote:
         @Query(value = "SELECT t FROM Taxi t WHERE t.available = true " +
         "AND t.latitude BETWEEN :minLat AND :maxLat " +
         "AND t.longitude BETWEEN :minLng AND :maxLng")
         This is a valid JPQL query because it references the Taxi entity directly and uses its properties (available, 
         latitude, longitude). The parameters are also correctly referenced with :paramName.
         Native SQL Queries:
         
         Native queries are written in SQL and operate on the actual database tables.
         When you set nativeQuery = true, Spring Data JPA expects the query to be a valid SQL statement, meaning it must 
         match the actual column names and tables in the database.
         If the column names or the table names in the SQL query do not match the actual database schema, you'll encounter 
         errors (like the one you saw before).

# Conclusion:
         Since you are working with entity classes and their fields, it is generally more beneficial to use JPQL for querying
         in Spring Data JPA, as it allows for better abstraction and less dependency on the underlying database schema. 
         If you ever need to write a query that doesn’t conform to the JPQL rules or requires specific SQL features, 
         then you would switch to a native query.

