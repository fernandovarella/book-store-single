package com.fernando.bookstore.data.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(value = "books")
public class Book extends DefaultEntity {
    
    private static final long serialVersionUID = 7596222660798490812L;

    private String isbn;

    private String title;

    @Field("original_title")
    private String originalTitle;

    @Field("original_publication_year")
    private Integer publicationYear;

    @Field("average_rating")
    private Double averageRating;

    @Field("ratings_count")
    private Integer ratingsCount;

    @Field("image_url")
    private String imgUrl;

    @Field("small_image_url")
    private String imgUrlSmall;

    private List<String> authors;

}
