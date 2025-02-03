## 리엑트 && SpringBoot
#[frontend 보기](https://github.com/KIMJUNGRYUN/frontend/tree/main)

- JPA, Lombokm, Spring Web, JDBC, MySQL Driver

 
- JPA란?
  - JPA는 Java Persistence API의 약자입니다. Persistence라는 단어는 Java DTO(Data Transfer Object)에게 '없어지지 않고 오랫동안 지속'되는 '영속성(persistence)'을 부여해준다는 의미입니다. 즉, 데이터를 DB상에 영구적으로 저장해주는 API라고 생각하면 됩니다. 기존 JDBC만을 이용하는 경우 반복적이며 비슷한 SQL문을 많이 만들어야하고, 데이터베이스의 테이블과 자바 객체간의 매핑하는 소모적인 작업을 지속해야 합니다. JPA를 사용하게 되면 SQL문을 개발자가 만들지 않기 때문에 객체 중심적인 개발로 생산성과 유지보수의 능률이 크게 오르고, 특정 DBMS 문법에 종속적이지 않은 개발이 가능합니다. 또한 JPA의 영속성 컨텍스트는 효율적인 SQL처리에 크게 기여하여 성능상의 이점도 취할 수 있습니다.

​- Entity
  - Entity란 DB에서 영속적으로 저장된 데이터를 자바 객체로 매핑하여 '인스턴스의 형태'로 존재하는 데이터를 말합니다. ( 테이블과 매핑됨 )


```spring
@Entity
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String name;
	private String email;

}
```

# UserRepository, 어플리케이션.propertires, 부트 실행

```spring
public interface UserRepository extends JpaRepository<User, Long> {

}
​
```

- 워크벤치에서 crud 스키마 생성
  - 워크벤치에 보면 테이블이 자동 생성됨.spring.jpa.hibernate.ddl-auto=update 는 엔티티와 매핑된 테이블을 자동생성및 수정한다. 개발시에만 update고 실제 서비스할때는 none 으로 바꿔야 한다.

<hr>

# Controller - 새 유저(post)

- @Controller 와 @RestController 차이

![controller](https://github.com/user-attachments/assets/0ca88ab3-30a5-4b62-8000-0a38bef87c4e)

![RestApi](https://github.com/user-attachments/assets/db21b696-e4c2-4cca-8a0c-cc0d31886cd6)

```spring
@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
}
```

- 스프링 프레임워크에서 비동기 통신, 즉 API 통신을 구현하기 위해 @RequestBody와 @ResponseBody 어노테이션을 사용.
  - 클라이언트 -> 서버 요청 : @RequestBody
  - 서버 -> 클라이언트 응답 : @ResponseBody
  - @RequestBody
     - json 기반의 HTTP Body를 자바 객체로 변환
   
  <hr>

  # 포스트맨 - 새 유저

![new user](https://github.com/user-attachments/assets/9bac739a-6627-4deb-aa6f-5ade0539512b)

- DB에 입력되었는지 확인

![sql](https://github.com/user-attachments/assets/edd1860f-cdcc-4522-84db-e8f7582421b4)

# 모든 유저 (get)

**Rest**
  -  URI는 정보의 자원을 표현해야 한다.
  -  자원에 대한 행위는 HTTP 메서드(GET, POST, PUT, DELETE)로 표현해야 한다.
  
![Rest](https://github.com/user-attachments/assets/16355caa-b1cb-4531-9641-4f474ac69fe2)

- 컨트롤러

```spring
	@GetMapping("/users")
	List<User> getAllUsers(){
		return userRepository.findAll();
	}
​
```

- 포스트맨 모든유저

![alluser](https://github.com/user-attachments/assets/387a41a4-31bd-4894-b3b5-8f58f9bbcd23)

<hr>

# @CrossOrigin 설정

- @CrossOrigin("*") 을 하게 되면 모든 요청주소에 대해 Cross-origin을 허가하고
- @CrossOrigin("http://localhost:3173") 현재 리액트 서버에 대해 허가함

```spring
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
​
```
