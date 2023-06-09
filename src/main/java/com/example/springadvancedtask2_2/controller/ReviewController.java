package com.example.springadvancedtask2_2.controller;

import com.example.springadvancedtask2_2.entity.Review;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.ReviewDto;
import com.example.springadvancedtask2_2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/api/review")
    public ResponseEntity<List<Review>> getAllReview() {
        List<Review> allReview = reviewService.getAllReview();
        return ResponseEntity.ok(allReview);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/api/review/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer id) {
        Review reviewById = reviewService.getReviewById(id);
        return ResponseEntity.ok(reviewById);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("/api/reviw")
    public ResponseEntity<ApiResponse> addReview(@RequestBody ReviewDto reviewDto) {
        ApiResponse apiResponse = reviewService.addReview(reviewDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/api/review/{id}")
    public ResponseEntity<ApiResponse> editReview(@PathVariable Integer id, @RequestBody ReviewDto reviewDto) {
        ApiResponse apiResponse = reviewService.editReview(id, reviewDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/api/review/{id}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Integer id) {
        ApiResponse apiResponse = reviewService.deleteReview(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
