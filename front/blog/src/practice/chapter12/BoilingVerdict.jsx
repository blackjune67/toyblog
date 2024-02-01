export function BoilingVerdict(props) {

  if (props.celsius >= 100) {
    return (
        <p>물이 끓습니다.</p>
    )
  } else {
    return <p>물이 끓지않습니다.</p>
  }
}