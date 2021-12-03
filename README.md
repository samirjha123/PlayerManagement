# Java coding test

A Java coding skill test.

# How to build
## Database Preparation:
  * Change Database properties in {project}/src/resources/application.properties file,
  * Execute {project}/sql/ddl.sql file
## Windows/Linux/Mac:
  * mvn spring-boot:run

# I/Fs specification:
## 1. {{end-point}}/player/register
## Request
``` 
curl --location --request POST 'localhost:8084/player/register' \
--header 'scorer: hggh' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=AC54ACC006FB760C4C83A09B20E4AFC8' \
--data-raw '{
    "player":"vvv",
    "score": 890,
    "time": "2021-08-17T11:43:39.198+00:00"
}'
```

## Response
```
{
    "id": 11,
    "player": "vvv",
    "score": 890,
    "time": "2021-08-17T11:43:39.198+00:00"
}
```
## 2. {{end-point}}/player/get
## Request
``` 
curl --location --request GET 'localhost:8084/player/get?id=11' \
--header 'Cookie: JSESSIONID=AC54ACC006FB760C4C83A09B20E4AFC8'
```

## Response
```
{
    "id": 11,
    "player": "vvv",
    "score": 890,
    "time": "2021-08-17 20:43:39"
}
```
## 3. {{end-point}}/player/delete
## Request
``` 
curl --location --request DELETE 'localhost:8084/player/delete/6' \
--header 'scorer: hggh' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=AC54ACC006FB760C4C83A09B20E4AFC8'
```

## Response
```

```
## 4. {{end-point}}/player/getHistory
## Request
``` 
curl --location --request GET 'localhost:8084/player/getHistory?player=gghghg' \
--header 'Cookie: JSESSIONID=AC54ACC006FB760C4C83A09B20E4AFC8' 
```

## Response
```
{
    "lowScore": 2,
    "highScore": 90,
    "averageScore": 37.0,
    "scores": [
        {
            "id": 7,
            "player": "gghghg",
            "score": 90,
            "time": "2021-08-16 20:43:39"
        },
        {
            "id": 8,
            "player": "gghghg",
            "score": 2,
            "time": "2021-08-16 20:43:39"
        },
        {
            "id": 9,
            "player": "gghghg",
            "score": 24,
            "time": "2021-08-16 20:43:39"
        },
        {
            "id": 10,
            "player": "gghghg",
            "score": 35,
            "time": "2021-08-16 20:43:39"
        }
    ]
}
```
## 5. {{end-point}}/player/list
## Request
``` 
curl --location --request GET 'localhost:8084/player/list?players=vvV,gghghg&time=2021-08-19 20:43:39&after=false' \
--header 'Cookie: JSESSIONID=AC54ACC006FB760C4C83A09B20E4AFC8'
```

## Response
```
{
    "content": [
        {
            "id": 8,
            "player": "gghghg",
            "score": 2,
            "time": "2021-08-16 20:43:39"
        },
        {
            "id": 9,
            "player": "gghghg",
            "score": 24,
            "time": "2021-08-16 20:43:39"
        },
        {
            "id": 10,
            "player": "gghghg",
            "score": 35,
            "time": "2021-08-16 20:43:39"
        },
        {
            "id": 11,
            "player": "vvv",
            "score": 890,
            "time": "2021-08-17 20:43:39"
        },
        {
            "id": 12,
            "player": "vvV",
            "score": 890,
            "time": "2021-08-17 20:43:39"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageNumber": 0,
        "pageSize": 20,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 5,
    "numberOfElements": 5,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "first": true,
    "number": 0,
    "size": 20,
    "empty": false
}
```
