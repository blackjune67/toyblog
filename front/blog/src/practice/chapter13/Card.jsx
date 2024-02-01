export function Card({title, backgroundColor, children}) {
  // const {title, backgroundColor, children} = props;

  // * children 을 사용한 부분이 Containment
  // * Specialization => title, backgroundColor
  return (
      <div style={{
        margin: 8,
        padding: 8,
        borderRadius: 8,
        boxShadow: "",
        backgroundColor: backgroundColor || "white",
      }}>
        {title && <h1>{title}</h1>}
        {children}
      </div>
  )
}