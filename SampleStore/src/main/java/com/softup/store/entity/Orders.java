package com.softup.store.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

	private static final long serialVersionUID = -3672662224925418969L;

	@Id
	@Column(name = "ord_id", nullable = false)
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

	@Column(name = "cancel", nullable = true, columnDefinition = "tinyint(1) default 0")
	private boolean canceled;

	@Column(name = "cause", nullable = true)
	private String cancelCause;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
	private List<CartItem> items = new ArrayList<CartItem>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;

	public Orders() {
	}

	public Orders(Date deliveryDate, List<CartItem> items, User user) {
		this.orderDate = new Date();
		this.deliveryDate = deliveryDate;
		this.items = items;
		this.user = user;
	}

	public Orders(List<CartItem> items, User user) {
		this.orderDate = new Date();
		this.items = items;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + ", user=" + user
				+ "]";
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public String getCancelCause() {
		return cancelCause;
	}

	public void setCancelCause(String cancelCause) {
		this.cancelCause = cancelCause;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cancelCause == null) ? 0 : cancelCause.hashCode());
		result = prime * result + (canceled ? 1231 : 1237);
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (cancelCause == null) {
			if (other.cancelCause != null)
				return false;
		} else if (!cancelCause.equals(other.cancelCause))
			return false;
		if (canceled != other.canceled)
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
		return true;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

}
