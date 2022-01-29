package com.sample.todolistmybatismysql.domain.model.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // DBの設定に従い値が設定される。（AUTOの場合JPAがそれぞれのDBに最適化された値を自動で設定）
	private long id;

	@NotEmpty
	private String description;

	@Size(min = 1, max = 5)
	private String status;

//	@CreatedDate
	private Timestamp createdDate;

//	@CreatedBy
	private String createdBy;

//	@LastModifiedDate
	private Timestamp lastModifiedDate;

//	@LastModifiedBy
	private String lastModifiedBy;

	private boolean isDeleted;

	public Todo() {

	}

	public Todo(String description, String status, Timestamp createdDate, String createdBy, Timestamp lastModifiedDate,
			String lastModifiedBy, boolean isDeleted) {
		super();
		this.description = description;
		this.status = status;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", status=" + status + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy="
				+ lastModifiedBy + ", isDeleted=" + isDeleted + "]";
	}
}
