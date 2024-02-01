import {Comment} from "./Comment";

const comments = [
  {
    name: "최하준",
    comment: "제가 만든 첫 컴포넌트입니다."
  },
  {
    name: "하이",
    comment: "안녕하세여?"
  },
  {
    name: "메렁",
    comment: "ㅋㅋㅋㅋ"
  },{
    name: "바보",
    comment: "ㅋㅋㅋㅋㅋㅋ"
  },
]

export const CommentList = (props) => {
  return (
      <div>
        {comments.map((comment) => (
            <Comment name={comment.name} comment={comment.comment}/>
        ))}
        {/*<Comment name={"최하준"} comment={"제가 만든 첫 컴포넌트입니다."}/>*/}
      </div>
  )
}