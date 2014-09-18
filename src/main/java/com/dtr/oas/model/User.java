package com.dtr.oas.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Objects;

@Entity  
@Table(name="USERS")
public class User implements UserDetails {

	private static final long serialVersionUID = 6311364761937265306L;
	static Logger logger = LoggerFactory.getLogger(User.class);

	// MySQL ::
	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition="int(6)")
	// PgSQL ::
//	@Id
//	@SequenceGenerator(name="usersIdGenerator", sequenceName="users_id_seq")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usersIdGenerator")
//	@Column(name="ID", columnDefinition="serial")
	private long id;

	@NotNull(message = "{error.user.username.null}")
	@NotEmpty(message = "{error.user.username.empty}")
	@Size(max = 50, message = "{error.user.username.max}")
	@Column(name = "username", length = 50)
	private String username;

	@NotNull(message = "{error.user.password.null}")
	@NotEmpty(message = "{error.user.password.empty}")
	@Size(max = 50, message = "{error.user.password.max}")
	@Column(name = "password", length = 50)
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@OneToOne(fetch = FetchType.EAGER)  
	// MySQL ::
	    @JoinTable(name = "user_roles",  
	        joinColumns        = {@JoinColumn(name = "user_id", referencedColumnName = "id", columnDefinition="int(6)")},  
	        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", columnDefinition="int(6)")}  
	    )  
	// PgSQL ::
//	@JoinTable(name = "user_roles",  
//	joinColumns        = {@JoinColumn(name = "user_id", referencedColumnName = "id", columnDefinition="bigint")},  
//	inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", columnDefinition="bigint")}  
//			)
	private Role role;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Transient
	public Set<Permission> getPermissions() {
		Set<Permission> perms = new HashSet<Permission>();
		perms.addAll(role.getPermissions()); 
		return perms;
	}

	@Override
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(getRole());
		authorities.addAll(getPermissions());
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		//return true = account is valid / not expired
				return true; 
	}

	@Override
	public boolean isAccountNonLocked() {
		//return true = account is not locked
				return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//return true = password is valid / not expired
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}



}
