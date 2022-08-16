package Model;

import lombok.Data;

@Data
public class Cart{
	private String id;
	private String ISBN;
	private String title;
	private String borrowDate;
	private String category;
	private int duration;
	private String dueDate;
	private boolean status;
}
//@Data will generate everthing related to the class such as constructor, @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode