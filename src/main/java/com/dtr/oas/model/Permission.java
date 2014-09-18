package com.dtr.oas.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "PERMISSIONS")
public class Permission implements GrantedAuthority {

    private static final long serialVersionUID = -5404269148967698143L;
    static Logger logger = LoggerFactory.getLogger(Permission.class);
    
    // MySQL ::
	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition="int(6)")
	// PgSQL ::
//    @Id
//	@SequenceGenerator(name="permissionsIdGenerator", sequenceName="permissions_id_seq")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="permissionsIdGenerator")
//	@Column(name="ID", columnDefinition="serial")
    private long id;

    @NotNull(message = "{error.permission.permissionname.null}")
    @NotEmpty(message = "{error.permission.permissionname.empty}")
    @Size(max = 50, message = "{permission.permissionname.role.max}")
    @Column(name = "permissionname", length = 50)
    private String permissionname;

    @OneToMany(fetch = FetchType.EAGER)  
    // MySQL ::
    @JoinTable(name = "role_permissions",   
        joinColumns        = {@JoinColumn(name = "permission_id", referencedColumnName = "id", columnDefinition="int(6)")},  
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", columnDefinition="int(6)")}  
    ) 
    // PgSQL ::
//	@JoinTable(name = "role_permissions",   
//	joinColumns        = {@JoinColumn(name = "permission_id", referencedColumnName = "id", columnDefinition="bigint")},  
//	inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", columnDefinition="bigint")}  
//			) 
    private Set<Role> permRoles;

    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    @Override
    public String getAuthority() {
        return permissionname;
    }

    public Set<Role> getPermRoles() {
        return permRoles;
    }

    public void setPermRoles(Set<Role> permRoles) {
        this.permRoles = permRoles;
    }

	@Override
	public String toString() {
		return "Permission [id=" + id + ", permissionname=" + permissionname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((permissionname == null) ? 0 : permissionname.hashCode());
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
		Permission other = (Permission) obj;
		if (id != other.id)
			return false;
		if (permissionname == null) {
			if (other.permissionname != null)
				return false;
		} else if (!permissionname.equals(other.permissionname))
			return false;
		return true;
	}

    
}
