const students = [
  {
    id: 1,
    name: "하준",
  },
  {
    id: 2,
    name: "삐약",
  },
  {
    id: 3,
    name: "득구",
  },
  {
    id: 4,
    name: "포포",
  },
  {
    id: 5,
    name: "주빈",
  },
]

export function AttendanceBook() {
  return (
      <ul>
        {students.map((student, index) => {
          return <li key={index}>{student.name}</li>
        })
        }
      </ul>
  );
}