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
@Table(name = "category", schema = "ecommerce")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Title must not be null, empty or blank")
    @Size(min = 3,max = 50,message = "Title must not be less than 3 and greater than 50 characters.")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Gender must not be null, empty or blank")
    @Size(min = 1,max = 10,message = "Gender must not be less than 3 and greater than 10 characters.")
    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "Code must not be null, empty or blank")
    @Size(min = 1,max = 50,message = "Code must not be less than 3 and greater than 50 characters.")
    @Column(name = "code")
    private String code;

    @Min(value = 0,message = "Rating must not be null less than 0")
    @Column(name = "rating")
    private Double rating;

    @NotBlank(message = "Img must not be null, empty or blank")
    @Size(min = 3,max = 200,message = "Img must not be less than 3 and greater than 200 characters.")
    @Column(name = "img")
    private String img;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "category")
    private List<Product> products;

}
