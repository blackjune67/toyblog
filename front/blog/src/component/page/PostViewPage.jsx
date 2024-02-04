import styled from "styled-components";
import {Button} from "../ui/Button";
import {CommentList} from "../list/CommentList";
import {TextInput} from "../ui/TextInput";
import {useNavigate, useParams} from "react-router-dom";
import data from "../../data.json";
import {useState} from "react";

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`

const Container = styled.div`
    width: 100%;
    max-width: 720px;

    :not(:last-child) {
        margin-bottom: 16px;
    }
`

const PostContainer = styled.div`
    padding: 8px 16px;
    border: 1px solid grey;
    border-radius: 8px;
`

const TitleText = styled.p`
    font-size: 28px;
    font-weight: 500;
`

const ContentText = styled.p`
    font-size: 20px;
    line-height: 32px;
    white-space: pre-wrap;
`

const CommentLabel = styled.p`
    font-size: 16px;
    font-weight: 500;
`
export function PostViewPage() {
  const navigate = useNavigate();
  const {postId} = useParams();
  const [comment, setComment] = useState('');

  // console.log('==> data: ' + typeof postId)

  // * 일치비교 시 타입까지 비교하기 때문에 주의
  const post = data.find((item) => {
    // console.log('==> typeof ' + typeof item.id)
    // ! item.id는 number , postId는 string
    return String(item.id) === String(postId);
  })

  return (
      <Wrapper>
        <Container>
          <Button
              title={'뒤로가기'}
              onClick={() => {
                navigate('/');
              }}
          />
          <PostContainer>
            <TitleText>{post.title}</TitleText>
            <ContentText>{post.content}</ContentText>
          </PostContainer>

          <CommentLabel>댓글</CommentLabel>
          <CommentList comments={post.comments} />

          <TextInput
              height={40}
              value={comment}
              onChange={(event) => {
                setComment(event.target.value);
              }}
          />
          <Button
              title='댓글 작성하기'
              onClick={() => {
                navigate('/');
              }}
          />
        </Container>
      </Wrapper>
  );
}