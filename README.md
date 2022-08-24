### spring-boot-microservices-level2
> main commit conatins video from 1 to 17

| Software     | Version      |
| ------       | ------       |
| Java         |  "1.8.0_161" |
| Apache Maven |  "3.8.5"     |
| Spring Boot  |  "2.7.2"     |

> Note: `JAVA_HOME` required to run `mvn package`
> `mvn package` or `mvn install` for generate jar file in target directory.

```
Run in cmd not in powershell:
mvn package
or mvn install
it will generate jar file in target dir
java -Dserver.port=8086 -jar movie-catalog-service-0.0.1-SNAPSHOT.jar
java -Dserver.port=8086 -jar movie-info-service-0.0.1-SNAPSHOT.jar
```

- MovieCatalogService (8081) :
```sh
http://localhost:8081/catalog/1234
```
```
[
  {
    "name": "100",
    "desc": "Test Desc",
    "rating": 3
  },
  {
    "name": "200",
    "desc": "Test Desc",
    "rating": 4
  }
]
```
```sh
http://localhost:8081/catalog/1234
```
```
If You stop (Movie info service (8082) and call this api then it should be return below reponse:
[
  {
    "name": "No Movie",
    "desc": "",
    "rating": 0
  }
]
```
- MovieInfoService (8082) :
```sh
http://localhost:8082/movies/transformer
```
```
{
  "movieId": "transformer",
  "name": "Test Name",
  "description": "Test Desc"
}
```
- RatingsDataService (8083) :
```sh
http://localhost:8083/ratingsdata/transformer
```
```
{
  "movieId": "transformer",
  "rating": 4
}
```
```
http://localhost:8083/ratingsdata/users/vijay
```
```
{
  "userId": "vijay",
  "ratings": [
    {
      "movieId": "100",
      "rating": 3
    },
    {
      "movieId": "200",
      "rating": 4
    }
  ]
}
```

>Eureka Server will have it's own UI.
```
http://localhost:8761/
```

> Note: in pom.xml you need below changes as Hystix has depricated and no longer available in `start.spring.io` :
```
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.7.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	
	<properties>
		<java.version>1.8</java.version>
		<spring-cloud.version>Hoxton.SR9</spring-cloud.version>
	</properties>

	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
	</dependency>
```
