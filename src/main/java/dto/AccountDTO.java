package dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AccountDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty
	@Size(max=20)
	@Size(min=5)
	private String name;
	@NotEmpty
	@Size(max=20)
	@Size(min=8)
	private String password;
	private String role;
}
