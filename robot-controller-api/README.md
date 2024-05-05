# Robot Controller API

This is the REST API that handles the movement logic of the Robot.
The API accepts a movement script and executes it to determine the new position of the Robot

### Sample Request

```
curl --location 'localhost:8080/api/v1/position' \
--header 'Content-Type: application/json' \
--data '{
    "script":"POSITION 1 3 EAST\nFORWARD 3\n \nWAIT\nTURNAROUND\nFORWARD 1\nRIGHT\nFORWARD 2"
}'
```

### Sample Response

```
{
    "x": 3,
    "y": 1,
    "direction": "NORTH"
}
```

### How to build the project

- Make sure you have Java 17
- From project folder (robot-controller-api) run `./mvnw clean package`
- Then run `java -jar target/robot-controller-api-0.0.1-SNAPSHOT.jar`

## Areas to improve
### Current status
All the basic requirements mentioned in the Coding Challenge is completed however given more time there can be 
more improvements done

### Improvements

- Write more unit, component & integration tests
- Add API documentation (ex: Swagger)
- More input validations and better error handling
- Refactor some of the functions to make the code clean and readable