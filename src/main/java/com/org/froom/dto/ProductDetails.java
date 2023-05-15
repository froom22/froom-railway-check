//package com.org.froom.dto;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//
//@Data
//@JsonIgnoreProperties
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class ProductDetails implements Serializable {
//    @JsonProperty("key")
//    private Integer key;
//
//    @JsonProperty("productAdded")
//    private String productAdded;
//    @JsonProperty("productCategory")
//    private String productCategory;
//    @JsonProperty("productDescription")
//    private String productDescription;
//    @JsonProperty("productId")
//    private Integer productId;
//    @JsonProperty("productImageUrl")
//    private String productImageUrl;
//    @JsonProperty("productName")
//    private String productName;
//    @JsonProperty("productPrice")
//    private Float productPrice;
//    @JsonProperty("productQuantity")
//    private Integer productQuantity;
//    @JsonProperty("productQuatity")
//    private Integer productQuatity;
//    @JsonProperty("uri")
//    private String uri;
//    @JsonProperty("$key")
//    private String $key;
//
//    public ProductDetails() {
//    }
//
//    public ProductDetails(Integer key, String productAdded, String productCategory, String productDescription, Integer productId, String productImageUrl, String productName, Float productPrice, Integer productQuantity, Integer productQuatity, String uri, String $key) {
//        this.key = key;
//        this.productAdded = productAdded;
//        this.productCategory = productCategory;
//        this.productDescription = productDescription;
//        this.productId = productId;
//        this.productImageUrl = productImageUrl;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.productQuantity = productQuantity;
//        this.productQuatity = productQuatity;
//        this.uri = uri;
//        this.$key = $key;
//    }
//
//    @Override
//    public String toString() {
//        return "ProductDetails{" +
//                "key=" + key +
//                ", productAdded='" + productAdded + '\'' +
//                ", productCategory='" + productCategory + '\'' +
//                ", productDescription='" + productDescription + '\'' +
//                ", productId=" + productId +
//                ", productImageUrl='" + productImageUrl + '\'' +
//                ", productName='" + productName + '\'' +
//                ", productPrice=" + productPrice +
//                ", productQuantity=" + productQuantity +
//                ", productQuatity=" + productQuatity +
//                ", uri='" + uri + '\'' +
//                ", $key='" + $key + '\'' +
//                '}';
//    }
//}
