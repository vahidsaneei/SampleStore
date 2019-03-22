package com.softup.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "saleitems", uniqueConstraints = {})
public class CartItem implements Serializable {

	private static final long serialVersionUID = 7968604053015663078L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Product product;

	@Column(name = "quantity", nullable = false, columnDefinition = "int(11) default 1")
	private Integer quantity;

	@Column(name = "totalprice", nullable = false)
	private BigDecimal totalprice;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "orderid", nullable = false)
	private Orders order;

	public CartItem(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
		setTotalprice();
	}

	public CartItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice() {
		this.totalprice = getProduct().getPrice().multiply(new BigDecimal(getQuantity()));
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

}
