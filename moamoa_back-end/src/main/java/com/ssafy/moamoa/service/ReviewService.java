package com.ssafy.moamoa.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.dto.ReviewForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.Review;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ReviewRepository;
import com.ssafy.moamoa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ReviewService {

	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	private final ProfileRepository profileRepository;

	private final UserRepository userRepository;

	private final ReviewRepository reviewRepository;

	TimeService ts = TimeService.getInstance();

	public List<ReviewForm> getReviews(Long profileId)
	{
		List<Review> reviewList= reviewRepository.getReviewsByOrderAsc(profileId);
		List<ReviewForm> reviewFormList = new ArrayList<>();

		for(Review review : reviewList)
		{
			ReviewForm tempReviewForm = ReviewForm.builder()
				.id(review.getId())
					.profileId(profileId)
				.senderId(review.getSendUser().getId())
				.context(review.getContext())
				.time(review.getTime()+"")
				.build();

			reviewFormList.add(tempReviewForm);

		}
		return reviewFormList;
	}


	public ReviewForm addReview(Long profileId, Long senderId, String context) {
		Timestamp curTime = Timestamp.valueOf(ts.getCurrentTime());

		Optional<Profile> receiver = profileRepository.findById(profileId);
		Optional<User> sender = userRepository.findById(senderId);

		Review review = Review.builder(
			).context(context)
			// LocalDateTime 넣기
			//.time(t)
			.receiveProfile(receiver.get())
			.sendUser(sender.get()).build();

		reviewRepository.save(review);
		ReviewForm reviewForm = ReviewForm.builder(

			).profileId(profileId)
			.senderId(senderId)
			.time(curTime.toString())

			.context(context).build();

		return reviewForm;
	}

	public String getUserImg(Long senderId) {
		Optional<Profile> profile = profileRepository.findById(senderId);

		return profile.get().getImg();
	}

}

