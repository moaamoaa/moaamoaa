package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.dto.ReviewForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.Review;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ReviewRepository;
import com.ssafy.moamoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<ReviewForm> getReviews(Long profileId) {
        List<Review> reviewList = reviewRepository.getReviewsByOrderAsc(profileId);
        List<ReviewForm> reviewFormList = new ArrayList<>();

        for (Review review : reviewList) {
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

    // 작성자 : profileId , 받는 사람 : reviewForm.getProfileId();
    public List<ReviewForm> addReview(Long profileId, ReviewForm reviewForm) {
        LocalDateTime curTime = ts.getCurrentTime();
        String parsedTime = ts.parseCurrentTime(curTime);

        Long receiveId = reviewForm.getProfileId();


        Profile receiver = profileRepository.getProfileById(receiveId);

        Profile sender = profileRepository.getProfileById(profileId);


        Review review = Review.builder(
                ).context(reviewForm.getContext())
                .time(curTime)
                .receiveProfile(receiver)
                .sendUser(sender).build();

        reviewRepository.save(review).getId();

        // Return List
        List<Review> reviews = reviewRepository.getReviewsByOrderAsc(receiveId);
        ArrayList<ReviewForm> returnList = new ArrayList<>();
        for (Review r : reviews) {
            ReviewForm result = ReviewForm.builder(

                    ).id(r.getId())
                    .profileId(r.getReceiveProfile().getId())
                    .senderId(r.getSendUser().getId())
                    .name(r.getSendUser().getNickname())
                    .img(r.getSendUser().getImg())
                    .time(ts.parseCurrentTime(r.getTime()))
                    .context(r.getContext()).build();

            returnList.add(result);
        }


        return returnList;

    }
    // 작성자 : profileId , 받는 사람 : reviewForm.getProfileId();

    public List<ReviewForm> modifyReview(Long profileId, ReviewForm reviewForm) {


        Review review = reviewRepository.getReviewById(reviewForm.getId());

        if (review.getSendUser().getId() != profileId) {
            return null;
        }


        review.setContext(reviewForm.getContext());

        reviewRepository.save(review);

        // Return

        List<Review> reviews = reviewRepository.getReviewsByOrderAsc(reviewForm.getProfileId());
        ArrayList<ReviewForm> returnList = new ArrayList<>();

        for (Review r : reviews) {
            ReviewForm result = ReviewForm.builder(

                    ).id(r.getId())
                    .profileId(r.getReceiveProfile().getId())
                    .senderId(r.getSendUser().getId())
                    .name(r.getSendUser().getNickname())
                    .img(r.getSendUser().getImg())
                    .time(ts.parseCurrentTime(r.getTime()))
                    .context(r.getContext()).build();

            returnList.add(result);
        }


        return returnList;

    }

    public List<ReviewForm> deleteReview(Long userId, Long reviewId) {
        // 전송한 사람과 작성한 사람 비교 작업 진행.


        Profile senderProfile = profileRepository.getProfileByUserId(userId);

        Profile profile = reviewRepository.getProfileByReviewId(reviewId);

        Review review = reviewRepository.getReviewById(reviewId);

        log.info("Review SendUser : " + review.getSendUser().getNickname() + "     UserId Profile : " + senderProfile.getNickname());

        if (review.getSendUser().getId() != senderProfile.getId()) {
            return null;
        }
        //
        Long deleteCount = reviewRepository.deleteReviewById(reviewId);

        // Return

        List<Review> reviewList = reviewRepository.getReviewsByOrderAsc(profile.getId());
        List<ReviewForm> returnList = new ArrayList<>();
        for (Review returnReview : reviewList) {
            ReviewForm tempReviewForm = ReviewForm.builder()
                    .id(returnReview.getId())
                    .profileId(returnReview.getReceiveProfile().getId())
                    .senderId(returnReview.getSendUser().getId())
                    .name(returnReview.getSendUser().getNickname())
                    .time(ts.parseCurrentTime(returnReview.getTime()))
                    .img(returnReview.getSendUser().getImg())
                    .context(returnReview.getContext())
                    .build();

            returnList.add(tempReviewForm);
        }

        return returnList;
    }

}

