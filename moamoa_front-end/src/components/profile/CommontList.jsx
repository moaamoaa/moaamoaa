import PropTypes from 'prop-types';
import Comment from 'components/profile/Comment';

function CommentList(props) {
  const comments = props.comments;
  return (
    <>
      {comments.map((comment, idx) => {
        <Comment key={idx} comment={comment}></Comment>;
      })}
    </>
  );
}

CommentList.propTypes = {
  comments: PropTypes.array.isRequired,
};

export default CommentList;
