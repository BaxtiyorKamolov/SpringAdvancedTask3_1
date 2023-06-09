package com.example.springadvancedtask2_2.service;

import com.example.springadvancedtask2_2.entity.ProductTable;
import com.example.springadvancedtask2_2.entity.Review;
import com.example.springadvancedtask2_2.entity.UserTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.ReviewDto;
import com.example.springadvancedtask2_2.repository.ReviewRepository;
//import org.hibernate.metamodel.internal.AbstractPojoInstantiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserTableService userTableService;

    @Autowired
    ProductTableService productTableService;

    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Integer id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        return optionalReview.orElse(null);
    }

    public ApiResponse addReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());

        List<UserTable> userTableAllById = userTableService.getUserTableAllById(reviewDto.getUserTables());
        review.setUserTables(userTableAllById);

        List<ProductTable> productTableAllById = productTableService.getProductTableAllById(
                reviewDto.getProductTables());
        review.setProductTables(productTableAllById);

        reviewRepository.save(review);
        return new ApiResponse("Qoshilidi", true);
    }

    public ApiResponse editReview(Integer id, ReviewDto reviewDto) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isEmpty()) {
            return new ApiResponse("Mavjud emas", false);
        }
        Review review = optionalReview.get();
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());

        List<UserTable> userTableAllById = userTableService.getUserTableAllById(reviewDto.getUserTables());
        review.setUserTables(userTableAllById);

        List<ProductTable> productTableAllById = productTableService.getProductTableAllById(
                reviewDto.getProductTables());
        review.setProductTables(productTableAllById);

        reviewRepository.save(review);
        return new ApiResponse("Tahrirlandi", true);
    }

    public ApiResponse deleteReview(Integer id) {
        try {
            reviewRepository.deleteById(id);
            return new ApiResponse("Ochirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Mavjud emas", false);
        }
    }
}
