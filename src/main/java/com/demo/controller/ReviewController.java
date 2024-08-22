package com.demo.controller;

import com.demo.entity.AppUser;
import com.demo.entity.Property;
import com.demo.entity.Review;
import com.demo.repo.PropertyRepository;
import com.demo.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @PostMapping("/add")
    public ResponseEntity<Review>addReview(@RequestBody Review review,
                                           @RequestParam long propertyId,
                                           @AuthenticationPrincipal AppUser appUser)
    //by supplying JWT token then @AuthenticationPrincipal gives/fetches all userDetails
    {
        Property property = propertyRepository.findById(propertyId).get();
        review.setProperty(property);
        review.setAppUser(appUser);
        return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.CREATED);

    }
}
