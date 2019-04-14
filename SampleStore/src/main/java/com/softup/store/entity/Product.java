package com.softup.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = -7738539408628995177L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prd_id")
	private Long id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "seller_name")
	private String seller;

	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "created_date")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date createdDate;

	@Column(name = "expiry_date")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date expiryDate;

	@Column(name = "insert_date")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date insertDate;

	@Column(name = "likes", nullable = true)
	private Integer likeCount;

	@Column(name = "quantity", nullable = true)
	private Integer quantity;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "category", nullable = false)
	private String category;

	@Column(name = "description", nullable = true)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private Set<CartItem> items;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private List<Comment> comments = new ArrayList<Comment>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private List<Likes> likes = new ArrayList<Likes>();

	public Product(String fullName, String companyName, Date expiryDate, Integer quantity, BigDecimal price,
			String description) {
		super();
		this.fullName = fullName;
		this.companyName = companyName;
		this.expiryDate = expiryDate;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
	}

	public Product(String fullName, String companyName, Integer quantity, Date expiryDate, BigDecimal price,
			String category) {
		super();
		this.fullName = fullName;
		this.companyName = companyName;
		this.quantity = quantity;
		this.expiryDate = expiryDate;
		this.price = price;
	}

	public Product(Long id, String fullName, String seller, String companyName, Integer quantity, String category) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.seller = seller;
		this.companyName = companyName;
		this.quantity = quantity;
	}

	public Product() {
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", fullName=" + fullName + ", seller=" + seller + ", companyName=" + companyName
				+ ", createdDate=" + createdDate + ", expiryDate=" + expiryDate + ", likeCount=" + likeCount
				+ ", quantity=" + quantity + ", price=" + price + ", category=" + category + ", description="
				+ description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((insertDate == null) ? 0 : insertDate.hashCode());
		result = prime * result + ((likeCount == null) ? 0 : likeCount.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
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
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (insertDate == null) {
			if (other.insertDate != null)
				return false;
		} else if (!insertDate.equals(other.insertDate))
			return false;
		if (likeCount == null) {
			if (other.likeCount != null)
				return false;
		} else if (!likeCount.equals(other.likeCount))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		return true;
	}

	public Set<CartItem> getItems() {
		return items;
	}

	public void setItems(Set<CartItem> items) {
		this.items = items;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}
}
