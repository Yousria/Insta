package com.example.fileApi;

import com.example.loginAPI.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.*;

/**
 *  @author Nicolas Sirac
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductEntity {
    @Id
    @Column
    @GeneratedValue
    private Long id;


    @Column
    private String title;


    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "users",referencedColumnName = "id")
    private User user;
    @JsonIgnore
    @OneToMany(fetch = LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private List<ImageEntity> imageEntityList;

    @Override
    public boolean equals(Object obj) {
        ProductEntity productEntity = (ProductEntity) obj;
        return productEntity.getId().equals(id)&&
                productEntity.getTitle().equals(title);
    }
}
