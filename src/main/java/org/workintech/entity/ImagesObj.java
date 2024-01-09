package org.workintech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "images", schema = "ecommerce")
public class ImagesObj {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Url must not be null, empty or blank")
    @Size(min = 3,max = 200,message = "Title must not be less than 3 and greater than 200 characters.")
    @Column(name = "url")
    private String url;

    @Min(value = 0,message = "Index must not be null less than 0")
    @Column(name = "index")
    private int index;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;
}
