const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
  deleteButton.addEventListener('click', event => {
    let id = document.getElementById('article-id').value;

    fetch(`/api/articles/${id}`, {
      method: 'DELETE'
    }).then(() => {
      alert('삭제가 완료됐습니다.');
      location.replace('/articles');
    });
  });
}

const modifyBtn = document.getElementById('modify-btn');
if (modifyBtn) {
  modifyBtn.addEventListener('click', event => {
    console.log('==> 수정 클릭2');
    let params = new URLSearchParams(location.search);
    let id = params.get('id');
    let content = document.getElementById('content').value;

    fetch(`/api/articles/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        content: content
      })
    }).then(() => {
      alert('수정이 완료됐습니다.');
      location.replace(`/articles/${id}`);
    });
  });
}