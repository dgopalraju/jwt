package com.securityjwt.model;

import javax.persistence.*;

@Entity
@Table(name = "roles_up")
public class Role {
	@Id
	@SequenceGenerator(name = "sequen_role", sequenceName = "sequen_role", allocationSize = 1)
	@GeneratedValue(generator = "sequen_role", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}