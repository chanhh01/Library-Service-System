package Model;


import lombok.Data;

@Data
public class Book{
	private String ISBN;
	private String title;
	private String category;
	private String author;
	private String publisher;
	
}
//@Data will generate everthing related to the class such as constructor, @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode