package com.ssafy.moamoa.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ssafy.moamoa.domain.dto.SidePjtForm;
import com.ssafy.moamoa.domain.entity.SidePjt;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
			// 프로필로 해야 이미지 정보 가져오기 가능.

			Long senderId = review.getSendUser().getId();
			ReviewForm tempReviewForm = ReviewForm.builder()
				.id(review.getId())
				.profileId(profileId)
				.senderId(senderId)
				.name(profileRepository.getProfileByUserId(senderId).getNickname())
				.context(review.getContext())
				.time(ts.parseCurrentTime(review.getTime()))
				.build();

			reviewFormList.add(tempReviewForm);

		}
		return reviewFormList;
	}

 	// ReviewForm 에는 String 저장, DB에는 LDT 로 저장  -> DB에서 읽어올 때 parse 후 보내자.
	public ReviewForm addReview(Long profileId, ReviewForm reviewForm) {
		LocalDateTime curTime =ts.getCurrentTime();
		String parsedTime = ts.parseCurrentTime(curTime);


		Profile receiver = profileRepository.getProfileById(profileId);

		Profile sender = profileRepository.getProfileById(reviewForm.getSenderId());



		Review review = Review.builder(
			).context(reviewForm.getContext())
				.time(curTime)
			.receiveProfile(receiver)
			.sendUser(sender).build();

		Long reviewId = reviewRepository.save(review).getId();

		// Return

		ReviewForm result = ReviewForm.builder(

			).id(reviewId)
				.profileId(profileId)
			.senderId(reviewForm.getSenderId())
			.name(sender.getNickname())
			.img(review.getSendUser().getImg())
			.time(parsedTime)
			.context(reviewForm.getContext()).build();

		return result;

	}

	public ReviewForm modifyReview(Long profileId, ReviewForm reviewForm) {


		Profile receiver = profileRepository.getProfileById(profileId);

		Profile sender = profileRepository.getProfileById(reviewForm.getSenderId());

		Review review = reviewRepository.getReviewById(reviewForm.getId());

		review.setContext(reviewForm.getContext());

		Review resultReview = reviewRepository.save(review);

		// Return

		ReviewForm result = ReviewForm.builder(

				).id(resultReview.getId())
				.profileId(resultReview.getReceiveProfile().getId())
				.senderId(sender.getId())
				.name(sender.getNickname())
				.img(sender.getImg())
				.time(ts.parseCurrentTime(resultReview.getTime()))
				.context(resultReview.getContext()).build();

		return result;

	}

	public List<ReviewForm> deleteReview(Long profileId, ReviewForm reviewForm)
	{
		Long deleteCount = reviewRepository.deleteReviewById(reviewForm.getId());

		// Return

		List<Review> reviewList = reviewRepository.getReviewsByOrderAsc(profileId);
		List<ReviewForm> returnList = new ArrayList<>();
		for(Review review : reviewList)
		{
			ReviewForm tempReviewForm = ReviewForm.builder()
					.id(review.getId())
					.profileId(review.getReceiveProfile().getId())
					.senderId(review.getSendUser().getId())
					.name(review.getSendUser().getNickname())
					.time(ts.parseCurrentTime(review.getTime()))
					.img(review.getSendUser().getImg())
					.context(review.getContext())
					.build();

			returnList.add(tempReviewForm);
		}

		return returnList;
	}

	public String getUserImg(Long senderId) {
		Optional<Profile> profile = profileRepository.findById(senderId);

		return profile.get().getImg();
	}

}

