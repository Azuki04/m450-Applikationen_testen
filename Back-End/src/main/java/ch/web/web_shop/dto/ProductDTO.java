package ch.web.web_shop.dto;

import ch.web.web_shop.model.Category;
import ch.web.web_shop.model.User;


public class ProductDTO {
    private String title;
    private String description;
    private String content;
    private int price;
    private int stock;
    private boolean published;
    private Category category;
    private User user;

    public ProductDTO() {
        // Default constructor
    }

    public static class Builder {
        private String title;
        private String description;
        private String content;
        private int price;
        private int stock;
        private boolean published;
        private Category category;
        private User user;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder withStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder withPublished(boolean published) {
            this.published = published;
            return this;
        }

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public ProductDTO build() {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setTitle(title);
            productDTO.setDescription(description);
            productDTO.setContent(content);
            productDTO.setPrice(price);
            productDTO.setStock(stock);
            productDTO.setPublished(published);
            productDTO.setCategory(category);
            productDTO.setUser(user);
            return productDTO;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
