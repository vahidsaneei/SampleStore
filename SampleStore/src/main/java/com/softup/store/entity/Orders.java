package com.softup.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

	private static final long serialVersionUID = -3672662224925418969L;

	@Id
	@Column(name = "orderid", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Column(name = "orderDate", nullable = false)
	private Date orderDate;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Column(name = "delivery", nullable = false)
	private Date deliveryDate;

	@Column(name = "success", nullable = true, columnDefinition = "tinyint(1) default 0")
	private boolean success;

	@Column(name = "finished", nullable = true, columnDefinition = "tinyint(1) default 0")
	private boolean finished;

	@Column(name = "usercancel", nullable = true, columnDefinition = "tinyint(1) default 0")
	private boolean canceledByUser;

	@Column(name = "admincancel", nullable = true, columnDefinition = "tinyint(1) default 0")
	private boolean canceledByAdmin;

	@Column(name = "usercause", nullable = true)
	private String userCancelCause;

	@Column(name = "admincause", nullable = true)
	private String adminCancelCause;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ORDERS_ITEMS", joinColumns = { @JoinColumn(name = "orderid") }, inverseJoinColumns = {
			@JoinColumn(name = "item_id") })
	private Set<CartItem> items = new HashSet<CartItem>(0);

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;
	
	public Orders() {
	}

	public Orders(Date deliveryDate, Set<CartItem> items) {
		this.orderDate = new Date();
		this.deliveryDate = deliveryDate;
		this.items = items;
	}

	public Orders(Set<CartItem> items) {
		this.orderDate = new Date();
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Set<CartItem> getItems() {
		return items;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setItems(Set<CartItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCanceledByUser() {
		return canceledByUser;
	}

	public void setCanceledByUser(boolean canceledByUser) {
		this.canceledByUser = canceledByUser;
	}

	public boolean isCanceledByAdmin() {
		return canceledByAdmin;
	}

	public void setCanceledByAdmin(boolean canceledByAdmin) {
		this.canceledByAdmin = canceledByAdmin;
	}

	public String getUserCancelCause() {
		return userCancelCause;
	}

	public void setUserCancelCause(String userCancelCause) {
		this.userCancelCause = userCancelCause;
	}

	public String getAdminCancelCause() {
		return adminCancelCause;
	}

	public void setAdminCancelCause(String adminCancelCause) {
		this.adminCancelCause = adminCancelCause;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + ", user="
				+ getUser().getUsername() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminCancelCause == null) ? 0 : adminCancelCause.hashCode());
		result = prime * result + (canceledByAdmin ? 1231 : 1237);
		result = prime * result + (canceledByUser ? 1231 : 1237);
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userCancelCause == null) ? 0 : userCancelCause.hashCode());
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
		Orders other = (Orders) obj;
		if (adminCancelCause == null) {
			if (other.adminCancelCause != null)
				return false;
		} else if (!adminCancelCause.equals(other.adminCancelCause))
			return false;
		if (canceledByAdmin != other.canceledByAdmin)
			return false;
		if (canceledByUser != other.canceledByUser)
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (success != other.success)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userCancelCause == null) {
			if (other.userCancelCause != null)
				return false;
		} else if (!userCancelCause.equals(other.userCancelCause))
			return false;
		return true;
	}

}
