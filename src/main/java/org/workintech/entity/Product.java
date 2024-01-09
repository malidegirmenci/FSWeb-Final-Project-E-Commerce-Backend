package org.workintech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product", schema = "ecommerce")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be null, empty or blank")
    @Size(min = 3,max = 100,message = "Name must not be less than 3 and greater than 100 characters.")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description must not be null, empty or blank")
    @Size(min = 3,max = 200,message = "Description must not be less than 3 and greater than 200 characters.")
    @Column(name ="description")
    private String description;

    @Min(value = 0,message = "Price must not be null less than 0")
    @Column(name = "price")
    private Double price;

    @Min(value = 0,message = "Price must not be null less than 0")
    @Column(name = "rating")
    private Double rating;

    @Min(value = 0,message = "Stock must not be null less than 0")
    @Column(name = "stock")
    private Integer stock;

    @Min(value = 0,message = "Sell count must not be null less than 0")
    @Column(name = "sell_count")
    private Integer sellCount;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<ImagesObj> images;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="store_id")
    private Store store;

}
