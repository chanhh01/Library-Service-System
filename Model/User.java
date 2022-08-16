package Model;

import lombok.Data;

@Data 
public class User{
	private String ID;
	private String name;
	private String gender;
	private String role;
	private String DOB;
	private String email;
	private String phone;
	private String password;
}
//@Data will generate everthing related to the class such as constructor, @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode