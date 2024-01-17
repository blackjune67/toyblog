const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
  deleteButton.addEventListener('click', event => {
    let id = document.getElementById('article-id').value;

    function success() {
      alert('삭제가 완료되었습니다.');
      location.replace("/articles")
    }

    function fail() {
      alert('삭제를 실패했습니다.');
      location.replace("/articles")
    }

    httpRequest("DELETE", "/api/articles/", +id, null, success, fail);
  });
}

const modifyBtn = document.getElementById('modify-btn');
if (modifyBtn) {
  modifyBtn.addEventListener('click', event => {
    let params = new URLSearchParams(location.search);
    let id = params.get('id');
    let title = document.getElementById('title').value;
    let content = document.getElementById('content').value;

    const body = JSON.stringify({
      title: title,
      content: content
    });

    function success() {
      alert('수정 완료되었습니다.');
      location.replace("/articles/" + id);
    }

    function fail() {
      alert('수정을 실패했습니다.');
      location.replace("/articles/" + id);
    }

    httpRequest("PUT", "/api/articles/" + id, body, null, success, fail);
  });
}

const createBtn = document.getElementById('create-btn');
if (createBtn) {
  createBtn.addEventListener('click', event => {
    let title = document.getElementById('title').value;
    let content = document.getElementById('content').value;

    const body = JSON.stringify({
      title: document.getElementById('title').value,
      content: document.getElementById('content').value,
      // createAt: document.getElementById('createAt').value,
    });

    console.log('==> body :', body);

    function success() {
      alert('등록 완료');
      location.replace("/articles")
    }

    function fail() {
      alert('등록 실패');
      location.replace("/articles")
    }

    httpRequest("POST", "/api/articles", body, success, fail);
  });
}

function getCookie(key) {
  let result = null;
  const cookie = document.cookie.split(";");
  cookie.some(function (item) {
    item = item.replace(" ", "");
    let dic = item.split("=");
    if (key === dic[0]) {
      result = dic[1];
      return true;
    }
  });
  return result;
}

function httpRequest(method, url, body, success, fail) {
  fetch(url, {
    method: method,
    headers: {
      Authorization: "Bearer" + localStorage.getItem("access_token"),
      'Content-Type': 'application/json',
    },
    body: body,
  }).then((response) => {
    if (response.status === 200 || response.status === 201) {
      return success();
    }
    const refresh_token = getCookie("refresh_token");
    if (response.status === 401 && refresh_token) {
      fetch("/api/token", {
        method: "POST",
        headers: {
          Authorization: "Bearer" + localStorage.getItem("access_token"),
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          refreshToken: getCookie("refresh_token"),
        }),
      }).then((res) => {
        if (res.ok) {
          return res.json();
        }
      }).then((result) => {
        localStorage.setItem("access_token", result.accessToken);
        httpRequest(method, url, body, success, fail);
      }).catch((error) => {
        alert(error);
        // fail();
      });
    } else {
      alert('알 수 없는 실패');
      return fail();
    }
  });
}