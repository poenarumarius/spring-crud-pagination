package com.dtr.oas.model;

import java.io.Serializable;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "STRATEGY")
public class Strategy implements Serializable {

	private static final long serialVersionUID = 96285180113476324L;
	static Logger logger = LoggerFactory.getLogger(Strategy.class);


	// MySQL ::
	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition="int(6)")
	// Pg:SQL ::
//	@Id
//	@SequenceGenerator(name="strategyIdGenerator", sequenceName="strategy_id_seq")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="strategyIdGenerator")
//	@Column(name="ID", columnDefinition="serial")
	private long id;

	@NotNull(message = "{error.strategy.type.null}")
	@NotEmpty(message = "{error.strategy.type.empty}")
	@Size(max = 50, message = "{error.strategy.type.max}")
	@Column(name = "TYPE", length = 50)
	private String type;

	@NotNull(message = "{error.strategy.name.null}")
	@NotEmpty(message = "{error.strategy.name.empty}")
	@Size(max = 50, message = "{error.strategy.name.max}")
	@Column(name = "NAME", length = 50)
	private String name;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Strategy [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Strategy other = (Strategy) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



}
