package com.dtr.oas.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Objects;

@Entity
@Table(name = "ROLES")
public class Role implements Serializable, GrantedAuthority  {

    private static final long serialVersionUID = 6874667425302308430L;
    static Logger logger = LoggerFactory.getLogger(Role.class);

    // MySQL ::
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition="int(6)")
    // PgSQL ::
//    @Id
//    @SequenceGenerator(name="rolesIdGenerator", sequenceName="roles_id_seq")
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="rolesIdGenerator")
//    @Column(name="ID", columnDefinition="serial")
    private long id;

    @NotNull(message = "{error.roles.role.null}")
    @NotEmpty(message = "{error.roles.role.empty}")
    @Size(max = 50, message = "{error.roles.role.max}")
    @Column(name = "rolename", length = 50)
    private String rolename;
    
    //@OneToMany(cascade = CascadeType.ALL)  
    @OneToMany(fetch = FetchType.EAGER)
    // MySQL ::
    @JoinTable(name = "user_roles",   
        joinColumns        = {@JoinColumn(name = "role_id", referencedColumnName = "id", columnDefinition="int(6)")},  
        inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", columnDefinition="int(6)")}  
    )
    // PGSQL ::
//    @JoinTable(name = "user_roles",   
//    	joinColumns        = {@JoinColumn(name = "role_id", referencedColumnName = "id", columnDefinition="bigint")},  
//    	inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", columnDefinition="bigint")}  
//    		)
    private Set<User> userRoles;
    
    @OneToMany(fetch = FetchType.EAGER)
    // MySQL ::
    @JoinTable(name = "role_permissions",
        joinColumns        = { @JoinColumn(name = "role_id",       referencedColumnName = "id", columnDefinition="int(6)") },
        inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id", columnDefinition="int(6)") }
    )    
    // PgSL ::
//    @JoinTable(name = "role_permissions",
//    	joinColumns        = { @JoinColumn(name = "role_id",       referencedColumnName = "id", columnDefinition="bigint") },
//    	inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id", columnDefinition="bigint") }
//    		)
    private Set<Permission> permissions;

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<User> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<User> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Permission> getPermissions() { 
        return permissions; 
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    

    @Override
    public String getAuthority() {
        return getRolename();
    }

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((rolename == null) ? 0 : rolename.hashCode());
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
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		return true;
	}
    
    
}
